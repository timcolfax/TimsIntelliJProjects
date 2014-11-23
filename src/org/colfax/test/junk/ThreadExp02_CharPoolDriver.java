package org.colfax.test.junk;

import java.util.ArrayList;

/**
 * Created by colfax on 11/21/2014.
 */
public class ThreadExp02_CharPoolDriver {

    public static void main(String[] args) throws InterruptedException {
        ThreadExp02_CharPoolDriver app = new ThreadExp02_CharPoolDriver();
        app.go();
    }

    private void go() throws InterruptedException {
        ThreadExp02_CharServer w1 = new ThreadExp02_CharServer();
        w1.addStateSource(new ThreadExp02_AdminPoller());
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
