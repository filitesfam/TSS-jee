/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.contract;

import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Person;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.i18n.MessageUtils;
import com.papa.jee18.logic.ContractLogic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class CreateContractBean implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    private static final long serialVersionUID = 7334933466112512752L;

    @EJB
    private ContractLogic contractLogic;

    private Contract contract;

    private Person employeePerson;  //selected

    private Person supervisorPerson; //selected

    private List<Person> selectedAssistantPersons; //selected

    private Person selectedAssistantPerson;
    
    private Person selectedSecretaryPerson;  //selected
    
    private List<Person> selectedSecretaryPersons;  //selected

    private List<Person> employeePersons; //table list

    private List<Person> supervisorPersons; //table list

    private List<Person> assistantPersons; //table list

    private List<Person> secretaryPersons; //table list

    private boolean editFieldsActive;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Person> getEmployeePersons() {
        if (employeePersons == null) {
            employeePersons = contractLogic.getPersonList();
        }
        return employeePersons;
    }

    public void setEmployeePersons(List<Person> employeePersons) {
        this.employeePersons = employeePersons;
    }

    public Person getSelectedAssistantPerson() {
        return selectedAssistantPerson;
    }

    public void setSelectedAssistantPerson(Person selectedAssistantPerson) {
        this.selectedAssistantPerson = selectedAssistantPerson;
    }
    
    

    public List<Person> getSupervisorPersons() {
        if (supervisorPersons == null) {
            supervisorPersons = contractLogic.getPersonList();
        }
        return supervisorPersons;
    }

    public void setSupervisorPersons(List<Person> supervisorPersons) {
        this.supervisorPersons = supervisorPersons;
    }

    public Person getEmployeePerson() {
        return employeePerson;
    }

    public void setEmployeePerson(Person employeePerson) {
        this.employeePerson = employeePerson;
    }

    public Person getSupervisorPerson() {
        return supervisorPerson;
    }

    public void setSupervisorPerson(Person supervisorPerson) {
        this.supervisorPerson = supervisorPerson;
    }

    public boolean isEditFieldsActive() {
        return editFieldsActive;
    }

    public Person getSelectedSecretaryPerson() {
        return selectedSecretaryPerson;
    }

    public void setSelectedSecretaryPerson(Person selectedSecretaryPerson) {
        this.selectedSecretaryPerson = selectedSecretaryPerson;
    }
    
    

    public void setEditFieldsActive(boolean editFieldsActive) {
        this.editFieldsActive = editFieldsActive;
    }

    public List<Person> getAssistantPersons() {
        if (assistantPersons == null) {
            assistantPersons = contractLogic.getPersonList();
        }
        return assistantPersons;
    }

    public void setAssistantPersons(List<Person> assistantPersons) {
        this.assistantPersons = assistantPersons;
    }

    public List<Person> getSelectedAssistantPersons() {
        return selectedAssistantPersons;
    }

    public void setSelectedAssistantPersons(List<Person> selectedAssistantPersons) {
        this.selectedAssistantPersons = selectedAssistantPersons;
    }

    public List<Person> getSecretaryPersons() {
        if (secretaryPersons == null) {
            secretaryPersons = contractLogic.getPersonList();
        }
        return secretaryPersons;
    }

    public void setSecretaryPersons(List<Person> secretaryPersons) {
        this.secretaryPersons = secretaryPersons;
    }

    public List<Person> getSelectedSecretaryPersons() {
        return selectedSecretaryPersons;
    }

    public void setSelectedSecretaryPersons(List<Person> selectedSecretaryPersons) {
        this.selectedSecretaryPersons = selectedSecretaryPersons;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void init() {
        initCreateContractForm();
    }

    public void SaveContract() {
        try {
            
            selectedSecretaryPersons = new ArrayList<Person>();
            selectedSecretaryPersons.add(selectedSecretaryPerson);
            
            selectedAssistantPersons = new ArrayList<Person>();
            selectedAssistantPersons.add(selectedAssistantPerson);
            
            contractLogic.saveContract(contract, employeePerson, supervisorPerson, selectedAssistantPersons, selectedSecretaryPersons);
            this.editFieldsActive = false;
            FacesContext.getCurrentInstance().addMessage("createContractForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("saveSuccess"), "")
            );
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException bex = (BusinessException) e.getCause();
                String errMsg=bex.getErrorMessage();
                try{
                    errMsg = MessageUtils.getMessage(errMsg);
                }catch(Exception ee){}
                
                FacesContext.getCurrentInstance().addMessage("createContractForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, errMsg, "")
                );
            } else {
                FacesContext.getCurrentInstance().addMessage("createContractForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
                );
            }
        }
    }

    public void onSelectEmployee(Person person) {
        this.employeePerson = person;
    }

    public void onSelectSupervisor(Person person) {
        this.supervisorPerson = person;
    }

    public void onSelectAssistant(Person person) {
        this.selectedAssistantPersons.add(person);
    }

    public void onSelectSecretary(Person person) {
        this.selectedSecretaryPersons.add(person);
    }

    public void clearContractForm() {
        initCreateContractForm();
    }

    public void initCreateContractForm() {
        contract = new Contract();
        contract.setStatus(ContractStatus.PREPARED);
        employeePerson = new Person();
        supervisorPerson = new Person();
        selectedAssistantPersons = new ArrayList<Person>();
        selectedSecretaryPersons = new ArrayList<Person>();

        this.editFieldsActive = true;
    }
    // </editor-fold>

}
