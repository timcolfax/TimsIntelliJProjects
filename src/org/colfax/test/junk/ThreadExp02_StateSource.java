package org.colfax.test.junk;

/**
 * Created by colfax on 11/23/2014.
 */
public abstract class ThreadExp02_StateSource extends Thread{
    public abstract void run();
    public abstract void addListener(ThreadExp02_StateChangeListener l);
    public abstract void removeListener(ThreadExp02_StateChangeListener l);
}
