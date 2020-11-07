/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CONTRACT")
@NamedQueries({
    @NamedQuery(
            name = "ContractEntity.getContractList",
            query = "SELECT ce FROM ContractEntity ce"
    )
    ,
    @NamedQuery(
            name = "ContractEntity.getEntityByUUID",
            query = "SELECT ce FROM ContractEntity ce WHERE ce.uuid = :uuid"
    )
})
public class ContractEntity extends BaseEntity {

    private static final long serialVersionUID = -4842757610783056683L;
    
    @OneToMany(mappedBy = "contract")
    private Set<TimesheetEntity> timesheets;
    
    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private EmployeeEntity employee;
    
    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL)
    private SupervisorEntity supervisor;
    
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)  
    private Set<SecretaryEntity> secretaries;  
    
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)  
    private Set<AssistantEntity> assistants;
    
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "STARTDATE")
    private LocalDate startDate;
    
    @Column(name = "ENDDATE")
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    private TimesheetFrequency frequency;
    
    @Column(name = "TERMINATIONDATE")
    private LocalDate terminationDate;
    
    @Column(name = "HOURSPERWEEK")
    private double hoursPerWeek;
    
    @Column(name = "VACATIONHOURS")
    private double vacationHours;
    
    @Column(name = "HOURSDUE")
    private double hoursDue;
    
    @Column(name = "WORKINGDAYSPERWEEK")
    private int workingDaysPerWeek;
    
    @Column(name = "VACATIONDAYSPERYEAR")
    private int vacationDaysPerYear;
    
    public ContractEntity() {
        this(false);
    }

    ContractEntity(boolean isNew) {
        super(isNew);
        if (isNew) {
            timesheets = new HashSet<>();
        }
    }

    public static ContractEntity newInstance() {
        return new ContractEntity(true);
    }
    
    public Set<TimesheetEntity> getTimesheets() {
        return timesheets;
    }
    
    public void setTimesheets(Set<TimesheetEntity> timesheets) {
        this.timesheets = timesheets;
    }
    
    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimesheetFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TimesheetFrequency frequency) {
        this.frequency = frequency;
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
    
    public SupervisorEntity getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SupervisorEntity supervisor) {
        this.supervisor = supervisor;
    }

    public Set<SecretaryEntity> getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(Set<SecretaryEntity> secretaries) {
        this.secretaries = secretaries;
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

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    
    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
    
    public Set<AssistantEntity> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<AssistantEntity> assistants) {
        this.assistants = assistants;
    }
    
}
