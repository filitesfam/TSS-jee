/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.logic;

import com.papa.jee18.dto.TimesheetEntry;
import java.util.List;


public interface TimesheetEntryLogic {
    public TimesheetEntry getTimesheetEntry(String uuid);
    public List<TimesheetEntry> getTimesheetEntryList();
    public List<TimesheetEntry> getTimesheetEntryList(String timesheetUuid);
    public void deleteTimesheetEntry(TimesheetEntry dto);
    public void saveTimesheetEntry(TimesheetEntry dto);
    public TimesheetEntry updateTimesheetEntry(TimesheetEntry dto);
}
