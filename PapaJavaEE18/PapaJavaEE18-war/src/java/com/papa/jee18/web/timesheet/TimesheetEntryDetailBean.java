/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.timesheet;

import com.papa.jee18.dto.BusinessException;
import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.dto.TimesheetEntry;
import com.papa.jee18.entities.ReportType;
import com.papa.jee18.i18n.MessageUtils;
import com.papa.jee18.logic.ContractLogic;
import com.papa.jee18.logic.TimesheetEntryLogic;
import com.papa.jee18.logic.TimesheetLogic;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class TimesheetEntryDetailBean implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    private static final long serialVersionUID = 1599747670367916004L;

    @EJB
    private TimesheetEntryLogic timesheetEntryLogic;

    @EJB
    private ContractLogic contractLogic;

    @EJB
    private TimesheetLogic timesheetLogic;

    private String uuid;

    private TimesheetEntry timesheetEntry;

    private List<Contract> contractList;

    private Contract contract;

    private boolean editFieldsActive;

    private Set<ReportType> reportTypeList;

    private List<Timesheet> timesheetList;

    private Timesheet timesheet;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public TimesheetEntry getTimesheetEntry() {
        try {
            if (this.timesheetEntry == null) {
                if (this.uuid != null) {
                    this.timesheetEntry = timesheetEntryLogic.getTimesheetEntry(uuid);
                    if (this.timesheetEntry != null) {
                        for (Contract entity : contractList) {
                            if (entity.getUuid().equalsIgnoreCase(timesheetEntry.getTimesheet().getContract().getUuid())) {
                                this.contract = entity;
                                this.timesheetList = timesheetLogic.getTimesheetList(this.contract.getUuid());
                                for (Timesheet tEntity : timesheetList) {
                                    if (tEntity.getUuid().equalsIgnoreCase(timesheetEntry.getTimesheet().getUuid())) {
                                        this.timesheet = tEntity;
                                    }
                                }
                                break;
                            }
                        }

                    }
                }

                if (this.timesheetEntry == null) {
                    this.timesheetEntry = new TimesheetEntry();
                }
            }
            return timesheetEntry;
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException bex = (BusinessException) e.getCause();
                String errMsg=bex.getErrorMessage();
                try{
                    errMsg = MessageUtils.getMessage(errMsg);
                }catch(Exception ee){}
                FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, errMsg, "")
                );
            } else {
                FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
                );
            }
            
            this.timesheetEntry = new TimesheetEntry();
            return timesheetEntry;
        }
    }

    public void setTimesheetEntry(TimesheetEntry timesheetEntry) {
        this.timesheetEntry = timesheetEntry;
    }

    public boolean isEditFieldsActive() {
        return editFieldsActive;
    }

    public void setEditFieldsActive(boolean editFieldsActive) {
        this.editFieldsActive = editFieldsActive;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<ReportType> getReportTypeList() {
        return reportTypeList;
    }

    public void setReportTypeList(Set<ReportType> reportTypeList) {
        this.reportTypeList = reportTypeList;
    }

    public List<Timesheet> getTimesheetList() {
        return timesheetList;
    }

    public void setTimesheetList(List<Timesheet> timesheetList) {
        this.timesheetList = timesheetList;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void logConstruction() {
        this.editFieldsActive = false;
        System.out.println("TimesheetEntryDetailBean was created!");

        this.contractList = contractLogic.getContractList(); //TODO : sadece kendı cont. getir
        this.reportTypeList = new HashSet<ReportType>();
        this.reportTypeList.add(ReportType.WORK);
        this.reportTypeList.add(ReportType.VACATION);
        this.reportTypeList.add(ReportType.SICK_LEAVE);
    }

    public void EditTimesheetEntry() {
        this.editFieldsActive = true;
    }

    public void CancelEdit() {
        this.editFieldsActive = false;
        this.timesheetEntry = getTimesheetEntry();
    }

    public void DeleteTimesheetEntry() {
        try {
            timesheetEntryLogic.deleteTimesheetEntry(this.timesheetEntry);
            this.editFieldsActive = false;
            FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("deleteSuccess"), ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("deleteException"), e.toString())
            );
        }
    }

    public void UpdateTimesheetEntry() {
        try {
            timesheetEntryLogic.updateTimesheetEntry(this.timesheetEntry);
            setEditFieldsActive(false);
            FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("saveSuccess"), ""));
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException bex = (BusinessException) e.getCause();
                String errMsg=bex.getErrorMessage();
                try{
                    errMsg = MessageUtils.getMessage(errMsg);
                }catch(Exception ee){}
                
                FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, errMsg, "")
                );
            } else {
                FacesContext.getCurrentInstance().addMessage("timesheetEntryDetailForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
                );
            }
        }
    }
    // </editor-fold>    

}
