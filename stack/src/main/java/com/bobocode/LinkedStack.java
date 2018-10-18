package com.bobocode;

import java.util.Arrays;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> root;
    private int size = 0;

    @Override
    public void push(T element) {
        if (root == null) {
            root = new Node<>(element, null);
        } else {
            root = new Node<>(element, root);
        }
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new StackException("Can't Pop. Stack is empty");
        Node<T> current = this.root;
        this.root = current.getPrev();
        size--;
        return current.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    public static <T> LinkedStack<T> Empty() {
        return new LinkedStack<>();
    }

    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> linkedStack = new LinkedStack<>();
        Arrays.stream(elements).forEach(linkedStack::push);
        return linkedStack;
    }

    private class Node<T> {
        private T value;
        private Node<T> prev;

        private Node(T value, Node<T> prev) {
            this.value = value;
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }
        public Node<T> getPrev() {
            return prev;
        }
    }
}

