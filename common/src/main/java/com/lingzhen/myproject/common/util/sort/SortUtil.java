package com.lingzhen.myproject.common.util.sort;

/**
 * 排序类（算法）
 * createTime:2020-08-07
 * @Author lingz
 */
public class SortUtil<T extends RecType> {

    /**
     * 插入排序：直接插入排序
     * O(n^2)
     * @param ts
     * @return
     */
    public T[] directInsertSort(T[] ts){
        int index = 1;
        for (int i = 2; i < ts.length; i++) {
            if (ts[i].key < ts[i-1].key) {
                ts[0] = ts[i];
                int j;
                for (j = i-1; ts[0].key < ts[j].key; j--){
                    ts[j+1] = ts[j];
                    System.out.println("第"+(index++)+"次交换");
                }
                ts[j+1] = ts[0];
            }
        }
        return ts;
    }

    /**
     * 插入排序：希尔排序
     * O(nlog2n)
     * @param ts
     * @param ds
     * @return
     */
    public T[] shellSort(T[] ts,int[] ds){
        int index = 1;
        for (int n : ds) {
            for (int i = n+1; i < ts.length; i++) {
                if (ts[i].key < ts[i - n].key) {
                    ts[0] = ts[i];
                    int j;
                    for (j = i-n; j > 0 && ts[0].key < ts[j].key; j -= n) {
                        ts[j + n] = ts[j];
                        System.out.println("第"+(index++)+"次交换");
                    }
                    ts[j+n] = ts[0];
                }
            }
        }
        return ts;
    }

    /**
     * 交换排序：冒泡排序
     * O(n^2)
     * @param ts
     * @return
     */
    public T[] bubblingSort(T[] ts) {
        int index = 1;
        for (int i = 1; i < ts.length; i++) {
            for (int j =0; j < ts.length -i; j++) {
                if (ts[j].key > ts[j+1].key) {
                    T t = ts[j+1];
                    ts[j+1] = ts[j];
                    ts[j] = t;
                    System.out.println("第"+(index++)+"次交换");
                }
            }
        }
        return ts;
    }

    /**
     * 交换排序：快速排序
     * O(nlog2n)
     * @param ts
     * @return
     */
    public T[] quickSort(T[] ts, int low, int high) {
        if (low < high) {
            T t = ts[low];
            int i = low;
            int j = high;
            while (i < j) {
                while (i < j && t.key <= ts[j].key) {
                    j--;
                }
                if (i < j) {
                    ts[i] = ts[j];
                    i++;
                }
                while (i < j && ts[i].key <= t.key){
                    i++;
                }
                if (i < j) {
                    ts[j] = ts[i];
                    j--;
                }
            }
            ts[i] = t;
            quickSort(ts,low,i-1);
            quickSort(ts,i+1,high);
        }
        return ts;
    }

    /**
     * 选择排序：直接选择排序
     * O(n^2)
     * @param ts
     * @return
     */
    public T[] directSelectionSort(T[] ts){
        T t;
        for (int i = 0; i < ts.length-1; i++) {
            for (int j = i; j < ts.length; j++) {
                if (ts[j].key < ts[i].key) {
                    t = ts[i];
                    ts[i] = ts[j];
                    ts[j] = t;
                }
            }
        }
        return ts;
    }

    /**
     * 选择排序：堆排序
     * O(nlog2n)
     * @param ts
     * @return
     */
    public T[] heapSort(T[] ts){
        for (int i = ts.length/2; i>0; i--){
            heapSortSon(ts,i,ts.length);
        }
        for (int i = ts.length; i > 0; i--) {
            T t = ts[0]; ts[0] = ts[i-1]; ts[i-1] = t;
            heapSortSon(ts,1,i-1);
        }
        return ts;
    }
    private T[] heapSortSon(T[] ts,int i,int n){
        int j = i *2;
        while (j <= n) {
            if (j+1 < n && ts[j-1].key < ts[j].key) {
                j++;
            }
            if (ts[i-1].key < ts[j-1].key) {
                T t = ts[i-1];
                ts[i-1] = ts[j-1];
                ts[j-1] = t;
            }
            i = j;
            j = i*2;
        }
        return ts;
    }

    /**
     * 并归排序：并归排序
     * O(nlog2n)
     * @param ts
     * @return
     */
    public RecType[] mergeSort(RecType[] ts){
        RecType[] newRt = new RecType[ts.length];
        for (int len = 1; len <= ts.length; len = len*2) {
            int i;
            for (i = 1; i+len*2 <= ts.length; i = i +len*2) {
                mergeSortSon(ts,newRt,i,len,i+len*2-1);
            }
            if (i+len+1 <= ts.length) {
                mergeSortSon(ts,newRt,i,len,ts.length);
            } else {
                for (;i <= ts.length; i++) {
                    newRt[i-1] = ts[i-1];
                }
            }
            for (int ts_i = 0; ts_i < ts.length; ts_i++) {
                ts[ts_i] = newRt[ts_i];
            }
        }
        return newRt;
    }
    private void mergeSortSon(RecType[] rt, RecType[] newRt, int low, int len, int high){
        int i = low;
        int j = low+len;
        int index = low;
        for (;i < low+len && j <= high;) {
            if (rt[i-1].key <= rt[j-1].key) {
                newRt[index-1] = rt[i-1];
                i++;
            } else {
                newRt[index-1] = rt[j-1];
                j++;
            }
            index++;
        }
        for (; i < low+len;) {
            newRt[index-1] = rt[i-1];
            i++;index++;
        }
        for (; j <= high;) {
            newRt[index-1] = rt[j-1];
            j++;index++;
        }
    }

    /**
     * 基数排序：箱排序
     * O(d*n)
     * @param ts
     * @return
     */
    public RecType[] boxSort(RecType[] ts){
        int max = ts[0].key;
        for (RecType t : ts) {
            if (t.key > max) {
                max = t.key;
            }
        }
        RecType[] rt = new RecType[max+1];
        for (RecType t : ts) {
            rt[t.key] = t;
        }
        int i = 0;
        for (RecType t : rt) {
            if (null != t) {
                ts[i++] = t;
            }
        }
        return ts;
    }



}
