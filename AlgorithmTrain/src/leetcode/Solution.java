package leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 方法1：入度表（广度优先遍历）
 */
public class Solution {
    public static boolean canFinish(int numCourses, @NotNull int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for(int[] cp : prerequisites)
            indegrees[cp[0]]++;
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0)
                queue.addLast(i);
        }
        while(!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            numCourses--;
            for(int[] req : prerequisites) {
                if(req[1] != pre) continue;
                if(--indegrees[req[0]] == 0) queue.add(req[0]);
            }
        }
        return numCourses == 0;
    }

    public static void main(String[] args) {
        int a=5;
        int[][] b = {{3,2}};
        boolean b1 = canFinish(a, b);
        System.out.println(b1);
    }
}
