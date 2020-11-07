/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic.impl;

import com.papa.jee18.dao.AssistantAccess;
import com.papa.jee18.dao.ContractAccess;
import com.papa.jee18.dao.EmployeeAccess;
import com.papa.jee18.dao.HolidayAccess;
import com.papa.jee18.dao.HolidayCountry;
import com.papa.jee18.dao.HolidayRegion;
import com.papa.jee18.dao.PersonAccess;
import com.papa.jee18.dao.SecretaryAccess;
import com.papa.jee18.dao.SupervisorAccess;
import com.papa.jee18.dao.TimesheetAccess;
import com.papa.jee18.dao.TimesheetEntryAccess;
import com.papa.jee18.dto.Assistant;
import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Employee;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Secretary;
import com.papa.jee18.dto.Supervisor;
import com.papa.jee18.dto.helper.CommonHelper;
import com.papa.jee18.dto.helper.ConvertToDTOHelper;
import com.papa.jee18.dto.helper.ConvertToEntityHelper;
import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.HolidayEntity;
import com.papa.jee18.entities.HolidayType;
import com.papa.jee18.entities.LanguageType;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.SupervisorEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import com.papa.jee18.entities.TimesheetFrequency;
import com.papa.jee18.entities.TimesheetStatus;
import com.papa.jee18.logic.ContractLogic;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;


@Stateless
public class ContractLogicImplementation implements ContractLogic {

    @EJB
    private ContractAccess ca;

    @EJB
    private SecretaryAccess sea;

    @EJB
    private EmployeeAccess ea;

    @EJB
    private SupervisorAccess sa;

    @EJB
    private AssistantAccess aa;

    @EJB
    private TimesheetAccess ta;

    @EJB
    private PersonAccess pa;

    @EJB
    private TimesheetEntryAccess tea;

    @EJB
    private HolidayAccess ha;

    @Resource
    private EJBContext ejbContext;

    private PersonEntity caller;

    private Set<RoleEntity> _roles;

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

    @Override
    public List<Employee> getEmployeeList() {
        List<EmployeeEntity> employees = ea.getEmployeeList();
        List<Employee> employeeListResult = new ArrayList<Employee>(employees.size());
        for (EmployeeEntity ee : employees) {
            // Employee e = getEmployeeFromEntity(ee);
            Employee e = ConvertToDTOHelper.convertToEmployeeDTO(ee);
            employeeListResult.add(e);
        }
        return employeeListResult;

    }

    @Override
    public List<Supervisor> getSupervisorList() {
        List<SupervisorEntity> supervisors = sa.getSupervisorList();
        List<Supervisor> supervisorListResult = new ArrayList<Supervisor>(supervisors.size());
        for (SupervisorEntity ee : supervisors) {
            Supervisor e = ConvertToDTOHelper.convertToSupervisorDTO(ee);
            supervisorListResult.add(e);
        }
        return supervisorListResult;
    }

    @Override
    public List<Contract> getContractList() {
        System.err.println("CallerPrincipal: " + ejbContext.getCallerPrincipal().getName());
        List<ContractEntity> contracts = ca.getContractList();
        List<Contract> contractListResult = new ArrayList<Contract>(contracts.size());
        for (ContractEntity ce : contracts) {
            Contract c = ConvertToDTOHelper.convertToContractDTO(ce, true);
            contractListResult.add(c);
        }
        return contractListResult;
    }

    @Override
    public Contract getContractDetails(String uuid) {
        if (uuid == null) {
            throw new BusinessException("busExContractNotFound");
        }

        ContractEntity entity = ca.getContract(uuid);

        if (entity == null) {
            throw new BusinessException("busExContractNotFound");
        }

        return getContractDTO(entity);
    }

    private Contract getContractDTO(ContractEntity entity) {
        return CommonHelper.setRoles(ConvertToDTOHelper.convertToContractDTO(entity, true), getRoles());
    }

    @Override
    public List<Person> getPersonList() {
        List<PersonEntity> persons = pa.getPersonList();
        List<Person> personListResult = new ArrayList<Person>(persons.size());
        for (PersonEntity pe : persons) {
            Person p = ConvertToDTOHelper.convertToPersonDTO(pe);
            personListResult.add(p);
        }
        return personListResult;
    }

