/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic;

import com.papa.jee18.entities.PersonEntity;


public interface PersonLogic {
    PersonEntity getPerson(String uuid);
    PersonEntity getPersonByEmailAddress(String emailAddress);
}
