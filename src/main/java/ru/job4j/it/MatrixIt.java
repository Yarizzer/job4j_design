package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int rowPointer;
    private int colPointer = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        this.rowPointer = nextRow(0);
    }

    private int nextRow(int startRow) {
        for (int i = startRow; i < data.length; i++) {
            if (data[i].length > 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return rowPointer != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var value = data[rowPointer][colPointer++];
        if (colPointer == data[rowPointer].length) {
            rowPointer = nextRow(rowPointer + 1);
            colPointer = 0;
        }
        return value;
    }
}