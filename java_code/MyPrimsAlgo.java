import java.lang.*;
import java.util.*;

public class MyPrimsAlgo {
  public static void main(String[] args) {
    int[][] g = {
      { 0, 9, 75, 0, 0 }, 
      { 9, 0, 95, 19, 42 }, 
      { 75, 95, 0, 51, 66 }, 
      { 0, 19, 51, 0, 31 },
      { 0, 42, 66, 31, 0 } 
    };
    printMst(g, 0);
  }

  // Idea is to select the vertex that is closest to the set of selected vertices one by one until all the vertices are selected
  static void printMst(int[][] g, int s) {
    // Array to hold the vertices that have been selected
    boolean[] selected = new boolean[g.length];
    for (int i = 0; i < selected.length; i++) {
      selected[i] = false;
    }
    // The source is selected firstly. Everything else follows from there. We print the selected vertex immediately after selection
    selected[s] = true;
    System.out.println(s);

    // We need to iterate V - 1 (no of edges) times where V is the total number of vertices in the graph
    for (int i = 0; i < g.length - 1; i++) {
      // In each iteration to select the closest vertex, we figure out these values
      int minDistance = Integer.MAX_VALUE;
      int minDistanceIndex = -1;

      // Iterating over rows of the adjacency matrix
      for (int j = 0; j < g.length; j++) {
        // We need to filter out the vertices that have already been selected. The condition is to figure out which vertex is the closest to the set of selected vertices
        if (selected[j]) {
          // Iterating over columns
          for (int k = 0; k < g.length; k++) {
            // Non selected vertex which is connected to the selected vertices
            if (!selected[k] && g[j][k] > 0) {
              if (g[j][k] < minDistance) {
                minDistance = g[j][k];
                minDistanceIndex = k;
              }
            }
          }
        }
      }

      System.out.println(minDistanceIndex);
      selected[minDistanceIndex] = true;
    }
  }
}
