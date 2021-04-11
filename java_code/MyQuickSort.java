import java.lang.*;
import java.util.*;

public class MyQuickSort {
  public void sort(int[] arr) {
    if (arr.length <= 1) return;
    quickSort(arr, 0, arr.length - 1);
  }

  private void quickSort(int[] arr, int start, int end) {
    if (start >= end) return;
    int pivot = arr[start];
    int p1 = start + 1;
    int p2 = end;
    while (p1 <= p2) {
      if (arr[p1] > pivot && arr[p2] < pivot) {
        swap(arr, p1, p2);
        p1++;
        p2--;
      } else {
        if (arr[p1] <= pivot) p1++;
        if (arr[p2] >= pivot) p2--;
      }
    }
    swap(arr, start, p2);
    quickSort(arr, start, p2 - 1);
    quickSort(arr, p2 + 1, end);
  }

  void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[] {5,4,1,2,1};
    MyQuickSort mqs = new MyQuickSort();
    mqs.sort(arr);
    for (int a : arr) System.out.println(a);
  }
}
