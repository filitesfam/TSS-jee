/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "TIMESHEETENTRY")
@NamedQueries({
    @NamedQuery(
            name = "TimesheetEntryEntity.getEntityByUUID",
            query = "SELECT tsee FROM TimesheetEntryEntity tsee WHERE tsee.uuid = :uuid"
    ),
    @NamedQuery(
            name = "TimesheetEntryEntity.getEntityByTimesheetUUID",
            query = "SELECT tsee FROM TimesheetEntryEntity tsee "
                    + "JOIN  TimesheetEntity te "
                    + "WHERE tsee.timesheet.id = te.id "
                    + "AND te.uuid = :uuid"
    ),
    @NamedQuery(
            name = "TimesheetEntryEntity.getTimesheetEntryList",
            query = "SELECT tee FROM TimesheetEntryEntity tee"
    ),
    @NamedQuery(
            name = "TimesheetEntryEntity.getEntityByHourAndTimesheet",
            query = "SELECT e FROM TimesheetEntryEntity e "
                    + "WHERE e.timesheet.id = :timesheetId "
                    + "AND e.entryDate = :entrydate "
                    + "AND ( "
                    + "( e.startTime >= :starttime "
                    + "AND e.endTime <= :starttime ) "

                    + "OR (e.startTime >= :endtime "
                    + "AND e.endTime <= :endtime) "
                    
                    + "OR (e.startTime <= :starttime "
                    + "AND e.endTime >= :endtime) ) "
    )
}
)
public class TimesheetEntryEntity extends BaseEntity {

    private static final long serialVersionUID = -6378228844384249241L;
    
    @Enumerated(EnumType.STRING)
    private ReportType type;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "HOURS")
    private double hours;
    
    @Column(name = "STARTTIME")
    private LocalTime startTime;
    
    @Column(name = "ENDTIME")
    private LocalTime endTime;
    
    @Column(name = "ENTRYDATE")
    private LocalDate entryDate;

    @ManyToOne
    private TimesheetEntity timesheet;
    
    public TimesheetEntryEntity() {
        this(false);
    }

    TimesheetEntryEntity(boolean isNew) {
        super(isNew);
    }

    public static TimesheetEntryEntity newInstance() {
        return new TimesheetEntryEntity(true);
    }    

    public TimesheetEntity getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(TimesheetEntity timesheet) {
        this.timesheet = timesheet;
    }  
    
    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
    
            
}
