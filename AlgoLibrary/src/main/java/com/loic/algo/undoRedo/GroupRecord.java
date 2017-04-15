package com.loic.algo.undoRedo;

public class GroupRecord extends AbstractRecord {
    private static final int Max_Record_Count = 100;

    private final int maxCount;

    private Node head, cur;

    public GroupRecord() {
        this(Max_Record_Count);
    }

    public GroupRecord(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void undo() {
        while (cur != null) {
            cur.record.undo();
            cur = cur.prev;
        }
    }

    @Override
    public void redo() {
        while (getNextNode() != null) {
            cur = getNextNode();
            cur.record.redo();
        }
    }

    private Node getNextNode() {
        return (cur == null) ? head : cur.next;
    }

    public void addRecord(IRecordable record) {
        IRecordable recordToAdd = (record instanceof GroupRecord) ? ((GroupRecord) record).simplifySelf() : record;
        if (recordToAdd != null) {
            recordToAdd.redo(); // execute record

            Node newNode = new Node(null, recordToAdd, null);
            if (cur == null) {
                head = newNode;
            } else {
                // remove all undo record
                Node curNext = cur.next;
                if (curNext != null) {
                    curNext.next = null; // help GC
                    curNext.prev = null;
                    curNext.record = null;
                }
                cur.next = newNode;
                newNode.prev = cur;
            }
            cur = newNode;
        }
    }

    public void undoOneStep() {
        if (cur != null) {
            cur.record.undo();
            cur = cur.prev;
        }
    }

    public void redoOneStep() {
        if (getNextNode() != null) {
            cur = getNextNode();
            cur.record.redo();
        }
    }

    public IRecordable simplifySelf() {
        // remove all undo record
        if (cur != null && cur.next != null) {
            Node curNext = cur.next;
            curNext.next = null; // help GC
            curNext.prev = null;
            curNext.record = null;
            cur.next = null;
        }
        // check merge record
        Node curNode = head;
        while (curNode != null && curNode.next != null) {
            try {
                IRecordable mergeRecord = curNode.record.merge(curNode.next.record);
                Node preNode = curNode.prev;
                Node nextNode = curNode.next;
                Node nextNextNode = nextNode.next;
                if (mergeRecord == null) {
                    removeNode(curNode);
                    removeNode(nextNode);
                    curNode = (preNode != null) ? preNode : nextNextNode;
                } else {
                    removeNode(curNode);
                    nextNode.record = mergeRecord;
                    curNode = nextNode;
                }
            } catch (UnsupportedOperationException e) {
                // can't merge record, skip to next
                curNode = curNode.next;
            }
        }

        // check if empty
        if (cur == null) {
            return null;
        }
        // only one record
        else if (head == cur) {
            return head.record;
        }
        return this;
    }

    private void removeNode(Node node) {
        if (node.prev == null)
            head = node.next;
        else
            node.prev.next = node.next;
        if (node.next == null)
            cur = node.prev;
        else
            node.next.prev = node.prev;
        // help GC
        node.next = null;
        node.prev = null;
        node.record = null;
    }

    private static class Node {
        IRecordable record;
        Node next;
        Node prev;

        Node(Node prev, IRecordable record, Node next) {
            this.record = record;
            this.next = next;
            this.prev = prev;
        }
    }
}
