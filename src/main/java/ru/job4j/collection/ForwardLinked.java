package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        var rsl = head.value;
        var newHead = head.next;
        head.next = null;
        head.value = null;
        head = newHead;
        return rsl;
    }
    public boolean revert() {
        var rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> previous = null;
            Node<T> current = head;
            while (current != null) {
                var next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }
        return rsl;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
    private static class Node<T> {
        T value;
        Node<T> next;
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
