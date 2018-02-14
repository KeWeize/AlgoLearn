import java.util.Scanner;

/**
 * 复习深度优先搜索
 *   深度优先是一种用于遍历或者搜索图的算法。沿着树的深度遍历树的节点。尽可能深的搜索树的分支。
 *   递归结束的条件：当某个节点没有下一个可到达的节点。
 */
public class DFSSort {

    public static void main(String[] agrs) {

        //输出1~n的全排序
//        question01();

        //迷宫
        question02();

    }


    /******************************** 输出 1~n的全排序 **********************************/
    /**
     * 1、输出 1~n的全排序
     * 如1~3的全排序为 1 2 3， 1 3 2， 2 1 3，2 3 1， 3 1 2， 3 2 1。
     */
    private static int[] nums01;
    private static int n01;
    //标记某个数是否已被使用
    private static int[] books;
    private static void question01() {
        //如果n太大的话会导致程序运行很久
        while (n01 <= 1 || n01 > 5) {
            System.out.println("请输入n的值(n大于1且小于6)：");
            Scanner scanner = new Scanner(System.in);
            n01 = scanner.nextInt();
        }
        nums01 = new int[n01];
        books = new int[n01];
        dfs01(0);
    }

    private static void dfs01(int step) {
        if (step >= n01) {
            //一次搜索结束，打印路径
            for (int i=0; i<nums01.length; i++) {
                System.out.print(nums01[i] + " ");
            }
            System.out.println();
            //跳出递归
            return ;
        }
        for (int i=0; i<n01; i++) {
            if (books[i] == 0) {
                //数i+1可用
                nums01[step] = i+1;
                books[i] = 1;
                dfs01(step+1);
                //该节点已搜索完一次路径，即将在当前节点上重新开始进行新的搜索，将i表示的数组重置为可使用
                books[i] = 0;
            }
        }
    }




    /*********************** 求两点之间的最短路径 ***********************/
    /**
     * 二维数组：
     *   0  0  1  0
     *   0  0  0  0
     *   0  0  1  0
     *   0  1  0  0
     *   0  0  0  1
     *  中代表这一个迷宫，其中1表示障碍物， 0表示空地。现从原点（0， 0）出发，求到达
     */
    //迷宫二维数组
    private static int[][] a02;
    //标记是否走过
    private static int[][] book02;
    //最小路径长度
    private static int min02 = 9999;
    //终点
    private static int[][] d = {
            {0, 1},     //向下
            {1, 0},     //向右
            {0, -1},    //向上
            {-1, 0}     //向左
    };
    private static int px, py;
    private static void question02() {
        a02 = new int[4][5];
        book02 = new int[4][5];
        a02[2][0] = 1;
        a02[2][2] = 1;
        a02[1][3] = 1;
        a02[3][4] = 1;
        px = 2;
        py = 3;

        book02[0][0] = 1;
        dfs02(0, 0, 0);
        System.out.println("从(0， 0)到(2, 3)的最短路程是：" + min02);
    }

    private static void dfs02(int x, int y, int step) {

        System.out.print("(" + x + ", " + y + ") -> ");

        if (x == px && y == py) {
            //到达了终点，计算此时路径长度
            if (step < min02) {
                min02 = step;
            }
            System.out.println();
            System.out.println("1条到达终点的路径结束");
            return ;
        }

        for (int i= 0; i<=3; i++) {
            //计算下一个点(下一个点有四个方向四种可能)
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];
            if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 5) {
                //此节点方向越界
                System.out.println("----节点" + nextX + ", " + nextY + "越界");
                continue;
            }
            if (a02[nextX][nextY] == 1 || book02[nextX][nextY] == 1) {
                //此节点为障碍或者已经走过
                System.out.println("----节点" + nextX + ", " + nextY + "为障碍或已经走过");
                continue;
            }
            //下一个访问节点合法
            book02[nextX][nextY] = 1;
            dfs02(nextX, nextY, step + 1);
            //当前路径访问完毕，该下一节点标记为未访问
            book02[nextX][nextY] = 0;
        }

    }



}
