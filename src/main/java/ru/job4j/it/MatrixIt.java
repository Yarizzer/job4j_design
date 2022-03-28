package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int rowPointer = 0;
    private int colPointer = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (colPointer == data[rowPointer].length) {
            colPointer = 0;
            for (int i = rowPointer + 1; i < data.length; i++) {
                if (data[i].length > 0) {
                    rowPointer = i;
                    return true;
                }
            }
        }
        return colPointer < data[rowPointer].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[rowPointer][colPointer++];
    }
}