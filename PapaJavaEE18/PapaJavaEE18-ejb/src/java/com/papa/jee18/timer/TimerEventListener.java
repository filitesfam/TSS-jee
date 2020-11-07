/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.timer;

import com.papa.jee18.logic.TimesheetLogic;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;

/**
 * This class will listen to all TimerEvent and will collect them
 *
 * 
 */
@Startup
@Singleton
public class TimerEventListener {

    @EJB
    private TimesheetLogic tl;
    
    @Resource
    private EJBContext ejbContext;

    final List<TimerEvent> events = new CopyOnWriteArrayList<>();

    public void listen(@Observes TimerEvent event) {
        System.out.println("event = " + event);
        try {
            tl.insertEmailsForEmployee();
            tl.insertEmailsForSupervisorAndAssistant();
            tl.sendEmail();
        } catch (Exception ex) {
            System.err.println("TimerEventListener error " + ex.toString());
        }
        events.add(event);
    }

    public List<TimerEvent> getEvents() {
        return events;
    }
}
