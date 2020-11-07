/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto.helper;

import com.papa.jee18.dto.Assistant;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Employee;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Secretary;
import com.papa.jee18.dto.Supervisor;
import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.dto.TimesheetEntry;
import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.SupervisorEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import com.papa.jee18.entities.TimesheetStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class ConvertToDTOHelper implements Serializable {

    private static final long serialVersionUID = -8487457356168055134L;

    public static Contract convertToContractDTO(ContractEntity ce, boolean loadTimesheets) {
        Contract c = new Contract();
        c.setId(ce.getId());
        c.setUuid(ce.getUuid());
        c.setName(ce.getName());
        c.setStatus(ce.getStatus());
        c.setStartDate(CommonHelper.convertToDate(ce.getStartDate()));
        c.setEndDate(CommonHelper.convertToDate(ce.getEndDate()));
        c.setFrequency(ce.getFrequency());
        c.setVacationHours(ce.getVacationHours());
        c.setTerminationDate(CommonHelper.convertToDate(ce.getTerminationDate()));
        c.setHoursDue(ce.getHoursDue());
        c.setHoursPerWeek(ce.getHoursPerWeek());
        c.setVacationDaysPerYear(ce.getVacationDaysPerYear());
        c.setVacationHours(ce.getVacationHours());
        c.setWorkingDaysPerWeek(ce.getWorkingDaysPerWeek());

        if (ce.getSupervisor() != null && ce.getSupervisor().getPerson() != null) {
            if (ce.getSupervisor() != null) {
                c.setSupervisor(convertToSupervisorDTO(ce.getSupervisor()));

            }
            String supervisorName = ce.getSupervisor().getPerson().getFirstName()
                    + " " + ce.getSupervisor().getPerson().getLastName();
            c.setSupervisorName(supervisorName);
        }

        if (ce.getEmployee() != null && ce.getEmployee().getPerson() != null) {
            c.setEmployee(convertToEmployeeDTO(ce.getEmployee()));
            String employeeName = ce.getEmployee().getPerson().getFirstName()
                    + " " + ce.getEmployee().getPerson().getLastName();
            c.setEmployeeName(employeeName);
        }
        
        if(ce.getAssistants() != null && ce.getAssistants().size() > 0){
            Set<Assistant> assSet = new HashSet<Assistant>();
            for(AssistantEntity ae : ce.getAssistants()){
                if(ae.getPerson() != null){
                    assSet.add(convertToAssistantDTO(ae));
                }
            }
            c.setAssistants(assSet);
        }
        
        if(ce.getSecretaries() != null && ce.getSecretaries().size() > 0){
            Set<Secretary> seSet = new HashSet<Secretary>();
            for(SecretaryEntity ae : ce.getSecretaries()){
                if(ae.getPerson() != null){
                    seSet.add(convertToSecretaryDTO(ae));
                }
            }
            c.setSecretaries(seSet);
        }

        if (loadTimesheets) {
            if (ce.getTimesheets() != null && !ce.getTimesheets().isEmpty()) {
                Set<Timesheet> timesheets = new HashSet<Timesheet>(ce.getTimesheets().size());
                Iterator iterator = ce.getTimesheets().iterator();
                while (iterator.hasNext()) {
                    TimesheetEntity te = (TimesheetEntity) iterator.next();
                    timesheets.add(convertToDTO(te, false));
                }
                c.setTimesheets(timesheets);
            }
            
            c = setHoursDueBalance(c);
        }
        
        return c;
    }
    
    private static Contract setHoursDueBalance(Contract contract) {
        if (contract != null) {
            if (contract.getTimesheets() != null && contract.getTimesheets().size() > 0) {
                double contractHoursDue = 0;
                double approvedHours = 0;
                if (contract.getTimesheets() != null) {
                    for (Timesheet ts : contract.getTimesheets()) {
                        contractHoursDue += ts.getHoursDue();
                        if (ts.getStatus().equals(TimesheetStatus.SIGNED_BY_SUPERVISOR)) {
                            approvedHours += ts.getHoursDue();
                        }
                    }
                    //contract.setHoursDue(contractHoursDue);
                    contract.setRemainingHoursDue(contractHoursDue - approvedHours);
                    double contractBalance = approvedHours / contractHoursDue;
                    contract.setBalance(String.format("%.02f", contractBalance));
                }
            }else{
                contract.setRemainingHoursDue(contract.getHoursDue());
                contract.setBalance("0");
            }
        }
        return contract;
    }

    public static Person convertToPersonDTO(PersonEntity personEntity) {
        Person personDTO = new Person();
        if (personEntity.getId() != null) {
            personDTO.setId(personEntity.getId());
            personDTO.setUuid(personEntity.getUuid());
            personDTO.setFirstName(personEntity.getFirstName());
            personDTO.setLastName(personEntity.getLastName());
            personDTO.setEmailAddress(personEntity.getEmailAddress());
            personDTO.setDateOfBirth(personEntity.getDateOfBirth());
        }

        return personDTO;
    }

    public static Supervisor convertToSupervisorDTO(SupervisorEntity supervisorEntity) {
        Supervisor supervisorDTO = new Supervisor();
        if (supervisorEntity.getId() != null) {
            supervisorDTO.setId(supervisorEntity.getId());
            supervisorDTO.setUuid(supervisorEntity.getUuid());
            if (supervisorEntity.getPerson() != null) {
                supervisorDTO.setPerson(convertToPersonDTO(supervisorEntity.getPerson()));
            }
        }
        return supervisorDTO;
    }

    public static Employee convertToEmployeeDTO(EmployeeEntity employeeEntity) {
        Employee employeeDTO = new Employee();
        if (employeeEntity.getId() != null) {
            employeeDTO.setId(employeeEntity.getId());
            employeeDTO.setUuid(employeeEntity.getUuid());
            if (employeeEntity.getPerson() != null) {
                employeeDTO.setPerson(convertToPersonDTO(employeeEntity.getPerson()));
            }
        }
        return employeeDTO;
    }
    
    public static Assistant convertToAssistantDTO(AssistantEntity assistantEntity) {
        Assistant assistantDTO = new Assistant();
        if (assistantEntity.getId() != null) {
            assistantDTO.setId(assistantEntity.getId());
            assistantDTO.setUuid(assistantEntity.getUuid());
            if (assistantEntity.getPerson() != null) {
                assistantDTO.setPerson(convertToPersonDTO(assistantEntity.getPerson()));
            }
        }
        return assistantDTO;
    }
    
    public static Secretary convertToSecretaryDTO(SecretaryEntity secretaryEntity) {
        Secretary secretaryDTO = new Secretary();
        if (secretaryEntity.getId() != null) {
            secretaryDTO.setId(secretaryEntity.getId());
            secretaryDTO.setUuid(secretaryEntity.getUuid());
            if (secretaryEntity.getPerson() != null) {
                secretaryDTO.setPerson(convertToPersonDTO(secretaryEntity.getPerson()));
            }
        }
        return secretaryDTO;
    }

    public static Timesheet convertToTimesheetDTO(TimesheetEntity timesheetEntity, boolean loadEntries,boolean setRoles,Set<RoleEntity> roles){
        Timesheet dto = convertToDTO(timesheetEntity, loadEntries);
        if(setRoles && roles != null)
            dto = CommonHelper.setRoles(dto, roles);
        return dto;
    }
    
    public static Timesheet convertToDTO(TimesheetEntity timesheetEntity, boolean loadEntries) {
        Timesheet dto = new Timesheet();
        dto.setUuid(timesheetEntity.getUuid());
        dto.setId(timesheetEntity.getId());
        dto.setContract(convertToContractDTO(timesheetEntity.getContract(), false));
        //dto.setContract(timesheetEntity.getContract());
        dto.setStatus(timesheetEntity.getStatus());
        dto.setStartDate(timesheetEntity.getStartDate());
        dto.setEndDate(timesheetEntity.getEndDate());
        dto.setHoursDue(timesheetEntity.getHoursDue());
        dto.setSignedByEmployee(timesheetEntity.getSignedByEmployee());
        dto.setSignedBySupervisor(timesheetEntity.getSignedBySupervisor());
        if (loadEntries) {
            Set<TimesheetEntry> teList = convertToTimesheetEntryDTOList(timesheetEntity.getEntries());
            dto.setEntries(teList);
        }
        return dto;
    }

    private static Set<TimesheetEntry> convertToTimesheetEntryDTOList(Set<TimesheetEntryEntity> entries) {
        Set<TimesheetEntry> timesheetDTOList = new HashSet<TimesheetEntry>(entries.size());
        for (TimesheetEntryEntity entity : entries) {
            timesheetDTOList.add(convertToDTO(entity));
        }
        return timesheetDTOList;
    }

    public static List<Timesheet> convertToTimesheetDTOList(Set<TimesheetEntity> timesheetEntitySet, boolean loadEntries) {
        List<Timesheet> timesheetDTOList = new ArrayList<Timesheet>(timesheetEntitySet.size());
        for (TimesheetEntity entity : timesheetEntitySet) {
            Timesheet dto = convertToDTO(entity, loadEntries);
            timesheetDTOList.add(dto);
        }
        return timesheetDTOList;
    }

    public static TimesheetEntry convertToDTO(TimesheetEntryEntity entity) {
        TimesheetEntry dto = new TimesheetEntry();

        if (entity != null) {
            dto.setUuid(entity.getUuid());
            dto.setId(entity.getId());
            dto.setType(entity.getType());
            dto.setDescription(entity.getDescription());
            dto.setHours(entity.getHours());
            dto.setStartTime(CommonHelper.toDate(entity.getStartTime()));
            dto.setEndTime(CommonHelper.toDate(entity.getEndTime()));
            dto.setEntryDate(CommonHelper.convertToDate(entity.getEntryDate()));
            dto.setTimesheet(convertToDTO(entity.getTimesheet(),false));
        }
        
        return dto;
    }    
}
