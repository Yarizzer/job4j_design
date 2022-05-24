package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }
    private void increase() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length == 0 ? container.length + 10 : container.length * 2);
        }
    }
    @Override
    public void add(T value) {
        modCount++;
        increase();
        container[size++] = value;
    }
    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }
    @Override
    public T remove(int index) {
        modCount++;
        T rsl = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        return rsl;
    }
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = modCount;
            private int point;
            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}