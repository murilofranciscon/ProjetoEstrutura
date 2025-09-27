package br.mack.labirinto.ds;

@SuppressWarnings("unchecked")
public class Stack<T> {
    public T popAndReturn() {
        if (isEmpty()) return null;
        T value = vetor[topo];
        vetor[topo--] = null;
        return value;
    }
    private final T[] vetor;
    private int topo = -1;

    public Stack(int capacidade){
        if (capacidade <= 0) throw new IllegalArgumentException("capacidade");
        this.vetor = (T[]) new Object[capacidade];
    }

    public boolean isEmpty(){ return topo == -1; }
    public boolean isFull(){ return topo == vetor.length - 1; }

    public void push(T x){
        if (isFull()) throw new IllegalStateException("Pilha cheia");
        vetor[++topo] = x;
    }

    public void pop(){
        if (isEmpty()) return;
        vetor[topo--] = null;
    }

    public T peek(){ return isEmpty()? null : vetor[topo]; }
    public int size() { return topo + 1; }
    public int capacity() { return vetor.length; }
}
