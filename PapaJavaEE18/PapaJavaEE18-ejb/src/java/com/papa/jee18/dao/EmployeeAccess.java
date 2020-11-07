/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@LocalBean
public class EmployeeAccess extends RoleAccess {

    public EmployeeAccess() {
        super();
    }
    
    public EmployeeEntity createEmployee(PersonEntity person) {
        EmployeeEntity se = EmployeeEntity.newInstance();
        se.setPerson(person);
        em.persist(se);
        return se;
    }
    
    public List<EmployeeEntity> getEmployeeList() {
        try {
            return em.createNamedQuery("EmployeeEntity.getEmployeeList", EmployeeEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public EmployeeEntity getEmployeeByUUID(String uuid){
        return (EmployeeEntity)getBaseEntityAccessByUuid(uuid);
    }
    
    public EmployeeEntity saveEmployee(EmployeeEntity ee, PersonEntity person){
        if(person != null){
           ee.setPerson(person);
           em.persist(ee);
        }
        return ee;
    }
    
    public void deleteEmployeeRole(EmployeeEntity entity){
        this.deleteRole(entity);
    }
}
