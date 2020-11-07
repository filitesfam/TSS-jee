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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(
            name = "PersonEntity.getEntityByUUID",
            query = "SELECT pe FROM PersonEntity pe WHERE pe.uuid = :uuid"
    )
    ,    
    @NamedQuery(
            name = "PersonEntity.getPersonList",
            query = "SELECT pe FROM PersonEntity pe"
    )
    ,
    @NamedQuery(
            name = "PersonEntity.getPersonByFirstName",
            query = "SELECT pe FROM PersonEntity pe WHERE pe.firstName = :firstName")
    ,
    @NamedQuery(
            name = "PersonEntity.getPersonByEmailAddress",
            query = "SELECT pe FROM PersonEntity pe WHERE pe.emailAddress = :emailAddress")
})
public class PersonEntity extends BaseEntity {

    private static final long serialVersionUID = 5424029969809352198L;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAILADDRESS")
    private String emailAddress;

    @Column(name = "DATEOFBIRTH")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person")
    private Set<RoleEntity> roles;
    
    @OneToMany(mappedBy = "person")
    private Set<EmailPersonEntity> emailPerson;

    public PersonEntity() {
        this(false);
    }

    PersonEntity(boolean isNew) {
        super(isNew);
        if (isNew) {
            roles = new HashSet<>();
        }
    }

    public static PersonEntity newInstance() {
        return new PersonEntity(true);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<EmailPersonEntity> getEmailPerson() {
        return emailPerson;
    }

    public void setEmailPerson(Set<EmailPersonEntity> emailPerson) {
        this.emailPerson = emailPerson;
    }
    
    
}
