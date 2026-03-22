package array.sort;

import java.util.Arrays;

/**
 * 数组排序工具类，集中存放多种排序算法
 */
public class SortArray {
    // ========== 1. 冒泡排序（基础） ==========
    public void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        //冒泡排序
        for (int i = 0; i < n - 1; i++) { //第一层循环是执行n-1次，每次把最大的的元素移到最后
            for (int j = 0; j < n - 1 - i; j++) { //第二层循环是比较前面还没有完成排序的元素，共n-i个，但是比较是前后元素，所以可以需要-1
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }//交换次数多，但比较高效一点

    // ========== 2.选择排序(给到拉完了，优点是很好想) =========
    public void selectionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) { //找到n-1个最小值
            int min = i;
            for (int j = i; j < n; j++) { //后面未排序的最小值
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            //交换第i小的元素和原本的元素
            int tmp = nums[min];
            nums[min] = nums[i];
            nums[i] = tmp;
        }
    }//交换次数少，但效率固定最低

    // ========== 3. 插入排序（比冒泡高效，基础） ==========
    public void insertionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int tmp = nums[i];
            //与前面已排好序的元素比较，找到插入位置
            int j = i - 1;
            while (j >= 0 && nums[j] > tmp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
        }
    }

    // ========== 4. 快速排序（基础版，分治思想） ==========
    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 调用递归核心方法，排序整个数组
        quickSort(nums, 0, nums.length - 1);
    }//平均效率最高，时间复杂度O(nlogn)，最坏O(n²)

    // 快速排序递归核心方法：排序nums[left...right]区间
    private void quickSort(int[] nums, int left, int right) {
        // 递归终止条件：子数组只有1个元素或空，无需排序
        if (left >= right) {
            return;
        }
        // 分区：返回基准值的最终位置
        int pivotIndex = partition(nums, left, right);
        // 递归排序左半区（基准左侧）
        quickSort(nums, left, pivotIndex - 1);
        // 递归排序右半区（基准右侧）
        quickSort(nums, pivotIndex + 1, right);
    }

    // 快速排序分区方法：选最右侧元素为基准，划分左小右大
    private int partition(int[] nums, int left, int right) {
        // 基准值：选最右侧元素
        int pivot = nums[right];
        // i：左区的最后一个位置（初始为left-1，表示左区为空）
        int i = left - 1;

        // 遍历left到right-1的所有元素，划分左右区
        for (int j = left; j < right; j++) {
            // 当前元素<=基准，放入左区
            if (nums[j] <= pivot) {
                i++; // 左区边界右移
                // 交换元素到左区
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }

        // 把基准值放到最终位置（i+1）
        int tmp = nums[i + 1];
        nums[i + 1] = nums[right];
        nums[right] = tmp;
        // 返回基准值索引
        return i + 1;
    }

    public static void main(String[] args) {
        SortArray sorter = new SortArray();
        int[] testNums = {5, 2, 3, 1, 4};
        System.out.println("排序前：" + Arrays.toString(testNums));

        // 可切换测试不同排序算法
        // sorter.bubbleSort(testNums);
        // sorter.selectionSort(testNums);
        // sorter.insertionSort(testNums);
        sorter.quickSort(testNums);

        System.out.println("排序后：" + Arrays.toString(testNums));
    }
}