package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

public class Inventory {
    private Stack<Character> keys;

    public Inventory(int capacity) {
        keys = new Stack<>(capacity);
    }

    public void addKey(char k) {
        keys.push(k);
    }

    // ✅ Corrigido: retorna a chave removida
    public Character useKey() {
        return keys.pop();
    }

    public Character peekKey() {
        return keys.peek();
    }
}
