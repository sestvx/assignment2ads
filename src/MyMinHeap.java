import java.util.NoSuchElementException;

public class MyMinHeap<T extends Comparable<T>> {
    private final MyArrayList<T> list;

    public MyMinHeap() {
        this.list = new MyArrayList<>();
    }

    public void insert(T item) {
        list.add(item);
        heapifyUp(list.size() - 1);
    }

    public T extractMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        T min = list.get(0);
        T last = list.get(list.size() - 1);
        list.set(0, last);
        list.remove(list.size() - 1);
        if (!isEmpty()) {
            heapifyDown(0);
        }
        return min;
    }

    public T getMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (list.get(index).compareTo(list.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;
        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;

            if (leftChild < list.size() && list.get(leftChild).compareTo(list.get(minIndex)) < 0) {
                minIndex = leftChild;
            }

            if (rightChild < list.size() && list.get(rightChild).compareTo(list.get(minIndex)) < 0) {
                minIndex = rightChild;
            }

            if (minIndex == index) {
                break;
            }

            swap(index, minIndex);
            index = minIndex;
        }
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}