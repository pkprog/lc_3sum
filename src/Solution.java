import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static class Triple {
        final List<Integer> list;

        public Triple(int item0, int item1, int item2) {
            list = Arrays.asList(item0, item1, item2);
        }

        public boolean equalsTriple(int test0, int test1, int test2) {
            final boolean[] equalsItemPositions = new boolean[3];
            boolean foundTriplet = true;
            for (Integer item: list) {

                boolean found = false;
                if (!equalsItemPositions[0] && item.equals(test0)) {
                    found = true;
                    equalsItemPositions[0] = true;
                } else
                if (!equalsItemPositions[1] && item.equals(test1)) {
                    found = true;
                    equalsItemPositions[1] = true;
                } else
                if (!equalsItemPositions[2] && item.equals(test2)) {
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

    public List<List<Integer>> threeSum(int[] nums) {
        final List<Triple> tempResult = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            for (int j = (i+1); j < nums.length; j++) {
                final int minusTemp = -(nums[i] + nums[j]);
                for (int k = (j+1); k < nums.length; k++) {
                    if (minusTemp == nums[k]) {
                        if (!existsItem(tempResult, nums[i], nums[j], nums[k])) {
                            tempResult.add(new Triple(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
        }

        return tempResult.parallelStream().map(t -> t.list).collect(Collectors.toList());
    }

}
