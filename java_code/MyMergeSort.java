import java.lang.*;
import java.util.*;

public class MyMergeSort {

  void sort(int[] arr) {
    if (arr.length == 0) return;
    mergeSort(arr, 0, arr.length - 1);
  }

  // Sort first and then merge. Merge sort function calls itself recursively to sort and merges the two sorted halves returned to it

  void mergeSort(int[] arr, int start, int end) {
    if (start == end) {
      return;
    }

    int mid = (start + end) / 2;
    mergeSort(arr, start, mid);
    mergeSort(arr, mid + 1, end);
    merge(arr, start, mid, end);
  }

  void merge(int[] arr, int start, int mid, int end) {
    int[] aux = new int[end - start + 1];
    int auxi = 0;
    int p1 = start;
    int p2 = mid + 1;
    while (p1 <= mid || p2 <= end) {
      if (p2 > end || arr[p1] < arr[p2]) {
        aux[auxi] = arr[p1];
        p1++;
      } else if (p1 > mid || arr[p2] <= arr[p1]) {
        aux[auxi] = arr[p2];
        p2++;
      }
      auxi++;
    }
    for (int i = 0; i < aux.length; i++) arr[i + start] = aux[i];
  }

  public static void main(String[] args) {
    int[] arr = new int[] {1};
    MyMergeSort mms = new MyMergeSort();
    mms.sort(arr);
    for (int a : arr) System.out.println(a);
  }
}
