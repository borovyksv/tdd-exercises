package com.bobocode;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SingleLinkedListTest extends LinkedListTest {
    @Before
    public void init() {
        list = SingleLinkedList.of(0, 1, 2);
    }
}
