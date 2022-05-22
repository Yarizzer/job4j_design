package ru.job4j.it;

import org.w3c.dom.ls.LSOutput;

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
        while (rowPointer < data.length && colPointer == data[rowPointer].length) {
            rowPointer++;
            colPointer = 0;
        }
        return rowPointer < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[rowPointer][colPointer++];
    }
}