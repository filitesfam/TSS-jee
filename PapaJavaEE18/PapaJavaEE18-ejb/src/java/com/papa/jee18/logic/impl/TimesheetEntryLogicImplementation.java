/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic.impl;

import com.papa.jee18.dao.ContractAccess;
import com.papa.jee18.dao.HolidayAccess;
import com.papa.jee18.dao.HolidayCountry;
import com.papa.jee18.dao.HolidayRegion;
import com.papa.jee18.dao.PersonAccess;
import com.papa.jee18.dao.TimesheetEntryAccess;
import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.TimesheetEntry;
import com.papa.jee18.dto.helper.CommonHelper;
import com.papa.jee18.dto.helper.ConvertToDTOHelper;
import com.papa.jee18.dto.helper.ConvertToEntityHelper;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.HolidayEntity;
import com.papa.jee18.entities.HolidayType;
import com.papa.jee18.entities.LanguageType;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.ReportType;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import com.papa.jee18.logic.TimesheetEntryLogic;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;


@Stateless
public class TimesheetEntryLogicImplementation implements TimesheetEntryLogic {

    @Resource
    private EJBContext ejbContext;

    @EJB
    private PersonAccess pa;

    @EJB
    private ContractAccess ca;

    @EJB
    private TimesheetEntryAccess tea;

    @EJB
    private HolidayAccess ha;

    private PersonEntity caller;

    private Set<RoleEntity> _roles;

