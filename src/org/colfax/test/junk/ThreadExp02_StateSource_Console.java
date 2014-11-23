package org.colfax.test.junk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by colfax on 11/21/2014.
 */
public class ThreadExp02_StateSource_Console extends ThreadExp02_StateSource{
    private BufferedInputStream                         bis         = new BufferedInputStream(System.in);
    private ArrayList<ThreadExp02_StateChangeListener>  listeners   = new ArrayList<ThreadExp02_StateChangeListener>();
    private ThreadExp02_AdminFlags                      flag        = null;

    public void addListener(ThreadExp02_StateChangeListener listener){
        listeners.add(listener);
    }

    public void removeListener(ThreadExp02_StateChangeListener listener){
        listeners.remove(listener);
    }

    @Override
    public void run(){

        while(!isInterrupted()) {
            System.out.println("Polling for Admin input.");

            try {
                flag = ThreadExp02_AdminFlags.charToFlag((char) bis.read());
                for(ThreadExp02_StateChangeListener l : listeners)
                    l.processStateChange(flag);
                bis.skip(bis.available()); // Skip all but the first char of each line.

                if(flag.equals(ThreadExp02_AdminFlags.MODE_X))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
