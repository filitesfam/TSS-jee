/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TIMESHEET")
@NamedQueries({
    @NamedQuery(
            name = "TimesheetEntity.getEntityByUUID",
            query = "SELECT tse FROM TimesheetEntity tse WHERE tse.uuid = :uuid"
    )
    ,
    @NamedQuery(
            name = "TimesheetEntity.getTimesheetList",
            query = "SELECT tse FROM TimesheetEntity tse"
    )
    ,
    @NamedQuery(
            name = "TimesheetEntity.getTimesheetListByStatusAndEndDate",
            query = "SELECT tse FROM TimesheetEntity tse "
            + "WHERE tse.status = :status "
            + "AND tse.endDate = :enddate"
    )
}
)
public class TimesheetEntity extends BaseEntity {

    private static final long serialVersionUID = 8019857468111860264L;

    @ManyToOne
    private ContractEntity contract;

    @Enumerated(EnumType.STRING)
    private TimesheetStatus status;

    @Column(name = "STARTDATE")
    private LocalDate startDate;

    @Column(name = "ENDDATE")
    private LocalDate endDate;

    @Column(name = "HOURSDUE")
    private double hoursDue;

    @Column(name = "SIGNEDBYEMPLOYEE")
    private LocalDate signedByEmployee;

    @Column(name = "SIGNEDBYSUPERVISOR")
    private LocalDate signedBySupervisor;

    @OneToMany(mappedBy = "timesheet")
    private Set<TimesheetEntryEntity> entries;

    public TimesheetEntity() {
        this(false);
    }

    TimesheetEntity(boolean isNew) {
        super(isNew);
        if (isNew) {
            entries = new HashSet<>();
        }
    }

    public static TimesheetEntity newInstance() {
        return new TimesheetEntity(true);
    }

    public Set<TimesheetEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(Set<TimesheetEntryEntity> entries) {
        this.entries = entries;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
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

    public PersonEntity getContractEmployee() {
        if (this.contract != null) {
            if (this.contract.getEmployee() != null) {
                if (this.contract.getEmployee().getPerson() != null) {
                    return this.contract.getEmployee().getPerson();
                }
            }
        }
        return null;
    }

    public PersonEntity getContractSupervisor() {
        if (this.contract != null) {
            if (this.contract.getSupervisor() != null) {
                if (this.contract.getSupervisor().getPerson() != null) {
                    return this.contract.getSupervisor().getPerson();
                }
            }
        }
        return null;
    }

    public Set<AssistantEntity> getContractAsistants() {
        if (this.contract != null) {
            if (this.contract.getAssistants() != null && this.contract.getAssistants().size() > 0) {
                if (this.contract.getAssistants() != null) {
                    return this.contract.getAssistants();
                }
            }
        }
        return null;
    }

    public PersonEntity getContractAsistant(String uuid) {
        if (this.contract != null) {
            if (this.contract.getAssistants() != null && this.contract.getAssistants().size() > 0) {
                if (this.contract.getAssistants() != null) {
                    try {
                        Optional<AssistantEntity> assistantEntity
                                = this.contract.getAssistants().stream()
                                        .filter(x -> uuid.equals(x.getPerson().getUuid())).findFirst();

                        return assistantEntity.get().getPerson();
                    } catch (Exception ex) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

}
