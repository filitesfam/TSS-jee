/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.timesheet;

import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.entities.TimesheetStatus;
import com.papa.jee18.logic.TimesheetLogic;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@RequestScoped
@Named
public class TimesheetListBean {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    @EJB
    private TimesheetLogic timesheetlogic;

    private List<Timesheet> timesheetList;

    private List<Timesheet> timesheetListInProgress;

    private List<Timesheet> timesheetListSignedBySupervisor;

    private List<Timesheet> timesheetListSignedByEmployee;
    
        private List<Timesheet> timesheetListBelongToEmployee;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public List<Timesheet> getTimesheetList() {
        setTimesheetList(timesheetlogic.getTimesheetList());
        return timesheetList;
    }

    public void setTimesheetList(List<Timesheet> timesheetList) {
        this.timesheetList = timesheetList;
    }

    public List<Timesheet> getTimesheetListInProgress() {
        setTimesheetListInProgress(getTimesheetListByStatus(TimesheetStatus.IN_PROGRESS));
        return timesheetListInProgress;
    }

    public void setTimesheetListInProgress(List<Timesheet> timesheetListInProgress) {
        this.timesheetListInProgress = timesheetListInProgress;
    }

    public List<Timesheet> getTimesheetListSignedBySupervisor() {
        setTimesheetListSignedBySupervisor(getTimesheetListByStatus(TimesheetStatus.SIGNED_BY_SUPERVISOR));
        return timesheetListSignedBySupervisor;
    }

    public void setTimesheetListSignedBySupervisor(List<Timesheet> timesheetListSignedBySupervisor) {
        this.timesheetListSignedBySupervisor = timesheetListSignedBySupervisor;
    }

    public List<Timesheet> getTimesheetListSignedByEmployee() {
        setTimesheetListSignedByEmployee(getTimesheetListByStatus(TimesheetStatus.SIGNED_BY_EMPLOYEE));
        return timesheetListSignedByEmployee;
    }

    public void setTimesheetListSignedByEmployee(List<Timesheet> timesheetListSignedByEmployee) {
        this.timesheetListSignedByEmployee = timesheetListSignedByEmployee;
    }

    public List<Timesheet> getTimesheetListBelongToEmployee() {
        List<Timesheet> list = getTimesheetList();
        List<Timesheet> retVal = new ArrayList<Timesheet>();
        if(list != null && list.size() >0){
            for(Timesheet t: list){
                if(t.isEmployeeRoleBelongsToUser())
                    retVal.add(t);
            }
        }
        setTimesheetListBelongToEmployee(retVal);
        return timesheetListBelongToEmployee;
    }

    public void setTimesheetListBelongToEmployee(List<Timesheet> timesheetListBelongToEmployee) {
        this.timesheetListBelongToEmployee = timesheetListBelongToEmployee;
    }
    
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void logConstruction() {
        System.err.println("TimesheetListBean was created!");
    }

    public void sendTimesheetToSupervisor(Timesheet timesheet) {
        timesheet = timesheetlogic.sendTimesheetToSupervisor(timesheet);
        timesheetList = timesheetlogic.getTimesheetList(); //TODO: Getrid of get operation via returned entity!
    }

    public void claimTimesheet(Timesheet timesheet) {
        timesheet = timesheetlogic.claimTimesheet(timesheet);
        setTimesheetList(timesheetlogic.getTimesheetList()); //TODO: Getrid of get operation via returned entity!
    }

    public void aprroveTimesheet(Timesheet timesheet) {
        timesheet = timesheetlogic.aprroveTimesheet(timesheet);
        setTimesheetList(timesheetlogic.getTimesheetList()); //TODO: Getrid of get operation via returned entity!
    }

    public void sendTimesheetToEmployee(Timesheet timesheet) {
        timesheet = timesheetlogic.sendTimesheetToEmployee(timesheet);
        setTimesheetList(timesheetlogic.getTimesheetList()); //TODO: Getrid of get operation via returned entity!
    }

    public void archiveTimesheet(Timesheet timesheet) {
        timesheet = timesheetlogic.archiveTimesheet(timesheet);
        setTimesheetList(timesheetlogic.getTimesheetList()); //TODO: Getrid of get operation via returned entity!
    }
    
    public List<Timesheet> getTimesheetListByStatus(TimesheetStatus status) {
        List<Timesheet> list = getTimesheetList();
        List<Timesheet> retVal = new ArrayList<Timesheet>();
        if(list != null && list.size() >0){
            for(Timesheet t: list){
                if(t.getStatus().equals(status))
                    retVal.add(t);
            }
        }
        return retVal;
    }
    // </editor-fold>

}
