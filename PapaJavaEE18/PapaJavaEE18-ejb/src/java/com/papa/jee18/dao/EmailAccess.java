/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.EmailEntity;
import com.papa.jee18.entities.EmailPersonEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class EmailAccess extends BaseEntityAccess<EmailEntity>{

    public EmailAccess() {
        super(EmailEntity.class);
    }
    
    public EmailEntity getEmail(String uuid){
       return getBaseEntityAccessByUuid(uuid);
       
    }
    
    public EmailEntity createEmail(String content,EmailPersonEntity emailPerson) {
        EmailEntity entity = EmailEntity.newInstance();
        entity.setContent(content);
        entity.setEmailPerson(emailPerson);
        
        em.persist(entity);
        return entity;
    }
    
    public List<EmailEntity> getEmailList(Long emailPersonId) {
        try {
            return em.createNamedQuery("EmailEntity.getEntityByEmailPersonId", EmailEntity.class)
                    .setParameter("emailPersonId", emailPersonId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
