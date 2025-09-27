package br.mack.labirinto.util;

import br.mack.labirinto.model.ScoreEntry;

public class Sorts {

    // Ordena por score decrescente
    public static void insertionSortScoreDesc(ScoreEntry[] a, int n){
        for (int i=1;i<n;i++){
            ScoreEntry key=a[i]; int j=i-1;
            while (j>=0 && a[j].getScore() < key.getScore()) { a[j+1]=a[j]; j--; }
            a[j+1]=key;
        }
    }

    public static void quickSortScoreDesc(ScoreEntry[] a, int l, int r){
        if (l>=r) return;
        int i=l, j=r; int pivot = a[(l+r)/2].getScore();
        while (i<=j){
            while (a[i].getScore() > pivot) i++;
            while (a[j].getScore() < pivot) j--;
            if (i<=j){ ScoreEntry tmp=a[i]; a[i]=a[j]; a[j]=tmp; i++; j--; }
        }
        if (l<j) quickSortScoreDesc(a,l,j);
        if (i<r) quickSortScoreDesc(a,i,r);
    }

    // Busca binária por nome (assume array ordenado alfabeticamente por nome)
    public static int binarySearchByName(ScoreEntry[] arr, int n, String name) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int cmp = arr[mid].getPlayer().compareToIgnoreCase(name);
            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
