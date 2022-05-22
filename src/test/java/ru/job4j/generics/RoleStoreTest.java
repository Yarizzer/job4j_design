package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndSearchThenFindRoleByName() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        var result = store.findById("1");
        assertThat(result.getName(), is("foo"));
    }
    @Test
    public void whenAddAndSearchThanRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        var result = store.findById("10");
        assertNull(result);
    }
    @Test
    public void whenAddDuplicateAndSearchThanRoleNameIsFirst() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        store.add(new Role("1", "bar"));
        var result = store.findById("1");
        assertThat(result.getName(), is("foo"));
    }
    @Test
    public void whenReplaceThenRoleNameIsSecond() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        store.replace("1", new Role("1", "bar"));
        var result = store.findById("1");
        assertThat(result.getName(), is("bar"));
    }
    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        store.replace("10", new Role("10", "bar"));
        var result = store.findById("1");
        assertThat(result.getName(), is("foo"));
    }
    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        store.delete("1");
        var result = store.findById("1");
        assertNull(result);
    }
    @Test
    public void whenNoRoleToDeleteThaneRoleNameIsTheSame() {
        var store = new RoleStore();
        store.add(new Role("1", "foo"));
        store.delete("10");
        var result = store.findById("1");
        assertThat(result.getName(), is("foo"));
    }
}