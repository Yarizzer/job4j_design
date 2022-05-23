package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }
    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }
    @Test
    public void whenAddAfterMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }
    @Test
    public void whenRemoveIfValueLessThan2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeIf(input, a -> a < 2);
        assertThat(input, is(Arrays.asList(2, 3, 4)));
    }
    @Test
    public void whenReplaceIfValueLessThan2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, a -> a < 2, 5);
        assertThat(input, is(Arrays.asList(5, 5, 2, 3, 4)));
    }

    @Test
    public void whenRemoveAllInList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> list = Arrays.asList(0, 1, 2);
        ListUtils.removeAll(input, list);
        assertThat(input, is(Arrays.asList(3, 4)));
    }
}