/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import com.papa.jee18.dto.BaseDTO;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "EMAIL")
@NamedQueries({
    @NamedQuery(
            name = "EmailEntity.getEntityByUUID",
            query = "SELECT e FROM EmailEntity e WHERE e.uuid = :uuid"
    ),
    @NamedQuery(
            name = "EmailEntity.getEntityByEmailPersonId",
            query = "SELECT epe FROM EmailEntity epe "
            + "WHERE epe.emailPerson.id = :emailPersonId"
    )
})
public class EmailEntity extends BaseEntity {

    private static final long serialVersionUID = -9213020230859558312L;
    
    @ManyToOne
    private EmailPersonEntity emailPerson;
    
    @Column(name = "CONTENT")
    private String content;

    public EmailEntity() {
        this(false);
    }
    
    EmailEntity(boolean isNew) {
        super(isNew);
    }

    public static EmailEntity newInstance() {
        return new EmailEntity(true);
    }

    public EmailPersonEntity getEmailPerson() {
        return emailPerson;
    }

    public void setEmailPerson(EmailPersonEntity emailPerson) {
        this.emailPerson = emailPerson;
    }
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
