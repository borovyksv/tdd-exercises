package com.bobocode;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link DoublyLinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<E>}.
 *
 * @param <E> generic type parameter
 */
public class DoublyLinkedList<E> implements List<E> {

    private Node<E> last;
    private Node<E> first;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <E>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <E> List<E> of(E... elements) {
        DoublyLinkedList<E> list = new DoublyLinkedList<>();
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
        if (last == null) {
            last = new Node<>(element);
            first = last;
        } else {
            Node<E> l = last;
            Node<E> newNode = new Node<>(element, l, null);
            l.next = newNode;
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
        Node<E> current = getNode(index);
        Node<E> newNode = new Node<>(element, current.prev, current);
        if (index == 0) {
            current.next.prev = newNode;
            first = newNode;
        } else {
            current.prev.next = newNode;
            if (current.next != null) {
                current.next.prev = newNode;
            }
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
        Node<E> current = getNode(index);
        current.element = element;
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

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = this.first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            current = current.next;
        }
        return current;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     */
    @Override
    public E getFirst() {
        if (first == null) throw new NoSuchElementException();
        return first.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     */
    @Override
    public E getLast() {
        if (last == null) throw new NoSuchElementException();
        return last.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public void remove(int index) {
        Node<E> current = getNode(index);
        current.element = null;
        if (index == 0) {
            first = current.next;
            first.prev = null;
        } else if (index == size - 1) {
            last = current.prev;
            last.next = null;
        } else {
            current.next.prev = current.prev;
            current.prev.next = current.next;
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
        Node<E> current = this.last;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.element, element)) {
                return true;
            } else {
                current = current.prev;
            }
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
        Node<E> current = last;
        int originalSize = size;
        for (int i = 0; i < originalSize; i++) {

            Node<E> tmp = current.prev;
            current.element = null;
            current.prev = null;
            current.next = null;
            current = tmp;
            size--;
        }
        last = null;
        first = null;
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public Node(E element) {
            this.element = element;
        }
    }
}
