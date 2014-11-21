package org.colfax.test.junk;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by colfax on 11/21/2014.
 */
public class ThreadExp02_AdminPoller extends Thread{
    BufferedInputStream                  bis    = new BufferedInputStream(System.in);
    ThreadExp02_CharacterPool.CharServer parent = null;
    private ThreadExp02_AdminFlags       flag   = null;

    public ThreadExp02_AdminPoller(ThreadExp02_CharacterPool.CharServer parent){
        this.parent = parent;
    }

    @Override
    public void run(){

        while(!isInterrupted()) {
            System.out.println("Polling for Admin input.");

            try {
                flag = ThreadExp02_AdminFlags.charToFlag((char) bis.read());
                parent.processAdminInput(flag);
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
