/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetStatus;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class TimesheetAccess extends BaseEntityAccess<TimesheetEntity> {

    public TimesheetAccess() {
        super(TimesheetEntity.class);
    }

    public TimesheetEntity getTimesheet(String uuid) {
        return getBaseEntityAccessByUuid(uuid);

    }

    public List<TimesheetEntity> getTimesheetList() {
        try {
            return em.createNamedQuery("TimesheetEntity.getTimesheetList", TimesheetEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TimesheetEntity> getTimesheetList(TimesheetStatus status, LocalDate enddate) {
        try {
            return em.createNamedQuery("TimesheetEntity.getTimesheetListByStatusAndEndDate", TimesheetEntity.class)
                    .setParameter("status", status)
                    .setParameter("enddate", enddate)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public TimesheetEntity createTimesheet(LocalDate startDate, LocalDate endDate, double hoursDue, ContractEntity contract) {
        TimesheetEntity te = TimesheetEntity.newInstance();
        te.setStatus(TimesheetStatus.IN_PROGRESS);
        te.setStartDate(startDate);
        te.setEndDate(endDate);
        te.setHoursDue(hoursDue);
        te.setContract(contract);
        em.persist(te);
        return te;
    }

    public TimesheetEntity updateTimesheet(TimesheetEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public void deleteTimesheet(TimesheetEntity entity) {
        em.remove(entity);
    }
}
