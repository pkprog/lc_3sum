import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static class Triple {
        int item0;
        int item1;
        int item2;
        final int[] list;

        public Triple(int item0, int item1, int item2) {
            list = new int[] {item0, item1, item2};
            this.item0 = item0;
            this.item1 = item1;
            this.item2 = item2;
        }

        public boolean equalsTriple(int test0, int test1, int test2) {
            final boolean[] equalsItemPositions = new boolean[3];
            boolean foundTriplet = true;
            for (int i = 0; i < 3; i++) {
                int item = list[i];

                boolean found = false;
                if (!equalsItemPositions[0] && item == test0) {
                    found = true;
                    equalsItemPositions[0] = true;
                } else
                if (!equalsItemPositions[1] && item == test1) {
                    found = true;
                    equalsItemPositions[1] = true;
                } else
                if (!equalsItemPositions[2] && item == test2) {
                    found = true;
                    equalsItemPositions[2] = true;
                }

                if (!found) {
                    foundTriplet = false;
                    break;
                }
            }

            return foundTriplet;
        }

        public List<Integer> toList() {
            return Arrays.asList(list[0], list[1], list[2]);
        }

        public static Triple crTriple(int item0, int item1, int item2) {
            return new Triple(item0, item1, item2);
        }
    }

    //true - if exists in Collection
    private boolean existsItem(List<Triple> list, int item0, int item1, int item2) {
        for (Triple triple: list) {
            if (triple.equalsTriple(item0, item1, item2)) {
                return true;
            }
        }
        return false;
    }

    private boolean has(List<Integer> nums, int value) {
        return nums.indexOf(value) > 0;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        final List<Triple> tempResult = new ArrayList<>(nums.length);

        final List<Integer> prevI = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (has(prevI, nums[i])) continue;

            final List<Integer> prevJ = new ArrayList<>(nums.length);
            for (int j = (i+1); j < nums.length; j++) {
                if (has(prevJ, nums[j])) continue;

                final int minusTemp = -(nums[i] + nums[j]);
                for (int k = (j+1); k < nums.length; k++) {
                    if (minusTemp == nums[k]) {
                        if (!existsItem(tempResult, nums[i], nums[j], nums[k])) {
                            tempResult.add(Triple.crTriple(nums[i], nums[j], nums[k]));
                        }
                    }
                }

                prevJ.add(nums[j]);
            }

            prevI.add(nums[i]);
        }

        return tempResult.parallelStream().map(t -> t.toList()).collect(Collectors.toList());
    }

}
