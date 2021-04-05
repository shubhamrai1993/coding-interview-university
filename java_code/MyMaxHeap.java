import java.lang.*;
import java.util.*;

public class MyMaxHeap implements IMyMaxHeap {
  int[] list = new int[50];
  int maxSize = 50;
  int size = 0;

  public void insert(int val) {
    if (size == maxSize) throw new Error("Heap full");
    list[size] = val;
    siftUp(size);
    size++;
  }
  public void siftUp(int index) {
    if (index == 0) return;
    int parentIndex = getParent(index);
    if (list[parentIndex] < list[index]) {
      int tmp = list[index];
      list[index] = list[parentIndex];
      list[parentIndex] = tmp;
      siftUp(parentIndex);
    }
    return;
  }
  public int getMax() {
    if (size == 0) throw new Error("Heap is empty");
    return list[0];
  }
  public int getSize() {
    return size;
  }
  public boolean isEmpty() {
    return size == 0;
  }
  public int extractMax() {
    int max = list[0];
    remove(0);
    return max;
  }
  public void siftDown(int index) {
    Integer leftIndex = getLeftChildIndex(index);
    Integer rightIndex = getRightChildIndex(index);
    if (leftIndex == null) return;
    if (rightIndex == null) {
      if (list[leftIndex] > list[index]) {
        swap(leftIndex, index);
        return;
      } else {
        return;
      }
    } else {
      int maxIndex = leftIndex;
      if (list[rightIndex] > list[leftIndex]) maxIndex = rightIndex;
      swap(maxIndex, index);
      siftDown(maxIndex);
    }
    return;
  }
  private void swap(int a, int b) {
    int tmp = list[a];
    list[a] = list[b];
    list[b] = tmp;
  }
  public void remove(int index) {
    if (index >= size || index < 0) {
      throw new Error("Invalid index supplied for removal");
    }
    if (index == size - 1) {
      size = size - 1;
      return;
    }

    list[index] = list[size - 1];
    size = size - 1;
    siftDown(index);
  }
  public void heapSort() {}
  public void print() {
    for (int i = 0; i < size; i++) System.out.print(list[i] + " ");
    System.out.println("");
  }

  private Integer getParent(int index) {
    if (index == 0) return null;
    return (index - 1) / 2;
  }
  private Integer getLeftChildIndex(int index) {
    int leftChildIndex = 2 * index + 1;
    if (leftChildIndex >= size) return null;
    return leftChildIndex;
  }
  private Integer getRightChildIndex(int index) {
    int rightChildIndex = 2 * index + 2;
    if (rightChildIndex >= size) return null;
    return rightChildIndex;
  }

  public static void main(String[] args) {
    MyMaxHeap heap = new MyMaxHeap();
    heap.insert(1);
    heap.insert(4);
    heap.insert(3);
    heap.insert(2);
    heap.insert(0);
    heap.print();
    System.out.println(heap.getMax());
    heap.remove(0);
    heap.print();
    while (heap.getSize() != 0) {
      System.out.println(heap.extractMax());
      heap.print();
    }
  }
}

interface IMyMaxHeap {
  void insert(int val);
  void siftUp(int index);
  int getMax();
  int getSize();
  boolean isEmpty();
  int extractMax();
  void siftDown(int index);
  void remove(int index);
  void heapSort();
}
