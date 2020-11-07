/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic.impl;

import com.papa.jee18.dao.PersonAccess;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.logic.PersonLogic;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class PersonLogicImplementation implements PersonLogic {

    @EJB
    private PersonAccess pa;
    
    @Override
    public PersonEntity getPerson(String uuid) {
        return pa.getPersonByUUID(uuid);
    }

    @Override
    public PersonEntity getPersonByEmailAddress(String emailAddress) {
        return pa.getPersonByEmailAddress(emailAddress);
    }
    
}
