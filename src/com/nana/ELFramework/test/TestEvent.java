package com.nana.ELFramework.test;

import com.nana.ELFramework.Event.ELFEvent;

public class TestEvent extends ELFEvent {
    @Override
    public Class<? extends ELFEvent> getEventClass() {
        return TestEvent.class;
    }
    private String str;

    public TestEvent(String str) {
        this.str = str;
    }
    public void print(){
        System.out.println(str);
    }
}
