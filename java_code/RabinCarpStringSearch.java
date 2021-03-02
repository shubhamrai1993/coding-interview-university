import java.util.*;
import java.lang.*;

public class RabinCarpStringSearch {
  public static void main(String[] args) {
  }

  public int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) return -1;
    if (needle.isEmpty()) return 0;

    int l = needle.length();
    int p = calculateNextPrime(l);

    int power = 1;
    for (int i = 0; i < l - 1; i++) {
      power = (power * 26) % p;
    }

    int needleHash = calculateHash(needle, p);
    int runningHash = 0;

    for (int i = 0; i <= haystack.length() - l; i++) {
      if (i == 0) {
        runningHash = calculateHash(haystack.substring(0, l), p);
      } else {
        runningHash = (26 * (runningHash - haystack.charAt(i - 1) * power) + haystack.charAt(i + l - 1)) % p;
        if (runningHash < 0) {
            runningHash = runningHash + p;
        }
      }
      if (runningHash == needleHash) {
        if (needle.equals(haystack.substring(i, i + l))) {
          return i;
        }
      }
    }
    return -1;
  }

  private int calculateNextPrime(int a) {
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    for (int i = 3;; i = i + 2) {
      boolean found = false;
      for (int j = 0; j < primes.size(); j++) {
        if (i % primes.get(j) == 0) {
          found = true;
          break;
        }
      }
      if (i >= a) return i;
      if (!found) primes.add(i);
    }
  }

  private int calculateHash(String a, int mod) {
    int result = 0;
    for (int i = 0; i < a.length(); i++) {
      result = (result * 26 + a.charAt(i)) % mod;
    }
    return result;
  }
}
