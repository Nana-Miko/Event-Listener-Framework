package com.nana.ELFramework.test;

import com.nana.ELFramework.Event.EventCallback;
import com.nana.ELFramework.Manager.ELFManager;
import com.nana.ELFramework.Manager.ELFManagerBuilder;

public class test {
    public static void main(String[] args) {
        ELFManagerBuilder.createELFManager();
        TestEvent event = new TestEvent("你好");
        TestListener testListener = new TestListener();
        ELFManager elfManager = ELFManagerBuilder.getInstance();
        elfManager.registerListener(testListener);


        event.callEvent();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
