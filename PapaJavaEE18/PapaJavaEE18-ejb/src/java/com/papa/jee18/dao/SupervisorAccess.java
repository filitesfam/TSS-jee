/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.SupervisorEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class SupervisorAccess extends RoleAccess {

    public SupervisorAccess() {
        super();
    }

    public SupervisorEntity createSupervisor(PersonEntity person) {
        SupervisorEntity se = SupervisorEntity.newInstance();
        se.setPerson(person);
        em.persist(se);
        return se;
    }

    public List<SupervisorEntity> getSupervisorList() {
        try {
            return em.createNamedQuery("SupervisorEntity.getSupervisorList", SupervisorEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public SupervisorEntity getSupervisorByUUID(String uuid) {
        return (SupervisorEntity) getBaseEntityAccessByUuid(uuid);
    }

    public SupervisorEntity saveSupervisor(SupervisorEntity se, PersonEntity person) {
        if(person != null){
            se.setPerson(person);
            em.persist(se);
        }
        return se;
    }
    
    public void deleteSupervisorRole(SupervisorEntity entity){
        this.deleteRole(entity);
    }
}
