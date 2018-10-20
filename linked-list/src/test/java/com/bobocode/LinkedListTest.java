package com.bobocode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class LinkedListTest {
    private List<Integer> list;

    @Before
    public void init() {
        list = DoublyLinkedList.of(0, 1, 2);
    }

    @Test
    public void testAddElements() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void testAddElementByIndex() {
        list.add(1, 5);
    }

    @Test
    public void testAddFirstElementByIndex() {
        list.add(0, 5);
        assertThat(list.getFirst(), is(5));
    }

    @Test
    public void testAddLastElementByIndex() {
        list.add(2, 5);
        assertThat(list.get(2), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddElementByInvalidIndex() {
        list.add(10, 5);
    }

    @Test
    public void testSetElement() {
        list.set(1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetElementByInvalidIndex() {
        list.set(99, 5);
    }

    @Test
    public void testGetElements() {
        assertThat(list.get(0), is(0));
        assertThat(list.get(1), is(1));
        assertThat(list.get(2), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementByInvalidIndex() {
        list.get(-1);
    }

    @Test
    public void testSetAndGetElements() {
        list.set(0, 10);
        list.set(1, 11);
        list.set(2, 12);
        assertThat(list.get(0), is(10));
        assertThat(list.get(1), is(11));
        assertThat(list.get(2), is(12));
    }

    @Test
    public void testGetFirstElement() {
        assertThat(list.getFirst(), is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFirstElementFromEmptyList() {
        list.clear();
        list.getFirst();
    }

    @Test
    public void testGetLastElement() {
        assertThat(list.getLast(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetLastElementFromEmptyList() {
        list = new DoublyLinkedList<>();
        list.getLast();
    }

    @Test
    public void testRemoveElement() {
        list.remove(1);
        assertThat(list.get(0), is(0));
        assertThat(list.get(1), is(2));
    }

    @Test
    public void testRemoveFirstElement() {
        assertThat(list.getFirst(), is(0));
        list.remove(0);
        assertThat(list.getFirst(), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test
    public void testRemoveLastElement() {
        assertThat(list.getLast(), is(2));
        list.remove(2);
        assertThat(list.get(0), is(0));
        assertThat(list.getLast(), is(1));
    }

    @Test
    public void testContainsElement() {
        list.add(null);
        assertThat(list.contains(1), is(true));
        assertThat(list.contains(2), is(true));
        assertThat(list.contains(99), is(false));
        assertThat(list.contains(-1), is(false));
        assertThat(list.contains(null), is(true));
    }

    @Test
    public void testIsEmpty() {
        list = new DoublyLinkedList<>();
        assertThat(list.isEmpty(), is(true));
        list.add(1);
        assertThat(list.isEmpty(), is(false));

    }

    @Test
    public void testSize() {
        list = new DoublyLinkedList<>();
        assertThat(list.size(), is(0));
        list.add(1);
        assertThat(list.size(), is(1));
        list.add(2);
        assertThat(list.size(), is(2));
        list.remove(0);
        assertThat(list.size(), is(1));
    }

    @Test
    public void testClear() {
        assertThat(list.size(), is(3));
        list.clear();
        assertThat(list.size(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetSetAfterClear() {
        assertThat(list.size(), is(3));
        list.clear();
        list.get(0);
        list.get(1);
        list.set(1, 1);
    }
}
