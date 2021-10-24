package leetcode;

import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.shape.HLineTo;
import 数据结构.TreeNode;

import java.util.*;

public class Main {

    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * Example:
     * Input: [1,null,2,3]
     *     1
     *       \
     *         2
     *       /
     *     3
     * Output: [1,2,3]
     * @param root
     * @return
     */
    public static ArrayList<Integer> preorderTraversal (TreeNode root){
        ArrayList<Integer> out = new ArrayList<>();
        Stack<TreeNode> q = new  Stack<>();

        if (root == null){
            return out;
        }
        q.push(root);
        while(!q.isEmpty()){
            TreeNode temp = q.pop();
            if(temp.getRightNode()!=null){
                q.push(temp.getRightNode());
            }

            if(temp.getLefTreeNode()!=null){
                q.push(temp.getLefTreeNode());
            }

            out.add(temp.getValue());
        }
        return out;
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * @param
     */
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> ans = new ArrayList<>();
        int len  = nums.length;
        if(nums == null || len<3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if(nums[i]>0) break;//如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i>0 && nums[i] == nums[i-1]) continue;//去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while(L<R && nums[L] == nums[L+1]) L++;//去重
                    while(L<R && nums[R] == nums[R-1]) R--;//去重
                    L++;
                    R--;
                }
                else if(sum<0) L++;
                else if(sum>0) R--;
            }
        }
        return ans;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param
     */
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x+y+carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j] == target-nums[i]){
                    return new int[] {i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[] {map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s 12121909090990
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128];// current index of character
        // try to extend the range [i, j]
        for(int j = 0, i = 0; j < n; j++){
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j+1;
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int maxSize = 0;
        int [] dict = new int[256]; //记录ASCII 码字符出现的位置，以字符作为下标
        int base = 0;
        int key = 0;
        int i=0;
        while(key < s.length()){
            i = s.charAt(key);
            if(dict[i] > base)
                base = dict[i];
            dict[i] = key+1; //以1作为起始位置，下标加1
            maxSize = (maxSize>key-base+1)?maxSize:key-base+1;
            key++;
        }
        return maxSize;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：中心扩展算法
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s == null || s.length()<1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i , i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start,end+1);
    }

    private static int expandAroundCenter(String s, int left, int right){
        int L = left, R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     *  @param
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     *
     *
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     *
     * 说明：m 和 n 的值均不超过 100。
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                cur[j] +=cur[j-1];
            }
        }
        return cur[n-1];
    }

    /**
     * 求盛最多的水的容器
     * @param height
     * @return
     */
    public static int maxArea(int[] height){
        int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxarea = Math.max(maxarea,Math.min(height[i],height[j]) * (j-i));
            }
        }
        return maxarea;
    }

    /**
     * 找出最长公共字符串
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 0; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    /**
     * Given a sorted interger array where the range of elements are in the inclusive range
     * [lower, upper], return its missing ranges
     * @param nums 0,2,34,59
     * @param lower 0
     * @param upper 89
     * @return 1,3->33, 35->58, 60->88
     */
    private static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        //Step 1: Define return value
        List<String> rst = new ArrayList<>();

        //Step 2: handle coener cases
        if (nums == null || nums.length ==0) {
            rst.add(lower + "->" + upper);
            return rst;
        }
        //Step 3: Fill in business logic
        //first, add the range after lower
        addToList(rst, lower, nums[0] - 1);
        //secrond, add all the ranges between lower and upper
        int prev = nums[0];
        int i = 1;
        while (i < nums.length) {
            int cur = nums[i];
            if (cur != prev + 1) {
                addToList(rst, prev+1, cur-1);
            }
            prev = cur;
            i++;
        }
        //third:, add the range before upper
        addToList(rst, nums[nums.length-1]+1, upper);
        return rst;
    }
    public static void addToList(List<String> rst, int start, int end) {
        if (start == end) {
            rst.add(String.valueOf(start));
        } else if (start < end) {
            rst.add(start + "->" + end);
        }
    }

    /**
     * 不同的二叉搜索树
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = 0;
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];//卡特兰数
            }
        }

        return dp[n];
    }

    //给定一个二叉树，找出其最小深度。
    //
    // 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
    //
    // 说明: 叶子节点是指没有子节点的节点。
    //
    // 示例:
    //
    // 给定二叉树 [3,9,20,null,null,15,7],
    //
    //     3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    //
    // 返回它的最小深度 2.
    // Related Topics 树 深度优先搜索 广度优先搜索
    public static int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.getLefTreeNode() == null && root.getRightNode() == null) {
                return 1;
            }

            int mindepth = Integer.MAX_VALUE;
            if (root.getLefTreeNode() != null) {
                mindepth = Math.min(minDepth(root.getLefTreeNode()), mindepth);
            }
            if (root.getRightNode() != null) {
                mindepth = Math.min(minDepth(root.getRightNode()), mindepth);
            }
            return mindepth + 1;
        }



    public static void main(String[] args) {


//        TreeNode root = new TreeNode();
//        root.setValue(3);
//        TreeNode node1 = new TreeNode();
//        node1.setValue(4);
//        root.setLefTreeNode(node1);
//        TreeNode node2 = new TreeNode();
//        node2.setValue(6);
//        root.setRightNode(node2);
//        node2.setLefTreeNode(new TreeNode(8));
//        TreeNode node3 = new TreeNode();
//        node3.setValue(5);
//        node2.setLefTreeNode(node3);
//        node3.setRightNode(new TreeNode(2));
//        node3.getRightNode().setLefTreeNode(new TreeNode(1));
//
//        System.out.println(minDepth(root));
//        ArrayList<Integer> list = preorderTraversal(root);
//
//
//
//        int numTrees = numTrees(3);

//        int[] i = {2,1,5,0,-6,-6,6,-3,-2,-1,3,1,2};
//        List<List<Integer>> list = threeSum(i);

//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(1);
//        l1.val = 2;
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        l2.val = 5;
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//
//        ListNode listNode = addTwoNumbers(l1, l2);


//        int[] twoSum = twoSum2(i, 6);

//        String str = "12121909090990";
//        int l = lengthOfLongestSubstring(str);


//        String s = longestPalindrome(str);

//        uniquePaths(2,1);

//        String[] str = {"vds","df","vdg"};
//        String s = longestCommonPrefix(str);
//        System.out.println(s);

//        int[] nums = {0,2,34,59};
//        int lower = 0;
//        int upper = 89;
//        List<String> missingRanges = findMissingRanges(nums, lower, upper);
//        for (int i = 0; i < missingRanges.size(); i++) {
//            System.out.println(missingRanges.get(i));
//        }
    }

}
