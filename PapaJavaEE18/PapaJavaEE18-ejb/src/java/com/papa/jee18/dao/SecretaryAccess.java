/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.SecretaryEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class SecretaryAccess extends RoleAccess {

    public SecretaryAccess() {
        super();
    }

    public List<SecretaryEntity> getAssistantList() {
        try {
            return em.createNamedQuery("SecretaryEntity.getSecretaryList", SecretaryEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public SecretaryEntity createSecretary(PersonEntity person) {
        SecretaryEntity se = SecretaryEntity.newInstance();
        se.setPerson(person);
        em.persist(se);
        return se;
    }
    
    public SecretaryEntity getSecretaryByUUID(String uuid){
        return (SecretaryEntity)getBaseEntityAccessByUuid(uuid);
    }    
    
    public void deleteSecretaryRole(SecretaryEntity entity){
        this.deleteRole(entity);
    }
    
    public SecretaryEntity saveSecretary(SecretaryEntity ee, PersonEntity person){
        if(person != null){
           ee.setPerson(person);
           em.persist(ee);
        }
        return ee;
    }
}
