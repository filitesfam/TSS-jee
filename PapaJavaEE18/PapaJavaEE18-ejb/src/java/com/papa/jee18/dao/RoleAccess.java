/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.entities.RoleEntity;
import javax.ejb.LocalBean;


@LocalBean
public class RoleAccess extends BaseEntityAccess<RoleEntity> {

    public RoleAccess() {
        super(RoleEntity.class);
    }
    
    protected void deleteRole(RoleEntity entity){
        em.remove(entity);
    }
       
}
