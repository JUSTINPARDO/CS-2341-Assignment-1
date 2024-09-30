package src;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Steque<Item> {
    private Node First;
    private Node Last;
    private int size;
    private final int capacity;

    public class Node {
        Item item;
        Node next;
    }

    public Steque(int capacity) {
        this.capacity = capacity;
        First = null;
        Last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pop() {
        if (isEmpty()) {
            return;
        }
        Item item = First.item;
        First = First.next;
        size--;
        if (isEmpty()) {
            Last = null;
        }
        StdOut.println(item);
    }

    public void enqueue(Item item) {
        Node oldLast = Last;
        Last = new Node();
        Last.item = item;
        Last.next = null;
        if (isEmpty()) First = Last;
        else oldLast.next = Last;
        size++;
    }

    public Item deque() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = First.item;
        First = First.next;
        size--;
        if (isEmpty()) Last = null;
        return item;
    }
}

