/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.timer;


public class TimerEvent {

    private String eventInfo;
    private long time = System.currentTimeMillis();

    public TimerEvent(String s) {
        this.eventInfo = s;
    }

    public long getTime() {
        return time;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    @Override
    public String toString() {
        return "TimerEvent {" +
            "eventInfo='" + eventInfo + '\'' +
            ", time=" + time +
            '}';
    }
}
