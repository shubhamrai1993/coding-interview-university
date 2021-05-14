import java.util.*;
import java.lang.*;

public class MergeMultipleArrays {
  public static void main(String[] args) {
    List<List<Integer>> fullList = new ArrayList<>();
    List<Integer> list1 = Arrays.asList(3, 5, 7);
    List<Integer> list2 = Arrays.asList(0, 6);
    List<Integer> list3 = Arrays.asList(0, 6, 28);
    List<Integer> list4 = Arrays.asList();
    fullList.add(list1);
    fullList.add(list2);
    fullList.add(list3);
    fullList.add(list4);
    MergeMultipleArrays a = new MergeMultipleArrays();
    List<Integer> l = a.sort(fullList);
    for (int i = 0; i < l.size(); i++) System.out.println(l.get(i));
  }

  public List<Integer> sort(List<List<Integer>> l) {
    PriorityQueue<ValIndex> pq = new PriorityQueue<>();
    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < l.size(); i++) {
      indices.add(0);
      if (!l.get(i).isEmpty()) {
        pq.add(new ValIndex(l.get(i).get(0), i));
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      ValIndex min = pq.poll();
      result.add(min.val);

      int listIndex = min.index;
      int index = indices.get(listIndex);
      if (index < l.get(listIndex).size() - 1) {
        index++;
        pq.add(new ValIndex(l.get(listIndex).get(index), listIndex));
        indices.set(listIndex, index);
      }
    }
    return result;
  }

}

class ValIndex implements Comparable<ValIndex> {
  int val;
  int index;

  ValIndex(int val, int index) {
    this.val = val;
    this.index = index;
  }

  public int compareTo(ValIndex that) {
    return this.val - that.val;
  }
}
