/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic;

import com.papa.jee18.dto.Assistant;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Employee;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Secretary;
import com.papa.jee18.dto.Supervisor;
import com.papa.jee18.entities.ContractEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.TimesheetEntity;
import java.util.List;
import java.util.Set;


public interface ContractLogic {

    public List<Contract> getContractList();
    
    public List<Contract> getStartedContractListByRole(boolean loadTimesheets,boolean loadTimesheetEntries);
    
    public List<Contract> getContractListByRole(boolean loadTimesheets,boolean loadTimesheetEntries);
    
    public Contract getContractDetails(String uuid);
    
    public Contract updateContract(Contract c,Person supervisorPerson,List<Person> assistantPersons,List<Person> secretaryPersons);
    
    public Contract startContract(Contract c);
    
    public Contract deleteContract(Contract ce);

    public List<Employee> getEmployeeList();

    public List<Supervisor> getSupervisorList();
    
    public List<Person> getPersonList();

    public ContractEntity getContractEntityByRole(RoleEntity entity);
    
    public Contract terminateContractAndDeleteInProgressTimesheets(Contract c);
    
    public Set<TimesheetEntity> createTimesheetByContract(ContractEntity contract); 

    public Contract saveContract(Contract contractDTO, Person employeePerson, Person supervisorPerson,
            List<Person> selectedAssistantPersons, List<Person> selectedSecretaryPersons);
    
    public List<Contract> getStartedEmployeContractList();

}
