package com.test.datastructure;

/**
 * @author Snowson
 **/
public class MaxHeap {
    private int[] data;
    private int length;
    public MaxHeap(int[] arrays) {
        data = new int[arrays.length + 1];
        length = data.length;
        create(arrays);
    }

    public void create(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            insert(i + 1, arrays[i]);
        }
    }

    public void insert(int i, int ele) {
        data[i] = ele;
        int p = i / 2;
        if (p <= 0) return;
        while (data[p] < data[i]) {
            swap(p, i);
            i = p;
            p = p / 2;
        }
    }

    public void swap(int p, int i) {
        int cur = data[p];
        data[p] = data[i];
        data[i] = cur;
    }

    public int delMax() {
        int ret = data[1];
        data[1] = data[length - 1];
        length -= 1;
        return ret;
    }
}
