/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.contract;

import com.papa.jee18.dto.Assistant;
import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Person;
import com.papa.jee18.dto.Secretary;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.TimesheetFrequency;
import com.papa.jee18.i18n.MessageUtils;
import com.papa.jee18.logic.ContractLogic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class ContractDetailsBean implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    private static final long serialVersionUID = 3400180056083107504L;

    @EJB
    private ContractLogic contractLogic;

    private String uuid;

    private Contract contract;

    private boolean editFieldsActive;

    private TimesheetFrequency[] frequencies;

    private double remainingHoursDue;

    private String balance;

    private boolean isContractTerminated;

    private boolean contractStarted;

    private String vacationHoursFormatted;

    private List<Person> supervisorPersons; //table list

    private Person supervisorPerson; //selected

    private List<Person> assistantPersons; //table list

    private String assistants; //selected

    private List<Person> assistantsList; //selected

    private List<Person> secretaryPersons; //table list

    private String secretaries; //selected

    private List<Person> secretaryList; //selected
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isEditFieldsActive() {
        return editFieldsActive;
    }

    public void setEditFieldsActive(boolean editFieldsActive) {
        this.editFieldsActive = editFieldsActive;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public TimesheetFrequency[] getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(TimesheetFrequency[] frequencies) {
        this.frequencies = frequencies;
    }

    public double getRemainingHoursDue() {
        return remainingHoursDue;
    }

    public void setRemainingHoursDue(double remainingHoursDue) {
        this.remainingHoursDue = remainingHoursDue;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isIsContractTerminated() {
        return isContractTerminated;
    }

    public void setIsContractTerminated(boolean isContractTerminated) {
        this.isContractTerminated = isContractTerminated;
    }

    public boolean isContractStarted() {
        return contractStarted;
    }

    public void setContractStarted(boolean contractStarted) {
        this.contractStarted = contractStarted;
    }

    public String getVacationHoursFormatted() {
        return String.format("%.02f", contract.getVacationHours());
    }

    public void setVacationHoursFormatted(String vacationHoursFormatted) {
        this.vacationHoursFormatted = vacationHoursFormatted;
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

    public Person getSupervisorPerson() {
        return supervisorPerson;
    }

    public void setSupervisorPerson(Person supervisorPerson) {
        this.supervisorPerson = supervisorPerson;
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

    public String getAssistants() {
        return assistants;
    }

    public void setAssistants(String assistants) {
        this.assistants = assistants;
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

    public String getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(String secretaries) {
        this.secretaries = secretaries;
    }

    public List<Person> getAssistantsList() {
        return assistantsList;
    }

    public void setAssistantsList(List<Person> assistantsList) {
        this.assistantsList = assistantsList;
    }

    public List<Person> getSecretaryList() {
        return secretaryList;
    }

    public void setSecretaryList(List<Person> secretaryList) {
        this.secretaryList = secretaryList;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void logConstruction() {
        this.editFieldsActive = false;
        System.out.println("ContractDetailsBean was created!");
    }

    public void EditContract() {
        this.editFieldsActive = true;
    }

    public void CancelEdit() {
        this.editFieldsActive = false;
        this.contract = getContract();
    }

    public Contract getContract() {
        try {
            if (contract == null && uuid != null) {
                contract = contractLogic.getContractDetails(uuid);

                if (getSupervisorPersons() != null && getSupervisorPersons().size() > 0) {
                    for (Person person : supervisorPersons) {
                        if (person.getUuid().equals(contract.getSupervisor().getPerson().getUuid())) {
                            this.supervisorPerson = person;
                            break;
                        }
                    }
                }

                if (getAssistantPersons() != null && getAssistantPersons().size() > 0) {
                    if (contract.getAssistants() != null && contract.getAssistants().size() > 0) {
                        assistants = "";
                        for (Assistant as : contract.getAssistants()) {
                            assistants += as.getPerson().getUuid() + ",";
                        }
                    }
                }

                if (getSecretaryPersons() != null && getSecretaryPersons().size() > 0) {
                    if (contract.getSecretaries() != null && contract.getSecretaries().size() > 0) {
                        secretaries = "";
                        for (Secretary as : contract.getSecretaries()) {
                            secretaries += as.getPerson().getUuid() + ",";
                        }
                    }
                }
            }

            SetSelectedFrequency();
            SetContractStatus();

            return contract;
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException bex = (BusinessException) e.getCause();
                String errMsg=bex.getErrorMessage();
                try{
                    errMsg = MessageUtils.getMessage(errMsg);
                }catch(Exception ee){}
                
                FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, errMsg, "")
                );
            } else {
                FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("contractNotFoundMessage"), e.toString())
                );
            }

            this.contract = new Contract();
            return this.contract;
        }
    }

    public void DeleteContract() {
        try {
            this.contract = contractLogic.deleteContract(this.contract);
            this.editFieldsActive = false;
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("deleteSuccess"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("deleteException"), e.toString())
            );
        }
    }

    public void UpdateContract() {
        try {
            fillAssistansList();
            fillSecretaryList();

            this.contract = contractLogic.updateContract(this.contract, supervisorPerson,
                    assistantsList, secretaryList);
            this.editFieldsActive = false;
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("saveSuccess"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
            );
        }
    }

    //SelectMultiMenu works with String
    //Example selected value : 123-45-65er,546-56-3gd54
    private void fillAssistansList() {
        if (assistants != null) {
            if (assistantsList == null) {
                assistantsList = new ArrayList<Person>();
            }
            String[] assistantsUuidArray = assistants.split(",");
            for (String assUuid : assistantsUuidArray) {
                List<Person> assPerList = assistantPersons.stream()
                        .filter(x -> x.getUuid().equals(assUuid))
                        .collect(Collectors.toList());

                assistantsList.addAll(assPerList);
            }
        }
    }

    //SelectMultiMenu works with String
    private void fillSecretaryList() {
        if (secretaries != null) {
            if (secretaryList == null) {
                secretaryList = new ArrayList<Person>();
            }
            String[] secretariesUuidArray = secretaries.split(",");
            for (String secUuid : secretariesUuidArray) {
                List<Person> secPerList = secretaryPersons.stream()
                        .filter(x -> x.getUuid().equals(secUuid))
                        .collect(Collectors.toList());

                secretaryList.addAll(secPerList);
            }
        }
    }

    public void StartContract() {
        try {
            this.contract = contractLogic.startContract(this.contract);
            this.editFieldsActive = false;
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Record successfully Updated", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
            );
        }
    }

    public void TerminateContract() {
        try {
            this.contract = contractLogic.terminateContractAndDeleteInProgressTimesheets(this.contract);
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Record successfully Updated", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("contractDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
            );
        }

    }

    private void SetSelectedFrequency() {
        if (contract.getFrequency().equals(TimesheetFrequency.WEEKLY)) {
            setFrequencies(new TimesheetFrequency[]{TimesheetFrequency.WEEKLY, TimesheetFrequency.MONTHLY});
        } else {
            setFrequencies(new TimesheetFrequency[]{TimesheetFrequency.MONTHLY, TimesheetFrequency.WEEKLY});

        }
    }

    private void SetContractStatus() {
        if (contract.getStatus().equals(ContractStatus.TERMINATED)) {
            setIsContractTerminated(true);
        } else if (contract.getStatus().equals(ContractStatus.STARTED)) {
            setContractStarted(true);
        }
    }

    // </editor-fold>

}
