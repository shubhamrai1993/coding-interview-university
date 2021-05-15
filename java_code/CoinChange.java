import java.lang.*;
import java.util.*;

public class CoinChange {
  public static void main(String[] args) {
    int[] denominations = new int[] {2, 3, 7};
    int ways = calculateWays(denominations, 0, 12, new Stack<Integer>());
    System.out.println(ways);
  }

  static int calculateWays(
      // denominations
      int[] den,
      // The element from which point to pick up the element under consideration */
      int offset,
      int sum,
      /*Keeps track of the elements selected in this particular recursion till this point*/Stack<Integer> results) {
    // Sum being 0 means we have achieved the sum and nothing more is required
    if (sum == 0) {
      for (int i = 0; i < results.size(); i++) {
        System.out.print(results.get(i) + ",");
      }
      System.out.println();
      // Returning 1 ie we have found one way of reaching the targeted sum
      return 1;
    }
    // Offset going beyond the denomination array means, we will not be able to reach the target
    if (offset >= den.length) return 0;

    int ways = 0;
    // We are iterating over the number of times we want to select the element at the offset
    for (int j = 0; j*den[offset] <= sum; j++) {
      for (int k = 0; k < j; k++) {
        results.push(den[offset]);
      }

      ways = ways + calculateWays(den, offset + 1, sum - j*den[offset], results);

      for (int k = 0; k < j; k++) {
        results.pop();
      }
    }
    return ways;
  }
}
