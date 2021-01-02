import java.lang.*;
import java.util.*;

public class BinarySearch {
  public static void main(String[] args) {
    BinarySearch search = new BinarySearch();
    int[] arr = new int[]{1,2,3,4,5};
    int ind = search.find(arr, 3);
    if (search.find(arr, 3) != 2) throw new Error("Search failed");
    if (search.findRecursive(arr, 4, 0, 3) != 2) throw new Error("Search failed");

    arr = new int[]{1};
    if (search.find(arr, 1) != 0) throw new Error("Search failed");
    if (search.findRecursive(arr, 0, 0, 1) != 0) throw new Error("Search failed");
    if (search.find(arr, 2) != -1) throw new Error("Search failed");
    if (search.findRecursive(arr, 0, 0, 2) != -1) throw new Error("Search failed");

    arr = new int[]{};
    if (search.find(arr, 2) != -1) throw new Error("Search failed");
    if (search.findRecursive(arr, -1, 0, 2) != -1) throw new Error("Search failed");
  }

  public int find(int[] arr, int target) {
    if (arr.length == 0) return -1;
    int low = 0;
    int high = arr.length - 1;


    while (true) {
      if (high == low) {
        if (arr[high] == target) return high;
        else return -1;
      }
      int middle = low + (high - low) / 2;
      if (arr[middle] > target) {
        high = middle;
      } else if (arr[middle] < target) {
        low = middle;
      } else {
        return middle;
      }
    }
  }

  public int findRecursive(int[] arr, int high, int low, int target) {
    if (arr.length == 0) {
      return -1;
    }
    if (high == low) {
      if (arr[high] == target) {
        return high;
      } else {
        return -1;
      }
    }

    int middle = low + (high - low) / 2;
    if (arr[middle] > target) {
      return findRecursive(arr, middle, low, target);
    } else if (arr[middle] < target) {
      return findRecursive(arr, high, middle, target);
    } else {
      return middle;
    }
  }
}