    @Override
    public Contract updateContract(Contract c, Person supervisorPerson,
            List<Person> selectedAssistantPersons, List<Person> selectedSecretaryPersons) {

        ContractEntity ce = ca.getContract(c.getUuid());

        if (ce == null) {
            throw new BusinessException("busExContractNotFound");
        }

        Set<PersonEntity> assPersons = new HashSet<PersonEntity>();
        Set<PersonEntity> sePersons = new HashSet<PersonEntity>();

        if (selectedAssistantPersons != null && selectedAssistantPersons.size() > 0) {
            for (Person personDTO : selectedAssistantPersons) {
                PersonEntity pEntity = pa.getPersonByUUID(personDTO.getUuid());
                assPersons.add(pEntity);
            }
        }

        if (selectedSecretaryPersons != null && selectedSecretaryPersons.size() > 0) {
            for (Person personDTO : selectedSecretaryPersons) {
                PersonEntity pEntity = pa.getPersonByUUID(personDTO.getUuid());
                sePersons.add(pEntity);
            }
        }

        SupervisorEntity se = ce.getSupervisor();
        if (!ce.getSupervisor().getPerson().getUuid().equals(supervisorPerson.getUuid())) {
            se = saveSupervisor(null, supervisorPerson);
        }

        ce = updateContractEntity(ce, c);
        ca.updateContract(ce, se, assPersons, sePersons);
        c = getContractDTO(ce);
        return c;
    }

    public Contract updateContract(Contract c) {
        ContractEntity ce = ca.getContract(c.getUuid());

        if (ce == null) {
            throw new BusinessException("busExContractNotFound");
        }

        ce = updateContractEntity(ce, c);
        ca.updateContract(ce);
        c = getContractDTO(ce);
        return c;
    }

    @Override
    public Contract startContract(Contract dto) {
        ContractEntity entity = ca.getContract(dto.getUuid());
        if (entity == null || entity.getUuid() == null) {
            throw new BusinessException("busExContractNotFound");
        }

        if (!CommonHelper.isContractCanBeEdited(dto)) {
            throw new BusinessException("busExContractCanNotBeEdited");
        }

        Set<TimesheetEntity> timesheetList = createTimesheetByContract(entity);

        entity.setStatus(ContractStatus.STARTED);
        entity.setHoursDue(getHoursDueForContract(timesheetList));
        entity.setTimesheets(timesheetList);
        ca.updateContract(entity);

        return ConvertToDTOHelper.convertToContractDTO(entity, true);
    }

    private Contract terminateContract(Contract c) {
        c.setTimesheets(null);
        c.setStatus(ContractStatus.TERMINATED);
        c.setTerminationDate(CommonHelper.convertToDate(LocalDate.now()));
        updateContract(c);
        return c;
    }

    @Override
    public Contract deleteContract(Contract dto) {
        ContractEntity entity = ca.getContract(dto.getUuid());

        if (entity == null || entity.getUuid() == null) {
            throw new BusinessException("busExContractNotFound");
        }

        if (!CommonHelper.isContractCanBeEdited(dto)) {
            throw new BusinessException("busExContractCanNotBeDeleted");
        }

        deleteContractRoles(entity);
        ca.deleteContract(entity);
        dto.setCanBeEdited(false);
        return dto;
    }

