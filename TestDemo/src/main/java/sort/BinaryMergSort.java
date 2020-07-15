package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinaryMergSort {

	public static void main(String[] args) {
		int data[] = {4,6,3,7,2,8};
		mergeSort(data, 0, data.length-1);
		System.out.println(Arrays.toString(data));
	}

	/*
	* 二叉分割，最终两两比较，合并
	* */
	public static void mergeSort(int data[], int left, int right){
		if (left < right) { // left = right 表示只有一个元素，分不了
			int mid = (left + right) / 2;
			// 分割
			mergeSort(data, left, mid);
			mergeSort(data,mid+1, right);
			merge(data,left,mid,right);
		}
	}

	//合并
	public static void merge(int data[], int left, int mid, int right){
		int[]temp = new int[data.length];
		int p1 = left;  //左边第一个数
		int p2 = mid + 1;  //右边第一个数
		int loc = left;
		while (p1 <= mid && p2 <= right){
			if (data[p1] <= data[p2]){
				temp[loc] = data[p1];
				loc++;
				p1++;
			}else {
				temp[loc] = data[p2];
				loc++;
				p2++;
			}
		}
		while (p1 <= mid){ // 左边都大于右边的情况，上面把右边都取了，左边还没取
			temp[loc++] = data[p1++];
		}
		while (p2 <= right) {  // 左边都小于右边的情况，上面把左边都取了，右边还没取
			temp[loc++] = data[p2++];
		}
		for (int i = left; i <= right; i++)
			data[i] = temp[i];
	}
}
