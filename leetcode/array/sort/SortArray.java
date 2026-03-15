package array.sort;

import java.util.Arrays;

/**
 * 数组排序工具类，集中存放多种排序算法
 */
public class SortArray {
    // ========== 1. 冒泡排序（基础） ==========
    public void bubbleSort(int[] nums){
        if(nums==null||nums.length<=1){return;}
        int n=nums.length;
        //冒泡排序
        for(int i=0;i<n-1;i++){//第一层循环是执行n-1次，每次把最大的的元素移到最后
            for(int j=0;j<n-1-i;j++){//第二层循环是比较前面还没有完成排序的元素，共n-i个，但是比较是前后元素，所以可以需要-1
                if(nums[j]>nums[j+1]){
                    int tmp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=tmp;
                }
            }
        }
    }

    // ========== 2. 插入排序（比冒泡高效，基础） ==========
    public void insertionSort(int[] nums){
        if(nums==null||nums.length<=1){return;}
        int n=nums.length;
        for(int i=1;i<n;i++){
            int tmp=nums[i];
            int j=i-1;
            for(;j>=0;j--){
                if(tmp<nums[j]){
                    nums[j+1]=nums[j];
                }
                else{break;}
            }
            nums[j+1]=tmp;
        }
    }

    public  static void main(String[] args){
        SortArray sorter=new SortArray();
        int[] testNums = {5,2,3,1,4};
        System.out.println("排序前："+Arrays.toString(testNums));

        sorter.insertionSort(testNums);
        System.out.println("排序后："+Arrays.toString(testNums));
    }
}