    private void deleteContractRoles(ContractEntity entity) {
        PersonEntity empPerson = pa.getPersonByUUID(entity.getEmployee().getPerson().getUuid());
        Set<RoleEntity> roles = empPerson.getRoles();
        if (roles != null && roles.size() > 0) {
            roles.remove(entity.getEmployee());
            empPerson.setRoles(roles);
        }

        ea.deleteEmployeeRole(entity.getEmployee());

        PersonEntity superson = pa.getPersonByUUID(entity.getSupervisor().getPerson().getUuid());
        Set<RoleEntity> rolesSup = superson.getRoles();
        if (rolesSup != null && rolesSup.size() > 0) {
            rolesSup.remove(entity.getSupervisor());
            superson.setRoles(rolesSup);
        }

        sa.deleteSupervisorRole(entity.getSupervisor());

        if (entity.getAssistants() != null && entity.getAssistants().size() > 0) {
            for (AssistantEntity assistantEntity : entity.getAssistants()) {

                PersonEntity asperson = pa.getPersonByUUID(assistantEntity.getPerson().getUuid());
                Set<RoleEntity> rolesAs = asperson.getRoles();
                if (rolesAs != null && rolesAs.size() > 0) {
                    rolesAs.remove(assistantEntity);
                    asperson.setRoles(rolesAs);
                }

                aa.deleteAssistantRole(assistantEntity);
            }
        }

        if (entity.getSecretaries() != null && entity.getSecretaries().size() > 0) {
            for (SecretaryEntity secretaryEntity : entity.getSecretaries()) {

                PersonEntity seperson = pa.getPersonByUUID(secretaryEntity.getPerson().getUuid());
                Set<RoleEntity> rolesSe = seperson.getRoles();
                if (rolesSe != null && rolesSe.size() > 0) {
                    rolesSe.remove(secretaryEntity);
                    seperson.setRoles(rolesSe);
                }

                sea.deleteSecretaryRole(secretaryEntity);
            }
        }
    }

    private ContractEntity updateContractEntity(ContractEntity ce, Contract c) {
        //Employee and Supervisor of the contract can not be updated, current ones are used!
        ce.setName(c.getName());
        ce.setStartDate(CommonHelper.convertToLocalDate(c.getStartDate()).withDayOfMonth(1));

        LocalDate endDate = CommonHelper.convertToLocalDate(c.getEndDate());
        ce.setEndDate(endDate.withDayOfMonth(endDate.lengthOfMonth()));

        ce.setVacationHours(calculateVacationHours(c));
        if (c.getTerminationDate() != null) {
            ce.setTerminationDate(CommonHelper.convertToLocalDate(c.getTerminationDate()));
        }
        ce.setHoursPerWeek(c.getHoursPerWeek());
        ce.setVacationDaysPerYear(c.getVacationDaysPerYear());
        ce.setWorkingDaysPerWeek(c.getWorkingDaysPerWeek());
        if (c.getStatus() != null) {
            ce.setStatus(c.getStatus());
        } else {
            ce.setStatus(ContractStatus.PREPARED);
        }
        ce.setFrequency(c.getFrequency());

        if (c.getTimesheets() == null) {
            ce.setTimesheets(null);
        }
        //else
        //ce.setTimesheets(ConvertToEntityHelper.convertToTimesheetEntityList(c.getTimesheets()));

        return ce;
    }

    private double calculateVacationHours(Contract c) {
        //CN 4a MIN: vacationHours = vacationDaysPerYear * durationOfContract / 12 * hoursPerWeek / workingDaysPerWeek
        //The duration of the contract is counted in months. 
        double vacationHours = c.getVacationDaysPerYear() * (getDurationOfContract(c) / 12)
                * (c.getHoursPerWeek() / c.getWorkingDaysPerWeek());
        return vacationHours;
    }

    private double getDurationOfContract(Contract c) {
        LocalDate start = CommonHelper.convertToLocalDate(c.getStartDate());
        LocalDate end = CommonHelper.convertToLocalDate(c.getEndDate());
        int m1 = start.getYear() * 12 + start.getMonthValue();
        int m2 = end.getYear() * 12 + end.getMonthValue();
        return m2 - m1 + 1;

    }

    @Override
    public List<Contract> getStartedContractListByRole(boolean loadTimesheets, boolean loadTimesheetEntries) {

        List<Contract> contractDTOList = new ArrayList<Contract>();

        for (RoleEntity entity : getRoles()) {

            ContractEntity contractEntity = getContractEntityByRole(entity);

            if (ContractStatus.STARTED == contractEntity.getStatus()) {
                contractDTOList.add(ConvertToDTOHelper.convertToContractDTO(contractEntity, loadTimesheets));
            }
        }

        return contractDTOList;
    }

