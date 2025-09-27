package br.mack.labirinto.ds;

public class SinglyLinkedList<T> {
    private static class Node<U>{ U v; Node<U> n; Node(U v){this.v=v;} }
    private Node<T> head, tail;

    public void add(T value) {
        Node<T> nn = new Node<>(value);
        if (tail == null) { head = tail = nn; }
        else { tail.n = nn; tail = nn; }
    }

    public void printAll() {
        Node<T> cur = head;
        while (cur != null) { System.out.println(" - " + cur.v); cur = cur.n; }
    }
}
