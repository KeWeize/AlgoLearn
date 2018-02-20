package base;

/**
 * 复习基本排序算法：
 *     选择排序  插入排序  冒泡排序
 *     归并排序  希尔排序  快速排序
 */
public class SortAlgo {

    private static int[] nums = {15, 8, 16, 20, 2, 0, -5, 99, 86, 45};

    public static void main(String[] atgs) {

        //选择排序
//        selectSort(nums);

        //插入排序
//        insertSort(nums);

        //冒泡排序
//        bubbleSort(nums);

        //希尔排序
//        shellSort(nums);

        //快速排序
//        quickSort(nums, 0, nums.length-1);
//        System.out.println("------ QuickSort ------");
//        show(nums);

        //归并排序
        mergeSort(nums);

    }



    /**
     * 选择排序
     * 选择排序的运行时间和输入结果无关，就算是对于已经排好序的数组同样需要走那么多步
     * 时间复杂度为 O(N^2)
     *
     * @param nums
     */
    private static void selectSort(int[] nums) {
        int N = nums.length;
        for (int i=0; i<N; i++) {
            int min = i;
            for (int j=i; j<N; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            exchange(nums, i, min);
        }
        System.out.println("------ selectSort ------");
        show(nums);
    }



    /**
     * 插入排序
     * 运行时间和输入结果有关，对部分有序的数组更加有效
     * 时间复杂度为 O(N^2)
     *
     * @param nums
     */
    private static void insertSort(int[] nums) {
        int N = nums.length;
        for (int i=1; i<N; i++) {
            for (int j=i; j>0 && nums[j]<nums[j-1]; j--) {
                exchange(nums, j, j-1);
            }
        }
        System.out.println("------ insertSort ------");
        show(nums);
    }



    /**
     * 冒泡排序
     * 与插入排序相似，对部分有序的数字更加有效
     * 时间复杂度为 O(N^2)
     *
     * @param nums
     */
    private static void bubbleSort(int[] nums) {
        int N = nums.length;
        for (int i=1; i<N; i++) {
            for (int j=0; j<N-i; j++) {
                if (nums[j] > nums[j+1]) {
                    exchange(nums, j, j+1);
                }
            }
        }
        System.out.println("------ bubbleSort ------");
        show(nums);
    }



    /**
     * 希尔排序
     * 是插入排序的一种改进版本，相比基础插入排序算法，希尔排序更快速的原因是希尔排序是跳跃式的交换和插入
     * 时间复杂度约为 O(N^1.3)
     *
     * @param nums
     */
    private static void shellSort(int[] nums) {

        int N = nums.length;
        //此处我们选择的递增行数为 h = 1, 2, 4, ..., N/2;
        int h = N / 2;

        while (h > 0) {
            for (int i=h; i<N; i++) {
                for (int j=i; j>=h && nums[j] < nums[j-h]; j-=h) {
                    exchange(nums, j, j-h);
                }
            }
            h = h/2;
        }
        System.out.println("------ ShellSort ------");
        show(nums);
    }



    /**
     * 快速排序
     * 是分治的一种经典实现，采用递归的方法。通过若干次分区操作，最终使得数据有序
     * 它相对于冒泡排序更快速是因为其每次交换都是跳跃式的
     * 时间复杂度 O(NlongN)
     *
     * @param nums
     * @param left
     * @param right
     */
    private static void quickSort(int[] nums, int left, int right) {

        if (left > right) {
            //结束递归判断条件
            return ;
        }
        //第一个数作为基准数
        int tmp = nums[left];
        int i = left;
        int j = right;
        while (i < j) {

            while (nums[j] >= tmp && j > i) {
                // j从右边开始往左寻找比基准数小的
                j--;
            }
            while (nums[i] <= tmp && i < j) {
                // i从左边开始往右寻找比基准数大的
                i++;
            }

            if (i < j) {
                //交换i、j位置上的值
                exchange(nums, i, j);
            }
        }
        //将基准数换到中间（i和j相遇的位置就是中间位置）
        exchange(nums, left, i);

        //将中间位置左边进行快速排序
        quickSort(nums, left, i-1);
        //将中间数右边进行快速排序
        quickSort(nums, i+1, right);
    }



    /**
     * 归并排序
     * 是分治思想的另一种典型实现，归并排序是将两个有序的数组合并成一个大的有序数组。使用递归的方式自下向上逐步让数组有序
     * {15, 8, 16, 20, 2, 0, -5, 99, 86, 45};
     * @param nums
     */
    private static void mergeSort(int[] nums) {
        aux = new int[nums.length];
        mergeSort(nums, 0, nums.length-1);
        show(nums);
    }

    //辅助归并排序数组
    private static int[] aux;

    private static void mergeSort(int[] nums, int left, int right) {

        if (left >= right) {
            //跳出递归条件
            return;
        }

        int mid = (left + right) / 2;

        //左半边排序
        mergeSort(nums, left, mid);
        //右半边排序
        mergeSort(nums, mid + 1, right);

        //将两个有序的数组归并为大的有序数组
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        //归并操作
        int i = left;
        int j = mid + 1;

        for (int k=left; k<=right; k++) {
            //将nums[left...right]复制到aux[left...right]中
            aux[k] = nums[k];
        }

        //原地归并到nums[left...right]中
        for (int k=left; k<=right; k++) {
            if (i > mid) {
                nums[k] = aux[j];
                j++;
            }else if (j > right) {
                nums[k] = aux[i];
                i++;
            }else if (aux[i] < aux[j]) {
                nums[k] = aux[i];
                i++;
            } else {
                nums[k] = aux[j];
                j++;
            }
        }
    }




    /******************************* 通用方法 *********************************/

    /**
     * 交换数组nums中 n位置 和 m位置上的值
     *
     * @param nums
     * @param n
     * @param m
     */
    private static void exchange(int[] nums, int n, int m) {
        int t = nums[n];
        nums[n] = nums[m];
        nums[m] = t;
    }

    /**
     * 按顺序打印nums中的值
     *
     * @param nums
     */
    private static void show(int[] nums) {
        System.out.println("nums sort : ");
        for (int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

}
