/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto;

import com.papa.jee18.entities.TimesheetStatus;
import java.time.LocalDate;
import java.util.Set;


public class Timesheet extends BaseDTO {

    private static final long serialVersionUID = -3052686276950519999L;

    private Contract contract;

    private TimesheetStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private double hoursDue;

    private LocalDate signedByEmployee;

    private LocalDate signedBySupervisor;

    private Set<TimesheetEntry> entries;

    private String formattedName;

    private boolean canBeArchived;

    private boolean canBeApproved;

    private boolean canBeSendedToEmployee;

    private boolean canBeClaimed;

    private boolean canBeSendedToSupervisor;

    private boolean editAuthorized;

    public boolean isEditAuthorized() {
        return editAuthorized;
    }

    public void setEditAuthorized(boolean editAuthorized) {
        this.editAuthorized = editAuthorized;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public TimesheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimesheetStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(double hoursDue) {
        this.hoursDue = hoursDue;
    }

    public LocalDate getSignedByEmployee() {
        return signedByEmployee;
    }

    public void setSignedByEmployee(LocalDate signedByEmployee) {
        this.signedByEmployee = signedByEmployee;
    }

    public LocalDate getSignedBySupervisor() {
        return signedBySupervisor;
    }

    public void setSignedBySupervisor(LocalDate signedBySupervisor) {
        this.signedBySupervisor = signedBySupervisor;
    }

    public Set<TimesheetEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<TimesheetEntry> entries) {
        this.entries = entries;
    }

    public String getFormattedName() {
        setFormattedName(getStartDate().toString() + " - " + getEndDate().toString()
                + " ( " + getStatus().name() + " ) ");
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public boolean isCanBeArchived() {
        return canBeArchived;
    }

    public void setCanBeArchived(boolean canBeArchived) {
        this.canBeArchived = canBeArchived;
    }

    public boolean isCanBeApproved() {
        return canBeApproved;
    }

    public void setCanBeApproved(boolean canBeApproved) {
        this.canBeApproved = canBeApproved;
    }

    public boolean isCanBeSendedToEmployee() {
        return canBeSendedToEmployee;
    }

    public void setCanBeSendedToEmployee(boolean canBeSendedToEmployee) {
        this.canBeSendedToEmployee = canBeSendedToEmployee;
    }

    public boolean isCanBeClaimed() {
        return canBeClaimed;
    }

    public void setCanBeClaimed(boolean canBeClaimed) {
        this.canBeClaimed = canBeClaimed;
    }

    public boolean isCanBeSendedToSupervisor() {
        return canBeSendedToSupervisor;
    }

    public void setCanBeSendedToSupervisor(boolean canBeSendedToSupervisor) {
        this.canBeSendedToSupervisor = canBeSendedToSupervisor;
    }

}
