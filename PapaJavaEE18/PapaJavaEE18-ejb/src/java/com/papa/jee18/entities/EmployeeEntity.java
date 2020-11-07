/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "EmployeeEntity")
@DiscriminatorValue("EmployeeEntity")
@NamedQueries({
    @NamedQuery(
            name = "EmployeeEntity.getEntityByUUID",
            query = "SELECT ee FROM EmployeeEntity ee WHERE ee.uuid = :uuid"
    ) 
    ,    
    @NamedQuery(
            name = "EmployeeEntity.getEmployeeList",
            query = "SELECT ee FROM EmployeeEntity ee"
    )
}
)
public class EmployeeEntity extends RoleEntity {
        
    private static final long serialVersionUID = -2155238362743920696L;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ContractEntity contract;

    public EmployeeEntity() {
        this(false);
    }

    public EmployeeEntity(boolean isNew) {
        super(isNew);
    }
    
    public static EmployeeEntity newInstance() {
        return new EmployeeEntity(true);
    } 

    
    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}
