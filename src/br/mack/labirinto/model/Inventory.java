package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

public class Inventory {
    private final Stack<Character> keys;

    public Inventory(int capacity) { keys = new Stack<>(capacity); }

    public boolean addKey(char k) {
        if (keys.isFull()) return false;
        keys.push(k);
        return true;
    }

    public Character useKey() {
        Character k = keys.peek();
        if (k != null) keys.pop();
        return k;
    }

    public Character peekKey() { return keys.peek(); }
    public int size() { return keys.size(); }

    public String asStringTopToBase() {
        if (keys.isEmpty()) return "(vazio)";
        Stack<Character> aux = new Stack<>(keys.capacity());
        StringBuilder sb = new StringBuilder();
        Character x;
        boolean first = true;
        while ((x = keys.popAndReturn()) != null) aux.push(x);
        while ((x = aux.popAndReturn()) != null) {
            if (!first) sb.append(' ');
            sb.append(x);
            keys.push(x);
            first = false;
        }
        return sb.toString();
    }
}
