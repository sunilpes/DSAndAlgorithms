package com.sunil.collections.array;


import java.util.Arrays;
import java.util.stream.IntStream;

public class DynamicArray {

    int initial_capacity = 10;
    int[] array = null;
    int size = 0;
    double size_limit = 0.8;
    boolean limitReached = false;


    public DynamicArray(int capacity) {
        System.out.println("creating array of size: " + capacity);
        assert (capacity <= Integer.MAX_VALUE): "Integer value too large!";
        capacity = Math.max(capacity, initial_capacity);
        array = new int[capacity];
    }

    public void add(int value) {
        if (size >= Math.round(array.length * size_limit)) {
            increaseCapacity();
        }
        array[size] = value;
        size++;
    }

    void increaseCapacity() {
        if (limitReached) return;
        long length = array.length;
        int tempArraySize = (int)length*2;

        System.out.println("increased capacity: " + tempArraySize);

        if (tempArraySize <= -1) {
            System.out.println("Max capacity reached!");
            tempArraySize = Integer.MAX_VALUE - 8;
            limitReached = true;
        }

        int[] temp_array = new int[tempArraySize];
        System.arraycopy(array, 0, temp_array, 0, array.length);
        array = temp_array;
        size = (int)length/2;
    }

    void print() {
        System.out.println(Arrays.toString(array));
    }

    public int length() {
        return array.length;
    }


    public static void main(String[] args) {
        DynamicArray da = new DynamicArray(10);
        IntStream.range(1, 1000).forEach((n) -> {
                da.add(1);
        });
        da.print();
        System.out.println("Array length : " + da.length());
    }
}
