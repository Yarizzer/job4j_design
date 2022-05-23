package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(10);
    @Override
    public boolean add(T value) {
        var rsl = contains(value);
        if (!rsl) {
            set.add(value);
        }
        return !rsl;
    }
    @Override
    public boolean contains(T value) {
        var rsl = false;
        for (T item : set) {
            if (Objects.equals(item, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
