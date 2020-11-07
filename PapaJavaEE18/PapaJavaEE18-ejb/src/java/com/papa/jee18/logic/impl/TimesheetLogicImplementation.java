/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic.impl;

import com.papa.jee18.dao.ContractAccess;
import com.papa.jee18.dao.EmailAccess;
import com.papa.jee18.dao.EmailPersonAccess;
import com.papa.jee18.dao.HolidayAccess;
import com.papa.jee18.dao.PersonAccess;
import com.papa.jee18.dao.TimesheetAccess;
import com.papa.jee18.dao.TimesheetEntryAccess;
import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.dto.helper.CommonHelper;
import com.papa.jee18.dto.helper.ConvertToDTOHelper;
import com.papa.jee18.dto.helper.ConvertToEntityHelper;
import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.EmailEntity;
import com.papa.jee18.entities.EmailPersonEntity;
import com.papa.jee18.entities.EmailStatus;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.SupervisorEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetStatus;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import com.papa.jee18.logic.TimesheetLogic;
import com.papa.jee18.notification.EmailContentHelper;
import com.papa.jee18.notification.SmtpServerHelper;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.Session;


@Stateless
public class TimesheetLogicImplementation
        implements TimesheetLogic {

    @EJB
    private PersonAccess pa;

    @EJB
    private TimesheetAccess ta;

    @EJB
    private ContractAccess ca;

    @EJB
    private EmailAccess ea;

    @EJB
    private EmailPersonAccess epa;

    @EJB
    private TimesheetEntryAccess tea;

    @EJB
    private HolidayAccess ha;

    @Resource
    private EJBContext ejbContext;

    private PersonEntity caller;

    private Set<RoleEntity> _roles;

    @Resource(lookup = "mail/gmail-mail")
    private Session mailSession;

    public Set<RoleEntity> getRoles() {
        setCaller();
        if (caller != null && caller.getRoles() != null) {
            setRoles(caller.getRoles());
        } else {
            setRoles(new HashSet());
        }
        return _roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this._roles = roles;
    }
    
    public void setCaller() {
        Principal principal = ejbContext.getCallerPrincipal();
        caller = principal == null ? null : pa.getPersonByEmailAddress(principal.getName());
    }

    private ContractEntity getContractEntityByRole(RoleEntity entity) {
        if (entity instanceof SupervisorEntity) {
            SupervisorEntity ee = (SupervisorEntity) entity;
            return ee.getContract();
        } else if (entity instanceof SecretaryEntity) {
            SecretaryEntity ee = (SecretaryEntity) entity;
            return ee.getContract();
        } else if (entity instanceof EmployeeEntity) {
            EmployeeEntity ee = (EmployeeEntity) entity;
            return ee.getContract();
        } else if (entity instanceof AssistantEntity) {
            AssistantEntity ee = (AssistantEntity) entity;
            return ee.getContract();
        }
        return null;
    }

    @Override
    public Timesheet getTimesheet(String uuid) {
        TimesheetEntity entity = ta.getTimesheet(uuid);

        if (entity == null) {
            throw new BusinessException("busExTimesheetNotFound");
        }

        Timesheet timesheetDTO = ConvertToDTOHelper.convertToDTO(entity, true);
        timesheetDTO = CommonHelper.setRoles(timesheetDTO, getRoles());

        return timesheetDTO;
    }

    @Override
    public List<Timesheet> getTimesheetList() {

        Set<TimesheetEntity> timesheetSet = new HashSet<>();

        for (RoleEntity entity : getRoles()) {

            ContractEntity contractEntity = getContractEntityByRole(entity);

            Set<TimesheetEntity> contractTimesheetList = contractEntity.getTimesheets();
            for (TimesheetEntity timesheetEntity : contractTimesheetList) {
                timesheetEntity.setEntries(tea.getTimesheetEntrySet(timesheetEntity.getUuid()));
            }
            timesheetSet.addAll(contractTimesheetList);
        }

        List<Timesheet> timesheetDTOList = ConvertToDTOHelper.convertToTimesheetDTOList(timesheetSet, true);
        timesheetDTOList = CommonHelper.setRoles(timesheetDTOList, getRoles());
        return timesheetDTOList;
    }

    private TimesheetEntity getEntityFromDTO(Timesheet dto) {
        TimesheetEntity entity = ta.getTimesheet(dto.getUuid());

        if (entity != null) {
            entity.setContract(ConvertToEntityHelper.convertToContractEntity(dto.getContract()));
            entity.setStatus(dto.getStatus());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setHoursDue(dto.getHoursDue());
            entity.setSignedByEmployee(dto.getSignedByEmployee());
            entity.setSignedBySupervisor(dto.getSignedBySupervisor());
        }

        return entity;
    }

    private TimesheetEntity getTimesheetEntity(Timesheet timesheet) {
        TimesheetEntity entity = getEntityFromDTO(timesheet);

        if (entity == null) {
            throw new BusinessException("busExTimesheetNotFound");
        }

        return entity;
    }

    @Override
    public Timesheet sendTimesheetToSupervisor(Timesheet timesheet) {
        TimesheetEntity entity = getTimesheetEntity(timesheet);

        if (!CommonHelper.timesheetCanBeSendedToSupervisor(timesheet)) {
            throw new BusinessException("busExTimesheetSend");
        }

        entity.setStatus(TimesheetStatus.SIGNED_BY_EMPLOYEE);
        entity.setSignedByEmployee(LocalDate.now());
        ta.updateTimesheet(entity);

        return ConvertToDTOHelper.convertToTimesheetDTO(entity, true, true, getRoles());
    }

    @Override
    public Timesheet claimTimesheet(Timesheet timesheet) {
        TimesheetEntity entity = getTimesheetEntity(timesheet);

        if (!CommonHelper.timesheetCanBeClaimed(timesheet)) {
            throw new BusinessException("busExTimesheetClaim");
        }

        entity.setStatus(TimesheetStatus.IN_PROGRESS);
        entity.setSignedByEmployee(null);
        ta.updateTimesheet(entity);

        return ConvertToDTOHelper.convertToTimesheetDTO(entity, true, true, getRoles());
    }

    @Override
    public Timesheet aprroveTimesheet(Timesheet timesheet) {
        TimesheetEntity entity = getTimesheetEntity(timesheet);

        if (!CommonHelper.timesheetCanBeApproved(timesheet)) {
            throw new BusinessException("busExTimesheetApprove");
        }

        entity.setStatus(TimesheetStatus.SIGNED_BY_SUPERVISOR);
        entity.setSignedBySupervisor(LocalDate.now());
        ta.updateTimesheet(entity);

        return ConvertToDTOHelper.convertToTimesheetDTO(entity, true, true, getRoles());
    }

    @Override
    public Timesheet archiveTimesheet(Timesheet timesheet) {
        TimesheetEntity entity = getEntityFromDTO(timesheet);

        if (entity == null) {
            throw new BusinessException("busExTimesheetNotFound");
        }

        if (!CommonHelper.timesheetCanBeArchieved(timesheet)) {
            throw new BusinessException("busExTimesheetArchieve");
        }

        entity.setStatus(TimesheetStatus.ARCHIVED);
        ta.updateTimesheet(entity);

        ContractEntity contract = ca.getContract(entity.getContract().getUuid());
        Set<TimesheetEntity> timesheetList = contract.getTimesheets();
        boolean canContractBeArchived = CommonHelper.canContractBeArchived(timesheetList, entity);
        if (canContractBeArchived) {
            contract.setStatus(ContractStatus.ARCHIVED);
            entity.getContract().setStatus(ContractStatus.ARCHIVED);
        }
        return ConvertToDTOHelper.convertToTimesheetDTO(entity, true, true, getRoles());
    }

    @Override
    public Timesheet sendTimesheetToEmployee(Timesheet timesheet) {
        TimesheetEntity entity = getTimesheetEntity(timesheet);

        if (!CommonHelper.timesheetcanBeSendedToEmployee(timesheet)) {
            throw new BusinessException("busExTimesheetSendStatus");
        }

        entity.setStatus(TimesheetStatus.IN_PROGRESS);
        entity.setSignedByEmployee(null);
        ta.updateTimesheet(entity);

        return ConvertToDTOHelper.convertToTimesheetDTO(entity, true, true, getRoles());
    }

    @Override
    public List<Timesheet> getEditableTimesheetList(String contractUuid) {
        List<Timesheet> timesheetDTOList = new ArrayList<>();
        ContractEntity contract = ca.getContract(contractUuid);
        Set<TimesheetEntity> timesheetList = contract.getTimesheets();

        for (TimesheetEntity entity : timesheetList) {
            if (entity.getStatus() == TimesheetStatus.IN_PROGRESS) {
                timesheetDTOList.add(ConvertToDTOHelper.convertToDTO(entity, false));
            }
        }

        return timesheetDTOList;
    }

    @Override
    public List<Timesheet> getTimesheetList(String contractUuid) {
        List<Timesheet> timesheetDTOList = new ArrayList<>();
        ContractEntity contract = ca.getContract(contractUuid);
        Set<TimesheetEntity> timesheetList = contract.getTimesheets();

        for (TimesheetEntity entity : timesheetList) {
            timesheetDTOList.add(ConvertToDTOHelper.convertToDTO(entity, false));
        }

        return timesheetDTOList;
    }

    //RE1 - (On the last day of a time sheet (either end of week or end of month), 
    //       the TSS SHALL send a reminder mail to the employee if the time sheet is in state IN_PROGRESS.)
    @Override
    public void insertEmailsForEmployee() {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

        List<TimesheetEntity> timesheetList = ta.getTimesheetList(TimesheetStatus.IN_PROGRESS,
                localDate);

        if (timesheetList != null && timesheetList.size() > 0) {

            Map<PersonEntity, Long> employeeMap = timesheetList.stream().collect(
                    Collectors.groupingBy(TimesheetEntity::getContractEmployee, Collectors.counting()));

            employeeMap.forEach((key, value) -> {
                System.out.println("Person Uuid : " + key + " Person mail count : " + value);

                //Check EmailPersonEntity, if not exist insert!
                EmailPersonEntity emailPersonEntity = getEmailPersonEntity(key);

                List<TimesheetEntity> personsTimesheetsForMail = timesheetList.stream()
                        .filter(x -> key.equals(x.getContractEmployee()))
                        .collect(Collectors.toList());

                //Insert EmailEntity with EmailPersonEntity ID
                for (TimesheetEntity timesheetEntity : personsTimesheetsForMail) {
                    EmailEntity emailEntity = ea.createEmail(EmailContentHelper.getEmailContentForEmployeeTimesheetDelay(timesheetEntity),
                            emailPersonEntity);

                    Set<EmailEntity> emails = emailPersonEntity.getEmails();
                    emails.add(emailEntity);
                    emailPersonEntity.setEmails(emails);

                }

            });

        }

    }

    //RE2 - (The TSS SHALL send a reminder mail to the supervisor and the assistants 
    //      if the time sheet is in state SIGNED_BY_EMPLOYEE. (then, the supervisor
    //      may reject or sign the time sheet))
    @Override
    public void insertEmailsForSupervisorAndAssistant() {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

        List<TimesheetEntity> timesheetList = ta.getTimesheetList(TimesheetStatus.SIGNED_BY_EMPLOYEE,
                localDate);

        if (timesheetList != null && timesheetList.size() > 0) {
            insertEmailsForSupervisor(timesheetList);
            insertEmailsForAssistants(timesheetList);
        }
    }

    private void insertEmailsForSupervisor(List<TimesheetEntity> timesheetList) {
        Map<PersonEntity, Long> supervisorMap = timesheetList.stream().collect(
                Collectors.groupingBy(TimesheetEntity::getContractSupervisor, Collectors.counting()));

        supervisorMap.forEach((key, value) -> {
            System.out.println("Supervisor Uuid : " + key + " Supervisor mail count : " + value);

            //Check EmailPersonEntity, if not exist insert!
            EmailPersonEntity emailPersonEntity = getEmailPersonEntity(key);

            List<TimesheetEntity> employeeTimesheetsForSuperviserEmail = timesheetList.stream()
                    .filter(x -> key.equals(x.getContractSupervisor()))
                    .collect(Collectors.toList());

            //Insert EmailEntity with EmailPersonEntity ID
            for (TimesheetEntity timesheetEntity : employeeTimesheetsForSuperviserEmail) {
                EmailEntity emailEntity = ea.createEmail(EmailContentHelper.getEmailContentForSupervisorTimesheetApproveDelay(timesheetEntity),
                        emailPersonEntity);

                Set<EmailEntity> emails = emailPersonEntity.getEmails();
                emails.add(emailEntity);
                emailPersonEntity.setEmails(emails);
            }
        });
    }

    private void insertEmailsForAssistants(List<TimesheetEntity> timesheetList) {
        Set<AssistantEntity> assistantList = new HashSet<>();

        for (TimesheetEntity timesheetEntity : timesheetList) {
            Set<AssistantEntity> asistants = timesheetEntity.getContract().getAssistants();
            assistantList.addAll(asistants);
        }

        Map<PersonEntity, Long> assistantMap = assistantList.stream().collect(
                Collectors.groupingBy(AssistantEntity::getPerson, Collectors.counting()));

        assistantMap.forEach((key, value) -> {
            System.out.println("Assistant Uuid : " + key + " Assistant mail count : " + value);

            List<TimesheetEntity> employeeTimesheetsForAssistantEmail = timesheetList.stream()
                    .filter(x -> key.equals(x.getContractAsistant(key.getUuid())))
                    .collect(Collectors.toList());

            if (employeeTimesheetsForAssistantEmail != null
                    && employeeTimesheetsForAssistantEmail.size() > 0) {
                //Check EmailPersonEntity, if not exist insert!
                EmailPersonEntity emailPersonEntity = getEmailPersonEntity(key);

                //Insert EmailEntity with EmailPersonEntity ID
                for (TimesheetEntity timesheetEntity : employeeTimesheetsForAssistantEmail) {
                    EmailEntity emailEntity = ea.createEmail(EmailContentHelper.getEmailContentForAssistantTimesheetApproveDelay(timesheetEntity),
                            emailPersonEntity);

                    Set<EmailEntity> emails = emailPersonEntity.getEmails();
                    emails.add(emailEntity);
                    emailPersonEntity.setEmails(emails);
                }
            }
        });
    }

    //TODO : not min
    //RE3 - (The TSS SHALL send a reminder mail to the secretaries if the time sheet is 
    //       in state SIGNED_BY_SUPERVISOR.)
    @Override
    public void insertEmailsForSecretary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //RE5 - (The TSS SHOULD collect all reminders so that a person receives at most one e-mail per day.)
    private EmailPersonEntity getEmailPersonEntity(PersonEntity person) {
        //Check EmailPersonEntity, if not exist insert!
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

        EmailPersonEntity emailPersonEntity = epa.getEmailPerson(EmailStatus.WAITING,
                localDate, person.getId());
        if (emailPersonEntity == null) {
            emailPersonEntity = epa.createEmailPerson(person.getEmailAddress(), LocalDate.now(), person);
        }

        return emailPersonEntity;
    }

    @Override
    public void sendEmail() {
        try {
            List<EmailPersonEntity> emailPersonList = epa.getWaitingEmailPersonList();

            if (emailPersonList != null && emailPersonList.size() > 0) {

                for (EmailPersonEntity entity : emailPersonList) {
                    List<EmailEntity> emailList = ea.getEmailList(entity.getId());
                    String emailContent = "";
                    for (EmailEntity email : emailList) {
                        emailContent += email.getContent();
                    }
                    try {

                        String recipient = EmailContentHelper.getToEmailAddressForTest;
                        if(recipient.isEmpty())
                            recipient=entity.getEmailAddress();
                        
                        SmtpServerHelper.sendMail(recipient, "Reminders From Timesheet",
                                emailContent,
                                mailSession);

                        entity.setStatus(EmailStatus.SENT);
                        entity.setSendDate(LocalDate.now());
                        epa.updateEmailPerson(entity);
                    } catch (Exception ex) {
                        System.err.println("sendMailError:" + ex.toString());

                        entity.setStatus(EmailStatus.FAILED);
                        epa.updateEmailPerson(entity);
                    }

                }

            } else {
                System.out.println("emailPersonList is empty");
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}