    public Set<RoleEntity> getRoles() {
        setCaller();
        setRoles(caller.getRoles());
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
    public List<TimesheetEntry> getTimesheetEntryList() {
        System.err.println("CallerPrincipal: " + ejbContext.getCallerPrincipal().getName());
        List<TimesheetEntryEntity> timesheetentries = tea.getTimesheetEntryList();
        List<TimesheetEntry> timesheetentryDTOList = new ArrayList<TimesheetEntry>(timesheetentries.size());
        for (TimesheetEntryEntity entity : timesheetentries) {
            TimesheetEntry dto = ConvertToDTOHelper.convertToDTO(entity);
            timesheetentryDTOList.add(dto);
        }
        return timesheetentryDTOList;
    }

    @Override
    public List<TimesheetEntry> getTimesheetEntryList(String timesheetUuid) {
        List<TimesheetEntryEntity> timesheetentries = tea.getTimesheetEntryList(timesheetUuid);

        if (timesheetentries != null && timesheetentries.size() > 0) {

            List<TimesheetEntry> timesheetentryDTOList = new ArrayList<TimesheetEntry>(timesheetentries.size());
            for (TimesheetEntryEntity entity : timesheetentries) {
                TimesheetEntry dto = ConvertToDTOHelper.convertToDTO(entity);

                CommonHelper.setRoles(dto.getTimesheet(), getRoles());

                timesheetentryDTOList.add(dto);
            }
            return timesheetentryDTOList;
        } else {
            return new ArrayList<TimesheetEntry>();
        }
    }

    @Override
    public void deleteTimesheetEntry(TimesheetEntry dto) {
        TimesheetEntryEntity entity = tea.getTimesheetEntry(dto.getUuid());
        tea.deleteTimesheetEntry(entity);
    }

    @Override
    public void saveTimesheetEntry(TimesheetEntry dto) {
        checkEntry(dto);

        TimesheetEntryEntity entity = ConvertToEntityHelper.convertToTimesheetEntryEntity(dto);
        TimesheetEntity timesheetEntity = ConvertToEntityHelper.convertToTimesheetEntity(dto.getTimesheet(), false);
        entity.setHours((dto.getEndTime().getTime() - dto.getStartTime().getTime()) / 3600000);

        tea.saveTimesheetEntry(entity, timesheetEntity);
    }

    private void checkEntry(TimesheetEntry dto) {

        if (dto.getTimesheet() == null || dto.getTimesheet().getUuid() == null) {
            throw new BusinessException("busExTimesheetEmpty");
        }

        if (dto.getType() == null) {
            throw new BusinessException("busExReportTypeEmpty");
        }

        checkTimesheetEntryDate(dto);
        checkTimesheetEntryHours(dto);
        checkTimesheetEntryDescription(dto);

        checkPublicHolidayForTimesheetEntry(dto);
        checkVacationHoursExceedance(dto);
    }

    private void checkTimesheetEntryDate(TimesheetEntry dto) {
        if (dto.getEntryDate() == null) {
            throw new BusinessException("busExEntryDateEmpty");
        }

        LocalDate timesheetEntryDate = CommonHelper.convertToLocalDate(dto.getEntryDate());
        LocalDate timesheetStartDate = dto.getTimesheet().getStartDate();
        LocalDate timesheetEndDate = dto.getTimesheet().getEndDate();

        if (timesheetEntryDate.isAfter(timesheetEndDate)
                || timesheetEntryDate.isBefore(timesheetStartDate)) {
            throw new BusinessException("busExEntryDateWrong");
        }
    }

    private void checkTimesheetEntryHours(TimesheetEntry dto) {

        if (dto.getEndTime().equals(dto.getStartTime())
                || dto.getEndTime().before(dto.getStartTime())) {
            throw new BusinessException("busExEndTimeWrong");
        }

        double entryTimeDifference = (dto.getEndTime().getTime() - dto.getStartTime().getTime()) / 3600000;
        if (entryTimeDifference > 8) {
            throw new BusinessException("busExTimeDiffWrong");
        }

        List<TimesheetEntryEntity> entryList = tea.getTimesheetEntryList(dto.getTimesheet().getId(),
                CommonHelper.convertToLocalDate(dto.getEntryDate()),
                CommonHelper.convertToLocalTime(dto.getStartTime()),
                CommonHelper.convertToLocalTime(dto.getEndTime()));
        if (entryList != null && entryList.size() > 0) {
            throw new BusinessException("busExSameEntryEntered");
        }
    }

    private void checkTimesheetEntryDescription(TimesheetEntry dto) {
        if (dto.getDescription().isEmpty()) {
            throw new BusinessException("busExDescriptionEmpty");
        }
    }

    @Override
    public TimesheetEntry getTimesheetEntry(String uuid) {
        TimesheetEntryEntity entity = tea.getTimesheetEntry(uuid);

        if (entity == null) {
            throw new BusinessException("busExTimesheetEntryNotFound");
        }

        TimesheetEntry dto = ConvertToDTOHelper.convertToDTO(entity);
        CommonHelper.setRoles(dto.getTimesheet(), getRoles());

        return dto;
    }

    @Override
    public TimesheetEntry updateTimesheetEntry(TimesheetEntry dto) {
        if (dto == null) {
            throw new BusinessException("busExTimesheetEntryNotFound");
        }

        TimesheetEntryEntity entity = tea.getTimesheetEntry(dto.getUuid());

        if (entity == null) {
            throw new BusinessException("busExTimesheetEntryNotFound");
        }

        checkEntry(dto);

        entity = ConvertToEntityHelper.convertToTimesheetEntryEntity(dto);
        entity.setHours((dto.getEndTime().getTime() - dto.getStartTime().getTime()) / 3600000);

        tea.updateTimesheetEntry(entity);

        dto.setHours(entity.getHours());

        return dto;
    }

    //TS3 - (The TSS SHALL ensure that the total hours reported in entries of type VACATION 
    //       can not exceed the total vacation hours (see requirement CN4a).)
    private void checkVacationHoursExceedance(TimesheetEntry dto) {
        if (dto.getType() == ReportType.VACATION) {
            double reportedTotalVacationHours = (dto.getEndTime().getTime() - dto.getStartTime().getTime()) / 3600000;
            double maxVacationHours = dto.getTimesheet().getContract().getVacationDaysPerYear() * 8;

            ContractEntity ce = ca.getContract(dto.getTimesheet().getContract().getUuid());

            for (TimesheetEntity timesheetEntity : ce.getTimesheets()) {
                List<TimesheetEntry> entryList = getTimesheetEntryList(timesheetEntity.getUuid());
                for (TimesheetEntry entry : entryList) {
                    if (entry.getType() == ReportType.VACATION
                            && entry.getUuid() != dto.getUuid()) //Covers TimesheetEntry Update
                    {
                        reportedTotalVacationHours = reportedTotalVacationHours + entry.getHours();
                    }
                }
            }

            if (reportedTotalVacationHours > maxVacationHours) {
                throw new BusinessException("busExVacationHoursExceeded");
            }

        }
    }

    //CN4d - (The TSS SHALL be able to determine the public holidays in Rhineland-Palatinate 
    //        starting from January 01 2018 until December 31 2028 (at least).)
    private void checkPublicHolidayForTimesheetEntry(TimesheetEntry dto) {

        HolidayEntity holidayEntity = ha.getHoliday(dto.getLocalEntryDate(), LanguageType.EN, HolidayType.PUBLIC,
                HolidayCountry.deu, HolidayRegion.rp);
        if (holidayEntity != null) {
            throw new BusinessException("busExPublicHolidayEntry");
        }
    }
}
