/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.EmailPersonEntity;
import com.papa.jee18.entities.EmailStatus;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.TimesheetEntryEntity;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class EmailPersonAccess  extends BaseEntityAccess<EmailPersonEntity>{

    public EmailPersonAccess() {
        super(EmailPersonEntity.class);
    }
    
    public EmailPersonEntity getEmailPerson(String uuid){
       return getBaseEntityAccessByUuid(uuid);
       
    }
    
    public EmailPersonEntity createEmailPerson(String emailAddress,LocalDate insertDate,PersonEntity person) {
        EmailPersonEntity entity = EmailPersonEntity.newInstance();
        entity.setEmailAddress(emailAddress);
        entity.setInsertDate(insertDate);
        entity.setStatus(EmailStatus.WAITING);
        entity.setPerson(person);
        
        em.persist(entity);
        return entity;
    }
    
    public void updateEmailPerson(EmailPersonEntity entity) {
        em.merge(entity);
    }
    
    public EmailPersonEntity getEmailPerson(EmailStatus status, LocalDate insertDate,Long personId) {
        try {
            return em.createNamedQuery("EmailPersonEntity.getEntityByStatusAndInsertDateAndPErsonId", EmailPersonEntity.class)
                    .setParameter("status", status)
                    .setParameter("insertDate", insertDate)
                    .setParameter("personId", personId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<EmailPersonEntity> getWaitingEmailPersonList() {
        try {
            return em.createNamedQuery("EmailPersonEntity.getEntityByStatusAndInsertDate", EmailPersonEntity.class)
                    .setParameter("status", EmailStatus.WAITING)
                    .setParameter("insertDate", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()))
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
