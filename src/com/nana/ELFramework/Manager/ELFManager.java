package com.nana.ELFramework.Manager;

import com.nana.ELFramework.Event.ELFEvent;
import com.nana.ELFramework.Listener.ELFListener;

public interface ELFManager extends Runnable{
    /**
    * @Author: Nana
    * @Params: elfListener ELFListener监听器对象
    * @Return: null
    */
    Boolean registerListener(ELFListener elfListener);
    /**
    * @Author: Nana
    * @Params: event ELFEvent事件对象,此处调用为同步调用,异步请使用ELFEvent中的同名方法
    * @Return: null
    */
    void callEvent(ELFEvent event);

    void onStart();


}
