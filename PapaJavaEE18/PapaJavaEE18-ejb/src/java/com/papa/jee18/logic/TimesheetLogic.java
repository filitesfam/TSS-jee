/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic;

import com.papa.jee18.dto.Timesheet;
import java.util.List;


public interface TimesheetLogic {

    public Timesheet getTimesheet(String uuid);

    public List<Timesheet> getTimesheetList();

    public Timesheet sendTimesheetToSupervisor(Timesheet timesheet); //by Employee

    public Timesheet claimTimesheet(Timesheet timesheet); //by Employee

    public Timesheet aprroveTimesheet(Timesheet timesheet); //by Supervisor

    public Timesheet sendTimesheetToEmployee(Timesheet timesheet); //by Supervisor or Assistant

    public Timesheet archiveTimesheet(Timesheet timesheet);

    public List<Timesheet> getEditableTimesheetList(String contractUuid);

    public List<Timesheet> getTimesheetList(String contractUuid);

    //Reminder
    public void insertEmailsForEmployee();

    public void insertEmailsForSupervisorAndAssistant();

    public void insertEmailsForSecretary();

    public void sendEmail();
}
