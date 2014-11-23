package org.colfax.test.junk;

/**
 * The CharServer serves characters to clients who call getChar().
 * It possesses its OWN thread which reads configuration information
 * from the console, allowing the admin to change its behavior at runtime.
 */
public class ThreadExp02_CharServer extends Thread implements ThreadExp02_StateChangeListener{

    private ThreadExp02_AdminFlags  flag              = ThreadExp02_AdminFlags.MODE_A;
    //private ThreadExp02_AdminPoller adminPollThread   = new ThreadExp02_AdminPoller(this);
    private ThreadExp02_StateSource stateSource;

    public ThreadExp02_CharServer(ThreadExp02_StateSource source){
        stateSource = source;
        stateSource.addListener(this);
    }

    @Override
    public void run(){

        //adminPollThread.start();
        stateSource.start();

        while(!isInterrupted()) {
            if(flag.equals(ThreadExp02_AdminFlags.MODE_X)) {
                System.out.println("Received STOP command. Joining adminPollThread...");
//                adminPollThread.interrupt();
                stateSource.interrupt();
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
