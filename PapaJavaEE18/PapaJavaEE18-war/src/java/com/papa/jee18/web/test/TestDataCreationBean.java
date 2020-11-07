/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.test;

import com.papa.jee18.logic.TestDataCreationLogic;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@RequestScoped
@Named
public class TestDataCreationBean {

    @EJB
    private TestDataCreationLogic tdcl;

    @PostConstruct
    public void logConstruction() {
        System.err.println("TestDataCreationBean was created!");
    }

    public void createPersons() {
        tdcl.createPersons();
    }

    public void createHolidays() {
        tdcl.createHolidays();
    }
}
