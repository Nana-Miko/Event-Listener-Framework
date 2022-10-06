package com.nana.ELFramework.Manager;


import com.nana.ELFramework.Event.ELFEvent;
import com.nana.ELFramework.Event.HandleMethod;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ELFLocalManager extends ELFBaseManager{



    @Override
    public void callEvent(ELFEvent event) {
        Runnable runnable = () -> {
            if (event.getEventPriority()<=ELFEvent.URGENT){
                for (HandleMethod hm :
                        getHandleMethodList(event.getEventClass())) {
                    hm.dealEvent(event);
                }
            }
            else{
                getEventQueue(event.getEventPriority()).add(event);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }

    @Override
    public void onStart() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            LinkedList<ELFEvent> eventQueue = (LinkedList<ELFEvent>) getEventQueue(i);
            if (eventQueue.isEmpty()){continue;}

            while (true){
                ELFEvent event = eventQueue.poll();
                if (event==null){break;}
                Runnable runnable = () -> {
                    for (HandleMethod hm :
                            getHandleMethodList(event.getEventClass())) {
                        hm.dealEvent(event);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }



        }
    }
}
