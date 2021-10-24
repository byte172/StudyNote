package leetcode;

import org.springframework.util.StringUtils;
import sun.security.provider.MD5;
import 数据结构.TreeNode;

import java.util.*;

public class Main1 {

    /**
     * 1 两数之和
     *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 2 两数相加
     *
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
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
            if (l1 != null){
                l1 = l1.next;
            }

            if (l2 != null){
                l2 = l2.next;
            }
        }
        if(carry == 1){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 3 无重复字符的最长子串
     *
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> occ = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;

        for (int i = 0; i<n; ++i) {
            if (i != 0) {
                occ.remove(s.charAt(i-1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, rk-i+1);
        }
        return ans;
    }

    /**
     * 5. 最长回文子串
     *
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0;i < s.length(); i++) {
            int len1 = expandAroundCenter(s,i ,i);
            int len2 = expandAroundCenter(s,i ,i+1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter (String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     *7. 整数反转
     *
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     */
    public int reverse(int x) {
        int result = 0;
        int num = 0;
        while (x != 0) {
            num = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE/10 || result == Integer.MAX_VALUE/10 && num>7) {
                return 0;
            }
            if (result < Integer.MIN_VALUE/10 || result == Integer.MIN_VALUE/10 && num<-8) {
                return 0;
            }

            result = result*10 + num;
        }
        return result;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     *
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     *
     * 提示：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *  
     *
     * 示例 1:
     *
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     *
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     *
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     *
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     * 示例 5:
     *
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     */
    public int myAtoi(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();

        int index = 0;
        while (index < len && chars[index] == ' ') {
            index++;
        }

        if (index == len) {
            return 0;
        }

        int sign = 1;
        char firstchar = chars[index];
        if (firstchar == '+') {
            index++;
        } else if (firstchar == '-') {
            index++;
            sign = -1;
        }


        int res = 0;
        while (index < len) {
            int curChar = chars[index];

            if (curChar > '9' || curChar < '0') {
                break;
            }


            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && curChar - '0' > (Integer.MAX_VALUE % 10))) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (curChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (curChar - '0');
            index++;
        }
        return res;
    }

    /**
     * 9. 回文数
     *
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 示例 1:
     *
     * 输入: 121
     * 输出: true
     * 示例 2:
     *
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     *
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     */
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revx = 0;
        while (x > revx) {
            revx = revx * 10 + x % 10;
            x /= 10;
        }

        return x == revx || x == revx/10;
    }

    /**
     * 4 寻找两个正序数组的中位数
     *
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     * // 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * // 你可以假设 nums1 和 nums2 不会同时为空。
     * //
     * // 示例 1:
     * //
     * // nums1 = [1, 3]
     * //nums2 = [2]
     * //
     * //则中位数是 2.0
     * //
     * //
     * // 示例 2:
     * //
     * // nums1 = [1, 2]
     * //nums2 = [3, 4]
     * //
     * //则中位数是 (2 + 3)/2 = 2.5
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] nums = new int[m + n];

        if (m == 0) {
            if (n % 2 == 0) {
                return  (nums2[n / 2 -1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }

        if (n == 0) {
            if (m % 2 == 0) {
                return  (nums1[m / 2 -1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }


        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            }else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        }else {
            return nums[count / 2];
        }
    }

    /**
     * 14 最长公共前缀
     *
     * //编写一个函数来查找字符串数组中的最长公共前缀。
     * //
     * // 如果不存在公共前缀，返回空字符串 ""。
     * //
     * // 示例 1:
     * //
     * // 输入: ["flower","flow","flight"]
     * //输出: "fl"
     * //
     * //
     * // 示例 2:
     * //
     * // 输入: ["dog","racecar","car"]
     * //输出: ""
     * //解释: 输入不存在公共前缀。
     * //
     * //
     * // 说明:
     * //
     * // 所有输入只包含小写字母 a-z 。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }

       String pre = strs[0];
       int len = strs[0].length();
       int count = strs.length;

       for (int i = 0;i<len;i++) {
           char c = strs[0].charAt(i);
           for (int j = 1; j < count; j++) {
               if (strs[j].length() == i || strs[j].charAt(i) != c) {
                   return strs[0].substring(0,i);
               }
           }
       }
       return pre;
    }

    /**
     * 16 最接近的三数之和
     *
     * //给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
     * //。假定每组输入只存在唯一答案。
     * //
     * //
     * //
     * // 示例：
     * //
     * // 输入：nums = [-1,2,1,-4], target = 1
     * //输出：2
     * //解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 3 <= nums.length <= 10^3
     * // -10^3 <= nums[i] <= 10^3
     * // -10^4 <= target <= 10^4
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    /**
     * 17 电话号码的字母组合
     *
     * //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * //
     * // 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * //
     * //
     * //
     * // 示例:
     * //
     * // 输入："23"
     * //输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * //
     * //
     * // 说明:
     * //尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     * // Related Topics 字符串 回溯算法
     * // 👍 906 👎 0
     */
    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 19 删除链表的倒数第N个节点
     *
     * //给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * //
     * // 示例：
     * //
     * // 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * //
     * //当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * //
     * //
     * // 说明：
     * //
     * // 给定的 n 保证是有效的。
     * //
     * // 进阶：
     * //
     * // 你能尝试使用一趟扫描实现吗？
     * // Related Topics 链表 双指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dumpy = new ListNode(0);
        dumpy.next = head;
        ListNode first = dumpy;
        ListNode second = dumpy;

        for (int i = 1; i <= n+1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dumpy.next;
    }

    /**
     * 10 正则表达式匹配
     *
     * //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * //
     * // '.' 匹配任意单个字符
     * //'*' 匹配零个或多个前面的那一个元素
     * //
     * //
     * // 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * //
     * // 说明:
     * //
     * //
     * // s 可能为空，且只包含从 a-z 的小写字母。
     * // p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * //
     * //
     * // 示例 1:
     * //
     * // 输入:
     * //s = "aa"
     * //p = "a"
     * //输出: false
     * //解释: "a" 无法匹配 "aa" 整个字符串。
     * //
     * //
     * // 示例 2:
     * //
     * // 输入:
     * //s = "aa"
     * //p = "a*"
     * //输出: true
     * //解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * //
     * //
     * // 示例 3:
     * //
     * // 输入:
     * //s = "ab"
     * //p = ".*"
     * //输出: true
     * //解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * //
     * //
     * // 示例 4:
     * //
     * // 输入:
     * //s = "aab"
     * //p = "c*a*b"
     * //输出: true
     * //解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * //
     * //
     * // 示例 5:
     * //
     * // 输入:
     * //s = "mississippi"
     * //p = "mis*is*p*."
     * //输出: false
     * // Related Topics 字符串 动态规划 回溯算法
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 13 罗马数字转整数
     *
     * //罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * //
     * // 字符          数值
     * //I             1
     * //V             5
     * //X             10
     * //L             50
     * //C             100
     * //D             500
     * //M             1000
     * //
     * // 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
     * //I 。
     * //
     * // 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
     * // 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * //
     * //
     * // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * //
     * //
     * // 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * //
     * //
     * //
     * // 示例 1:
     * //
     * // 输入: "III"
     * //输出: 3
     * //
     * // 示例 2:
     * //
     * // 输入: "IV"
     * //输出: 4
     * //
     * // 示例 3:
     * //
     * // 输入: "IX"
     * //输出: 9
     * //
     * // 示例 4:
     * //
     * // 输入: "LVIII"
     * //输出: 58
     * //解释: L = 50, V= 5, III = 3.
     * //
     * //
     * // 示例 5:
     * //
     * // 输入: "MCMXCIV"
     * //输出: 1994
     * //解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * // IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * // 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     * //
     * // Related Topics 数学 字符串
     */
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int value = getValue(s.charAt(i));
            if (preNum < value) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = value;
        }
        sum += preNum;
        return sum;
    }
    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    /**
     * 20 有效的括号
     *
     * //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * //
     * // 有效字符串需满足：
     * //
     * //
     * // 左括号必须用相同类型的右括号闭合。
     * // 左括号必须以正确的顺序闭合。
     * //
     * //
     * // 注意空字符串可被认为是有效字符串。
     * //
     * // 示例 1:
     * //
     * // 输入: "()"
     * //输出: true
     * //
     * //
     * // 示例 2:
     * //
     * // 输入: "()[]{}"
     * //输出: true
     * //
     * //
     * // 示例 3:
     * //
     * // 输入: "(]"
     * //输出: false
     * //
     * //
     * // 示例 4:
     * //
     * // 输入: "([)]"
     * //输出: false
     * //
     * //
     * // 示例 5:
     * //
     * // 输入: "{[]}"
     * //输出: true
     * // Related Topics 栈 字符串
     */
    public static boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() % 2 ==1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<Character, Character>(){{
           put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new ArrayDeque<>();
        for (int i= 0;i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (stack.isEmpty() || !stack.peek().equals(map.get(s.charAt(i)))) {
                    return false;
                }
                stack.pop();
            }else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    /**
     * 21 合并两个有序链表
     *
     * //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * //
     * //
     * //
     * // 示例：
     * //
     * // 输入：1->2->4, 1->3->4
     * //输出：1->1->2->3->4->4
     * //
     * // Related Topics 链表
     */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 ==null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        ListNode l1Ptr = l1, l2Ptr = l2;
        while (l1Ptr != null && l2Ptr != null) {
            if (l1Ptr.val < l2Ptr.val) {
                tail.next = l1Ptr;
                l1Ptr = l1Ptr.next;
            } else {
                tail.next = l2Ptr;
                l2Ptr = l2Ptr.next;
            }
            tail = tail.next;
        }
        tail.next = l1Ptr==null ? l2Ptr : l1Ptr;
        return head.next;
    }

    /**
     * 22 括号生成
     *
     * //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * //
     * //
     * //
     * // 示例：
     * //
     * // 输入：n = 3
     * //输出：[
     * //       "((()))",
     * //       "(()())",
     * //       "(())()",
     * //       "()(())",
     * //       "()()()"
     * //     ]
     * //
     * // Related Topics 字符串 回溯算法
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 23 合并k个升序列表
     *
     * //给你一个链表数组，每个链表都已经按升序排列。
     * //
     * // 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * //
     * //
     * //
     * // 示例 1：
     * //
     * // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * //输出：[1,1,2,3,4,4,5,6]
     * //解释：链表数组如下：
     * //[
     * //  1->4->5,
     * //  1->3->4,
     * //  2->6
     * //]
     * //将它们合并到一个有序链表中得到。
     * //1->1->2->3->4->4->5->6
     * //
     * //
     * // 示例 2：
     * //
     * // 输入：lists = []
     * //输出：[]
     * //
     * //
     * // 示例 3：
     * //
     * // 输入：lists = [[]]
     * //输出：[]
     * //
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // k == lists.length
     * // 0 <= k <= 10^4
     * // 0 <= lists[i].length <= 500
     * // -10^4 <= lists[i][j] <= 10^4
     * // lists[i] 按 升序 排列
     * // lists[i].length 的总和不超过 10^4
     * //
     * // Related Topics 堆 链表 分治算法
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = new ListNode(Integer.MIN_VALUE);
        for (int i = 0; i < lists.length; i++) {
            mergeTwoLists2(ans, lists[i]);
        }
        return ans.next;
    }

    /**
     * 26 删除排序数组中的重复项
     *
     * //给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * //
     * // 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * //
     * //
     * //
     * // 示例 1:
     * //
     * // 给定数组 nums = [1,1,2],
     * //
     * //函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * //
     * //你不需要考虑数组中超出新长度后面的元素。
     * //
     * // 示例 2:
     * //
     * // 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * //
     * //函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * //
     * //你不需要考虑数组中超出新长度后面的元素。
     * //
     * //
     * //
     * //
     * // 说明:
     * //
     * // 为什么返回数值是整数，但输出的答案是数组呢?
     * //
     * // 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * //
     * // 你可以想象内部操作如下:
     * //
     * // // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * //int len = removeDuplicates(nums);
     * //
     * //// 在函数里修改输入数组对于调用者是可见的。
     * //// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
     * //for (int i = 0; i < len; i++) {
     * //    print(nums[i]);
     * //}
     * //
     * // Related Topics 数组 双指针
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {return 0;}
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
     * 28 实现 strStr()
     *
     * //实现 strStr() 函数。
     * //
     * // 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
     * //果不存在，则返回 -1。
     * //
     * // 示例 1:
     * //
     * // 输入: haystack = "hello", needle = "ll"
     * //输出: 2
     * //
     * //
     * // 示例 2:
     * //
     * // 输入: haystack = "aaaaa", needle = "bba"
     * //输出: -1
     * //
     * //
     * // 说明:
     * //
     * // 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * //
     * // 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     * // Related Topics 双指针 字符串
     */
    public static int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();

        for (int start = 0; start < n - L + 1; ++start) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * 29 两数相除
     *
     * //给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * //
     * // 返回被除数 dividend 除以除数 divisor 得到的商。
     * //
     * // 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * //
     * //
     * //
     * // 示例 1:
     * //
     * // 输入: dividend = 10, divisor = 3
     * //输出: 3
     * //解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     * //
     * // 示例 2:
     * //
     * // 输入: dividend = 7, divisor = -3
     * //输出: -2
     * //解释: 7/-3 = truncate(-2.33333..) = -2
     * //
     * //
     * //
     * // 提示：
     * //
     * //
     * // 被除数和除数均为 32 位有符号整数。
     * // 除数不为 0。
     * // 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     * //
     * // Related Topics 数学 二分查找
     */
    public static int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE&&divisor==-1)
            return Integer.MAX_VALUE;

        boolean k=(dividend>0&&divisor>0)||(dividend<0&&divisor<0);
        int result=0;
        dividend=-Math.abs(dividend);
        divisor=-Math.abs(divisor);
        while(dividend<=divisor) {
            int temp=divisor;
            int c=1;
            while(dividend-temp<=temp) {
                temp=temp<<1;
                c=c<<1;
            }
            dividend-=temp;
            result+=c;
        }
        return k?result:-result;
    }

    /**
     * 33 搜索旋转排序数组
     *
     * //假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * //
     * // ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * //
     * // 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * //
     * // 你可以假设数组中不存在重复的元素。
     * //
     * // 你的算法时间复杂度必须是 O(log n) 级别。
     * //
     * // 示例 1:
     * //
     * // 输入: nums = [4,5,6,7,0,1,2], target = 0
     * //输出: 4
     * //
     * //
     * // 示例 2:
     * //
     * // 输入: nums = [4,5,6,7,0,1,2], target = 3
     * //输出: -1
     * // Related Topics 数组 二分查找
     */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34在排序数组中查找元素的第一个和最后一个位置
     *
     * //给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * //
     * // 你的算法时间复杂度必须是 O(log n) 级别。
     * //
     * // 如果数组中不存在目标值，返回 [-1, -1]。
     * //
     * // 示例 1:
     * //
     * // 输入: nums = [5,7,7,8,8,10], target = 8
     * //输出: [3,4]
     * //
     * // 示例 2:
     * //
     * // 输入: nums = [5,7,7,8,8,10], target = 6
     * //输出: [-1,-1]
     * // Related Topics 数组 二分查找
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    /**
     * 206 反转链表
     *
     * //反转一个单链表。
     * //
     * // 示例:
     * //
     * // 输入: 1->2->3->4->5->NULL
     * //输出: 5->4->3->2->1->NULL
     * //
     * // 进阶:
     * //你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * // Related Topics 链表
     */
    public ListNode reverseList1(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            ListNode tempNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tempNext;
        }
        return pre;
    }
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static int toHash(String key) {
        int arraySize = 11113; // 数组大小一般取质数
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            int letterValue = key.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            // 就代表a的值，同理b=2；
            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
        }
        return hashCode;
    }

    /**144 二叉树的前序遍历
     *
     * //给定一个二叉树，返回它的 前序 遍历。
     * //
     * // 示例:
     * //
     * // 输入: [1,null,2,3]
     * //   1
     * //    \
     * //     2
     * //    /
     * //   3
     * //
     * //输出: [1,2,3]
     * //
     * //
     * // 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * // Related Topics 栈 树
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> input = new LinkedList<>();
        LinkedList<TreeNode> output = new LinkedList<>();
        if (root == null) {
            return input;
        }

        output.add(root);
        while (!output.isEmpty()) {
            TreeNode node = output.pollLast();
            input.add(node.getValue());
            if (node.getRightNode() != null) {
                output.add(node.getRightNode());
            }
            if (node.getLefTreeNode() != null) {
                output.add(node.getLefTreeNode());
            }

        }
        return input;

    }

    /**
     * 145 二叉树的后序遍历
     *
     * //给定一个二叉树，返回它的 后序 遍历。
     *     //
     *     // 示例:
     *     //
     *     // 输入: [1,null,2,3]
     *     //   1
     *     //    \
     *     //     2
     *     //    /
     *     //   3
     *     //
     *     //输出: [3,2,1]
     *     //
     *     // 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *     // Related Topics 栈 树
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> input = new LinkedList<>();
        LinkedList<TreeNode> output = new LinkedList<>();
        if (root == null) {
            return input;
        }

        output.add(root);
        while (!output.isEmpty()) {
            TreeNode node = output.pollLast();
            input.addFirst(node.getValue());
            if (node.getRightNode() != null) {
                output.add(node.getRightNode());
            }
            if (node.getLefTreeNode() != null) {
                output.add(node.getLefTreeNode());
            }

        }
        return input;
    }

    /**
     * 92 反转链表
     *
     * //反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * //
     * // 说明:
     * //1 ≤ m ≤ n ≤ 链表长度。
     * //
     * // 示例:
     * //
     * // 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * //输出: 1->4->3->2->5->NULL
     * // Related Topics 链表
     *
     */
    // Object level variables since we need the changes
    // to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || right.next == this.left) {
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left = head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

        public static void main(String[] args) {


//        ListNode node = new ListNode(0);
//        ListNode node1 = new ListNode(1);
//        node.next = node1;
//        ListNode node2 = new ListNode(2);
//        node1.next = node2;
//        ListNode node3 = new ListNode(3);
//        node2.next = node3;
//        reverseList2(node);


//        int[] nums = new int[]{6,7,0,1,2,3,4};
//        int[] result = searchRange(nums, 8);
//        System.out.println(result[0]+","+result[1]);

//        System.out.println(search(nums,0));

//        int dividend = 9, divisor = 2;
//        System.out.println(divide(dividend, divisor));

//        String haystack = "a", needle = "a";
//        int index = strStr(haystack, needle);
//        System.out.println(index);

//        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
//
//        System.out.println(removeDuplicates(nums));

//        ListNode l1 = new ListNode(0);
//        l1.next = new ListNode(2);
//
//        ListNode l2 = new ListNode(2);
//        l2.next = new ListNode(3);
//        ListNode[] lists = new ListNode[]{l1};
//        ListNode listNode = mergeKLists(lists);
//        while (listNode != null) {
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }


//        generateParenthesis(2);

//        String s = "([])";
//        isValid(s);

//        String s = "asdd";
//        String p = ".sd*";
//        System.out.println(isMatch(s, p));

//        String str = "23";
//        letterCombinations(str);

//        int[] arr1 = new int[]{1,3};
//        int[] arr2 = new int[]{2};
//        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}
