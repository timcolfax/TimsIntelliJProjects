package org.colfax.test.junk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by colfax on 11/23/2014.
 */
public class ThreadExp02_StateSource_Auto extends ThreadExp02_StateSource{
    private Random                                     random    = new Random();
    private ArrayList<ThreadExp02_StateChangeListener> listeners = new ArrayList<ThreadExp02_StateChangeListener>();
    private ThreadExp02_AdminFlags                     flag      = null;

    public void addListener(ThreadExp02_StateChangeListener listener){
        listeners.add(listener);
    }

    public void removeListener(ThreadExp02_StateChangeListener listener){
        listeners.remove(listener);
    }

    @Override
    public void run(){

        while(!isInterrupted()) {
            System.out.println("Auto-Polling for Admin input.");

            flag = ThreadExp02_AdminFlags.values()[random.nextInt(ThreadExp02_AdminFlags.values().length - 1)];
            for(ThreadExp02_StateChangeListener l : listeners)
                l.processStateChange(flag);

            try {
                long interval = random.nextInt(4500) + 500L;
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                break;
            }

            if(flag.equals(ThreadExp02_AdminFlags.MODE_X))
                break;
        }
    }
}
