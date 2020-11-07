/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.BaseEntity;
import javax.persistence.NoResultException;

/**
 *
 * 
 * @param <T> generic parameter
 */
public class BaseEntityAccess<T extends BaseEntity> extends AbstractAccess {

    protected Class<T> entityClass;

    public BaseEntityAccess(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected T getBaseEntityAccessById(long id) {
        return em.find(entityClass, id);
    }

    protected T getBaseEntityAccessByUuid(String uuid) {
        try {
            return em.createNamedQuery(entityClass.getSimpleName() + ".getEntityByUUID", entityClass)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}