/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.ReportType;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;


@Stateless
@LocalBean
public class TimesheetEntryAccess extends BaseEntityAccess<TimesheetEntryEntity> {
    
    public TimesheetEntryAccess() {
        super(TimesheetEntryEntity.class);
    }
    
    public TimesheetEntryEntity getTimesheetEntry(String uuid){
       return getBaseEntityAccessByUuid(uuid);
       
    }
    
    public List<TimesheetEntryEntity> getTimesheetEntryList() {
        try {
            return em.createNamedQuery("TimesheetEntryEntity.getTimesheetEntryList", TimesheetEntryEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public TimesheetEntryEntity saveTimesheetEntry(TimesheetEntryEntity entity,TimesheetEntity  timesheetEntity){
        if(entity != null && entity.getId() == null)
            em.persist(entity);
        
        if(timesheetEntity != null)
            entity.setTimesheet(timesheetEntity);
        
        return entity;
    }
    
    public TimesheetEntryEntity createTimesheetEntry(ReportType reportType,String description,double hours,
    LocalTime startTime,LocalTime endTime,LocalDate entryDate,TimesheetEntity timesheet) {
        TimesheetEntryEntity te = TimesheetEntryEntity.newInstance();
        te.setType(reportType);
        te.setDescription(description);
        te.setHours(hours);
        te.setStartTime(startTime);
        te.setEndTime(endTime);
        te.setEntryDate(entryDate);
        te.setTimesheet(timesheet);
        em.persist(te);
        return te;
    }
    
    public List<TimesheetEntryEntity> getTimesheetEntryList(String timesheetUuid){
        return em.createNamedQuery("TimesheetEntryEntity.getEntityByTimesheetUUID", TimesheetEntryEntity.class)
                .setParameter("uuid", timesheetUuid)
                .getResultList();
    }
    
    public List<TimesheetEntryEntity> getTimesheetEntryList(Long timesheetId,LocalDate entrydate,
           LocalTime starttime ,LocalTime endtime){
        return em.createNamedQuery("TimesheetEntryEntity.getEntityByHourAndTimesheet", TimesheetEntryEntity.class)
                .setParameter("timesheetId", timesheetId)
                .setParameter("entrydate", entrydate)
                .setParameter("starttime", starttime)
                .setParameter("endtime", endtime)
                .getResultList();
    }
    
    
    public Set<TimesheetEntryEntity> getTimesheetEntrySet(String timesheetUuid){
         List<TimesheetEntryEntity> entryList = getTimesheetEntryList(timesheetUuid);
         if(entryList != null && entryList.size() > 0)
            return new HashSet<TimesheetEntryEntity>(entryList);
         return new HashSet<TimesheetEntryEntity>();
    }
    
    public void deleteTimesheetEntry(TimesheetEntryEntity entity) {
        em.remove(entity);
    }
    
    public void updateTimesheetEntry(TimesheetEntryEntity ce) {
        em.merge(ce);
    }
}
