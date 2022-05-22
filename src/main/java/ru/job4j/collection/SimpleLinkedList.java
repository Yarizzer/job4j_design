package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    private static class Node<E> {
        Node<E> next;
        Node<E> previous;
        E item;
        Node(Node<E> previous, Node<E> next, E element) {
            this.next = next;
            this.previous = previous;
            this.item = element;
        }
    }
    @Override
    public void add(E value) {
        final Node<E> lastNode = last;
        final Node<E> newNode = new Node<E>(lastNode, null, value);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int count = modCount;
            private Node<E> node = first;
            @Override
            public boolean hasNext() {
                return node != null;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = node.item;
                node = node.next;
                return rsl;
            }
        };
    }
}