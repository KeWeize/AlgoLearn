package leetcode.weeklyContest72;

import java.util.ArrayList;
import java.util.List;

/**
 Given a string S, we can transform every letter individually to be lowercase or uppercase to
 create another string.  Return a list of all possible strings we could create.

 Examples:
 Input: S = "a1b2"
 Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

 Input: S = "3z4"
 Output: ["3z4", "3Z4"]

 Input: S = "12345"
 Output: ["12345"]

 Note:
 > S will be a string with length at most 12.
 > S will consist only of letters or digits.

 */

public class LetterCasePermutation {

    public static void main(String[] agrs) {
        letterCasePermutation("a1b2");
    }

    static List<String> result;
    public static List<String> letterCasePermutation(String S) {
        result = new ArrayList<>();
        //开始深搜
        dfs(S, 0, "");

        return result;
    }

    private static void dfs(String S, int index, String t) {

        if (index >= S.length()) {
            //结束递归条件
            System.out.println(t);
            result.add(t);
            return ;
        }

        char preChar = S.charAt(index);
        if (Character.isDigit(preChar)) {
            t += preChar;
            dfs(S, index+1, t);
            t = t.substring(0, t.length()-1);
        } else {
            if (Character.isLowerCase(preChar)) {
                //转换为大写字母
                dfs(S, index + 1, t + Character.toUpperCase(preChar));
            } else {
                dfs(S, index + 1, t + Character.toLowerCase(preChar));
            }
            t = t.substring(0, index);
            dfs(S, index + 1, t + preChar);
        }

    }

}
