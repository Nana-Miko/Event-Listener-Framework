package com.nana.ELFramework.Manager;

import com.nana.ELFramework.Event.ELFEvent;
import com.nana.ELFramework.Event.EventHandle;
import com.nana.ELFramework.Event.HandleMethod;
import com.nana.ELFramework.Listener.ELFListener;

import java.lang.reflect.Method;
import java.util.*;

public abstract class ELFBaseManager implements ELFManager{
    private Queue<ELFEvent>[] eventPriorityList = new Queue[5];
    //protected List<ELFListener>[] listenerPriorityList = new List[5];
    private Map<String,List<HandleMethod>> listenerMap = new HashMap<>();

    public ELFBaseManager() {
        for (int i = 0; i < 5; i++) {
            //listenerPriorityList[i] = new ArrayList<ELFListener>();
            eventPriorityList[i] = new LinkedList<ELFEvent>();
        }
        Thread thread = new Thread(this);
        thread.start();
    }
    protected Queue<ELFEvent> getEventQueue(int priority){
        return eventPriorityList[priority];
    }

    public List<HandleMethod> getHandleMethodList(Class<?extends ELFEvent> clazz){
        return listenerMap.getOrDefault(clazz.getName(), new ArrayList<>());
    }


    @Override
    public Boolean registerListener(ELFListener elfListener) {
        Boolean flag = false;
        Method[] methods = elfListener.getClass().getDeclaredMethods();

        for (Method method :
                methods) {
            if (method.getParameterCount()!=1){continue;}
            EventHandle eventHandle = method.getAnnotation(EventHandle.class);
            if (eventHandle!=null){
                String eventName = method.getParameterTypes()[0].getName();
                if (!listenerMap.containsKey(eventName)){
                    listenerMap.put(eventName,new ArrayList<HandleMethod>());
                }
                List<HandleMethod> list = listenerMap.get(eventName);
                list.add(new HandleMethod(method,elfListener));
                Collections.sort(list);
                flag = true;
            }
        }

        return flag;

    }

    @Override
    public void run() {
        onStart();
    }


}
