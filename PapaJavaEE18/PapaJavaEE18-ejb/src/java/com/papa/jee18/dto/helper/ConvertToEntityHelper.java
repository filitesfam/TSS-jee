/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto.helper;

import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Employee;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Supervisor;
import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.dto.TimesheetEntry;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.SupervisorEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ConvertToEntityHelper implements Serializable {

    private static final long serialVersionUID = 1043754492919571839L;

    public static ContractEntity convertToContractEntity(Contract c) {
        ContractEntity ce;
        if (c == null || c.getUuid() == null) {
            ce = ContractEntity.newInstance();
        } else {
            ce = new ContractEntity();
            ce.setId(c.getId());
            ce.setUuid(c.getUuid());
        }

        if (c != null) {
            ce.setName(c.getName());
            ce.setStartDate(CommonHelper.convertToLocalDate(c.getStartDate()).withDayOfMonth(1));
            System.err.println("c.getStartDate() : " + c.getStartDate().toString());
            System.err.println("c.getStartDate().withDayOfMonth(1) : " + CommonHelper.convertToLocalDate(c.getStartDate()).withDayOfMonth(1));

            LocalDate endDate = CommonHelper.convertToLocalDate(c.getEndDate());
            ce.setEndDate(endDate.withDayOfMonth(endDate.lengthOfMonth()));

            ce.setVacationHours(c.getVacationHours());
            ce.setTerminationDate(CommonHelper.convertToLocalDate(c.getTerminationDate()));
            //ce.setHoursDue(getContractHoursDue(c));
            ce.setHoursPerWeek(c.getHoursPerWeek());
            ce.setVacationDaysPerYear(c.getVacationDaysPerYear());
            ce.setVacationHours(c.getVacationHours());
            ce.setWorkingDaysPerWeek(c.getWorkingDaysPerWeek());
            ce.setStatus(c.getStatus());
            ce.setFrequency(c.getFrequency());
        }
        return ce;
    }

    public static ContractEntity convertToContractEntityForUpdate(Contract c) {
        ContractEntity ce;
        ce = new ContractEntity();
        ce.setUuid(c.getUuid());
        ce.setId(c.getId());
        ce.setName(c.getName());
        ce.setStartDate(CommonHelper.convertToLocalDate(c.getStartDate()).withDayOfMonth(1));

        LocalDate endDate = CommonHelper.convertToLocalDate(c.getEndDate());
        ce.setEndDate(endDate.withDayOfMonth(endDate.lengthOfMonth()));

        ce.setVacationHours(c.getVacationHours());
        ce.setTerminationDate(CommonHelper.convertToLocalDate(c.getTerminationDate()));
        ce.setHoursDue(getContractHoursDue(c));
        ce.setHoursPerWeek(c.getHoursPerWeek());
        ce.setVacationDaysPerYear(c.getVacationDaysPerYear());
        ce.setVacationHours(c.getVacationHours());
        ce.setWorkingDaysPerWeek(c.getWorkingDaysPerWeek());
        if (c.getStatus() != null) {
            ce.setStatus(c.getStatus());
        } else {
            ce.setStatus(ContractStatus.PREPARED);
        }
        ce.setFrequency(c.getFrequency());

        return ce;
    }

    public static PersonEntity convertToPersonEntity(Person personDTO) {
        PersonEntity personEntity;
        if (personDTO == null || personDTO.getId() == null) {
            personEntity = PersonEntity.newInstance();
        } else {
            personEntity = new PersonEntity();
            personEntity.setUuid(personDTO.getUuid());
            personEntity.setId(personDTO.getId());
        }

        personEntity.setFirstName(personDTO.getFirstName());
        personEntity.setLastName(personDTO.getLastName());
        personEntity.setEmailAddress(personDTO.getEmailAddress());
        personEntity.setDateOfBirth(personDTO.getDateOfBirth());
        return personEntity;
    }

    public static SupervisorEntity convertToSupervisorEntity(Supervisor supervisor) {
        SupervisorEntity supervisorEntity;
        if (supervisor == null || supervisor.getUuid() == null) {
            supervisorEntity = SupervisorEntity.newInstance();
        } else {
            supervisorEntity = new SupervisorEntity();
            supervisorEntity.setUuid(supervisor.getUuid());
            supervisorEntity.setId(supervisor.getId());
        }
        return supervisorEntity;
    }

    public static EmployeeEntity convertToEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity;
        if (employee == null || employee.getUuid() == null) {
            employeeEntity = EmployeeEntity.newInstance();
        } else {
            employeeEntity = new EmployeeEntity();
            employeeEntity.setUuid(employee.getUuid());
            employeeEntity.setId(employee.getId());
        }
        return employeeEntity;
    }

    private static double getContractHoursDue(Contract c) {
        //CN4 MIN - C hoursDue =(workingDaysInPeriod - publicHolidaysInPeriod) * hoursPerWeek / workingDaysPerWeek
        //Public holidays haven't been used!
        double hoursDue = getWorkingDaysBetweenTwoDates(CommonHelper.convertToLocalDate(c.getStartDate()), 
                CommonHelper.convertToLocalDate(c.getEndDate())) * (c.getHoursPerWeek() / c.getWorkingDaysPerWeek());
        return hoursDue;
    }

    public static int getWorkingDaysBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        Calendar startCal = Calendar.getInstance();
        Date sd = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        startCal.setTime(sd);

        Calendar endCal = Calendar.getInstance();
        Date ed = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        endCal.setTime(ed);

        int workDays = 0;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(ed);
            endCal.setTime(sd);
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }

    public static TimesheetEntity convertToTimesheetEntity(Timesheet dto, boolean loadEntries) {
        TimesheetEntity entity;
        if (dto == null || dto.getId() == null) {
            entity = TimesheetEntity.newInstance();
        } else {
            entity = new TimesheetEntity();
            entity.setUuid(dto.getUuid());
            entity.setId(dto.getId());
        }

        if (dto != null) {
            entity.setEndDate(dto.getEndDate());
            entity.setHoursDue(dto.getHoursDue());
            entity.setSignedByEmployee(dto.getSignedByEmployee());
            entity.setSignedBySupervisor(dto.getSignedBySupervisor());
            entity.setStartDate(dto.getStartDate());
            entity.setStatus(dto.getStatus());
            entity.setContract(convertToContractEntity(dto.getContract()));

            if (loadEntries && dto.getEntries() != null) {
                Set<TimesheetEntryEntity> timesheetEntryEntityList = new HashSet<TimesheetEntryEntity>();
                Set<TimesheetEntry> entries = dto.getEntries();
                for (TimesheetEntry tse : entries) {
                    TimesheetEntryEntity ent = convertToTimesheetEntryEntity(tse);
                    timesheetEntryEntityList.add(ent);
                }
                entity.setEntries(timesheetEntryEntityList);
            }
        }

        return entity;
    }

    public static TimesheetEntryEntity convertToTimesheetEntryEntity(TimesheetEntry dto) {
        TimesheetEntryEntity entity;
        if (dto == null || dto.getId() == null) {
            entity = TimesheetEntryEntity.newInstance();
        } else {
            entity = new TimesheetEntryEntity();
            entity.setUuid(dto.getUuid());
            entity.setId(dto.getId());
        }

        if (dto != null) {
            entity.setDescription(dto.getDescription());
            entity.setEntryDate(CommonHelper.convertToLocalDate(dto.getEntryDate()));
            entity.setHours(dto.getHours());
            entity.setStartTime(CommonHelper.convertToLocalTime(dto.getStartTime()));
            entity.setEndTime(CommonHelper.convertToLocalTime(dto.getEndTime()));
            entity.setType(dto.getType());
        }
        return entity;
    }

    public static Set<TimesheetEntity> convertToTimesheetEntityList(Set<Timesheet> timesheetDTOSet, boolean loadEntries) {
        Set<TimesheetEntity> timesheetEntityList = new HashSet<TimesheetEntity>(timesheetDTOSet.size());
        for (Timesheet dto : timesheetDTOSet) {
            TimesheetEntity entity = convertToTimesheetEntity(dto, loadEntries);
            timesheetEntityList.add(entity);
        }
        return timesheetEntityList;
    }
}
