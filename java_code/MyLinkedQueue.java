import java.lang.*;
import java.util.*;

public class MyLinkedQueue implements IMyLinkedQueue {
  public static void main(String[] args) {
    MyLinkedQueue q = new MyLinkedQueue();
    if (!q.empty()) throw new Error("empty() failed");
    q.enqueue(5);
    if (q.empty()) throw new Error("empty() failed");
    int last = q.dequeue();
    if (last != 5) throw new Error("dequeue failed");

    q.enqueue(5);
    q.enqueue(6);
    q.enqueue(10);
    last = q.dequeue();
    if (last != 5) throw new Error("dequeue failed");
  }

  class Node {
    int val;
    Node next;

    Node(int val) {
      this.val = val;
      this.next = null;
    }
  }

  Node head = null;
  Node tail = null;

  public void enqueue(int item) {
    Node node = new Node(item);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
  }

  public int dequeue() {
    if (tail == null) {
      throw new Error("Queue empty");
    }
    if (head == tail) {
      int val = head.val;
      head = null;
      tail = null;
      return val;
    }

    int val = head.val;
    head = head.next;
    return val;
  }

  public boolean empty() {
    if (head == null && tail == null) {
      return true;
    }
    return false;
  }
}

interface IMyLinkedQueue {
  void enqueue(int item);
  int dequeue();
  boolean empty();
}

