package SortingAlgorithms;

import AbstractDataTypes.Trees.Heap;
import AbstractDataTypes.Trees.Item;

public class HeapSort {

    Heap fpHeap;

    public HeapSort() {

    }

    public void Sort(Item[] v, int n) {

        fpHeap = new Heap(v);

        int dir = n;

        fpHeap.constroi();

        while ( dir > 1) {

            Item x = v[1];
            v[1] = v[dir] ;
            v[dir] = x;
            dir--;
            fpHeap.refaz(1, dir);
        }

    }

    public Heap getFpHeap() {
        return fpHeap;
    }
}
