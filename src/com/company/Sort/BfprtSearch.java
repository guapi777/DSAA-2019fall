package com.company.Sort;


/* 思想与随机排序一样，随机排序中吧第一个数当做 pivot ，BFPRT 排序中用中位数的中位数作为 pivot ，使划分效果更好
 * 找到中位数的位置后，与 a[low] 交换，这样就转化为随机排序
 * 比较a[low]=pivot 值得位置与 k 的大小，确定继续在左右还是右边递归查找
 * 最坏情况下时间复杂度为 O(n)
（ 1）将输入数组的个元素划分为 n/5 组，每组5 个元素，且至多只有一个组由剩下 n%5 的个元素组成。
（ 2）寻找各个组中每一个组的中位数，首先对每组的元素进行插入排序，然后从排序过的序列中选出中位数。
（ 3）对于（ 2 ）中找出的个中位数，递归进行步骤（ 1 ）和（2 ），直到只剩下一个数即为这个元素
    的中位数，找到中位数后并找到对应的下标。
（ 4）进行 Partion 划分过程， Partion划分中的 pivot 元素下标为。
（ 5）进行高低区判断即可。 */
public class BfprtSearch {
    public static void main(String[] args){
        int [] array ={4,45,32,67,5,78,4556,781,1,3,43,765,9,22};
        int k ;
        for (k =1; k<= array.length; k ++){
            int k_Num= quickSearch( array,0, array .length-1, k);
            System. out .println(" 第 "+ k +" 小的元素是： " +k_Num );
        }
    }
    private static int quickSearch(int[] array, int left, int right, int k){
        //left,right为下标， k 为第k 小的元素
        if( left== right){
            return array [left ];
        }
        int mid =getArrayMid (array , left, right); //mid 为中位数 id
        int mid_new =partition (array , left, right);// 根据中位数划分数组，是中位数在最终的位置上
        // 比较中位数与要查找数值的大小
        if( mid_new== k-1){
            return array [mid_new ];
        } else if (mid_new <k -1){
            left= mid_new+1;
            return quickSearch( array, left, right, k);
        } else {
            right= mid_new-1;
            return quickSearch( array, left, right, k);
        }
    }
    private static int getArrayMid(int[] a, int l, int r){ //l,r均为数组下标 , 返回中位数的位置
        if( l== r){
            return l ;
        }
        int i =l ;
        for (;i <=r -l -5;i +=5){// 子数组的元素个数为 5 个时
            insertSort( a, i, i+4); //对当前的五个数数进行排序
            swap( a, l+( i- l)/5, i+2); //将所有中位数放在数组 array 的前几位上
        }
        if( i< r- l){
            insertSort( a, i, r- l);
            swap( a, l+( i- l)/5,( i+ r- l)/2); //将最后一组数的中位数放在数组 array 的前几位上
        }
        return getArrayMid( a, l, l+( i- l)/5); // 返回中位数的中位数的 id

    }
    private static int partition(int[] a, int l, int r){
        int pivot =a [l ];
        while (l <r ){
            while (l <r && pivot<= a[ r]) r--;
            a[ l]= a[ r];
            while (l <r && pivot> a[ l]) l++;
            a[ r]= a[ l];
        }
        a[ l]= pivot;
        return l ;
    }
    private static void swap(int[] a, int i, int j){
        int temp= a[ i];
        a[ i]= a[ j];
        a[ j]= temp;
    }
    private static void insertSort(int[] a, int l, int r){ // 对下标为 l到 r之间的元素进行排序
        if( l< r){
            for( int i= l; i< r; i++){
                int j= i+1;
                int temp= a[ j];
                while( j> l&& temp< a[ j-1]){
                    a[ j]= a[ j-1];
                    j--;
                }
                a[ j]= temp;
            }
        }
    }
    public static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System. out.print( "\n");
    }
}
