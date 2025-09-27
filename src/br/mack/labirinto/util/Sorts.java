package br.mack.labirinto.util;

import br.mack.labirinto.model.ScoreEntry;

public class Sorts {
    public static void insertionSort(ScoreEntry[] arr, int n) {
        for (int i = 1; i < n; i++) {
            ScoreEntry key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getScore() < key.getScore()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int binarySearchByName(ScoreEntry[] arr, int n, String name) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = arr[mid].getPlayer().compareToIgnoreCase(name);
            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
