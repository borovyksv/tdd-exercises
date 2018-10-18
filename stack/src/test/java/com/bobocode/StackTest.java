package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class StackTest {

    @Test
    public void testCreateStack() {
        LinkedStack.Empty();
    }

    @Test
    public void testPushElement() {
       LinkedStack.of(1);
    }

    @Test
    public void testPushNullElement() {
        Stack<Integer> stack = LinkedStack.Empty();
        stack.push(null);
    }

    @Test
    public void testPushTwoElements() {
        LinkedStack.of(1, 2);
    }

    @Test
    public void testPushManyElements() {
        LinkedStack.of(1, 5, 10, 4, -1, null, 7);
    }

    @Test
    public void testPopElement() {
        Integer originalElement = 1;
        Stack<Integer> stack = LinkedStack.of(originalElement);
        Integer poppedElement = stack.pop();
        assertThat(poppedElement, is(originalElement));
    }

    @Test
    public void testPopTwoElements() {
        Integer originalElement1 = 1;
        Integer originalElement2 = 2;
        Stack<Integer> stack = LinkedStack.of(originalElement1, originalElement2);
        Integer poppedElement2 = stack.pop();
        Integer poppedElement1 = stack.pop();
        assertThat(poppedElement1, is(originalElement1));
        assertThat(poppedElement2, is(originalElement2));
    }

    @Test
    public void testPopManyElements() {
        Stack<Integer> stack = LinkedStack.of(null, 5, 10, null, -1, 12, null);
        assertNull(stack.pop());
        assertThat(stack.pop(), is(12));
        assertThat(stack.pop(), is(-1));
        assertNull(stack.pop());
        assertThat(stack.pop(), is(10));
        assertThat(stack.pop(), is(5));
        assertNull(stack.pop());
    }

    @Test(expected = StackException.class)
    public void testPopEmptyStack() {
        Stack<Integer> stack = LinkedStack.Empty();
        stack.pop();
    }

    @Test
    public void testIsEmptyForEmptyStack() {
        Stack<Integer> stack = LinkedStack.Empty();
        boolean stackIsEmpty = stack.isEmpty();
        assertThat(stackIsEmpty, is(true));
    }

    @Test
    public void testIsEmptyForOneElementStack() {
        Stack<Integer> stack = LinkedStack.of(1);
        boolean stackIsEmpty = stack.isEmpty();
        assertThat(stackIsEmpty, is(false));
    }

    @Test
    public void testIsEmptyForManyElementStack() {
        Stack<Integer> stack = LinkedStack.of(1, 5, 12, -99);
        boolean stackIsEmpty = stack.isEmpty();
        assertThat(stackIsEmpty, is(false));
    }

    @Test
    public void testEmptyStackSize() {
        Stack<Integer> stack = LinkedStack.Empty();
        int stackSize = stack.size();
        assertThat(stackSize, is(0));
    }

    @Test
    public void testOneElementStackSize() {
        Stack<Integer> stack = LinkedStack.of(1);
        int stackSize = stack.size();
        assertThat(stackSize, is(1));
    }

    @Test
    public void testManyElementStackSize() {
        Stack<Integer> stack = LinkedStack.of(1, 2, 3, 4, 5, 6);
        int stackSize = stack.size();
        assertThat(stackSize, is(6));
    }

    @Test
    public void testPushAndPopStackSize() {
        Stack<Integer> stack = LinkedStack.of(1, 2, 3, 4, 5, 6);
        stack.pop();
        stack.pop();
        stack.pop();
        int stackSize = stack.size();
        assertThat(stackSize, is(3));
    }

}

