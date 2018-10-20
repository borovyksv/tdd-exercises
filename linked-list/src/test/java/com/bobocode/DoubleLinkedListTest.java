package com.bobocode;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DoubleLinkedListTest extends LinkedListTest {
    @Before
    public void init() {
        list = DoublyLinkedList.of(0, 1, 2);
    }
}
