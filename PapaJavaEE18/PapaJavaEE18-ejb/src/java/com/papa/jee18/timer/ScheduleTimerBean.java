/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.timer;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;


@Startup
@Singleton
public class ScheduleTimerBean {

    @Inject
    Event<TimerEvent> event;

    /**
     * This method will be called every day at 23:00 and will fire an @TimerEvent
     */
    @Schedule(dayOfMonth = "*", hour = "23", info = "Every day timer")
    public void fireEvent() {
        event.fire(new TimerEvent("TimerEvent sent at :" + new Date()));
    }
}
