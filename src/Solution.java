import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static class Triple {
        final int item0;
        final int item1;
        final int item2;
        final List<Integer> list;
        final int hasCode;

        public Triple(int item0, int item1, int item2) {
            list = Arrays.asList(item0, item1, item2);
            Collections.sort(list);
//            int[] a = new int[] {item0, item1, item2};
//            Arrays.sort(a);
            this.item0 = list.get(0);
            this.item1 = list.get(1);
            this.item2 = list.get(2);
//            this.item0 = a[0];
//            this.item1 = a[1];
//            this.item2 = a[2];
            this.hasCode = createHashCode();
        }

        @Override
        public boolean equals(Object o) {
            Triple triple = (Triple) o;
            if (item0 != triple.item0) return false;
            if (item1 != triple.item1) return false;
            return item2 == triple.item2;
        }

        private int createHashCode() {
            int result = item0;
            result = 31 * result + item1;
            result = 31 * result + item2;
            return result;
        }

        @Override
        public int hashCode() {
            return this.hasCode;
        }

        public List<Integer> toList() {
            return this.list; //Arrays.asList(item0, item1, item2);
        }

        public static Triple create(int item0, int item1, int item2) {
            return new Triple(item0, item1, item2);
        }
        public static void create2(int item0, int item1, int item2) {
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        final List<Triple> tempResult = new ArrayList<>(30000);
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            int MAX_K = nums.length-1;
            int j = i+1, k = MAX_K;
            while (j < k && j < nums.length) {
                final int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    tempResult.add(Triple.create(nums[i], nums[j], nums[k]));
                    //
                    j++;
                    k--;
                    MAX_K = k;
                } else {
                    if (sum > 0) {
                        if (j == (k-1)) {
                            j++;
                            k = MAX_K;
                        } else{
                            k--;
                        }
                    } else if (sum < 0) {
                        j++;
                        k = MAX_K;
                    }
                }
            }
        }

        final List<List<Integer>> result = tempResult.stream().distinct().map(Triple::toList).collect(Collectors.toList());
        return result;
    }

}
