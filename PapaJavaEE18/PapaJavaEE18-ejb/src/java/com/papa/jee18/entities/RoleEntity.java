/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE")
@DiscriminatorColumn(name="DTYPE", discriminatorType=DiscriminatorType.STRING)
@NamedQueries({
    @NamedQuery(
            name = "RoleEntity.getEntityByUUID",
            query = "SELECT re FROM RoleEntity re WHERE re.uuid = :uuid"
    )
}
)
public abstract class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = -6473682067180484270L;
    
    @ManyToOne
    private PersonEntity person;
    
    public RoleEntity() {
        this(false);
    }

    public RoleEntity(boolean isNew) {
        super(isNew);
    }
    
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

}
