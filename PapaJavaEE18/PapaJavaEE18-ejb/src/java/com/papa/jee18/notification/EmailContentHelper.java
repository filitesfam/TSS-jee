/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.notification;

import com.papa.jee18.entities.TimesheetEntity;
import java.io.Serializable;


public class EmailContentHelper implements Serializable {

    private static final long serialVersionUID = -4300863951483191564L;
    
    public static String getToEmailAddressForTest="filaslee.com";

    public static String getEmailContentForEmployeeTimesheetDelay(TimesheetEntity timesheetEntity) {
        String emailContent = "";

        emailContent += "Action : Timesheet should be send to supervisor.";
        emailContent += " ( Timesheet ID : " + timesheetEntity.getId() + "  "
                + timesheetEntity.getStartDate().toString() + " - "
                + timesheetEntity.getEndDate().toString() + " ) ";

        return emailContent;
    }

    public static String getEmailContentForSupervisorTimesheetApproveDelay(TimesheetEntity timesheetEntity) {
        String emailContent = "";

        emailContent += "Action : Timesheet should be approved.";
        emailContent += " ( Timesheet ID : " + timesheetEntity.getId() + "  "
                + timesheetEntity.getStartDate().toString() + " - "
                + timesheetEntity.getEndDate().toString() + " ) ";

        return emailContent;
    }

    public static String getEmailContentForAssistantTimesheetApproveDelay(TimesheetEntity timesheetEntity) {
        String emailContent = "";

        emailContent += "Action : Timesheet should be approved by supervisor.";
        emailContent += " ( Timesheet ID : " + timesheetEntity.getId() + "  "
                + timesheetEntity.getStartDate().toString() + " - "
                + timesheetEntity.getEndDate().toString() + " ) ";
        emailContent += " Supervisor email : "
                + timesheetEntity.getContract().getSupervisor().getPerson().getEmailAddress();

        return emailContent;
    }
}
