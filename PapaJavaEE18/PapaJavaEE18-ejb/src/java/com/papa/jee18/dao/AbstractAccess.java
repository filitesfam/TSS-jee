/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractAccess {
    @PersistenceContext(unitName = "PapaJavaEE18-ejbPU")
    protected EntityManager em;
}
