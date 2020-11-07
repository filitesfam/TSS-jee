/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.timesheet;

import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.logic.TimesheetLogic;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class TimesheetEntryBean implements Serializable{

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    private static final long serialVersionUID = -4765124635250764194L;
    
    @EJB
    private TimesheetLogic timesheetlogic;
    
    private Timesheet timesheet;
    
    private String uuid;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timesheet getTimesheet() {
        if (uuid != null) {
            if(timesheet == null)
                setTimesheet(timesheetlogic.getTimesheet(uuid));
        }
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void logConstruction() {
        System.err.println("TimesheetEntryBean was created!");
    }
    
    public void sendTimesheetToSupervisor(Timesheet timesheet){
        this.timesheet = timesheetlogic.sendTimesheetToSupervisor(timesheet);
    }
    
    public void claimTimesheet(Timesheet timesheet){
        this.timesheet = timesheetlogic.claimTimesheet(timesheet);
    }
    public void aprroveTimesheet(Timesheet timesheet){
        this.timesheet = timesheetlogic.aprroveTimesheet(timesheet);
    }
    
    public void sendTimesheetToEmployee(Timesheet timesheet){
        this.timesheet = timesheetlogic.sendTimesheetToEmployee(timesheet);
    }
    
    public void archiveTimesheet(Timesheet timesheet){
        this.timesheet = timesheetlogic.archiveTimesheet(timesheet);
    }
    // </editor-fold>
    
}
