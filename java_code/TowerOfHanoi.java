import java.lang.*;
import java.util.*;

public class TowerOfHanoi {
  public static void main(String[] args) {
    moveTower(2, "src", "dst", "aux");
  }

  private static void moveTower(int n, String src, String dst, String tmp) {
    if (n > 0) {
      moveTower(n - 1, src, tmp, dst);
      System.out.println("Moving from: " + src + " to : " + dst);
      moveTower(n - 1, tmp, dst, src);
    }
  }
}
