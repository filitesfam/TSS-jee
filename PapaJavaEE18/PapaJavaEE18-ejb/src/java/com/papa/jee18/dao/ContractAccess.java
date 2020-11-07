/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dao;

import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.PersonEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.SupervisorEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;


@Stateless
@LocalBean
public class ContractAccess extends BaseEntityAccess<ContractEntity> {

    @EJB
    private SupervisorAccess sa;

    @EJB
    private AssistantAccess aa;

    @EJB
    private SecretaryAccess sea;

    @EJB
    private PersonAccess pa;

    public ContractAccess() {
        super(ContractEntity.class);
    }

    public List<ContractEntity> getContractList() {
        try {
            return em.createNamedQuery("ContractEntity.getContractList", ContractEntity.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ContractEntity createContract() {
        ContractEntity ce = ContractEntity.newInstance();
        em.persist(ce);
        return ce;
    }

    public ContractEntity saveContract(ContractEntity ce, EmployeeEntity ee, SupervisorEntity se,
            List<AssistantEntity> ae, List<SecretaryEntity> sce) {
        if (ce != null && ce.getId() == null) {
            em.persist(ce);
        }
        if (ee != null) {
            ce.setEmployee(ee);
            ee.setContract(ce);
        }
        if (se != null) {
            ce.setSupervisor(se);
            se.setContract(ce);
        }

        if (ae != null && ae.size() > 0) {
            Set<AssistantEntity> asSet = ce.getAssistants();
            if (asSet == null) {
                asSet = new HashSet<AssistantEntity>();
            }
            for (AssistantEntity entityAs : ae) {
                asSet.add(entityAs);
            }

            ce.setAssistants(asSet);
            for (AssistantEntity entityAs : ae) {
                entityAs.setContract(ce);
            }
        }

        if (sce != null && sce.size() > 0) {
            Set<SecretaryEntity> secSet = ce.getSecretaries();
            if (secSet == null) {
                secSet = new HashSet<SecretaryEntity>();
            }
            for (SecretaryEntity entitySec : sce) {
                secSet.add(entitySec);
            }

            ce.setSecretaries(secSet);
            for (SecretaryEntity entitySec : sce) {
                entitySec.setContract(ce);
            }
        }

        return ce;
    }

    public void updateContract(ContractEntity contractEntity, SupervisorEntity se,
            Set<PersonEntity> aeList, Set<PersonEntity> seList) {
        if (contractEntity == null || contractEntity.getUuid() == null) {
            throw new BusinessException("updateContract:invalid contract");
        }

        em.merge(contractEntity);

        updateSupervisor(contractEntity, se);
        updateAssistans(contractEntity, aeList);
        updateSecretaries(contractEntity, seList);

        se.setContract(contractEntity);
    }

    public void updateContract(ContractEntity entity) {
        if (entity == null || entity.getUuid() == null) {
            throw new BusinessException("updateContract:invalid contract");
        }

        em.merge(entity);
        // TODO : Can we check this case with 1 contract entity?  Dinçer ?
        //if(newContract.getStatus() == ContractStatus.STARTED && oldContract.getStatus() == ContractStatus.PREPARED)
        //{
        //ta.createTimesheetByContract(c);
        //}
    }

    public void deleteContract(ContractEntity ce) {
        em.remove(ce);
    }

    public ContractEntity getContract(String uuid) {
        // return getBaseEntityAccessByUuid(uuid); 
        return getBaseEntityAccessByUuid(uuid);

    }

    private void updateAssistans(ContractEntity contractEntity, Set<PersonEntity> aeList) {

        Set<AssistantEntity> currentAsSet = contractEntity.getAssistants();

        if (currentAsSet != null && currentAsSet.size() > 0) {

            contractEntity.setAssistants(null);
            for (AssistantEntity currentAs : currentAsSet) {
                PersonEntity pe = pa.getPersonByUUID(currentAs.getPerson().getUuid());
                Set<RoleEntity> rr = pe.getRoles();
                if (rr != null && rr.size() > 0) {
                    rr.remove(currentAs);
                    pe.setRoles(rr);
                }

                currentAs.setContract(null);
                currentAs.setPerson(null);
                aa.deleteAssistantRole(currentAs);
            }
        }

        if (aeList != null && aeList.size() > 0) {
            contractEntity.setAssistants(new HashSet<AssistantEntity>());
            for (PersonEntity person : aeList) {
                AssistantEntity asEntity = AssistantEntity.newInstance();
                aa.saveAssistant(asEntity, person);
                asEntity.setContract(contractEntity);

                Set<AssistantEntity> aaa = contractEntity.getAssistants();
                aaa.add(asEntity);
                contractEntity.setAssistants(aaa);

                Set<RoleEntity> rr = person.getRoles();
                if (rr != null && rr.size() > 0) {
                    rr.add(asEntity);
                    person.setRoles(rr);
                } else {
                    Set<RoleEntity> roles = new HashSet<RoleEntity>();
                    roles.add(asEntity);
                    person.setRoles(roles);
                }
            }

        }
    }

    private void updateSecretaries(ContractEntity contractEntity, Set<PersonEntity> seList) {

        Set<SecretaryEntity> currentAsSet = contractEntity.getSecretaries();

        if (currentAsSet != null && currentAsSet.size() > 0) {

            contractEntity.setSecretaries(null);
            for (SecretaryEntity currentAs : currentAsSet) {
                PersonEntity pe = pa.getPersonByUUID(currentAs.getPerson().getUuid());
                Set<RoleEntity> rr = pe.getRoles();
                if (rr != null && rr.size() > 0) {
                    rr.remove(currentAs);
                    pe.setRoles(rr);
                }

                currentAs.setContract(null);
                currentAs.setPerson(null);
                sea.deleteSecretaryRole(currentAs);
            }
        }

        if (seList != null && seList.size() > 0) {

            contractEntity.setSecretaries(new HashSet<SecretaryEntity>());
            for (PersonEntity person : seList) {
                SecretaryEntity asEntity = SecretaryEntity.newInstance();
                sea.saveSecretary(asEntity, person);
                asEntity.setContract(contractEntity);

                Set<SecretaryEntity> aaa = contractEntity.getSecretaries();
                aaa.add(asEntity);
                contractEntity.setSecretaries(aaa);

                Set<RoleEntity> rr = person.getRoles();
                if (rr != null && rr.size() > 0) {
                    rr.add(asEntity);
                    person.setRoles(rr);
                } else {
                    Set<RoleEntity> roles = new HashSet<RoleEntity>();
                    roles.add(asEntity);
                    person.setRoles(roles);
                }
            }

        }

    }

    private void updateSupervisor(ContractEntity contractEntity, SupervisorEntity se) {
        if (se != null) {
            if (!contractEntity.getSupervisor().getPerson().getUuid().equals(se.getPerson().getUuid())) {
                SupervisorEntity oldSe = contractEntity.getSupervisor();
                contractEntity.setSupervisor(se);
                se.setContract(contractEntity);

                PersonEntity pe = pa.getPersonByUUID(oldSe.getPerson().getUuid());
                Set<RoleEntity> roleSet = pe.getRoles();
                Set<RoleEntity> newRoleSet = new HashSet<>();
                if (roleSet != null && roleSet.size() > 0) {
                    for (RoleEntity role : roleSet) {
                        if (!role.getUuid().equals(oldSe.getUuid())) {
                            newRoleSet.add(role);
                        }
                    }

                    pe.setRoles(newRoleSet);
                }

                oldSe.setContract(null);
                oldSe.setPerson(null);
                sa.deleteSupervisorRole(oldSe);
            }
        }
    }

}
