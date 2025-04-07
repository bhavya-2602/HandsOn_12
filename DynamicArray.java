public class DynamicArray {
    private int[] arr;
    private int size;
    private int capacity;

    public DynamicArray() {
        capacity = 2;
        arr = new int[capacity];
        size = 0;
    }

    public void add(int val) {
        if (size == capacity) resize(capacity * 2); // double the capacity if full
        arr[size++] = val;
    }

    public void insert(int idx, int val) {
        if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
        if (size == capacity) resize(capacity * 2);
        for (int i = size; i > idx; i--) arr[i] = arr[i - 1]; // shift right
        arr[idx] = val;
        size++;
    }

    public void delete(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        for (int i = idx; i < size - 1; i++) arr[i] = arr[i + 1]; // shift left
        size--;
        if (size <= capacity / 4 && capacity > 2) resize(capacity / 2); // shrink if needed
    }

    public void remove() {
        if (size > 0) {
            size--;
            if (size <= capacity / 4 && capacity > 2) resize(capacity / 2);
        }
    }

    private void resize(int newCap) {
        int[] newArr = new int[newCap];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        capacity = newCap;
    }

    public int get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        return arr[idx];
    }

    public int getSize() { return size; }
    public int getCapacity() { return capacity; }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray dyn = new DynamicArray();

        dyn.add(1);
        dyn.add(2);
        dyn.add(3);
        dyn.add(4);

        System.out.println("Elements: " + dyn);
        System.out.println("Size: " + dyn.getSize());
        System.out.println("Capacity: " + dyn.getCapacity());

        dyn.insert(2, 99);
        System.out.println("After inserting 99 at index 2: " + dyn);

        dyn.delete(3);
        System.out.println("After deleting element at index 3: " + dyn);

        dyn.remove();
        System.out.println("After removing last element: " + dyn);
        System.out.println("Size: " + dyn.getSize());
        System.out.println("Capacity: " + dyn.getCapacity());
    }
}

