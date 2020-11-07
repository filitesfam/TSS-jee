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
@Table(name = "AssistantEntity")
@DiscriminatorValue("AssistantEntity")
@NamedQueries({
    @NamedQuery(
            name = "AssistantEntity.getEntityByUUID",
            query = "SELECT ae FROM AssistantEntity ae WHERE ae.uuid = :uuid"
    ) 
    ,
    @NamedQuery(
            name = "AssistantEntity.getAssistantList",
            query = "SELECT ae FROM AssistantEntity ae"
    )
}
)
public class AssistantEntity extends RoleEntity {

    private static final long serialVersionUID = -7536655246917923511L;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private ContractEntity contract;

    public AssistantEntity() {
        this(false);
    }

    public AssistantEntity(boolean isNew) {
        super(isNew);
    }    
    
    public static AssistantEntity newInstance() {
        return new AssistantEntity(true);
    } 
    
    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}
