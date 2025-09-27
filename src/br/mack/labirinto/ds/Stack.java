package br.mack.labirinto.ds;

public class Stack<T> {
    private final T[] vetor;
    private int topo = -1;
    private final int capacidade;

    public Stack(int capacidade){
        this.capacidade = capacidade;
        this.vetor = (T[]) new Object[capacidade];
    }

    public boolean isEmpty(){
        return topo == -1;
    }

    public boolean isFull(){
        return topo == capacidade - 1;
    }

    public void push(T x){
        if(!isFull()){
            vetor[++topo] = x;
        } 
    }

    public void pop(){
        if(!isEmpty()){
            vetor[topo--] = null;
        } 
    }

    public T peek(){ 
        if(!isEmpty()){
            return vetor[topo];
        } else {
            System.out.println("Pilha vazia!");
            return null; 
        }
    }
}