    @Override
    public ContractEntity getContractEntityByRole(RoleEntity entity) {
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

    //TS4 - (The TSS SHALL delete time sheets in status IN_PROGRESS as soon as the contract sta- tus changes to TERMINATED.)
    //TS5 - (The TSS SHALL not delete time sheets that are in the SIGNED_BY_EMPLOYEE state.)
    //TS6 - (The TSS SHALL not delete time sheets that are in the SIGNED_BY_SUPERVISOR state.)x
    @Override
    public Contract terminateContractAndDeleteInProgressTimesheets(Contract dto) {
        ContractEntity entity = ca.getContract(dto.getUuid());

        if (entity == null || entity.getUuid() == null) {
            throw new BusinessException("busExContractNotFound");
        }

        if (!CommonHelper.isContractCanBeTerminated(dto)) {
            throw new BusinessException("busExContractCanNotBeTerminated");
        }

        Set<TimesheetEntity> timesheetSet = entity.getTimesheets();

        if (timesheetSet != null && timesheetSet.size() > 0) {

            for (TimesheetEntity timesheetEntity : entity.getTimesheets()) {

                if (timesheetEntity.getStatus() == TimesheetStatus.IN_PROGRESS) {

                    Set<TimesheetEntryEntity> entryEntityList = timesheetEntity.getEntries();
                    if (entryEntityList != null && entryEntityList.size() > 0) {

                        for (TimesheetEntryEntity entryEntity : entryEntityList) {
                            tea.deleteTimesheetEntry(entryEntity);
                        }

                    }

                    ta.deleteTimesheet(timesheetEntity);
                }
            }

        }
        dto = terminateContract(dto);
        dto.setCanBeTerminated(false);
        return dto;
    }

    public double getHoursDueForContract(Set<TimesheetEntity> timesheetList) {
        if (timesheetList == null || timesheetList.size() <= 0) {
            throw new BusinessException("busExTimesheetListEmptyForHoursDue");
        }

        double totalHoursDue = 0;
        for (TimesheetEntity entity : timesheetList) {
            totalHoursDue += entity.getHoursDue();
        }

        return totalHoursDue;
    }

    @Override
    public List<Contract> getContractListByRole(boolean loadTimesheets, boolean loadTimesheetEntries) {
        List<Contract> contractDTOList = new ArrayList<Contract>();

        for (RoleEntity entity : getRoles()) {
            ContractEntity contractEntity = getContractEntityByRole(entity);

            List<Contract> checkcList = contractDTOList.stream()
                    .filter(x -> x.getUuid().equals(contractEntity.getUuid()))
                    .collect(Collectors.toList());
            if (checkcList == null || checkcList.size() <= 0) {
                Contract c = ConvertToDTOHelper.convertToContractDTO(contractEntity, loadTimesheets);
                c = CommonHelper.setRoles(c, entity);
                contractDTOList.add(c);
            }
        }

        return contractDTOList;
    }

    //CN4c - (The TSS SHALL calculate the hours due for a Timesheet by the following formula: 
    //        hoursDue = (workingDaysInPeriod - publicHolidaysInPeriod) * hoursPerWeek / workingDaysPerWeek )
    @Override
    public Set<TimesheetEntity> createTimesheetByContract(ContractEntity contract) {
        if (contract == null || contract.getUuid() == null) {
            throw new BusinessException("busExContractNotFound");
        }

        Set<TimesheetEntity> teList = new HashSet<>();

        int monthCount = getMonthCount(contract.getStartDate(), contract.getEndDate());
        double hoursPerWeek = contract.getHoursPerWeek();
        double workingDaysPerWeek = contract.getWorkingDaysPerWeek();

        if (TimesheetFrequency.MONTHLY == contract.getFrequency()) {
            LocalDate timesheetStartDate = contract.getStartDate();
            LocalDate timesheetEndDate = contract.getStartDate().plusDays(contract.getStartDate().lengthOfMonth() - 1);

            for (int i = 0; i <= monthCount; i++) {

                double hoursDue = getCalculatedHoursDue(timesheetStartDate, timesheetEndDate,
                        hoursPerWeek, workingDaysPerWeek, contract.getFrequency());

                TimesheetEntity te = ta.createTimesheet(timesheetStartDate, timesheetEndDate, hoursDue, contract);
                teList.add(te);

                timesheetStartDate = timesheetStartDate.plusMonths(1); //Get Next Month's First Day
                timesheetEndDate = timesheetStartDate.plusDays(timesheetStartDate.lengthOfMonth() - 1);
            }
        } else { //TimesheetFrequency.WEEKLY
            LocalDate monthStartDate = contract.getStartDate();
            LocalDate nextMonthStartDate = monthStartDate.plusMonths(1);

            for (int i = 0; i <= monthCount; i++) {

                LocalDate weekStartDate = checkWeekend(monthStartDate); //Saturday and Sunday Check and Set
                LocalDate weekEndDate = getWeekEndDate(weekStartDate);

                while (weekStartDate.isBefore(nextMonthStartDate)) {

                    double hoursDue = getCalculatedHoursDue(weekStartDate, weekEndDate,
                            hoursPerWeek, workingDaysPerWeek, contract.getFrequency());

                    TimesheetEntity te = ta.createTimesheet(weekStartDate, weekEndDate, hoursDue, contract);
                    teList.add(te);

                    weekStartDate = getNextMonday(weekStartDate);
                    weekEndDate = getWeekEndDate(weekStartDate);

                }

                monthStartDate = monthStartDate.plusMonths(1); //Get Next Month's First Day
                nextMonthStartDate = monthStartDate.plusMonths(1);
            }
        }

        return teList;
    }

    private double getCalculatedHoursDue(LocalDate startDate, LocalDate endDate, double hoursPerWeek,
            double workingDaysPerWeek, TimesheetFrequency frequency) {

        double publicHolidaysInPeriod = getPublicHolidaysInPeriod(startDate, endDate);
        double workingDaysInPeriod = getWorkingDaysInPeriod(startDate, endDate, frequency);
        double hoursDue = calculateHoursDue(workingDaysInPeriod, publicHolidaysInPeriod, hoursPerWeek, workingDaysPerWeek);

        return hoursDue;
    }

    private double calculateHoursDue(double workingDaysInPeriod, double publicHolidaysInPeriod, double hoursPerWeek,
            double workingDaysPerWeek) {

        return (workingDaysInPeriod - publicHolidaysInPeriod) * hoursPerWeek / workingDaysPerWeek;
    }

    private double getWorkingDaysInPeriod(LocalDate startDate, LocalDate endDate, TimesheetFrequency frequency) {
        double workingDaysInPeriod = 0;

        if (frequency == TimesheetFrequency.MONTHLY) {
            while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
                if (startDate.getDayOfWeek() != DayOfWeek.SATURDAY
                        && startDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    workingDaysInPeriod += 1;
                }
                startDate = startDate.plusDays(1);
            }
            workingDaysInPeriod += 1;
        } else {  //Weekly
            workingDaysInPeriod = startDate.until(endDate, ChronoUnit.DAYS) + 1;
        }

        return workingDaysInPeriod;
    }

