package org.colfax.test.junk;

/**
 * Created by colfax on 11/21/2014.
 */
public enum ThreadExp02_AdminFlags {
    MODE_A, MODE_B, MODE_C, MODE_X;

    public static ThreadExp02_AdminFlags charToFlag(char c){
        switch (c){
            case 'a':
                return MODE_A;
            case 'b':
                return MODE_B;
            case 'c':
                return MODE_C;
            case 'x':
                return MODE_X;
            default:
                return MODE_A;
        }
    }

    @Override
    public String toString(){
        switch (this){
            case MODE_A:
                return "MODE_A";
            case MODE_B:
                return "MODE_B";
            case MODE_C:
                return "MODE_C";
            case MODE_X:
                return "MODE_X";
            default:
                return "MODE_A";
        }
    }
}
