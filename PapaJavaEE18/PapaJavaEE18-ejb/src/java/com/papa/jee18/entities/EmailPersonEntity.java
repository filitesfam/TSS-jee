/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import java.time.LocalDate;
import java.util.HashSet;
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
@Table(name = "EMAILPERSON")
@NamedQueries({
    @NamedQuery(
            name = "EmailPersonEntity.getEntityByUUID",
            query = "SELECT epe FROM EmailPersonEntity epe WHERE epe.uuid = :uuid"
    ),
    @NamedQuery(
            name = "EmailPersonEntity.getEntityByStatusAndInsertDateAndPErsonId",
            query = "SELECT epe FROM EmailPersonEntity epe "
            + "WHERE epe.status = :status "
            + "AND epe.insertDate = :insertDate "
            + "AND epe.person.id = :personId"
    ),
    @NamedQuery(
            name = "EmailPersonEntity.getEntityByStatusAndInsertDate",
            query = "SELECT epe FROM EmailPersonEntity epe "
            + "WHERE epe.status = :status "
            + "AND epe.insertDate = :insertDate "
    )
})
public class EmailPersonEntity extends BaseEntity {

    private static final long serialVersionUID = 8914688259937648333L;

    @OneToMany(mappedBy = "emailPerson")
    private Set<EmailEntity> emails;
    
    @ManyToOne
    private PersonEntity person;
    
    @Enumerated(EnumType.STRING)
    private EmailStatus status;
    
    @Column(name = "EMAILADDRESS")
    private String emailAddress;
    
    @Column(name = "INSERTDATE")
    private LocalDate insertDate;
    
    @Column(name = "SENDDATE")
    private LocalDate sendDate;
    
    public EmailPersonEntity() {
        this(false);
    }
    
    EmailPersonEntity(boolean isNew) {
        super(isNew);
        if (isNew) {
            emails = new HashSet<>();
        }
    }

    public static EmailPersonEntity newInstance() {
        return new EmailPersonEntity(true);
    }

    public Set<EmailEntity> getEmails() {
        return emails;
    }

    public void setEmails(Set<EmailEntity> emails) {
        this.emails = emails;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    
}