    private double getPublicHolidaysInPeriod(LocalDate startDate, LocalDate endDate) {
        List<HolidayEntity> holidayList = ha.getHolidayList(startDate, endDate, LanguageType.EN, HolidayType.PUBLIC, HolidayCountry.deu, HolidayRegion.rp);
        double publicHolidaysInPeriod = 0;
        if (holidayList != null && holidayList.size() > 0) {
            publicHolidaysInPeriod = holidayList.size();
        }
        return publicHolidaysInPeriod;
    }

    private LocalDate getNextMonday(LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    }

    /*
        This method is written for fixing cases below : 
        1) 01.02.2019 is Monday, so endDate = startDate 
        2) 30.07.2018 - Monday , 03.08.2018 - Friday, so endDate =31.07.2018
     */
    private LocalDate getWeekEndDate(LocalDate date) {
        DayOfWeek dayofweek = date.getDayOfWeek();
        if (dayofweek == DayOfWeek.FRIDAY) {
            return date;
        } else {
            LocalDate weekEndDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (weekEndDate.getMonthValue() != date.getMonthValue()) {
                while (weekEndDate.getMonthValue() != date.getMonthValue()) {
                    weekEndDate = weekEndDate.minusDays(1);
                }
            }
            return weekEndDate;
        }
    }

