package javacode.algorithm;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
		
		sort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
	
	public static void sort(int [] array, int left, int right){
		
		int i,j,baseNum;
		if(left> right){
			return ;
		}
		i = left;
		j = right;
		baseNum = array[left];
		while(i != j){
			while(i<j && array[j] >= baseNum){
				--j;
			}
			
			while(i<j && array[i] <= baseNum){
				++i;
			}
			if(i<j){//没有碰头,右边是小于基准数的,左边是大约基数的,交换。
				int t = array[i];
				array[i] = array[j];
				array[j] =t;
				
			}
		}
		
		//到这里一定碰头，上次巡逻，i位置的是小于等于basenum【最左边】
		array[left] = array[i];
		array[i] =  baseNum;
		//此时左边的都小于中间树，右边的都大于中间数？？？
				sort(array, left, i-1);//继续处理左边的字表;递归
				sort(array, i+1, right);//继续处理右边的子表;递归
	}

}
