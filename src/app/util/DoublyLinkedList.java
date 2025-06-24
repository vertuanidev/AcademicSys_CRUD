package app.util;

import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {
    private static class Node<E> {
        E data;
        Node<E> next, prev;
        Node(E data) { this.data = data; }
    }

    private final Node<T> head;
    private int size;

    public DoublyLinkedList() {
        head = new Node<>(null);
        head.next = head.prev = head;
        size = 0;
    }

    public void add(T item) {
        Node<T> node = new Node<>(item);
        node.prev = head.prev;
        node.next = head;
        head.prev.next = node;
        head.prev = node;
        size++;
    }

    public boolean remove(T item) {
        for (Node<T> cur = head.next; cur != head; cur = cur.next) {
            if (cur.data.equals(item)) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                size--;
                return true;
            }
        }
        return false;
    }
    
@Override
    public void forEach(Consumer<? super T> action) {
        for (Node<T> cur = head.next; cur != head; cur = cur.next) {
            action.accept(cur.data);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> cur = head.next;
            @Override public boolean hasNext() { return cur != head; }
            @Override public T next() {
                T val = cur.data;
                cur = cur.next;
                return val;
            }
        };
    }

    public int size() { return size; }
}