    /*startDate of time sheet can not be a weekend.*/
    private LocalDate checkWeekend(LocalDate date) {
        DayOfWeek dayofweek = date.getDayOfWeek();
        if (dayofweek == DayOfWeek.SATURDAY) {
            date = date.plusDays(2);
        } else if (dayofweek == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }
        return date;
    }

    /*
        This method is written for fixing cases below : 
        1) contractStartDate = 01.07.2018
           contractEndDate = 30.09.2019
           then; difference.getYears() =1, difference.getMonths() = 2
           but we need "15" as a month count
     */
    private int getMonthCount(LocalDate startDate, LocalDate endDate) {
        Period difference = Period.between(startDate, endDate);
        int months = difference.getMonths();
        int years = difference.getYears();

        if (years > 0) {
            months = months + (years * 12);
        }

        return months;
    }

    private Employee saveEmployee(Employee emp, Person per) {
        EmployeeEntity ee = ConvertToEntityHelper.convertToEmployeeEntity(emp);
        PersonEntity pe = pa.getPersonByUUID(per.getUuid());
        ea.saveEmployee(ee, pe);
        ee.setPerson(pe);
        if (pe.getRoles() != null) {
            pe.getRoles().add(ee);
        } else {
            Set<RoleEntity> re = new HashSet<RoleEntity>();
            re.add(ee);
            pe.setRoles(re);
        }
        return ConvertToDTOHelper.convertToEmployeeDTO(ee);
    }

    private SupervisorEntity saveSupervisor(Supervisor supervisor, Person per) {
        SupervisorEntity se = ConvertToEntityHelper.convertToSupervisorEntity(supervisor);
        PersonEntity pe = pa.getPersonByUUID(per.getUuid());
        sa.saveSupervisor(se, pe);
        se.setPerson(pe);
        if (pe.getRoles() != null) {
            pe.getRoles().add(se);
        } else {
            Set<RoleEntity> re = new HashSet<RoleEntity>();
            re.add(se);
            pe.setRoles(re);
        }
        return se;
    }

    private Supervisor saveSupervisorPerson(Supervisor supervisor, Person per) {
        SupervisorEntity se = saveSupervisor(supervisor, per);
        return ConvertToDTOHelper.convertToSupervisorDTO(se);
    }

    private List<Assistant> saveAssistants(List<Person> selectedAssistantPersons) {

        List<Assistant> retVal = new ArrayList<Assistant>();

        if (selectedAssistantPersons != null && selectedAssistantPersons.size() > 0) {
            for (Person person : selectedAssistantPersons) {
                AssistantEntity asEntity = saveAssistant(person);
                retVal.add(ConvertToDTOHelper.convertToAssistantDTO(asEntity));
            }
        }
        return retVal;
    }

    private AssistantEntity saveAssistant(Person person) {
        PersonEntity pe = pa.getPersonByUUID(person.getUuid());
        AssistantEntity asEntity = AssistantEntity.newInstance();
        aa.saveAssistant(asEntity, pe);
        asEntity.setPerson(pe);

        if (pe.getRoles() != null) {
            pe.getRoles().add(asEntity);
        } else {
            Set<RoleEntity> re = new HashSet<RoleEntity>();
            re.add(asEntity);
            pe.setRoles(re);
        }
        return asEntity;
    }

    private Assistant saveAssistantPerson(Person person) {
        Assistant assistant = new Assistant();
        AssistantEntity entity = saveAssistant(person);
        if (entity != null) {
            assistant = ConvertToDTOHelper.convertToAssistantDTO(entity);
        }
        return assistant;
    }

    private List<Secretary> saveSecretaries(List<Person> selectedSecretaryPersons) {

        List<Secretary> retVal = new ArrayList<Secretary>();

        if (selectedSecretaryPersons != null && selectedSecretaryPersons.size() > 0) {
            for (Person person : selectedSecretaryPersons) {
                PersonEntity pe = pa.getPersonByUUID(person.getUuid());
                SecretaryEntity seEntity = SecretaryEntity.newInstance();
                sea.saveSecretary(seEntity, pe);
                seEntity.setPerson(pe);

                if (pe.getRoles() != null) {
                    pe.getRoles().add(seEntity);
                } else {
                    Set<RoleEntity> re = new HashSet<RoleEntity>();
                    re.add(seEntity);
                    pe.setRoles(re);
                }

                retVal.add(ConvertToDTOHelper.convertToSecretaryDTO(seEntity));
            }
        }
        return retVal;
    }

