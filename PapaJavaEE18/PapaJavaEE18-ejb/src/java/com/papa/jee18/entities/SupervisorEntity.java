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
@Table(name = "SupervisorEntity")
@DiscriminatorValue("SupervisorEntity")
@NamedQueries({
    @NamedQuery(
            name = "SupervisorEntity.getSupervisorList",
            query = "SELECT se FROM SupervisorEntity se"
    )
}
)
public class SupervisorEntity extends RoleEntity {

    private static final long serialVersionUID = -8557069677863807473L;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ContractEntity contract;

    public SupervisorEntity() {
        this(false);
    }
    
    public SupervisorEntity(boolean isNew) {
        super(isNew);
    }

    public static SupervisorEntity newInstance() {
        return new SupervisorEntity(true);
    }  
    
    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}
