import java.lang.*;
import java.util.*;

public class MyBST implements IMyBST {
  Node root = null;

  MyBST() {}

  MyBST(Node root) {
    this.root = root;
  }

  public Node insert(int data) {
    Node node = new Node(data);
    if (root == null) {
      root = node;
    } else {
      Node current = root;
      while (true) {
        if (node.val < current.val) {
          if (current.left == null) {
            current.left = node;
            break;
          } else {
            current = current.left;
          }
        } else {
          if (current.right == null) {
            current.right = node;
            break;
          } else {
            current = current.right;
          }
        }
      }
    }
    return node;
  }

  public int getNodeCount() {
    int count = 0;
    Stack<Node> s = new Stack<>();
    Node current = this.root;

    while (!s.empty() || current != null) {
      while (current != null) {
        s.push(current);
        current = current.left;
      }
      current = s.pop();
      count++;
      current = current.right;
    }
    return count;
  }

  public void printValues() {
    Stack<Node> stack = new Stack<>();
    Node current = root;
    while (!stack.empty() || current != null) {
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
      current = stack.pop();
      System.out.println(current.toString());
      current = current.right;
    }
  }

  public boolean isInTree(int val) {
    Node current = this.root;
    while (current != null) {
      if (val == current.val) {
        return true;
      }
      if (current.val > val) {
        current = current.left;
      } else {
        current = current.right;
      }
    }
    return false;
  }

  public int getHeight() {
    return getHeight(this.root);
  }

  private int getHeight(Node root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = getHeight(root.left);
    int rightHeight = getHeight(root.right);
    if (leftHeight > rightHeight) {
      return leftHeight + 1;
    } else {
      return rightHeight + 1;
    }
  }

  public int getMin() {
    if (this.root == null) return -1;
    Node current = this.root;
    while (current.left != null) {
      current = current.left;
    }
    return current.val;
  }

  public int getMax() {
    if (this.root == null) {
      return -1;
    }
    Node current = this.root;
    while (current.right != null) {
      current = current.right;
    }
    return current.val;
  }

  public boolean isBinarySearchTree() {
    return isBSTUtil(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean isBSTUtil(Node node, int min, int max) {
    if (node == null) {
      return true;
    }
    if (node.val < min || node.val > max) return false;
    return (isBSTUtil(node.left, min, node.val - 1) && isBSTUtil(node.right, node.val + 1, max));
  }

  public void deleteValue(int val) {
  }

  public static void main(String[] args) {
    MyBST bst = new MyBST();
    bst.insert(1);
    bst.insert(4);
    bst.insert(2);
    if (!bst.isInTree(2)) throw new Error("Insertion failed");
    if (bst.isInTree(0)) throw new Error("Insertion failed");
    bst.insert(5);
    bst.printValues();
    System.out.println(bst.getHeight());
    System.out.println(bst.getNodeCount());
    System.out.println(bst.getMin());
    System.out.println(bst.getMax());
    System.out.println(bst.isBinarySearchTree());
  }
}

class Node {
  Node left;
  Node right;
  int val;

  Node(int val) {
    this.val = val;
  }

  public String toString() {
    String left = "nullL";
    if (this.left != null) {
      left = this.left.val + "";
    }
    String right = "nullR";
    if (this.right != null) {
      right = this.right.val + "";
    }
    return this.val + "," + left + "," + right;
  }
}

interface IMyBST {
  Node insert(int data);
  int getNodeCount();
  void printValues();
  boolean isInTree(int val);
  int getHeight();
  int getMin();
  int getMax();
  boolean isBinarySearchTree();
  void deleteValue(int val);
}
