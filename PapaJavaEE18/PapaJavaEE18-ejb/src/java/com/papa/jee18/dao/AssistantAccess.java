/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.PersonEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class AssistantAccess extends RoleAccess {

    public AssistantAccess() {
        super();
    }

    public List<AssistantEntity> getAssistantList() {
        try {
            return em.createNamedQuery("AssistantEntity.getAssistantList", AssistantEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AssistantEntity createAssistant(PersonEntity person) {
        AssistantEntity se = AssistantEntity.newInstance();
        se.setPerson(person);
        em.persist(se);
        return se;
    }

    public AssistantEntity getAssistantByUUID(String uuid) {
        return (AssistantEntity) getBaseEntityAccessByUuid(uuid);
    }

    public AssistantEntity saveAssistant(AssistantEntity ae, PersonEntity person) {
        if (person != null) {
            ae.setPerson(person);
            em.persist(ae);
        }
        return ae;
    }
    
    public void deleteAssistantRole(AssistantEntity entity){
        this.deleteRole(entity);
    }
}
