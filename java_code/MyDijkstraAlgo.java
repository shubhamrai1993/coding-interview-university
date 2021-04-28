import java.lang.*;
import java.util.*;

public class MyDijkstraAlgo {
  public static void main(String[] args) {
    int[][] graph = new int[][] {
      { 0, 0, 1, 2, 0, 0, 0 },
      { 0, 0, 2, 0, 0, 3, 0 },
      { 1, 2, 0, 1, 3, 0, 0 },
      { 2, 0, 1, 0, 0, 0, 1 },
      { 0, 0, 3, 0, 0, 2, 0 },
      { 0, 3, 0, 0, 2, 0, 1 },
      { 0, 0, 0, 1, 0, 1, 0 }
    };
    int[] distance = findMinDistance(0, graph);
    for (int d : distance) {
      System.out.println(d);
    }
  }

  static int[] findMinDistance(int s, int[][] g) {
    if (g.length == 0) return new int[]{};
    // To keep track of the distance from source calculated till now
    int[] distanceArr = new int[g.length];
    // To keep track of the vertices visited till now
    boolean[] visitedArr = new boolean[g.length];
    // We start with max distance for all the vertices and no vertices visited
    for (int i = 0; i < g.length; i++) {
      distanceArr[i] = Integer.MAX_VALUE;
      visitedArr[i] = false;
    }
    // Distance of the source to itself will be 0
    distanceArr[s] = 0;

    // In each iteration we choose the vertex with the minimum calculated distance and mark it as being visited. Thus we need to run this operation the number of times
    // that the vertices are there.
    // For each of these vertices, once visited, we pick out its neighbours and calculate the updated distances for these vertices when seen from this vertex
    for (int i = 0; i < g.length; i++) {
      // Get the unvisited vertex with the minimum distance
      int minIndex = findMinDistanceIndex(distanceArr, visitedArr);
      // Mark this vertex as being visited so that we do not consider it again needlessly
      visitedArr[minIndex] = true;

      // Iterating over all of the selected vertex's unvisited neighbours
      for (int j = 0; j < g[minIndex].length; j++) {
        if (
            (g[minIndex][j] != 0) // Checking if there is an edge
            && !visitedArr[j] // Vertex should not be visited
            && (distanceArr[j] > distanceArr[minIndex] + g[minIndex][j]) // The newly calculated distance should be lesser than the already calculated one for it to be useful
        ) {
          distanceArr[j] = distanceArr[minIndex] + g[minIndex][j];
        }
      }
    }
    return distanceArr;
  }

  // Accepts arrays containing the distances calculated till now and the visited data. The unvisited vertex with minimum calculated distance is selected and returned
  // The index returned can be frozen and considered to be visited
  private static int findMinDistanceIndex(int[] distanceArr, boolean[] visitedVertex) {
    int minDistance = Integer.MAX_VALUE;
    int minDistanceIndex = -1;

    for (int i = 0; i < distanceArr.length; i++) {
      int distance = distanceArr[i];
      if (!visitedVertex[i] && distance < minDistance) {
        minDistance = distance;
        minDistanceIndex = i;
      }
    }
    return minDistanceIndex;
  }
}
