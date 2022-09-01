import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static class Triple {
        final int item0;
        final int item1;
        final int item2;
        final List<Integer> list;
        final int hasCode;

        public Triple(int item0, int item1, int item2) {
            this.list = sort3(item0, item1, item2);
            this.item0 = list.get(0);
            this.item1 = list.get(1);
            this.item2 = list.get(2);
            this.hasCode = createHashCode();
        }

        private List<Integer> sort3(int item0, int item1, int item2) {
            int min = 0, mid = 0, max = 0;

            if (item0 <= item1 && item0 <= item2) {
                min = item0;
                if (item1 < item2) {
                    mid = item1;
                    max = item2;
                } else {
                    mid = item2;
                    max = item1;
                }
            } else
            if (item1 <= item0 && item1 <= item2) {
                min = item1;
                if (item0 < item2) {
                    mid = item0;
                    max = item2;
                } else {
                    mid = item2;
                    max = item0;
                }
            } else
            if (item2 <= item0 && item2 <= item1) {
                min = item2;
                if (item0 < item1) {
                    mid = item0;
                    max = item1;
                } else {
                    mid = item1;
                    max = item0;
                }
            }

            List<Integer> list = new ArrayList<>(3);
            list.add(min);
            list.add(mid);
            list.add(max);
            return list;
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

        public static Triple create(int item0, int item1, int item2) {
            return new Triple(item0, item1, item2);
        }
        public static void create2(int item0, int item1, int item2) {
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        final List<Triple> tempResult = new ArrayList<>(20000);
        Arrays.sort(nums);

        for (int i = 0; i < (nums.length-2); i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            int MAX_K = nums.length-1;
            int j = i+1, k = MAX_K;
            int prevJValue = nums[j];
            int prevJ = j;
            int prevKValue = nums[k];
            int prevK = k;
            while (j < k && j < (nums.length-1) && nums[i] <= nums[j]) {
                if (prevJ != j && prevJValue == nums[j]) {
                    prevJ = j;
                    j++;
                    k = MAX_K;
                    continue;
                }
                if (prevK != k && prevKValue == nums[k]) {
                    prevK = k;
                    k--;
                    continue;
                }

                prevJ = j;
                prevJValue = nums[j];
                prevK = k;
                prevKValue = nums[k];

                final int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    tempResult.add(Triple.create(nums[i], nums[j], nums[k]));
                    //
                    j++;
                    k--;
                    MAX_K = k;
                } else {
                    if (sum > 0) {
                        k--;
                        if (j == k) {
                            j++;
                            k = MAX_K;
                        }
                    } else {
                        j++;
                        k = MAX_K;
                    }
                }
            }
        }

        final List<List<Integer>> result = tempResult.stream().distinct().map(t -> t.list).collect(
                () -> new ArrayList<>(tempResult.size()),
                (list, item) -> list.add(item),
                (list1, list2) -> list1.addAll(list2)
        );
        return result;
    }

}
