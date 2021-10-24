package 数据结构;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Heap {

    /**
     * 子堆已经是满足条件的堆了（父大于子）
     * @param tree
     * @param n 个数
     * @param i 操作对象
     */
    public static void heapify (int[] tree, int n, int i) {
        if (i >= n) {
            return;
        }
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;
        if(c1 < n && tree[c1] > tree[max]){
            max = c1;
        }
        if(c2 < n && tree[c2] > tree[max]){
            max = c2;
        }
        if(max != i){
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    private static void swap (int[] tree, int i, int j) {
        int temp = tree[j];
        tree[j] = tree[i];
        tree[i] = temp;
    }

    /**
     * 不满足条件的堆进行排序。采用从最后一个parent倒排的方式
     * @param tree
     * @param n
     */
    public static void build_heap (int[] tree, int n) {
        int last_node = n-1;
        int parent = (last_node -1) / 2;
        int i;
        for (i = parent; i >= 0; i--) {
            heapify(tree, n, i);
        }
    }


    public static void heap_sort (int[] tree, int n) {
        build_heap(tree, n);
        int i;
        for (i = n-1; i > 0; i--) {
            swap(tree, i, 0);
            heapify(tree, i, 0);
        }


    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 5, 10, 2};
        int n = arr.length;
        heap_sort(arr, n);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
