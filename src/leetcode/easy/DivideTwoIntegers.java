package leetcode.easy;

import sun.applet.Main;

/**
 * DivideTwoIntegers:
 *
 *  Divide two integers without using multiplication, division and mod operator.
 *  If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {

    public static void main(String[] agrs) {

        System.out.println("result is " + divide02(-2147483648, -1));

    }


    /*************************** 使用while， 运行LeetCode上运行 72ms (16%) *******************************/
    public static int divide01(int dividend, int divisor) {

        if (divisor == 0) {
            throw new ArithmeticException("/ by zero");
        }

        long dividendL = (long)dividend;
        long divisorL = (long)divisor;

        boolean isNegative = false;
        if (dividend < 0 && divisor >= 0) {
            isNegative = true;
        }
        if (dividend >= 0 && divisor < 0) {
            isNegative = true;
        }

        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);
        System.out.println("处理完的因子 因母：" + dividendL + "、" + divisorL);

        long result = 0;
        int flag = 1;
        long[] bookMultNums = new long[34];
        long[] bookDivisor = new long[34];
        bookMultNums[1] = 1;
        bookDivisor[1] = divisorL;
        while (dividendL - divisorL >= 0 || flag != 1) {
            if (dividendL - divisorL >= 0) {
                dividendL -= divisorL;
                result += bookMultNums[flag];
                flag++;
                divisorL += divisorL;
                bookMultNums[flag] = bookMultNums[flag - 1] + bookMultNums[flag - 1];
                bookDivisor[flag] = divisorL;
                System.out.println("mult is " + bookMultNums[flag - 1]);
            } else {
                if (flag == 1) {
                    break;
                }
                flag -= 1;
                divisorL = bookDivisor[flag];
            }
        }
        result = isNegative ? 0 - result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }


    /*************************** 使用递归, LeetCode提交运行 45ms (40%) *******************************/

    private static long globalDividend;

    public static int divide02(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("分母不可为0");
        }

        long dividendL = (long) dividend;
        long divisorL = (long) divisor;
        boolean isNegative = false;
        if ((dividendL < 0 && divisorL > 0) || (dividendL > 0 && divisorL < 0)) {
            isNegative = true;
        }

        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);

        globalDividend = dividendL;

        long result = operate(dividendL, divisorL, 1);
        result = isNegative ? 0-result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

    public static long operate (long dividend, long divisor, long multNum){
        if (dividend < divisor) {
            return 0;
        }
        long result = 0;
        long addNums = operate(dividend, divisor + divisor, multNum + multNum);

        while (globalDividend - divisor >= 0) {
            result += multNum;
            globalDividend -= divisor;
        }
        return result + addNums;
    }

}
