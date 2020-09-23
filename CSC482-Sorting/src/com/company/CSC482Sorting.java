package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
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
       myList = MergeSort(myList,0,N,k);
       System.out.println("After Merge Sort");
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
            int mid = (low + high)/2;
            sortList = MergeSort(sortList,low,mid,col);
            sortList = MergeSort(sortList,mid+1,high,col);
            sortList = Merge(sortList,low,high,mid,col);
        }
        return sortList;
    }
    public static char[][] Merge(char[][] sortList, int low, int high, int m, int col){
        char[][] temp = new char[high+1][col];
        int i = low;
        int j = m +1;
        int k = 0;

        while(i <= m && j <= high){
            if(sortList[i][0] <= sortList[j][0]){
                for(int x = 0; x < col; x++){
                    temp[k][x] = sortList[i][x];
                }
                k++;
                i++;
            }else if(sortList[i][0] == sortList[j][0]){
                for(int z = 0; z < col; z++){
                    if(sortList[i][z] <= sortList[j][z]){
                        for(int x = 0; x < col; x++){
                            temp[k][x] = sortList[i][x];
                        }
                        k++;
                        i++;
                    }else{
                        for(int x = 0; x < col; x++){
                            temp[k][x] = sortList[j][x];
                        }
                        k++;
                        j++;
                    }
                }
            }else{
                for(int x = 0; x < col; x++){
                    temp[k][x] = sortList[j][x];
                }
                k++;
                j++;
            }
        }
        while(i <= m){
            for(int x = 0; x < col; x++){
                temp[k][x] = sortList[i][x];
            }
            k++;
            i++;
        }
        while(j <= high){
            for(int x = 0; x < col; x++){
                temp[k][x] = sortList[j][x];
            }
            k++;
            j++;
        }
        for(int x = low; x < high; x++){
            for(i = 0; i < col; i++){
                sortList[x][i] = temp[x - low][i];
            }
        }

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
          pivot[x] = myList[end][x];
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
          myList[end][x] = temp[x];
      }

      return begin;
    }
    public static char[][] RadixSort(char[][] sortList, int length, int col){
        int m = getMax(sortList,length,col);
        return sortList;
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


