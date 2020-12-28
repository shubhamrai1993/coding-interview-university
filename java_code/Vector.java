import java.lang.*;
import java.util.*;

public class Vector implements IVector {
  public static void main(String[] args) {
    IVector v = new Vector(3);
    if (v.capacity() != 3) throw new Error("capacity() failed");
    if (v.size() != 0) throw new Error("size() failed");
    if (!v.isEmpty()) throw new Error("isEmpty() failed");

    v.push(2);
    v.push(3);

    if (v.at(1) != 3) throw new Error("at() failed");

    v.insert(1, 1);

    if (v.at(1) != 1) throw new Error("at() failed");
    if (v.size() != 3) throw new Error("size() failed");

    v.prepend(4);

    if (v.at(0) != 4) throw new Error("at() failed");
    if (v.size() != 4) throw new Error("size() failed");
    if (v.capacity() != 6) throw new Error("capacity() failed");

    int last = v.pop();
    if (last != 3) throw new Error("pop() failed");

    last = v.delete(0);
    if (last != 4) throw new Error("delete() failed");
    System.out.println(v.print());

    v.push(3);
    v.push(1);
    v.push(4);

    v.remove(1);
    if (v.at(1) != 3) throw new Error("remove() failed");
    if (v.at(2) != 4) throw new Error("remove() failed");
    System.out.println(v.print());

    if (v.find(3) != 1 || v.find(1) != -1) throw new Error("find() failed");
  }

  private int tailIndex = -1;
  private int[] arr;

  Vector(int size) {
    this.arr = new int[size];
  }

  public int size() {
    return this.tailIndex + 1;
  }

  public int capacity() {
    return arr.length;
  }

  public boolean isEmpty() {
    if (tailIndex == -1) {
      return true;
    }
    return false;
  }

  public int at(int index) {
    if (index >= 0 && index <= tailIndex) {
      return arr[index];
    }
    throw new InvalidInputException();
  }

  public void push(int item) {
    if (tailIndex == arr.length - 1) {
      resize(2 * arr.length);
    }
    arr[tailIndex + 1] = item;
    tailIndex += 1;
  }

  public void insert(int index, int item) {
    if (index > tailIndex || index < 0) {
      throw new InvalidInputException();
    }
    if (tailIndex >= arr.length - 1) {
      resize(2 * arr.length);
    }
    for (int i = tailIndex; i >= index; i--) {
      arr[i + 1] = arr[i];
    }
    arr[index] = item;
    tailIndex += 1;
  }

  public void prepend(int item){
    insert(0, item);
  }

  public int pop() {
    if (tailIndex == -1) {
      throw new InvalidInputException();
    }
    int lastElement = arr[tailIndex];
    tailIndex = tailIndex - 1;
    return lastElement;
  }

  public int delete(int index) {
    if (index > tailIndex || index < 0) {
      throw new InvalidInputException();
    }
    int val = arr[index];

    for (int i = index; i < tailIndex; i++) {
      arr[i] = arr[i + 1];
    }
    tailIndex = tailIndex - 1;
    return val;
  }

  public void remove(int item) {
    int skip = 0;
    for (int i = 0; i <= tailIndex; i++) {
      if (arr[i] == item) {
        skip++;
        continue;
      }
      arr[i - skip] = arr[i];
    }
    tailIndex = tailIndex - skip;
  }

  public int find(int item) {
    for (int i = 0; i < tailIndex; i++) {
      if (arr[i] == item) return i;
    }
    return -1;
  }

  private void resize(int newCapacity) {
    // Copy to new Array of double size
    int[] arr = new int[newCapacity];
    for (int i = 0; i < this.arr.length; i++) {
      arr[i] = this.arr[i];
    }
    this.arr = arr;
  }

  public String print() {
    String res = "";
    for (int i = 0; i <= tailIndex; i++) {
      if (i != tailIndex) {
        res = res + arr[i] + ",";
      } else {
        res = res + arr[i];
      }
    }
    return res;
  }

  class InvalidInputException extends Error {
    InvalidInputException(String message) {
      super(message);
    }

    InvalidInputException() {
      super("Invalid input");
    }
  }
}
  interface IVector {
    int size();
    int capacity();
    boolean isEmpty();
    int at(int index);
    void push(int item);
    void insert(int index, int item);
    void prepend(int item);
    int pop();
    int delete(int index);
    void remove(int item);
    int find(int item);
    String print();
  }