    private void checkParameters(Contract contractDTO, Person employeePerson, Person supervisorPerson,
            List<Person> selectedAssistantPersons, List<Person> selectedSecretaryPersons) {

        if (contractDTO.getStartDate().equals(contractDTO.getEndDate())
                || CommonHelper.convertToLocalDate(contractDTO.getStartDate()).isAfter(CommonHelper.convertToLocalDate(contractDTO.getEndDate()))) {
            throw new BusinessException("busExContractEndDateWrong");
        }

        if (employeePerson == null || employeePerson.getUuid() == null) {
            throw new BusinessException("busExEmployeeEmpty");
        }

        if (supervisorPerson == null || supervisorPerson.getUuid() == null) {
            throw new BusinessException("busExSupervisorEmpty");
        }

        if (employeePerson.getUuid().equals(supervisorPerson.getUuid())) {
            throw new BusinessException("busExEmployeeSupervisorAreSama");
        }

        if (contractDTO.getHoursPerWeek() <= 0) {
            throw new BusinessException("busExHoursPerWeekWrong");
        }

        if (contractDTO.getWorkingDaysPerWeek() <= 0) {
            throw new BusinessException("busExWorkingDaysPerWeekWrong");
        }

        if (contractDTO.getVacationDaysPerYear() <= 0) {
            throw new BusinessException("busExVacationDaysPerYearWrong");
        }
    }

    @Override
    public Contract saveContract(Contract contractDTO, Person employeePerson, Person supervisorPerson,
            List<Person> selectedAssistantPersons, List<Person> selectedSecretaryPersons) {

        checkParameters(contractDTO, employeePerson, supervisorPerson,
                selectedAssistantPersons, selectedSecretaryPersons);

        Employee e = saveEmployee(null, employeePerson);
        Supervisor s = saveSupervisorPerson(null, supervisorPerson);
        List<Assistant> assistants = saveAssistants(selectedAssistantPersons);
        List<Secretary> secretaries = saveSecretaries(selectedSecretaryPersons);

        contractDTO.setVacationHours(calculateVacationHours(contractDTO));
        ContractEntity ce = ConvertToEntityHelper.convertToContractEntity(contractDTO);
        EmployeeEntity ee = ea.getEmployeeByUUID(e.getUuid());
        SupervisorEntity se = sa.getSupervisorByUUID(s.getUuid());

        List<AssistantEntity> ae = new ArrayList<AssistantEntity>();
        if (assistants != null && assistants.size() > 0) {
            for (Assistant aDTO : assistants) {
                AssistantEntity asEnt = aa.getAssistantByUUID(aDTO.getUuid());
                ae.add(asEnt);
            }
        }

        List<SecretaryEntity> sce = new ArrayList<SecretaryEntity>();
        if (secretaries != null && secretaries.size() > 0) {
            for (Secretary sDTO : secretaries) {
                SecretaryEntity seEnt = sea.getSecretaryByUUID(sDTO.getUuid());
                sce.add(seEnt);
            }
        }

        ce = ca.saveContract(ce, ee, se, ae, sce);
        return CommonHelper.setRoles(contractDTO, getRoles());
    }

    public ContractEntity getContractEmployeeEntityByRole(RoleEntity entity) {
        if (entity instanceof EmployeeEntity) {
            EmployeeEntity ee = (EmployeeEntity) entity;
            return ee.getContract();
        }
        return null;
    }

    @Override
    public List<Contract> getStartedEmployeContractList() {
        List<Contract> contractDTOList = new ArrayList<Contract>();

        for (RoleEntity entity : getRoles()) {

            ContractEntity contractEntity = getContractEmployeeEntityByRole(entity);

            if (contractEntity != null && ContractStatus.STARTED == contractEntity.getStatus()) {
                contractDTOList.add(ConvertToDTOHelper.convertToContractDTO(contractEntity, false));
            }
        }

        return contractDTOList;
    }
}
