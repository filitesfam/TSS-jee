/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.web.contract;

import com.papa.jee18.dto.Contract;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.logic.ContractLogic;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;


@RequestScoped
@Named
public class ContractListBean {

    // <editor-fold defaultstate="collapsed" desc=" Prop">
    @EJB
    private ContractLogic contractLogic;

    private List<Contract> contractList;

    private List<Contract> contractListPrepared;

    private List<Contract> contractListByDateCriteria;
    
        private List<Contract> contractListBelongToEmployee;

    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc=" Getter and Setter">
    public List<Contract> getContractList() {
        setContractList(contractLogic.getContractListByRole(false, false));
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public List<Contract> getContractListPrepared() {
        setContractListPrepared(getContractListByRole(ContractStatus.PREPARED));
        return contractListPrepared;
    }

    public void setContractListPrepared(List<Contract> contractListPrepared) {
        this.contractListPrepared = contractListPrepared;
    }

    public List<Contract> getContractListByDateCriteria() {
        setContractListByDateCriteria(getContractListByRole(LocalDate.now().minusDays(1), LocalDate.now().plusDays(6)));
        return contractListByDateCriteria;
    }

    public void setContractListByDateCriteria(List<Contract> contractListByDateCriteria) {
        this.contractListByDateCriteria = contractListByDateCriteria;
    }

    public List<Contract> getContractListBelongToEmployee() {
        List<Contract> list = getContractList();
        List<Contract> retVal = new ArrayList<Contract>();
        if(list != null && list.size() >0){
            for(Contract t: list){
                if(t.isEmployeeRoleBelongsToUser())
                    retVal.add(t);
            }
        }
        setContractListBelongToEmployee(retVal);
        return contractListBelongToEmployee;
    }

    public void setContractListBelongToEmployee(List<Contract> contractListBelongToEmployee) {
        this.contractListBelongToEmployee = contractListBelongToEmployee;
    }
    
    

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Methods">
    @PostConstruct
    public void logConstruction() {
        System.err.println("ContractListBean was created!");
    }

    private List<Contract> getContractListByRole(ContractStatus contractStatus) {
        List<Contract> list = getContractList();

        List<Contract> retVal = new ArrayList<Contract>();
        if (list != null && list.size() > 0) {
            for (Contract c : list) {
                if (contractStatus == null) {
                    retVal.add(c);
                } else if (contractStatus.equals(c.getStatus())) {
                    retVal.add(c);
                }
            }
        }

        return retVal;
    }

    private List<Contract> getContractListByRole(LocalDate startEndDate, LocalDate endEndDate) {
        List<Contract> list = getContractList();

        List<Contract> retVal = new ArrayList<Contract>();
        if (list != null && list.size() > 0) {
            for (Contract c : list) {
                if (startEndDate != null && endEndDate != null) {
                    if (convert(c.getEndDate()).isAfter(startEndDate) && convert(c.getEndDate()).isBefore(endEndDate)) {
                        retVal.add(c);
                    }
                }

            }
        }

        return retVal;
    }
    public LocalDate convert(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    // </editor-fold>
}
