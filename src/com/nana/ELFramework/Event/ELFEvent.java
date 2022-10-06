package com.nana.ELFramework.Event;

import com.nana.ELFramework.Manager.ELFManagerBuilder;

import java.io.Serializable;

public abstract class ELFEvent implements Serializable {
    public static final int LOWEST = 4;
    public static final int LOW = 3;
    public static final int MIDDLE = 2;
    public static final int HIGH = 1;
    public static final int HIGHEST = 0;
    public static final int URGENT = -1;

    private int priority = MIDDLE;
    private boolean cancel = false;
    private int processing = 0;
    private EventCallback callback = null;

    public void callEvent(){
        Runnable runnable = () -> ELFManagerBuilder.getInstance().callEvent(ELFEvent.this);
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void setEventPriority(int priority){
        this.priority = priority;
    }
    public int getEventPriority(){
        return priority;
    }
    public Boolean isCancelled(){
        return cancel;
    }
    public void cancel(){
        cancel = true;
    }
    public void catchListener(){
        processing++;
    }
    public int processingCount(){
        return processing;
    }

    public EventCallback getCallback() {
        return callback;
    }

    public void setCallback(EventCallback callback) {
        this.callback = callback;
    }

    public abstract Class<?extends ELFEvent>getEventClass();
}
