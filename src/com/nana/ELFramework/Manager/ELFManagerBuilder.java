package com.nana.ELFramework.Manager;



public class ELFManagerBuilder {

    private static ELFManager elfManager = null;

    public static void createELFManager(){
        if (elfManager!=null){return;}
        ELFLocalManager elfLocalManager = new ELFLocalManager();
        elfManager = elfLocalManager;

    }
    public static void createELFManager(String host,int port){
        return;
    }

    public static ELFManager getInstance(){
        return elfManager;
    }


}
