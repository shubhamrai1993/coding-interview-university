import java.lang.*;
import java.util.*;

public class MyHashMap implements IMyHashMap {
  public static void main(String[] args) {
    IMyHashMap map = new MyHashMap(7);
    map.add(1, 21);
    if (!map.exists(1)) throw new Error("exists() failed");
    if (map.exists(8)) throw new Error("exists() failed");
    map.add(8, 28);
    if (map.get(1) != 21) throw new Error("Insertion failed");
    if (map.get(8) != 28) throw new Error("Linear probing failed");
    map.remove(1);
    if (map.exists(1) || !map.exists(8)) throw new Error("Deletion failed");
  }

  class Entry {
    boolean deleted;
    int key;
    int val;

    Entry(int key, int val) {
      this.key = key;
      this.val = val;
      this.deleted = false;
    }
  }

  Entry[] arr = null;

  MyHashMap(int m) {
    arr = new Entry[m];
  }

  public int hash(int k, int m) {
    return k % m;
  }

  public void add(int key, int value) {
    Entry e = new Entry(key, value);
    int hash = hash(key, this.arr.length);
    while (arr[hash] != null && !arr[hash].deleted) {
      if (hash == this.arr.length - 1) {
        hash = 0;
      } else {
        hash = hash + 1;
      }
    }
    arr[hash] = e;
  }

  public boolean exists(int key) {
    int hash = hash(key, this.arr.length);
    while (arr[hash] != null) {
      if (arr[hash].key == key && !arr[hash].deleted) {
        return true;
      }
      if (hash == this.arr.length - 1) {
        hash = 0;
      } else {
        hash = hash + 1;
      }
    }
    return false;
  }

  public int get(int key) {
    int hash = hash(key, this.arr.length);
    while (arr[hash] != null) {
      if (arr[hash].key == key && !arr[hash].deleted) {
        return arr[hash].val;
      }
      if (hash == this.arr.length - 1) {
        hash = 0;
      } else {
        hash = hash + 1;
      }
    }
    return -1;
  }

  public void remove(int key) {
    int hash = hash(key, this.arr.length);
    while (arr[hash] != null) {
      if (arr[hash].key == key) {
        arr[hash].deleted = true;
      }
      if (hash == this.arr.length - 1) {
        hash = 0;
      } else {
        hash = hash + 1;
      }
    }
  }
}

interface IMyHashMap {
  int hash(int k, int m);
  void add(int key, int value);
  boolean exists(int key);
  int get(int key);
  void remove(int key);
}
