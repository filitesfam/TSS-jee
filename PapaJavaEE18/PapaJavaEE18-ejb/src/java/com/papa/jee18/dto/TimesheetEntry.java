/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto;

import com.papa.jee18.dto.helper.CommonHelper;
import com.papa.jee18.entities.ReportType;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class TimesheetEntry extends BaseDTO{

    private static final long serialVersionUID = 8960725071930097511L;
    
    private ReportType type;
    
    private String description;
    
    private double hours;
    
    private Date startTime;
    
    private Date endTime;
    
    private Date entryDate;

    private Timesheet timesheet;
    
    private LocalDate localEntryDate;
    
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

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public LocalDate getLocalEntryDate() {
        return CommonHelper.convertToLocalDate(this.entryDate);
    }

    public void setLocalEntryDate(LocalDate localEntryDate) {
        this.localEntryDate = localEntryDate;
    }
    
    public void setDefaultValuesToTimes(){
        setStartTime(setTime(java.sql.Date.valueOf(LocalDate.MIN),9));
        setEndTime(setTime(java.sql.Date.valueOf(LocalDate.MIN),17));
    }
    
    private Date setTime(Date date,int hour) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, hour);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime();
    }
}
