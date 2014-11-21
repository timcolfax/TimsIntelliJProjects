package org.colfax.test.junk;

/**
 * Created by colfax on 11/20/2014.
 */
public class ThreadExperiment01_2Writers1Reader {

    private class Writer extends Thread{

        int x, count;
        public Writer(int x){
            this.x = x;
        }

        @Override
        public void run(){
            while(!isInterrupted()) {
                System.out.println("Thread " + x + ": B: " + count++);

                // 1).
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break;
//                }

                // 2).
                for(long i = 0; i < 45699999999L; i++) {long y = i*i;}// System.out.println("Y: " + y);}

                System.out.println("Thread " + x + ": A: " + count);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadExperiment01_2Writers1Reader app = new ThreadExperiment01_2Writers1Reader();
        app.go();
    }

    private void go() throws InterruptedException {
        Writer w1 = new Writer(1);
        w1.start();
        Thread.sleep(1000);
        System.out.println("Done sleeping. Interrupting w1.");
        w1.interrupt();
    }
}
