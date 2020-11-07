/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto;

import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.TimesheetFrequency;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Contract extends BaseDTO {

    private static final long serialVersionUID = 4799989666311674648L;
    
    private String name;
    
    private Date startDate; 
    
    private Date endDate;
        
    private Date terminationDate;
    
    private double hoursPerWeek;
    
    private double vacationHours;
    
    private double hoursDue;
    
    private int workingDaysPerWeek;
    
    private int vacationDaysPerYear;
    
    private ContractStatus status;
    
    private Supervisor supervisor;
    
    private Employee employee;
    
    private Set<Secretary> secretaries;
    
    private Set<Assistant> assistants;

    private Set<Timesheet> timesheets;

    private String supervisorName;

    private String employeeName;
    
    private String secretaryName;
    
    private String assistantName;
    
    private TimesheetFrequency frequency;
    
    private boolean canBeTerminated;
    
    private boolean canBeEdited;
    
    private double remainingHoursDue;
    
    private String balance;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getStartDate() {
       return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
    
    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    public double getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(double vacationHours) {
        this.vacationHours = vacationHours;
    }

    public double getHoursDue() {
        return hoursDue;
    }

    public void setHoursDue(double hoursDue) {
        this.hoursDue = hoursDue;
    }

    public int getWorkingDaysPerWeek() {
        return workingDaysPerWeek;
    }

    public void setWorkingDaysPerWeek(int workingDaysPerWeek) {
        this.workingDaysPerWeek = workingDaysPerWeek;
    }

    public int getVacationDaysPerYear() {
        return vacationDaysPerYear;
    }

    public void setVacationDaysPerYear(int vacationDaysPerYear) {
        this.vacationDaysPerYear = vacationDaysPerYear;
    }
    
    public ContractStatus getStatus(){
        return status;
    }
    
    public void setStatus(ContractStatus status ){
        this.status = status;
    }
    
    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
          
    public String getEmployeeName() {
        if (employee == null) {
            return null;
        }
        return employee.getPerson().getFirstName() + " " + employee.getPerson().getLastName();
    }
    
    public String getSupervisorName() {
        if (supervisor == null) {
            return null;
        }
        return supervisor.getPerson().getFirstName() + " " + supervisor.getPerson().getLastName();
    }
    
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    } 
    
    public Set<Secretary> getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(Set<Secretary> secretaries) {
        this.secretaries = secretaries;
    }

    public Set<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<Assistant> assistants) {
        this.assistants = assistants;
    }
    

    public Set<Timesheet> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(Set<Timesheet> timesheets) {
        this.timesheets = timesheets;
    }
        
    
    public String getSecretaryName() {
        if(secretaries !=  null && secretaries.size() > 0)
        {
            Iterator iterator = secretaries.iterator();
            SecretaryEntity sec = (SecretaryEntity)iterator.next();
            return sec.getPerson().getFirstName() + " "+ sec.getPerson().getLastName();
        }
        else
        {
            return "";
        }
    }

    public String getAssistantName() {
        if(assistants !=  null && assistants.size() > 0)
        {
            Iterator iterator = assistants.iterator();
            AssistantEntity ae = (AssistantEntity)iterator.next();
            return ae.getPerson().getFirstName() + " " + ae.getPerson().getLastName();
        }
        else
        {
            return "";
        }
    }
    
    public TimesheetFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TimesheetFrequency frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contract other = (Contract) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return true;
    }

    public boolean isCanBeTerminated() {
        return canBeTerminated;
    }

    public void setCanBeTerminated(boolean canBeTerminated) {
        this.canBeTerminated = canBeTerminated;
    }

    public boolean isCanBeEdited() {
        return canBeEdited;
    }

    public void setCanBeEdited(boolean canBeEdited) {
        this.canBeEdited = canBeEdited;
    }

    public double getRemainingHoursDue() {
        return remainingHoursDue;
    }

    public void setRemainingHoursDue(double remainingHoursDue) {
        this.remainingHoursDue = remainingHoursDue;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    
}
