/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "SecretaryEntity")
@DiscriminatorValue("SecretaryEntity")
@NamedQueries({
    @NamedQuery(
            name = "SecretaryEntity.getEntityByUUID",
            query = "SELECT se FROM SecretaryEntity se WHERE se.uuid = :uuid"
    ) 
    ,
    @NamedQuery(
            name = "SecretaryEntity.getSecretaryList",
            query = "SELECT ae FROM SecretaryEntity ae"
    )
}
)
public class SecretaryEntity extends RoleEntity {

    private static final long serialVersionUID = -4765291153521272102L;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private ContractEntity contract;

    public SecretaryEntity() {
        this(false);
    }
    
    public SecretaryEntity(boolean isNew) {
        super(isNew);
    }

    public static SecretaryEntity newInstance() {
        return new SecretaryEntity(true);
    }    
    
    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}
