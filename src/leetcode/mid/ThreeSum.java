package leetcode.mid;

import java.util.*;

/**
 * 3Sums:
 * Given an array S of n integers, are there elements a, b, c in S such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
  [
    [-1, 0, 1],
    [-1, -1, 2]
  ]
 */
public class ThreeSum {

    public static void main(String[] agrs) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }



    public static List<List<Integer>> threeSum(int[] num){
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        System.out.println(num[i] + "+" + num[lo] + "+" + num[hi] + "=0");
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }



    /************************ 深搜的解决方案，当输入长度较长时会造成Time Limit Exceeded。在没有限制输入长度时需要谨慎使用深搜 *************************/

//    /**
//     * 思路1：使用深度优先搜索找出所有a+b+c=0的组合
//     *        利用 "abc排序后" 组成的字符串的唯一性排重
//     * @param nums
//     * @return
//     */
//    static int[] resultIndex = new int[3];
//    static int[] books;
//    static HashMap<String, String> map;
//    static List<List<Integer>> result;
//    public static List<List<Integer>> threeSum01(int[] nums) {
//        result = new ArrayList<>();
//        map = new HashMap<>();
//        books = new int[nums.length];
//        dfs(nums, 0);
//
//        return result;
//    }
//
//    private static void dfs(int[] nums, int step) {
//        if (step > 2) {
//
//            int a = nums[resultIndex[0]];
//            int b = nums[resultIndex[1]];
//            int c = nums[resultIndex[2]];
//
//            if (a + b + c == 0) {
//                String flag = getFlagBySort(a, b, c);
//                if (!map.containsKey(flag)) {
//                    map.put(flag, "");
//                    List<Integer> preResult = new ArrayList<>();
//                    preResult.add(a);
//                    preResult.add(b);
//                    preResult.add(c);
//                    result.add(preResult);
//                }
//            }
//            return ;
//        }
//        for (int i=0; i<nums.length; i++) {
//            if (books[i] == 1) {
//                continue;
//            }
//            books[i] = 1;
//            resultIndex[step] = i;
//            dfs(nums, step+1);
//            books[i] = 0;
//        }
//    }
//
//    /**
//     * a、b、c排序后生成一个String
//     * @param a
//     * @param b
//     * @param c
//     * @return
//     */
//    private static String getFlagBySort(int a, int b, int c) {
//        int[] tmpNums = new int[3];
//        tmpNums[0] = a;
//        tmpNums[1] = b;
//        tmpNums[2] = c;
//        String result = "";
//        for (int i=0; i<tmpNums.length-1; i++) {
//            for (int j=i+1; j>0 && tmpNums[j] < tmpNums[j-1]; j--) {
//                int tmp = tmpNums[j];
//                tmpNums[j] = tmpNums[j-1];
//                tmpNums[j-1] = tmp;
//            }
//        }
//        for (int i=0; i<tmpNums.length; i++) {
//            result += String.valueOf(tmpNums[i]);
//        }
//        return result;
//    }

    /************************ 深搜的解决方案，当输入长度较长时会造成Time Limit Exceeded。在没有限制输入长度时需要谨慎使用深搜 *************************/

}
