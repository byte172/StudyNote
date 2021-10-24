package leetcode;

import java.util.Iterator;

/**
 * 给定一个带有方法next（）和hasNext（）的迭代器类接口，设计并实现一个支持peek（）操作的PeekingIterator——它实际上是peek（）在下一次调用next（）时返回的元素上。
 * 例子：
 * 假设迭代器初始化到列表的开头：[1,2,3]。
 * Call next（）获取列表中的第一个元素1。现在调用peek（）并返回下一个元素2。之后调用next（）仍然返回2。最后一次调用next（），它返回最后一个元素3。
 * 之后调用hasNext（）应该返回false。
 * @param <T>
 */
public class PeekingIterator<T> implements Iterator<T> {

    /**
     *为了能peek后下次next还得到同样的数字，
     * 我们要用一个缓存保存下一个数字。这样当peek时候，
     * 返回缓存就行了，迭代器位置也不会变。当next的时候除了要返回缓存，
     * 还要将缓存更新为下一个数字，如果没有下一个就将缓存更新为null。
     */
    T cache;
    Iterator<T> it;

    public PeekingIterator() {
    }

    public PeekingIterator(Iterator iterator) {
        this.cache = cache;
        this.it = iterator;
    }

    public T peek () {
        return cache;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext() || cache != null;
    }

    @Override
    public T next () {
        T res = cache;
        cache  =it.hasNext() ? it.next() : null;
        return res;
    }
}
