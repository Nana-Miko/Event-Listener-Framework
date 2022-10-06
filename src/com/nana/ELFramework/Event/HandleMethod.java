package com.nana.ELFramework.Event;

import com.nana.ELFramework.Listener.ELFListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandleMethod implements Comparable<HandleMethod>{


    private Method method;
    private ELFListener listener;

    public HandleMethod(Method method, ELFListener listener) {
        this.method = method;
        this.listener = listener;
    }

    public Method getMethod() {
        return method;
    }

    public void dealEvent(ELFEvent event){
        EventCallback callback = event.getCallback();

        // TODO 异常检查
        try {
            event.catchListener();
            method.invoke(listener,event);
            if (callback!=null){callback.onSuccess();}
        } catch (IllegalAccessException | InvocationTargetException e) {
            if (callback!=null){callback.onError(e.getMessage());}
        }
    }

    @Override
    public int compareTo(HandleMethod o) {
        EventHandle eventHandle = method.getAnnotation(EventHandle.class);
        EventHandle eventHandle1 = o.getMethod().getAnnotation(EventHandle.class);
        return eventHandle.priority()-eventHandle1.priority();
    }
}
