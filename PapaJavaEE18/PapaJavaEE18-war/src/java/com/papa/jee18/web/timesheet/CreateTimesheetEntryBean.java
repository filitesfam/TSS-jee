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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class CreateTimesheetEntryBean implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    private static final long serialVersionUID = -2596787557980137560L;

    @EJB
    private ContractLogic contractLogic;

    @EJB
    private TimesheetEntryLogic timesheetEntryLogic;

    @EJB
    private TimesheetLogic timesheetLogic;

    private List<Contract> contractList;

    private TimesheetEntry timesheetEntry;

    private Contract contract;

    private List<Timesheet> timesheetList;

    private Set<ReportType> reportTypeList;

    private boolean timesheetDisabled;

    private boolean hoursDisabled;

    private boolean descriptionDisabled;

    private boolean reportTypeDisabled;

    private List<TimesheetEntry> timesheetEntryList;

    private LocalTime now;

    private boolean isSaveButtonsActive;

    private boolean isClearButtonsActive;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public TimesheetEntry getTimesheetEntry() {
        if (this.timesheetEntry == null) {
            TimesheetEntry dto = new TimesheetEntry();
            dto.setDefaultValuesToTimes();
            setTimesheetEntry(dto);
        }

        return timesheetEntry;
    }

    public void setTimesheetEntry(TimesheetEntry timesheetEntry) {
        this.timesheetEntry = timesheetEntry;
    }

    public Contract getContract() {
        if (contract == null) {
            setContract(new Contract());
        }
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Timesheet> getTimesheetList() {
        return timesheetList;
    }

    public void setTimesheetList(List<Timesheet> timesheetList) {
        this.timesheetList = timesheetList;
    }

    public boolean isTimesheetDisabled() {
        return timesheetDisabled;
    }

    public void setTimesheetDisabled(boolean timesheetDisabled) {
        this.timesheetDisabled = timesheetDisabled;
    }

    public Set<ReportType> getReportTypeList() {
        return reportTypeList;
    }

    public void setReportTypeList(Set<ReportType> reportTypeList) {
        this.reportTypeList = reportTypeList;
    }

    public boolean isDescriptionDisabled() {
        return descriptionDisabled;
    }

    public void setDescriptionDisabled(boolean descriptionDisabled) {
        this.descriptionDisabled = descriptionDisabled;
    }

    public boolean isHoursDisabled() {
        return hoursDisabled;
    }

    public void setHoursDisabled(boolean hoursDisabled) {
        this.hoursDisabled = hoursDisabled;
    }

    public boolean isReportTypeDisabled() {
        return reportTypeDisabled;
    }

    public void setReportTypeDisabled(boolean reportTypeDisabled) {
        this.reportTypeDisabled = reportTypeDisabled;
    }

    public List<TimesheetEntry> getTimesheetEntryList() {
        return timesheetEntryList;
    }

    public void setTimesheetEntryList(List<TimesheetEntry> timesheetEntryList) {
        this.timesheetEntryList = timesheetEntryList;
    }

    public LocalTime getNow() {
        return now;
    }

    public void setNow(LocalTime now) {
        this.now = now;
    }

    public boolean isIsSaveButtonsActive() {
        return isSaveButtonsActive;
    }

    public void setIsSaveButtonsActive(boolean isSaveButtonsActive) {
        this.isSaveButtonsActive = isSaveButtonsActive;
    }

    public boolean isIsClearButtonsActive() {
        return isClearButtonsActive;
    }

    public void setIsClearButtonsActive(boolean isClearButtonsActive) {
        this.isClearButtonsActive = isClearButtonsActive;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Methods">
    public CreateTimesheetEntryBean() {

    }

    @PostConstruct
    public void init() {
        this.contractList = contractLogic.getStartedEmployeContractList();

        if (contractList.size() > 0) {
            setIsSaveButtonsActive(true);
            setIsClearButtonsActive(true);
        }

        this.timesheetList = new ArrayList<Timesheet>();
        this.timesheetDisabled = true;
        this.hoursDisabled = true;
        this.reportTypeDisabled = true;
        this.setDescriptionDisabled(true);
        this.reportTypeList = new HashSet<ReportType>();
        this.reportTypeList.add(ReportType.WORK);
        this.reportTypeList.add(ReportType.VACATION);
        this.reportTypeList.add(ReportType.SICK_LEAVE);
        this.now = LocalTime.now();

        timesheetEntryList = new ArrayList<TimesheetEntry>();
    }

    public void saveTimesheetEntry() {
        try {
            timesheetEntryLogic.saveTimesheetEntry(timesheetEntry);
            setIsSaveButtonsActive(false);
            setFieldDisabled(true);
            refreshTimesheetEntryList();
            FacesContext.getCurrentInstance().addMessage("createTimesheetEntryForm",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("saveSuccess"), "")
            );
        } catch (Exception e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException bex = (BusinessException) e.getCause();
                String errMsg=bex.getErrorMessage();
                try{
                    errMsg = MessageUtils.getMessage(errMsg);
                }catch(Exception ee){}
                
                FacesContext.getCurrentInstance().addMessage("createTimesheetEntryForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, errMsg, "")
                );
            } else {
                FacesContext.getCurrentInstance().addMessage("createTimesheetEntryForm",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getMessage("saveException"), e.toString())
                );
            }
        }
    }

    public void clearEntryForm() {
        setContract(null);
        setTimesheetEntry(null);
        refreshTimesheetEntryList();
        setIsSaveButtonsActive(true);
        setFieldDisabled(true);
    }

    public final void contractListenerEvent(final AjaxBehaviorEvent event) {
        System.out.println(this.contract);
        this.timesheetList.clear();
        this.timesheetList = timesheetLogic.getEditableTimesheetList(this.contract.getUuid());
        this.setTimesheetDisabled(false);
        this.timesheetEntryList.clear();
    }

    public final void timesheetListenerEvent(final AjaxBehaviorEvent event) {
        try {
            refreshTimesheetEntryList();
            if (this.timesheetEntry.getTimesheet().equals("NONE")) {
                setFieldDisabled(true);
                return;
            }
            setFieldDisabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFieldDisabled(boolean isDisabled) {
        this.setTimesheetDisabled(isDisabled);
        this.setHoursDisabled(isDisabled);
        this.setDescriptionDisabled(isDisabled);
        this.setReportTypeDisabled(isDisabled);
    }

    private void refreshTimesheetEntryList() {
        if (getTimesheetEntry().getTimesheet() != null
                && getTimesheetEntry().getTimesheet().getUuid() != null) {

            List<TimesheetEntry> _timesheetEntryList = timesheetEntryLogic.getTimesheetEntryList(this.timesheetEntry.getTimesheet().getUuid());
            if (_timesheetEntryList == null) {
                setTimesheetEntryList(new ArrayList<TimesheetEntry>());
            } else {
                setTimesheetEntryList(_timesheetEntryList);
            }

        } else {
            setTimesheetEntryList(new ArrayList<TimesheetEntry>());
        }

    }

    public void edit(TimesheetEntry timesheetEntry) {
        this.timesheetEntry = timesheetEntry;
        this.contract = timesheetEntry.getTimesheet().getContract();
    }
    // </editor-fold>

}
