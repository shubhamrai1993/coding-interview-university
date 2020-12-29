import java.lang.*;
import java.util.*;

public class MyArrayQueue implements IMyArrayQueue {
  public static void main(String[] args) {
    MyArrayQueue q = new MyArrayQueue(5);
    if (!q.empty() || q.full()) failed();

    q.enqueue(4);
    if (q.empty()) failed();
    int last = q.dequeue();
    if (last != 4) failed();
    if (!q.empty()) failed();

    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    if (!q.full()) failed();
    last = q.dequeue();
    if (last != 1) failed();
  }

  int read = 0;
  int write = 0;
  int[] arr;

  MyArrayQueue(int size) {
    arr = new int[size + 1];
  }

  public void enqueue(int item) {
    if (full()) {
      throw new Error("Queue is full");
    }

    arr[write] = item;
    if (write == arr.length - 1) write = 0;
    else write = write + 1;
  }

  public int dequeue() {
    if (empty()) {
      throw new Error("Queue is empty");
    }
    int val = arr[read];
    if (read == arr.length - 1) read = 0;
    else read = read + 1;
    return val;
  }

  public boolean empty() {
    if (read == write) {
      return true;
    }
    return false;
  }

  public boolean full() {
    if (read == write + 1 || (write == arr.length - 1 && read == 0)) {
      return true;
    }
    return false;
  }

  public static void failed() {
    throw new Error("Failed");
  }
}

interface IMyArrayQueue {
  void enqueue(int item);
  int dequeue();
  boolean empty();
  boolean full();
}
