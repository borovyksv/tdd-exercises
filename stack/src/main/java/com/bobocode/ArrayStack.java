package com.bobocode;

import java.util.Arrays;

public class ArrayStack<T> implements Stack<T> {
    public static final int INITIAL_CAPACITY = 1 << 4;

    private Object[] storage;
    private int elementsCount;
    private int max_capacity;

    public static <T> ArrayStack<T> of(T... elements) {
        ArrayStack<T> stack = new ArrayStack<>();
        Arrays.stream(elements).forEach(stack::push);
        return stack;
    }

    public ArrayStack() {
        this.storage = new Object[max_capacity = INITIAL_CAPACITY];
    }

    public ArrayStack(int capacity) {
        this.storage = new Object[max_capacity = capacity];
    }

    @Override
    public void push(T element) {
        if (elementsCount + 1 > max_capacity)
            grow();
        storage[elementsCount++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        throwIfEmpty();
        return (T) storage[elementsCount - 1];
    }

    @Override
    public T pop() {
        T peek = peek();
        deleteCurrentElement();
        elementsCount--;
        return peek;
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    private void deleteCurrentElement() {
        storage[elementsCount] = null;
    }

    private void throwIfEmpty() {
        if (isEmpty())
            throw new EmptyStackException("Can't Pop. The Stack is empty");
    }

    private void grow() {
        Object[] newStorage = new Object[max_capacity = max_capacity << 1];
        System.arraycopy(storage, 0, newStorage, 0, storage.length);
        storage = newStorage;
    }
}
