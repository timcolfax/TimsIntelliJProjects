package org.colfax.test.junk;

import java.util.ArrayList;

/**
 * The CharServer serves characters to clients who call getChar().
 * It possesses its OWN thread which reads configuration information
 * from the console, allowing the admin to change its behavior at runtime.
 */
public class ThreadExp02_CharServer extends Thread implements ThreadExp02_StateChangeListener{

    private ThreadExp02_AdminFlags             flag         = ThreadExp02_AdminFlags.MODE_A;
    private ArrayList<ThreadExp02_StateSource> stateSources = new ArrayList<ThreadExp02_StateSource>();

    public void addStateSource(ThreadExp02_StateSource source){
        source.addListener(this);
        stateSources.add(source);
    }

    public void removeStateSource(ThreadExp02_StateSource source){
        stateSources.remove(source);
    }

    @Override
    public void run(){

        for(ThreadExp02_StateSource s : stateSources)
            s.start();

        while(!isInterrupted()) {
            if(flag.equals(ThreadExp02_AdminFlags.MODE_X)) {
                System.out.println("Received STOP command. Joining adminPollThread...");
                for(ThreadExp02_StateSource s : stateSources)
                    s.interrupt();
                break;
            }
        }
    }

    public synchronized void processStateChange(ThreadExp02_AdminFlags f) {
        flag = f;
        System.out.println("Got input: " + flag.toString());
    }

    public synchronized char getChar(){
        switch (flag){
            case MODE_A:
                return '1';
            case MODE_B:
                return '2';
            case MODE_C:
                return '3';
            default:
                return '1';
        }
    }
}
