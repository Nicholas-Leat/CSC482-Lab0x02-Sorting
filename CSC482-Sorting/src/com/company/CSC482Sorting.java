package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

public class CSC482Sorting {
    public static void main(String[] args) {
        // write your code here
        int N;
        N = 10;
        int k = 6;
        TimePerformence(N);

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

        return sortList;
    }
    public static char[][] Merge(char[][] sortList, int low, int high, int m, int col){


        return sortList;
    }
    public static char[][] QuickSort(char[][] sortList,int row,int col, int low, int high){
        int partition = partition(sortList,low,high,col);
        if(partition-1 > low){
            sortList = QuickSort(sortList,row,col,low,partition-1);
        }
        if(partition+1 < high){
            sortList = QuickSort(sortList,row,col,partition+1,high);
        }
        return sortList;
    }
    public static int partition(char[][] myList, int begin, int end, int col){
      char[] pivot = new char[col];
      for(int x = 0; x < col; x++){
          pivot[x] = myList[end-1][x];
      }
      char[] temp = new char[col];
      for(int i = begin; i < end; i++){
          if((int)myList[i][0]<(int)pivot[0]){
              for(int x = 0; x < col; x++) {
                  temp[x] = myList[begin][x];
                  myList[begin][x] = myList[i][x];
                  myList[i][x] = temp[x];
              }
              begin++;
          }else if((int)myList[i][0] == (int)pivot[0]){
             for(int z =0; z < col; z++){
                 if((int)myList[i][z]<(int)pivot[z]){
                     for(int x = 0; x < col; x++) {
                         temp[x] = myList[begin][x];
                         myList[begin][x] = myList[i][x];
                         myList[i][x] = temp[x];
                     }
                 }
             }
          }
      }
      for(int x = 0; x < col; x++) {
          temp[x] = myList[begin][x];
          myList[begin][x] = pivot[x];
          myList[end-1][x] = temp[x];
      }

      return begin;
    }
    public static char[][] RadixSort(char[][] sortList, int length, int col){
        int m = getMax(sortList,length,col);
        for(int exp =1; m/exp >0; exp*=10){
            countSort(sortList,length,exp,col);
        }/**/
        System.out.println(m);
        return sortList;
    }
    public static char[][] countSort(char[][] sortList, int length, int exp, int col){
        char[][] output = new char[length][col];
        int max = (int) sortList[0][0];
        for(int i = 1; i <length; i++){
            if((int)sortList[i][0] > max){
                max = (int)sortList[i][0];
            }
        }
        int[] count = new int[max +1];

        for(int i = 0; i < max; i++){
            count[i] = 0;
        }
        for(int i = 0; i < length; i++){
            count[((int)sortList[i][0]/exp)%10]++;
        }
        for(int i = 1; i < 10; i++){
            count[i] += count[i-1];
        }
        for(int i = length-1; i >=0; i--){
            output[count[((int)sortList[i][0]/exp)%10]-1] = sortList[i];
            count[((int)sortList[i][0]/ exp)%10]--;
        }
        for(int i = 0; i < length; i++){
            for(int x = 0; x < col; x++) {
                sortList[i][x] = output[i][x];
            }
        }

        return sortList;
    }
    public static int getMax(char[][] sortList, int length, int col){
        int mx = (int)sortList[0][0];
        for(int i = 0; i < length; i++){
            for(int x = 0; x < col; x++){
                if((int)sortList[i][x] > mx){
                    mx = sortList[i][0];
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
    public static void TimePerformence(int N){
        //run for multiple N values and multiple k values
        //N is max value for N
        long Timemax = 1000000;
        int[] NVal = new int[N];
        int[] kVal = new int[4];
        long timeBefore;
        long timeAfter;
        long[][] selectionTime = new long[N][4];
        long[][] mergeTime = new long[N][4];
        long[][] quickTime = new long[N][4];
        long[][] radixTime = new long[N][4];
        double[][]selectionDR = new double[N][4];
        double[][] mergeDR = new double[N][4];
        double[][] quickDR = new double[N][4];
        double[][] radixDR = new double[N][4];
        double[] ExpSelectionDR = new double[N];
        double[] ExpmergeDR = new double[N];
        double[] ExpquickDR = new double[N];
        double[] ExpradixDR = new double[N];
        char[][] myList;

        int total = 0;
        int N_val = 1;
        int k_val = 6;
        for(int z = 0; z < 4; z++) {
            for (int x = 0; x < N; x++) {
                NVal[x] = N_val;
                System.out.printf("Generating list of length %d, key width of %d:\n",N_val,k_val);
                System.out.println("Sorting with Selection Sort!");
                myList = GenerateTestList(N_val, k_val, 65, 90);
                timeBefore = getCpuTime();
                myList = SelectionSort(myList, N_val, k_val);
                IsSorted(myList, N_val, k_val);
                timeAfter = getCpuTime();
                selectionTime[x][z] = timeAfter - timeBefore;

                System.out.println("Sorting with Quick Sort!");
                myList = GenerateTestList(N_val, k_val, 65, 90);
                timeBefore = getCpuTime();
                myList = QuickSort(myList, N_val, k_val, 0, N_val);
                IsSorted(myList, N_val, k_val);
                timeAfter = getCpuTime();
                quickTime[x][z] = timeAfter - timeBefore;

                System.out.println("Sorting with Merge Sort!");
                myList = GenerateTestList(N_val, k_val, 65, 90);
                timeBefore = getCpuTime();
                myList = MergeSort(myList, 0, N_val, k_val);
                IsSorted(myList, N_val, k_val);
                timeAfter = getCpuTime();
                mergeTime[x][z] = timeAfter - timeBefore;

                System.out.println("Sorting with Radix Sort!");
                myList = GenerateTestList(N_val, k_val, 65, 90);
                timeBefore = getCpuTime();
                myList = RadixSort(myList, N_val, k_val);
                IsSorted(myList, N_val, k_val);
                timeAfter = getCpuTime();
                radixTime[x][z] = timeAfter - timeBefore;

                if (x == 0) {
                    selectionDR[x][z] = 0;
                    quickDR[x][z] = 0;
                    mergeDR[x][z] = 0;
                    radixDR[x][z] = 0;
                    ExpmergeDR[x] = 0;
                    ExpquickDR[x] = 0;
                    ExpradixDR[x] = 0;
                    ExpSelectionDR[x] = 0;
                } else {
                    selectionDR[x][z] = (double) selectionTime[x][z] / (double) selectionTime[x - 1][z];
                    quickDR[x][z] = (double) quickTime[x][z] / (double) quickTime[x - 1][z];
                    mergeDR[x][z] = (double) mergeTime[x][z] / (double) mergeTime[x - 1][z];
                    radixDR[x][z] = (double) radixTime[x][z] / (double) radixTime[x - 1][z];
                    ExpmergeDR[x] = ((double)N_val *(double) Math.log(N_val))/((double)NVal[x-1] *(double) Math.log(NVal[x-1]));
                    ExpSelectionDR[x] = ((double)NVal[x]*(double)NVal[x])/((double)NVal[x-1]*(double)NVal[x-1]);
                    ExpradixDR[x] = ((double)NVal[x]*(double)kVal[z] )/ (double)NVal[x-1]*(double)kVal[z];
                    ExpquickDR[x] = ((double)N_val *(double) Math.log(N_val))/((double)NVal[x-1] *(double) Math.log(NVal[x-1]));
                }


                N_val = N_val * 2;
                if(k_val == 6){
                    total++;
                }
                if(selectionTime[x][z] > Timemax || quickTime[x][z] > Timemax || mergeTime[x][z] > Timemax ||radixTime[x][z] > Timemax){
                    break;
                }
            }
            kVal[z] = k_val;
            k_val = k_val * 2;
            N_val = 1;
        }
        System.out.println("Results for Selection Sort");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("k = %-68d k = %-57d k =%-58d k = %-57d\n",kVal[0],kVal[1],kVal[2],kVal[3] );
        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s\n",
                "N", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Predicted Doubling Ratio");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int iter = 0; iter < total; iter++){
            System.out.format("%-10d %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f\n",
                    NVal[iter], selectionTime[iter][0], selectionDR[iter][0], selectionTime[iter][1], selectionDR[iter][1], selectionTime[iter][2], selectionDR[iter][2], selectionTime[iter][3], selectionDR[iter][3]);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Results for Quick Sort");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("k = %-68d k = %-57d k =%-58d k = %-57d\n",kVal[0],kVal[1],kVal[2],kVal[3] );
        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s\n",
                "N", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Predicted Doubling Ratio");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int iter = 0; iter < total; iter++){
            System.out.format("%-10d %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f\n",
                    NVal[iter], quickTime[iter][0], quickDR[iter][0], quickTime[iter][1], quickDR[iter][1], quickTime[iter][2], quickDR[iter][2], quickTime[iter][3], quickDR[iter][3]);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Results for Merge Sort");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("k = %-68d k = %-57d k =%-58d k = %-57d\n",kVal[0],kVal[1],kVal[2],kVal[3] );
        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s\n",
                "N", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Predicted Doubling Ratio");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int iter = 0; iter < total; iter++){
            System.out.format("%-10d %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f\n",
                    NVal[iter], mergeTime[iter][0], mergeDR[iter][0], mergeTime[iter][1], mergeDR[iter][1], mergeTime[iter][2], mergeDR[iter][2], mergeTime[iter][3], mergeDR[iter][3]);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Results for Radix Sort");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("k = %-68d k = %-57d k =%-58d k = %-57d\n",kVal[0],kVal[1],kVal[2],kVal[3] );
        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s\n",
                "N", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Time", "Doubling Ratio", "Predicted Doubling Ratio");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int iter = 0; iter < total; iter++){
            System.out.format("%-10d %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f %-30d %-30.3f\n",
                    NVal[iter], radixTime[iter][0], radixDR[iter][0], radixTime[iter][1], radixDR[iter][1], radixTime[iter][2], radixDR[iter][2], radixTime[iter][3], radixDR[iter][3]);
        }
        return;
    }
    public static long getCpuTime( ) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );

        return ((ThreadMXBean) bean).isCurrentThreadCpuTimeSupported( ) ?

                bean.getCurrentThreadCpuTime( ) : 0L;

    }
}


