package org.colfax.test.junk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by colfax on 11/21/2014.
 */
public class ThreadExp02_CharacterPool {

    /**
     * The CharServer serves characters to clients who call getChar().
     * It possesses its OWN thread which reads configuration information
     * from the console, allowing the admin to change its behavior at runtime.
     */
    public class CharServer extends Thread{

        private ThreadExp02_AdminFlags  flag              = ThreadExp02_AdminFlags.MODE_A;
        private ThreadExp02_AdminPoller adminPollThread   = new ThreadExp02_AdminPoller(this);

        @Override
        public void run(){

            adminPollThread.start();

            while(!isInterrupted()) {
                if(flag.equals(ThreadExp02_AdminFlags.MODE_X)) {
                    System.out.println("Received STOP command. Joining adminPollThread...");
                    adminPollThread.interrupt();
                    break;
                }
            }
        }

        public synchronized void processAdminInput(ThreadExp02_AdminFlags f) {
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

    public static void main(String[] args) throws InterruptedException {
        ThreadExp02_CharacterPool app = new ThreadExp02_CharacterPool();
        app.go();
    }

    private void go() throws InterruptedException {
        CharServer w1 = new CharServer();
        w1.start();

        ArrayList<ThreadExp02_CharClient> clients = new ArrayList<ThreadExp02_CharClient>();
        for(int i = 0; i < 100; i++) {
            clients.add(new ThreadExp02_CharClient(w1));
            clients.get(i).start();
        }

        w1.join();

        for(ThreadExp02_CharClient c : clients)
            c.interrupt();
    }
}
