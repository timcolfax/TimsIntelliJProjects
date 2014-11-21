package org.colfax.test.junk;

import java.util.Random;

/**
 * Created by colfax on 11/21/2014.
 */
public class ThreadExp02_CharClient extends Thread{

    ThreadExp02_CharacterPool.CharServer server;
    Random random = new Random();

    public ThreadExp02_CharClient(ThreadExp02_CharacterPool.CharServer server){
        this.server = server;
    }

    @Override
    public void run(){
        while(!isInterrupted()){
            System.out.println(this.hashCode() + ": " + server.getChar());
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
