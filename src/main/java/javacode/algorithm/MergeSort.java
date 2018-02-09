package javacode.algorithm;



import java.util.Arrays;

public class MergeSort {
	// 划分子表;合并半子表
	public static int[] sort(int[] array, int low, int high, int[] temp) {

		int mid = (low + high) / 2;

		if (low < high) {
			// 左边
			sort(array, low, mid, temp);//递归不断拆,拆到不能拆，再合并。
			// 右边/
			sort(array, mid + 1, high, temp);
			//递归拆一点，合并一点。
			merge(array, low, mid, high, temp);
			//拆到最低的时候，此时拆出两个数组，然后开始合并他两。总结：是拆一点合并一点
		}
		return array;
	}

	/*
	 * 从两个比较数列，谁小取谁；取出后，对应的下标++继续比较。如果有个数列比较完了，空了。直接将另一个数列的数据依次取出（因为他已经是排序完的了）。
	 * 【分治思想啊。小的范围内是这样】
	 * 
	 * 将数组分解成只有一个数据时,可以认为小组组内已经达到排序，然后再合并相邻的两个小组。
	 */
	public static void merge(int[] array, int first, int mid, int last, int[] temp) {
		int i = first, j = mid + 1;       //相邻数组的第一个下标
		int m = mid, n = last;            //相邻数组的最后一个下标
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= m && j <= n) {        //循环遍历两个相邻数组;小的放入临时数组 
			if (array[i] <= array[j])
				temp[k++] = array[i++];   //左边的小放入临时数组，同时下标++
			else
				temp[k++] = array[j++];   //右边的小放入临时数组，同时下标++
		}
		// 把左边剩余的数移入数组
		while (i <= m)
			temp[k++] = array[i++];
		// 把右边边剩余的数移入数组
		while (j <= n)
			temp[k++] = array[j++];
		// 把临时数组中的数覆盖array数组，【因为拆多少，合并多少。所有此时覆盖回去还是刚拆出来的，且排了序。】
		for (i = 0; i < k; i++)//因为都是对等位置上的分解，所以复制回来不影响其他位置的。
			array[first + i] = temp[i];//把排序好的段，从低坐标放入临时数组
	}

	// 归并排序的实现
	public static void main(String[] args) {
		int[] array = { 2, 7, 8, 3, 1, 6, 9, 0, 5, 4 };
		int[] temp = new int[array.length];
		MergeSort.sort(array, 0, array.length - 1, temp);
		System.out.println(Arrays.toString(array));
	}

}
