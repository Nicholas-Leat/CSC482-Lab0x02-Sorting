package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;

public class CSC482Sorting {
    public static void main(String[] args) {
        // write your code here
        int N,k,d;
        N = 10;
        k = 6;
        System.out.printf("Generating list of length %d, key width of %d:\n",N,k);
        char[][] myList = GenerateTestList(N,k,65,90);
       /** System.out.printf("Sorting with Selection Sort!\n");
        myList = SelectionSort(myList,N,k);
        IsSorted(myList,N,k);
        /**/
       for(int i = 0; i < N; i++){
           for(int x = 0; x < k; x++){
               System.out.printf("%c",myList[i][x]);
           }
           System.out.println(" ");
       }
       myList = MergeSort(myList,0,N-1, k);
       System.out.println("After merge sort Sort");
        for(int i = 0; i < N; i++){
            for(int x = 0; x < k; x++){
                System.out.printf("%c",myList[i][x]);
            }
            System.out.println(" ");
        }
    }

    public static char[][] SelectionSort(char[][] sortList, int N, int k){
        char[] temp = new char[k];
        int comp1,comp2;
        for(int rOuter = 0; rOuter < N-1; rOuter++){
            int min_index = rOuter;
            for(int rInner = min_index; rInner < N; rInner++){
                comp1 = (int)sortList[rInner][0];
                comp2 = (int)sortList[min_index][0];
                if( comp1 < comp2){
                    min_index = rInner;
                }
                else if(comp1 == comp2){
                    for(int cInner = 0; cInner < k; cInner++){
                        if((int)sortList[rInner][cInner] < (int)sortList[min_index][cInner]){
                            min_index = rInner;
                        }
                    }
                }
            }

            for(int iter = 0; iter < k; iter++){
                temp[iter] = sortList[min_index][iter];
            }
            for(int z = 0; z < k; z++){
                sortList[min_index][z] = sortList[rOuter][z];
            }
            for(int f = 0; f < k; f++){
                sortList[rOuter][f] = temp[f];
            }
        }
        return sortList;
    }
    public static char[][] MergeSort(char[][] sortList,int low, int high,int col){
        if(low < high){
            int m = (low + high)/2;
            sortList = MergeSort(sortList,low, m,col);
            sortList = MergeSort(sortList,m+1,high,col);
            sortList = merge(sortList,low,high,col,m);
        }
        return sortList;
    }
    public static char[][] merge(char[][] sortList, int low, int high, int col, int m){
        int n1 = m - low + 1;
        int n2 = high - m;

        char[][] Left = new char[high][col];
        char[][] Right = new char[high][col];
        char[][] merged = new char[high][col];
        System.out.printf("Value of n1 : %d \n", n1);

        for(int i = 0; i < n1; i++){
            for(int x = 0; x < col; x++){
                System.out.println(i);
                Left[i][x] = 'c';//sortList[i][x];
            }
        }
        for(int i = 0; i < n2; i++){
            for(int x = 0; x < col; x++){
                Right[i][x] = sortList[m+1+i][x];
            }
        }
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < n1 && j < n2){
            if((int)Left[i][0] <= (int)Right[j][0]){
                for(int z = 0; z < col; z++){
                    merged[k][z] = Left[i][z];
                }
                i++;
            } else{
                for(int z = 0; z < col; z++){
                    merged[k][z] = Right[j][z];
                }
                j++;
            }
            k++;
        }
        while(i < n1 && k < high){
            for(int iter = 0; iter < col; iter++){
                merged[k][iter] = Left[i][iter];
            }
            i++;
            k++;
        }
        while(j < n2 && k < high){
            for(int iter = 0; iter < col; iter++){
                merged[k][iter] = Right[j][iter];
            }
            j++;
            k++;
        }

        System.out.printf("End of merge function\n");
        return merged;
    }
    public static char[][] QuickSort(char[][] sortList,int row,int col, int begin, int end){

        return sortList;
    }
    public static int partition(char[][] myList, int begin, int end, int col){

        return 1;
    }
    public static char[][] RadixSort(char[][] sortList, int length, int col){
        int m = getMax(sortList,length,col);
        return sortList;
    }
    public  static char[][] countSort(char[][] sortList, int length, int col, int exp){
        char[][] output = new char[length][col];
    }
    public static int getMax(char[][] sortList, int length, int col){
        int mx = (int)sortList[0][0];
        for(int i = 0; i < length; i++){
            for(int x = 0; x < col; x++){
                if((int)sortList[i][x] > mx){
                    mx = sortList[i][x];
                }
            }
        }
        return mx;
    }
    public static char[][] GenerateTestList(int N, int k, int minV, int maxV){
        char[][] myList = new char[N][k];
        Random r = new Random();
        int result;
        //generate random "string" array here
        for(int i = 0; i < N; i++){
            for(int x = 0; x < k; x++){
                //gives us a random int between minV (inclusive) and maxV(exclusive)
                result = r.nextInt(maxV-minV)+minV;
                myList[i][x] = (char) result;

            }
        }
        return myList;
    }
    public static void IsSorted(char[][] sortedList, int rows, int col){
        boolean[] sorted = new boolean[rows];
        System.out.println("Verifying...");
        for(int iter = 0; iter < rows; iter++){
            sorted[iter] = false;
        }

        for(int i = 0; i < rows-1; i++){
            if(sortedList[i][0] < sortedList[i+1][0]){
                sorted[i] = true;
            }else if(sortedList[i][0] == sortedList[i+1][0]){
                for(int x = 0; x < col; x++){
                    if(sortedList[i][x] < sortedList[i+1][x]){
                        sorted[i] = true;
                    }
                }
            }
        }
        int sort = 0;
        for(int iter = 0; iter < rows; iter++){
            if(sorted[iter]){
                sort++;
            }
        }
        if(sort == rows-1){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not Sorted!");
        }
    }
    public static void TimePerformence(char[][] myList, int row, int col,int N){
        //run for multiple N values and multiple k values
        long[] selectionTime = new long[N];
        long[] mergeTime = new long[N];
        long[] quickTime = new long[N];
        long[] radixTime = new long[N];

        for(int iter = 0; iter < N; iter++) {
            long BeforeTime = getCpuTime();
            myList = SelectionSort(myList, row, col);
            long AfterTime = getCpuTime();
            selectionTime[iter] = AfterTime - BeforeTime;
        }


        return;
    }
    public static long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return ((ThreadMXBean) bean).isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}


