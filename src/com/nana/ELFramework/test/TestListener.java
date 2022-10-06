package com.nana.ELFramework.test;

import com.nana.ELFramework.Event.EventHandle;
import com.nana.ELFramework.Listener.ELFListener;

public class TestListener implements ELFListener {

    @EventHandle(priority = EventHandle.HIGHEST)
    public void dealEvent(TestEvent event){
        System.out.println("第一个");
        event.print();
        System.out.println(event.getEventPriority());
        System.out.println(event.processingCount());
    }
    @EventHandle(priority = EventHandle.LOW)
    public void test(TestEvent event){
        System.out.println("第二个");
        event.print();
        System.out.println(event.processingCount());
    }
}
