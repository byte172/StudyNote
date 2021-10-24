package 数据结构;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    // 根节点
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 插入操作
     *
     * @param value
     */
    public void insert(int value) {


        TreeNode newNode = new TreeNode(value);

        if (root == null) {
            root = newNode;
            root.setLefTreeNode(null);
            root.setRightNode(null);
        } else {

            TreeNode currentNode = root;
            TreeNode parentNode;

            while (true) {

                parentNode = currentNode;
                // 往右放
                if (newNode.getValue() > currentNode.getValue()) {

                    currentNode = currentNode.getRightNode();

                    if (currentNode == null) {
                        parentNode.setRightNode(newNode);
                        return;
                    }
                } else {
                    // 往左放
                    currentNode = currentNode.getLefTreeNode();

                    if (currentNode == null) {
                        parentNode.setLefTreeNode(newNode);
                        return;
                    }
                }
            }
        }

    }




    /**
     * 查找
     *
     * @param key
     * @return
     */
    public TreeNode find(int key) {
        TreeNode currentNode = root;

        if (currentNode != null) {

            while (currentNode.getValue() != key) {

                if (currentNode.getValue() > key) {
                    currentNode = currentNode.getLefTreeNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }

                if (currentNode == null) {
                    return null;
                }

            }

            if (currentNode.isDelete()) {
                return null;
            } else {
                return currentNode;
            }

        } else {
            return null;
        }
    }

    /**
     * 中序遍历--递归
     *
     * @param treeNode
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode != null && treeNode.isDelete() == false) {

            inOrder(treeNode.getLefTreeNode());

            System.out.println("--" + treeNode.getValue());

            inOrder(treeNode.getRightNode());
        }
    }

    /**
     * 中序遍历--迭代
     *
     * @param treeNode
     */
    public List<Integer> inOrder2(TreeNode treeNode) {
        List<Integer> res = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.empty()){
            if(cur != null){
                stack.add(cur);
                cur = cur.getLefTreeNode();
            }else {
                cur = stack.pop();
                res.add(cur.getValue());
                cur = cur.getRightNode();
            }
        }
        return res;
    }


    /**
     * 广度优先遍历
     * @param node
     */
    public void levelOrderTraversal(TreeNode node){
        if(node==null){
            System.out.println("empty tree");
            return;
        }

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(node);
        while(!deque.isEmpty()){

            TreeNode rnode = deque.remove();
            System.out.println(rnode.getValue()+"  ");
            if(rnode.getLefTreeNode()!=null){
                deque.add(rnode.getLefTreeNode());
            }
            if(rnode.getRightNode()!=null){
                deque.add(rnode.getRightNode());
            }
        }

    }

    /**
     * 深度优先遍历
     * @param node
     */
    public void depthTraversal(TreeNode node){
        if(node==null){
            System.out.println("empty tree");
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode rnode = stack.pop();
            System.out.println(rnode.getValue());
            if(rnode.getRightNode()!=null){
                stack.push(rnode.getRightNode());
            }
            if(rnode.getLefTreeNode()!=null){
                stack.push(rnode.getLefTreeNode());
            }
        }
    }

    /**
     * 判断两颗二叉树是否相等
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.getValue() != q.getValue()) return false;
        return isSameTree(p.getLefTreeNode(),q.getLefTreeNode()) && isSameTree(p.getRightNode(),q.getRightNode());
    }

    /**
     * 判断两颗二叉树是否对称
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return isMirror(root.getLefTreeNode(),root.getRightNode());
    }
    public static boolean isMirror(TreeNode A, TreeNode B){
        if(A == null && B == null) return true;
        if(A == null || B == null) return false;
        if(A.getValue() != B.getValue()) return false;
        return isMirror(A.getLefTreeNode(), B.getRightNode()) && isMirror(A.getRightNode(), B.getLefTreeNode());
    }

    /**
     * 判断是否平衡二叉树(左右子树高度差值不超过1)
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root){
        if(root == null) return true;
        if(getHeight(root) == -1) return false;
        return true;
    }
    private static int getHeight(TreeNode root){
        if(root == null) return 0;
        int leftHeight = getHeight(root.getLefTreeNode());
        int rightHeight = getHeight(root.getRightNode());
        if(leftHeight == -1 || rightHeight == -1) return -1;
        if(Math.abs(rightHeight - leftHeight) > 1) return -1;

        return Math.max(rightHeight,leftHeight) + 1;
    }

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        // 添加数据测试
        tree.insert(10);
        tree.insert(40);
        tree.insert(20);
//        tree.insert(3);
//        tree.insert(2);
        tree.insert(49);
        tree.insert(13);
        tree.insert(123);

//        System.out.println("root=" + tree.getRoot().getValue());

//        boolean sameTree = tree.isSameTree(tree.root, tree.root);

//        tree.levelOrderTraversal(tree.getRoot());
//        System.out.println("---");
//        tree.depthTraversal(tree.getRoot());

//        List<Integer> list = tree.inOrder2(tree.getRoot());


        boolean balanced = isBalanced(tree.getRoot());
        System.out.println(balanced);
//        // 排序测试
//        tree.inOrder(tree.getRoot());
//
//        System.out.println(isSymmetric(tree.getRoot()));
//
//        // 查找测试
//        if (tree.find(10) != null) {
//            System.out.println("找到了");
//        } else {
//            System.out.println("没找到");
//        }
//
//        // 删除测试
//        tree.find(40).setDelete(true);
//
//        if (tree.find(40) != null) {
//            System.out.println("找到了");
//        } else {
//            System.out.println("没找到");
//        }
    }
}