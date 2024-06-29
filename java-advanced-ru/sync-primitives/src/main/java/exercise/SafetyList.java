package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] array;
    private int size;

    public SafetyList() {
        this.array = new int[10];
        this.size = 0;
    }

    public static void main(final String[] args) {
        SafetyList list = new SafetyList();
        list.add(5);
        list.add(7);
        System.out.println(list.get(0)); // 5
        System.out.println(list.get(1)); // 7
        System.out.println(list.getSize()); // 2
    }

    public synchronized void add(final int element) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = element;
    }

    public int get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
