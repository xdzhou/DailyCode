package com.loic.algo.queueStack;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkListNode<T> {
    private static final Logger Log = LoggerFactory.getLogger(LinkListNode.class);

    private T mValue;
    private LinkListNode<T> mNext;

    public LinkListNode(T value) {
        this.mValue = value;
    }

    public LinkListNode<T> getNext() {
        return mNext;
    }

    /**
     * remove a node in this linked list
     *
     * @param nodeToRemove
     */
    public void removeNode(LinkListNode<T> nodeToRemove) {
        Log.debug("remove node: %s", nodeToRemove.mValue.toString());

        if (this.mNext == nodeToRemove) {
            this.mNext = nodeToRemove.mNext;
        } else if (nodeToRemove.mNext != null) {
            Log.debug("remove node, copy next node: {}", nodeToRemove.mNext.mValue);
            // copy next node's value
            nodeToRemove.mValue = nodeToRemove.mNext.mValue;
            nodeToRemove.mNext = nodeToRemove.mNext.mNext;
        } else {
            Log.debug("remove last node");
            LinkListNode<T> curNode = this;
            while (curNode.mNext != null && curNode.mNext.mNext != null) {
                curNode = curNode.mNext;
            }
            curNode.mNext = null;
        }
    }

    /**
     * check whether this linked list has cycle
     *
     * @return a node in the cycle, null if no cycle
     */
    public LinkListNode<T> hasCycle() {
        LinkListNode<T> flag1 = this;
        LinkListNode<T> flag2 = this;
        while (flag2 != null && flag2.mNext != null) {
            flag1 = flag1.mNext;
            flag2 = flag2.mNext.mNext;
            if (flag1 == flag2) {
                return flag1;
            }
        }
        return null;
    }

    /**
     * check whether this list is joined with another list, Attention: make sure
     * that this list and another list has no cycle
     *
     * @param anotherList
     * @return is joined
     */
    private boolean isSimpleJoinedWith(LinkListNode<T> anotherList) {
        LinkListNode<T> root1 = this;
        LinkListNode<T> root2 = anotherList;
        while (root1.mNext != null) {
            root1 = root1.mNext;
        }
        while (root2.mNext != null) {
            root2 = root2.mNext;
        }
        return root1 == root2;
    }

    /**
     * check whether this list is joined with another list
     *
     * @param anotherList
     * @return is joined
     */
    public boolean isJoinedWith(LinkListNode<T> anotherList) {
        LinkListNode<T> cycle1 = hasCycle();
        LinkListNode<T> cycle2 = anotherList.hasCycle();
        if (cycle1 == null && cycle2 == null) {
            Log.debug("check joined state for noCycle list");
            return isSimpleJoinedWith(anotherList);
        } else if (cycle1 != null && cycle2 != null) {
            Log.debug("check joined state for 2 Cycle list");
            if (cycle1 == cycle2) {
                return true;
            }
            LinkListNode<T> curNode = cycle1.mNext;
            while (curNode != cycle1) {
                if (curNode == cycle2) {
                    return true;
                }
                curNode = curNode.mNext;
            }
        }
        return false;
    }

    /**
     * reverse this linked list
     *
     * @return
     */
    public LinkListNode<T> reverse() {
        if (mNext == null) {
            return this;
        }
        LinkListNode<T> p, q, r;
        p = null;
        q = this;
        r = q.mNext;
        while (q != null) {
            q.mNext = p;
            p = q;
            q = r;
            r = (r == null) ? null : r.mNext;
        }
        return p;
    }

    public T getValue() {
        return mValue;
    }

    public LinkListNode<T> getIntersectNodeIfCycle() {
        LinkListNode<T> node = hasCycle();
        if (node != null) {
            LinkListNode<T> cur1 = this, cur2 = node;
            while (cur1 != null && cur2 != null) {
                if (cur1 == cur2) {
                    return cur1;
                }
                cur1 = cur1.mNext;
                cur2 = cur2.mNext;
            }
        }
        return null;
    }

    /**
     * get first intersect node
     *
     * @param anotherList
     * @return null if not joined
     */
    public LinkListNode<T> getFirstIntersectNode(LinkListNode<T> anotherList) {
        LinkListNode<T> cycle1 = hasCycle();
        LinkListNode<T> cycle2 = anotherList.hasCycle();
        if (cycle1 == null && cycle2 == null) {
            int len1 = 0, len2 = 0;
            LinkListNode<T> cur1 = this, cur2 = anotherList;
            while (cur1 != null) {
                cur1 = cur1.mNext;
                len1++;
            }
            while (cur2 != null) {
                cur2 = cur2.mNext;
                len2++;
            }
            int delta = Math.abs(len1 - len2);

            cur1 = (len1 > len2) ? this : anotherList;
            cur2 = (cur1 == this) ? anotherList : this;

            for (int i = 0; i < delta; i++) {
                cur1 = cur1.mNext;
            }
            while (cur1 != null && cur2 != null) {
                if (cur1 == cur2) {
                    return cur1;
                }
                cur1 = cur1.mNext;
                cur2 = cur2.mNext;
            }
        }
        return null;
    }

    /**
     * find the nth to last element of a singly linked list
     */
    public LinkListNode<T> fineNthLastNode(int n) {
        checkArgument(n > 0);
        LinkListNode<T> resultIndi = this;
        LinkListNode<T> headIndi = this;
        n--;
        while (n > 0 && headIndi.mNext != null) {
            headIndi = headIndi.mNext;
            n--;
        }
        if (n > 0) {
            return null;
        } else {
            while (headIndi.mNext != null) {
                headIndi = headIndi.mNext;
                resultIndi = resultIndi.mNext;
            }
            return resultIndi;
        }
    }

    public LinkListNode<T> append(T nextValue) {
        this.mNext = new LinkListNode<T>(nextValue);
        return this.mNext;
    }

    public void setNextNode(LinkListNode<T> nextNode) {
        this.mNext = nextNode;
    }

    public String printList() {
        StringBuilder sb = new StringBuilder("[");
        LinkListNode<T> curNode = this;
        while (curNode != null) {
            sb.append(curNode.mValue).append(", ");
            curNode = curNode.mNext;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public static <T> LinkListNode<T> from(T... items) {
        requireNonNull(items);
        checkArgument(items.length > 0);
        LinkListNode<T> root = new LinkListNode<>(items[0]);
        LinkListNode<T> tail = root;
        for (int i = 1; i < items.length; i++) {
            tail = tail.append(items[i]);
        }
        return root;
    }
}
