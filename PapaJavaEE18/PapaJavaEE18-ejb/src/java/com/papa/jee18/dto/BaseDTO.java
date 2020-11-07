/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto;

import java.io.Serializable;


public class BaseDTO implements Serializable{

    private static final long serialVersionUID = -3677108214882950681L;
    
    protected String uuid;

    protected Long id;
    
    protected boolean employeeRoleBelongsToUser;
    
    protected boolean supervisorRoleBelongsToUser;
    
    protected boolean secretaryRoleBelongsToUser;
    
    protected boolean assistantRoleBelongsToUser;
    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEmployeeRoleBelongsToUser() {
        return employeeRoleBelongsToUser;
    }

    public void setEmployeeRoleBelongsToUser(boolean employeeRoleBelongsToUser) {
        this.employeeRoleBelongsToUser = employeeRoleBelongsToUser;
    }

    public boolean isSupervisorRoleBelongsToUser() {
        return supervisorRoleBelongsToUser;
    }

    public void setSupervisorRoleBelongsToUser(boolean supervisorRoleBelongsToUser) {
        this.supervisorRoleBelongsToUser = supervisorRoleBelongsToUser;
    }

    public boolean isSecretaryRoleBelongsToUser() {
        return secretaryRoleBelongsToUser;
    }

    public void setSecretaryRoleBelongsToUser(boolean secretaryRoleBelongsToUser) {
        this.secretaryRoleBelongsToUser = secretaryRoleBelongsToUser;
    }

    public boolean isAssistantRoleBelongsToUser() {
        return assistantRoleBelongsToUser;
    }

    public void setAssistantRoleBelongsToUser(boolean assistantRoleBelongsToUser) {
        this.assistantRoleBelongsToUser = assistantRoleBelongsToUser;
    }
    
    
}
