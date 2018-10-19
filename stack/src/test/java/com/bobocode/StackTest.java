package com.bobocode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StackTest {

    private Stack<Integer> stack;

    @Before
    public void init() {
        stack = new ArrayStack<>();
    }

    @Test
    public void testPushElements() {
        stack = ArrayStack.of(null, 1, 2, null, 4, 12, null);
        stack.push(99);
    }

    @Test
    public void testPopElements() {
        stack = ArrayStack.of(null, 1, 2, null);

        assertNull(stack.pop());
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));
        assertNull(stack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopEmptyStack() {
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekEmptyStack() {
        stack.peek();
    }

    @Test
    public void testEmptySize() {
        assertThat(stack.size(), is(0));
    }

    @Test
    public void testSize() {
        stack = ArrayStack.of(1,2,3);
        stack.pop();

        assertThat(stack.size(), is(2));
    }

    @Test
    public void testIsEmptyOnEmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyOnStackWithElements() {
        stack = ArrayStack.of(1);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPushAndOverflowMaxCapacity() {
        int stackCapacity = 2;
        stack = new ArrayStack<>(stackCapacity);
        int lastElement = stackCapacity * 3;
        IntStream.rangeClosed(0, lastElement)
                .forEach(stack::push);

        while (!stack.isEmpty()) {
            assertThat(stack.pop(), is(lastElement--));
        }

    }

    @Test
    public void testPeekElements() {
        stack = ArrayStack.of(null, 1, 2, null);

        assertNull(stack.peek());
        assertNull(stack.pop());
        assertThat(stack.peek(), is(2));
        assertThat(stack.peek(), is(2));
        assertThat(stack.pop(), is(2));
        assertThat(stack.peek(), is(1));
        assertThat(stack.pop(), is(1));
        assertNull(stack.peek());
        assertNull(stack.pop());
    }

    @Test
    public void testStringStack() {
        Stack<String> stringStack = new ArrayStack<>();
        String element = "someString";
        stringStack.push(element);
        assertThat(stringStack.pop(), is(element));
    }
}
