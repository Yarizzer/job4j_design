package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        this.index = findNext(0);
    }

    private int findNext(int start) {
        for (int i = start; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer elem = data[index++];
        index = findNext(index);
        return elem;
    }
}