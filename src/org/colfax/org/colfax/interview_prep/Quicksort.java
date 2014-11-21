package org.colfax.org.colfax.interview_prep;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;

/**
 * Created by colfax on 11/17/2014.
 */
public class Quicksort {

    public void quicksort(int[] A){
        quicksort(A, 0, A.length - 1);
    }

    private void quicksort(int[] A, int L, int R){
        int i = partition(A, L, R);
        if(L < i - 1) quicksort(A, L, i - 1);
        if(R > i) quicksort(A, i, R);
    }

    private int partition(int[] A, int L, int R){
        int pValue = A[(L+R)/2];
        while(L <= R){
            while(A[L] < pValue) L++;
            while(A[R] > pValue) R--;

            if(L <= R){
                int tmp = A[L];
                A[L] = A[R];
                A[R] = tmp;
                L++;
                R--;
            }
        }
        return L;
    }

    public static void main(String[] args){
        int[] arr = {5, 4, 7, 3, 23, 18, 7, 8, 9, 90, 1, 33};
        Quicksort qs = new Quicksort();
        qs.quicksort(arr);
        System.out.print("Array: [");
        for(int i = 0; i < arr.length - 2; i++)
            System.out.print(arr[i] + ", ");
        System.out.print(arr[arr.length - 1] + "]");
    }
}
