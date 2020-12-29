import java.lang.*;
import java.util.*;

public class MyLinkedList implements IMyLinkedList {
  public static void main(String[] args) {
    MyLinkedList l = new MyLinkedList(3);
    if (l.size() != 0) throw new Error("size() failed");
    if (!l.empty()) throw new Error("empty() failed");
    l.pushFront(10);
    l.pushFront(9);
    if (l.size() != 2) throw new Error("pushFront() failed");
    if (l.valueAt(1) != 10) throw new Error("valueAt() failed");
    int front = l.popFront();
    if (l.size() != 1) throw new Error("pushFront() failed");
    if (front != 9) throw new Error("popFront() failed");

    l.pushBack(11);
    if (l.valueAt(1) != 11) throw new Error("pushBack() failed");
    int last = l.popBack();
    if (last != 11) throw new Error("popBack() failed");
    l.pushBack(11);
    l.insert(1, 12);
    System.out.println(l.print());
    if (l.valueAt(1) != 12) throw new Error("valueAt() failed");
    if (l.valueNFromEnd(3) != 10) throw new Error("valueNFromEnd() failed");
    l.reverse();
    System.out.println(l.print());
    if (l.valueAt(0) != 11 || l.valueAt(1) != 12 || l.valueAt(2) != 10) throw new Error("reverse() failed");
  }

  class Node {
    Node(int val) {
      this.val = val;
      this.next = null;
    }

    int val;
    Node next;
  }

  int size = 0;
  Node head = null;
  Node tail = null;

	MyLinkedList(int size) {
	}

  public int size() {
    return this.size;
  }

  public boolean empty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  public int valueAt(int index) {
    if (head == null || index > size - 1) {
      throw new InvalidInputException();
    }
    Node node = head;
    for (int i = 0; i < size; i++) {
      if (i == index) {
        return node.val;
      }
      node = node.next;
    }
    throw new InvalidInputException();
  }

  public void pushFront(int item) {
    if (head == null && tail == null) {
      Node node = new Node(item);
      head = node;
      tail = node;
    } else {
      Node node = new Node(item);
      node.next = head;
      head = node;
    }
    size = size + 1;
  }

  public int popFront() {
    if (head == null) {
      throw new InvalidInputException();
    }

    Node node = head;
    head = head.next;
    size = size - 1;
    return node.val;
  }

  public void pushBack(int item) {
    if (tail == null) {
      Node node = new Node(item);
      tail = node;
      head = node;
    } else {
      Node node = new Node(item);
      tail.next = node;
      tail = tail.next;
    }
    size = size + 1;
  }

  public int popBack() {
    if (tail == null) {
      throw new InvalidInputException();
    }
    if (head == tail) {
      Node node = head;
      head = null;
      tail = null;
      return node.val;
    }

    Node secondToLast = head;
    for (int i = 0; i < size - 1; i++) {
      if (i == size - 2) {
        break;
      }
      secondToLast = secondToLast.next;
    }

    Node last = tail;
    tail = secondToLast;
    tail.next = null;
    size = size - 1;
    return last.val;
  }

  public int front() {
    if (head == null) {
      throw new InvalidInputException();
    }
    return head.val;
  }

  public int back() {
    if (tail == null) {
      throw new InvalidInputException();
    }
    return tail.val;
  }

  public void insert(int index, int value) {
    if (index < 0 || index > size - 1) {
      throw new InvalidInputException();
    }

    if (size == 1) {
      Node node = new Node(value);
      node.next = head;
      head = node;
      return;
    }

    Node nodeBefore = head;
    for (int i = 0; i <= index - 2; i++) {
      nodeBefore = nodeBefore.next;
    }
    Node nodeAtIndex = nodeBefore.next;
    Node node = new Node(value);
    node.next = nodeAtIndex;
    nodeBefore.next = node;

    size = size + 1;
  }

  public void erase(int index) {
  }

  public int valueNFromEnd(int n) {
    if (n > size) {
      throw new InvalidInputException();
    }

    Node leader = head;
    for (int i = 0; i < n; i++) {
      // Take care while iterating to make sure that you do not iterate over the line ie wait too much before stopping
      if (i == n - 1) {
        break;
      }
      leader = leader.next;
    }
    Node follower = head;
    while (leader.next != null) {
      leader = leader.next;
      follower = follower.next;
    }
    return follower.val;
  }

  public void reverse() {
    if (size == 1) return;

    Node a = head;
    Node b = head.next;

    a.next = null;
    while (b != null) {
      //System.out.println("a: " + a.val + ", b: " + b.val);
      Node c = b.next;
      b.next = a;

      a = b;
      b = c;
    }
    tail = head;
    head = a;
  }

  public void removeValue(int item) {}

  public String print() {
    String res = "";
    Node node = head;
    for (int i = 0; i < size; i++) {
      if (i != size - 1) {
        res = res + node.val + ",";
      } else {
        res = res + node.val;
      }
      node = node.next;
    }
    return res;
  }

  class InvalidInputException extends Error {
    InvalidInputException() {
      super("Invalid input");
    }
  }
}

interface IMyLinkedList {
  int size();
  boolean empty();
  int valueAt(int index);
  void pushFront(int item);
  int popFront();
  void pushBack(int item);
  int popBack();
  int front();
  int back();
  void insert(int index, int value);
  void erase(int index);
  int valueNFromEnd(int n);
  void reverse();
  void removeValue(int item);
}
