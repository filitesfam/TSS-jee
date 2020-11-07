/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.PersonEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class PersonAccess extends BaseEntityAccess<PersonEntity> {
    
    public PersonAccess() {
        super(PersonEntity.class);
    }
    
    public PersonEntity getPersonByUUID(String uuid){
       return getBaseEntityAccessByUuid(uuid);
    }
    
    public List<PersonEntity> getPersonList() {
        try {
            return em.createNamedQuery("PersonEntity.getPersonList", PersonEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }    
    
    public PersonEntity getPersonByFirstName(String firstName) {
        try {
            return em.createNamedQuery("PersonEntity.getPersonByFirstName", PersonEntity.class)
                    .setParameter("firstName", firstName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public PersonEntity getPersonByEmailAddress(String emailAddress) {
        try {
            return em.createNamedQuery("PersonEntity.getPersonByEmailAddress", PersonEntity.class)
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public PersonEntity createPerson(String email) {
        PersonEntity pe = PersonEntity.newInstance();
        pe.setEmailAddress(email);
        em.persist(pe);
        return pe;
    }
}
