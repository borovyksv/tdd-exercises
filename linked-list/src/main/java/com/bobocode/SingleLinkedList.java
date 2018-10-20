package com.bobocode;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link SingleLinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<E>}.
 *
 * @param <E> generic type parameter
 */
public class SingleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <E> List<E> of(E... elements) {
        List<E> list = new SingleLinkedList<>();
        Arrays.stream(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list. This operation is performed in constant time O(1)
     *
     * @param element element to add
     */
    @Override
    public void add(E element) {
        if (first == null) {
            last = first = new Node<>(element);
        } else {
            Node<E> newNode = new Node<>(element);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, E element) {
        Node<E> currentNode = getNode(index);
        Node<E> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = currentNode;
            first = newNode;
        } else {
            Node<E> previousNode = getNode(index - 1);
            newNode.next = currentNode;
            previousNode.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, E element) {
        getNode(index).element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     */
    @Override
    public E getFirst() {
        throwIfEmpty();
        return first.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     */
    @Override
    public E getLast() {
        throwIfEmpty();
        return last.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        Node<E> currentNode = getNode(index);
        currentNode.element = null;
        if (index == 0) {
            first = currentNode.next;
        } else if (index == size - 1) {
            Node<E> previousNode = getNode(index - 1);
            previousNode.next = null;
            last = previousNode;
        } else {
            Node<E> previousNode = getNode(index - 1);
            previousNode.next = currentNode.next;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(E element) {
        Node<E> current = this.first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.element, element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<E> getNode(int index) {
        throwIfOutOfBounds(index);

        Node<E> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void throwIfEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private void throwIfOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
