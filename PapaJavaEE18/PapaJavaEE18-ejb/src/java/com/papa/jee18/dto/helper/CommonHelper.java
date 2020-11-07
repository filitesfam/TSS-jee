/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.dto.helper;

import com.papa.jee18.dto.Contract;
import com.papa.jee18.dto.Timesheet;
import com.papa.jee18.entities.AssistantEntity;
import com.papa.jee18.entities.ContractStatus;
import com.papa.jee18.entities.EmployeeEntity;
import com.papa.jee18.entities.RoleEntity;
import com.papa.jee18.entities.SecretaryEntity;
import com.papa.jee18.entities.SupervisorEntity;
import com.papa.jee18.entities.TimesheetEntity;
import com.papa.jee18.entities.TimesheetStatus;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class CommonHelper implements Serializable {

    private static final long serialVersionUID = 4402448177465928690L;

    public static Date toDate(LocalTime localTime) {
        Instant instant = localTime.atDate(LocalDate.now())
                .atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date convertToDate(LocalDate localDate) {
        if(localDate != null)
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return null;
    }

    public static LocalDate convertToLocalDate(Date date) {
        if(date != null)
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return null;
    }

    public static LocalTime convertToLocalTime(Date date) {
        return LocalTime.of(date.getHours(), date.getMinutes(), date.getSeconds());
    }

    public static Timesheet setRoles(Timesheet timesheetDTO, Set<RoleEntity> roles) {
        boolean employeeRoleBelongsToUser = false;
        boolean supervisorRoleBelongsToUser = false;
        boolean secretaryRoleBelongsToUser = false;
        boolean assistantRoleBelongsToUser = false;

        for (RoleEntity entity : roles) {
            if (entity instanceof SupervisorEntity) {
                if (((SupervisorEntity) entity).getContract().getUuid()
                        == timesheetDTO.getContract().getUuid()) {
                    supervisorRoleBelongsToUser = true;
                }
            } else if (entity instanceof SecretaryEntity) {
                if (((SecretaryEntity) entity).getContract().getUuid()
                        == timesheetDTO.getContract().getUuid()) {
                    secretaryRoleBelongsToUser = true;
                }
            } else if (entity instanceof EmployeeEntity) {
                if (((EmployeeEntity) entity).getContract().getUuid()
                        == timesheetDTO.getContract().getUuid()) {
                    employeeRoleBelongsToUser = true;
                }
            } else if (entity instanceof AssistantEntity) {
                if (((AssistantEntity) entity).getContract().getUuid()
                        == timesheetDTO.getContract().getUuid()) {
                    assistantRoleBelongsToUser = true;
                }
            }
        }

        timesheetDTO.setAssistantRoleBelongsToUser(assistantRoleBelongsToUser);
        timesheetDTO.setEmployeeRoleBelongsToUser(employeeRoleBelongsToUser);
        timesheetDTO.setSecretaryRoleBelongsToUser(secretaryRoleBelongsToUser);
        timesheetDTO.setSupervisorRoleBelongsToUser(supervisorRoleBelongsToUser);

        timesheetDTO.setCanBeSendedToSupervisor(CommonHelper.timesheetCanBeSendedToSupervisor(timesheetDTO));
        timesheetDTO.setCanBeApproved(CommonHelper.timesheetCanBeApproved(timesheetDTO));
        timesheetDTO.setCanBeClaimed(CommonHelper.timesheetCanBeClaimed(timesheetDTO));
        timesheetDTO.setCanBeSendedToEmployee(CommonHelper.timesheetcanBeSendedToEmployee(timesheetDTO));
        timesheetDTO.setCanBeArchived(CommonHelper.timesheetCanBeArchieved(timesheetDTO));
        timesheetDTO.setEditAuthorized(CommonHelper.isEditTimesheetEntryAuthorized(timesheetDTO));

        return timesheetDTO;
    }

    public static List<Timesheet> setRoles(List<Timesheet> timesheetDTOList, Set<RoleEntity> roles) {
        for (Timesheet dto : timesheetDTOList) {
            setRoles(dto, roles);
        }
        return timesheetDTOList;
    }

    public static Contract setRoles(Contract contractDTO, RoleEntity role) {
        boolean employeeRoleBelongsToUser = false;
        boolean supervisorRoleBelongsToUser = false;
        boolean secretaryRoleBelongsToUser = false;
        boolean assistantRoleBelongsToUser = false;

        if (role instanceof SupervisorEntity) {
            if (((SupervisorEntity) role).getContract().getUuid()
                    == contractDTO.getUuid()) {
                supervisorRoleBelongsToUser = true;
            }
        } else if (role instanceof SecretaryEntity) {
            if (((SecretaryEntity) role).getContract().getUuid()
                    == contractDTO.getUuid()) {
                secretaryRoleBelongsToUser = true;
            }
        } else if (role instanceof EmployeeEntity) {
            if (((EmployeeEntity) role).getContract().getUuid()
                    == contractDTO.getUuid()) {
                employeeRoleBelongsToUser = true;
            }
        } else if (role instanceof AssistantEntity) {
            if (((AssistantEntity) role).getContract().getUuid()
                    == contractDTO.getUuid()) {
                assistantRoleBelongsToUser = true;
            }
        }

        contractDTO.setAssistantRoleBelongsToUser(assistantRoleBelongsToUser);
        contractDTO.setEmployeeRoleBelongsToUser(employeeRoleBelongsToUser);
        contractDTO.setSecretaryRoleBelongsToUser(secretaryRoleBelongsToUser);
        contractDTO.setSupervisorRoleBelongsToUser(supervisorRoleBelongsToUser);

        contractDTO.setCanBeTerminated(CommonHelper.isContractCanBeTerminated(contractDTO));
        contractDTO.setCanBeEdited(CommonHelper.isContractCanBeEdited(contractDTO));

        return contractDTO;
    }

    public static Contract setRoles(Contract contractDTO, Set<RoleEntity> roles) {
        boolean employeeRoleBelongsToUser = false;
        boolean supervisorRoleBelongsToUser = false;
        boolean secretaryRoleBelongsToUser = false;
        boolean assistantRoleBelongsToUser = false;

        for (RoleEntity entity : roles) {
            if (entity instanceof SupervisorEntity) {
                if (((SupervisorEntity) entity).getContract().getUuid()
                        == contractDTO.getUuid()) {
                    supervisorRoleBelongsToUser = true;
                }
            } else if (entity instanceof SecretaryEntity) {
                if (((SecretaryEntity) entity).getContract().getUuid()
                        == contractDTO.getUuid()) {
                    secretaryRoleBelongsToUser = true;
                }
            } else if (entity instanceof EmployeeEntity) {
                if (((EmployeeEntity) entity).getContract().getUuid()
                        == contractDTO.getUuid()) {
                    employeeRoleBelongsToUser = true;
                }
            } else if (entity instanceof AssistantEntity) {
                if (((AssistantEntity) entity).getContract().getUuid()
                        == contractDTO.getUuid()) {
                    assistantRoleBelongsToUser = true;
                }
            }
        }

        contractDTO.setAssistantRoleBelongsToUser(assistantRoleBelongsToUser);
        contractDTO.setEmployeeRoleBelongsToUser(employeeRoleBelongsToUser);
        contractDTO.setSecretaryRoleBelongsToUser(secretaryRoleBelongsToUser);
        contractDTO.setSupervisorRoleBelongsToUser(supervisorRoleBelongsToUser);

        contractDTO.setCanBeTerminated(CommonHelper.isContractCanBeTerminated(contractDTO));
        contractDTO.setCanBeEdited(CommonHelper.isContractCanBeEdited(contractDTO));

        return contractDTO;
    }

    //AR1 - (The TSS SHALL provide secretaries with the ability to archive time sheets that 
    //       are in sta- tus SIGNED_BY_SUPERVISOR. Then, the time sheet changes to status ARCHIVED.)
    public static boolean timesheetCanBeArchieved(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getStatus() == TimesheetStatus.SIGNED_BY_SUPERVISOR
                    && timesheet.isSecretaryRoleBelongsToUser()) {
                return true;
            }
        } else {
            System.err.println("timesheetCanBeArchieved: timesheet is null!");
        }
        return false;
    }

    //SG2 - ( The TSS SHALL provide supervisors with the ability to sign a time sheet 
    //that is in status SIGNED_BY_EMPLOYEE. Then, the time sheet changes to SIGNED_BY_SUPERVISOR status. )
    public static boolean timesheetCanBeApproved(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getStatus() == TimesheetStatus.SIGNED_BY_EMPLOYEE
                    && timesheet.isSupervisorRoleBelongsToUser()) {
                return true;
            }
        } else {
            System.err.println("timesheetCanBeApproved: timesheet is null!");
        }
        return false;
    }

    //SG3 - ( The TSS SHALL provide assistants and supervisors with the ability to request changes 
    //to a time sheet that is in status SIGNED_BY_EMPLOYEE. Then, the time sheet changes 
    //to IN_PROGRESS status. )
    public static boolean timesheetcanBeSendedToEmployee(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getStatus() == TimesheetStatus.SIGNED_BY_EMPLOYEE
                    && (timesheet.isSupervisorRoleBelongsToUser()
                    || timesheet.isAssistantRoleBelongsToUser())) {
                return true;
            }
        } else {
            System.err.println("timesheetcanBeSendedToEmployee: timesheet is null!");
        }
        return false;
    }

    //SG1a - ( The TSS SHALL provide employees with the ability to revoke the signature. 
    //Then, the time sheet changes from SIGNED_BY_EMPLOYEE to IN_PROGRESS status. )
    public static boolean timesheetCanBeClaimed(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getStatus() == TimesheetStatus.SIGNED_BY_EMPLOYEE
                    && timesheet.isEmployeeRoleBelongsToUser()) {
                return true;
            }
        } else {
            System.err.println("timesheetCanBeClaimed: timesheet is null!");
        }
        return false;
    }

    //SG1 - ( The TSS SHALL provide employees with the ability to sign a time sheet. 
    //        Then, the time sheet changes from IN_PROGRESS to SIGNED_BY_EMPLOYEE status. )
    public static boolean timesheetCanBeSendedToSupervisor(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getStatus() == TimesheetStatus.IN_PROGRESS
                    && timesheet.isEmployeeRoleBelongsToUser()) {

                if (timesheet.getEntries() != null
                        && timesheet.getEntries().size() > 0) {
                    return true;
                }
            }
        } else {
            System.err.println("timesheetCanBeSendedToSupervisor: timesheet is null!");
        }
        return false;
    }

    //TS2 - (The TSS SHALL ensure that time sheet entries can only be added, changed, 
    //     and re- moved when the time sheet is in IN_PROGRESS status and the contract of 
    //     the time sheet is in STARTED status.)
    //AR2 - (The TSS SHALL ensure that archived time sheets can not be changed.)
    public static boolean isEditTimesheetEntryAuthorized(Timesheet timesheet) {
        if (timesheet != null) {
            if (timesheet.getContract() != null) {
                if (timesheet.getStatus() == TimesheetStatus.IN_PROGRESS
                        && timesheet.isEmployeeRoleBelongsToUser()
                        && timesheet.getContract().getStatus() == ContractStatus.STARTED) {
                    return true;
                } else {
                    System.err.println("isEditTimesheetEntryAuthorized: timesheet.getContract() is null!");
                }
            }
        } else {
            System.err.println("isEditTimesheetEntryAuthorized: timesheet is null!");
        }
        return false;
    }

    //CN8 - (The TSS SHALL provide assistants and supervisors with the ability to 
    //       terminate a started contract. Then, the contract changes from STARTED to 
    //       ABORTED status.)
    //CN9 - (The TSS SHALL ensure that only contracts can be aborted whose time sheets 
    //       are in sta- tus SIGNED_BY_SUPERVISOR or IN_PROGRESS.)
    public static boolean isContractCanBeTerminated(Contract contract) {
        if (contract != null) {

            if (!contract.isSupervisorRoleBelongsToUser()
                    && !contract.isAssistantRoleBelongsToUser()) {
                return false;
            }

            if (contract.getStatus() != ContractStatus.STARTED) {
                return false;
            }

            if (contract.getTimesheets() == null || contract.getTimesheets().size() <= 0) {
                System.err.println("isContractCanBeTerminated: timesheets are null!");
                return true;
            }

            boolean checkTimesheetsStatus = true;
            for (Timesheet timesheet : contract.getTimesheets()) {
                if (timesheet.getStatus() != TimesheetStatus.IN_PROGRESS
                        && timesheet.getStatus() != TimesheetStatus.SIGNED_BY_SUPERVISOR) {
                    checkTimesheetsStatus = false;
                    break;
                }
            }
            return checkTimesheetsStatus;
        } else {
            System.err.println("isContractCanBeTerminated: contract is null!");
        }
        return false;
    }

    //CN1 - (The TSS SHALL provide assistants and supervisors with the ability to manage
    //       contracts. Manage means „CRUD“ (Create, Read, Update, Delete). 
    //       Update and deletion of con- tracts is only possible in state PREPARED.)
    //CN7 - (The TSS SHALL provide assistants and supervisors with the ability to start 
    //       a contract. Then, the contract changes from PREPARED to STARTED status. 
    //       All Timesheets are created in status IN_PROGRESS.)
    public static boolean isContractCanBeEdited(Contract contract) {
        if (contract != null) {

            if (!contract.isSupervisorRoleBelongsToUser()
                    && !contract.isAssistantRoleBelongsToUser()) {
                return false;
            }

            if (contract.getStatus() != ContractStatus.PREPARED) {
                return false;
            }

            return true;

        } else {
            System.err.println("isContractCanBeEdited: contract is null!");
        }
        return false;
    }

    //CN12 - (The TSS SHALL set a contract to status ARCHIVED as soon as all time sheets of 
    //        that contract are in status ARCHIVED.)
    public static boolean canContractBeArchived(Set<TimesheetEntity> timesheetList, TimesheetEntity archivedTimesheet) {
        if (timesheetList != null && timesheetList.size() > 0
                && archivedTimesheet != null
                && archivedTimesheet.getUuid() != null) {
            boolean canContractArchived = true;
            for (TimesheetEntity timesheetEntity : timesheetList) {
                if (timesheetEntity.getStatus() != TimesheetStatus.ARCHIVED
                        && !timesheetEntity.getUuid().equals(archivedTimesheet.getUuid())) {
                    canContractArchived = false;
                    break;
                }
            }
            return canContractArchived;
        } else {
            return false;
        }
    }
}
