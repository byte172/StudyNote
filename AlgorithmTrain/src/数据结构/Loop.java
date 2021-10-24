package 数据结构;

import java.util.List;

public class Loop {
    /**
     * 1.两个链表均不存在环
     * 无环情况下，判断两个链表是否相交，只需要遍历链表，判断尾节点是否相等即可。
     * @param l1
     * @param l2
     * @return
     */
    public static boolean isJoinNoLoop (ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;

        while (null != p1.getNext()) {
            p1 = p1.getNext();
        }
        while (null != p2.getNext()) {
            p2 = p2.getNext();
        }
        return p1==p2;
    }
    /**
     * 无环情况下找到第一个相交点
     * 方法： 算出两个链表的长度差为x,长链表先移动x步，之后两链表同时移动，直到相遇的第一个交点。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode getFirstJoinNode(ListNode l1, ListNode l2) {
        int len1 = 0;
        int len2 = 0;

        while (l1.getNext() != null) {
            len1++;
            l1 = l1.getNext();
        }
        while (l2.getNext() != null) {
            len2++;
            l2 = l2.getNext();
        }
        return len1-len2>=0?getNode(l1,l2,len1,len2):getNode(l2,l1,len2,len1);
    }

    public static ListNode getNode(ListNode l1, ListNode l2, int len1, int len2) {
        int i=0;
        while (i < len1 - len2) {
            l1 = l1.getNext();
        }
        while (true) {
            l1 = l1.getNext();
            l2 = l2.getNext();
            if (l1 == l2) {
                return l1;
            }
        }
    }
    /**141 判断是否存在环
     *
     * 步骤：设置两个指针同时指向head，其中一个一次前进一个节点（P1），另外一个一次前进两个节点(P2)。
     * p1和p2同时走，如果其中一个遇到null，则说明没有环，如果走了N步之后，二者指向地址相同，那么说明链表存在环。
     */
    public static boolean isLoop(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode l = head;
        ListNode r = head;
        while (r != null && r.next != null) {
            l = l.next;
            r = r.next.next;
            if (l == r) {
                return true;
            }
        }
        return false;
    }

    /**
     *  方法二： 将其中一个链表首尾相连 从另外一个链表开始，检测是否存在环，如果存在，则说明二者相交。
     *  如果需要找出环的入口，则设P1 P2 两个指针，P1一次走两步，P2一次走一步，两者在环上某一点相遇。记下此位置。
     *  此时设置一个指针P3指向表头，然后P1和P3每次同时行走一步，每步前进一个节点。等到P1、P3重合时，则重合位置即使环入口。
     * @param h1
     * @param h2
     * @return
     */
    public  ListNode entryNoLoop(ListNode h1,ListNode h2) {
        ListNode p = h1;
        while(null != p.getNext()){
            p = p.getNext();
        }
        p.setNext(h1);
        return entryLoop(h2);
    }

    /**
     * 获取环的入口点
     * @param h
     * @return
     */
    public ListNode entryLoop(ListNode h) {
        ListNode p1 = h;
        ListNode p2 = h;
        ListNode p3 = h;

        while(null != p2.getNext() && null != p2.getNext().getNext()){
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
            if(p1 == p2)
                break;
        }
        while(p3 != p1) {
            p1 = p1.getNext();
            p3 = p3.getNext();
        }
        return p3;
    }

    /**
     *2.两个链表均存在环
     * @param h1
     *            链表1的头节点
     * @param l1
     *            链表1的环入口
     * @param h2
     *            链表2的头节点
     * @param l2
     *            链表2的头节点
     * @return
     */
    public static ListNode bothLoop(ListNode h1, ListNode l1, ListNode h2, ListNode l2) {
        ListNode p1 = null;
        ListNode p2 = null;
        if (l1 == l2) {
            p1 = h1;
            p2 = h2;
            int n = 0;
            while (p1 != l1) {
                n++;
                p1 = p1.getNext();
            }
            while (p2 != l2) {
                n--;
                p2 = p2.getNext();
            }
            p1 = n > 0 ? h1 : h2;
            p2 = p1 == h1 ? h2 : h1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                h1 = h1.getNext();
            }
            while (p1 != p2) {
                p1 = p1.getNext();
                p2 = p2.getNext();
            }
            return p1;
        } else {
            p1 = l1.getNext();
            while (p1 != l1) {
                if (p1 == l2) {
                    return l1;
                }
            }
            return null;
        }
    }

    /**
     *
     * @param h1
     * @param h2
     * @return
     */
    public ListNode getJoinNode(ListNode h1, ListNode h2) {
        if (null == h1 || null == h2)
            return null;
        ListNode l1 = entryLoop(h1);
        ListNode l2 = entryLoop(h2);
        if (null == l1 && null == l2)
            return getFirstJoinNode(h1, h2);
        if (null != l1 && null != l2)
            return bothLoop(h1,l1,h2,l2);
        return null;
    }


    public static void main(String[] args) {
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(2);
        ListNode n3=new ListNode(2);
        ListNode n4=new ListNode(3);
        ListNode n5=new ListNode(6);
        ListNode n6=new ListNode(6);
        ListNode n7=new ListNode(9);
        ListNode n8=new ListNode(10);
        ListNode c1=new ListNode(5);
        ListNode c2=new ListNode(2);
        ListNode c3=new ListNode(2);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;
        n7.next=n8;
        c1.next=c2;
        c2.next=c3;
        c3.next=n4;

        System.out.println(getFirstJoinNode(n1,c1));
    }
}
