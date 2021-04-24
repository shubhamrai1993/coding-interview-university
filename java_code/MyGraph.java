import java.lang.*;
import java.util.*;

public class MyGraph {
  public static void main(String[] args) {
    AdjacencyListGraph.getGraphInstance().dfs();
    AdjacencyListGraph.getGraphInstance().bfs();
  }
}

class AdjacencyListGraph {
  List<AdjacencyListNode> nodes = new ArrayList<>();

  public static AdjacencyListGraph getGraphInstance() {
    AdjacencyListNode node0 = new AdjacencyListNode(0);
    AdjacencyListNode node1 = new AdjacencyListNode(1);
    AdjacencyListNode node2 = new AdjacencyListNode(2);
    AdjacencyListNode node3 = new AdjacencyListNode(3);
    AdjacencyListNode node4 = new AdjacencyListNode(4);
    AdjacencyListNode node5 = new AdjacencyListNode(5);

    node0.children.add(node1);
    node0.children.add(node4);
    node0.children.add(node5);

    node1.children.add(node4);
    node1.children.add(node3);

    node2.children.add(node1);

    node3.children.add(node2);
    node3.children.add(node4);

    AdjacencyListGraph graph = new AdjacencyListGraph();
    graph.nodes.add(node0);
    graph.nodes.add(node1);
    graph.nodes.add(node2);
    graph.nodes.add(node3);
    graph.nodes.add(node4);
    graph.nodes.add(node5);

    return graph;
  }

  public void bfs() {
    Queue<AdjacencyListNode> q = new LinkedList<>();
    Set<AdjacencyListNode> visitedSet = new HashSet<>();
    q.offer(nodes.get(0));

    while (!q.isEmpty()) {
      AdjacencyListNode n = q.poll();
      if (!visitedSet.contains(n)) {
        System.out.println(n.value);
        visitedSet.add(n);
        for (AdjacencyListNode child : n.children) {
          q.add(child);
        }
      }
    }
  }

  public void dfs() {
    Set<AdjacencyListNode> visitedSet = new HashSet<>();
    for (AdjacencyListNode node : nodes) {
      if (!visitedSet.contains(node)) traverseDfsIterative(node, visitedSet);
    }
  }

  private void traverseDfs(AdjacencyListNode root, Set<AdjacencyListNode> visitedSet) {
    if (root == null) return;
    visitedSet.add(root);
    System.out.println(root.value);
    for (AdjacencyListNode node : root.children) {
      if (!visitedSet.contains(node)) traverseDfs(node, visitedSet);
    }
  }

  private void traverseDfsIterative(AdjacencyListNode root, Set<AdjacencyListNode> visitedSet) {
    Stack<AdjacencyListNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      AdjacencyListNode n = stack.pop();
      if (!visitedSet.contains(n)) {
        System.out.println(n.value);
        visitedSet.add(n);

        for (AdjacencyListNode c : n.children) {
          if (!visitedSet.contains(c)) {
            stack.push(c);
          }
        }
      }
    }
  }
}
class AdjacencyListNode {
  int value;
  List<AdjacencyListNode> children = new ArrayList<>();

  AdjacencyListNode(int value) {
    this.value = value;
  }
}

class AdjacencyMatrixGraph {
  
}
