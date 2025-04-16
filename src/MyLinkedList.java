import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<T> {
        T data;
        MyNode<T> next;
        MyNode<T> prev;

        MyNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode<T> node = getNode(index);
        node.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode<T> current = getNode(index);
            MyNode<T> newNode = new MyNode<>(item);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        return tail.data;
    }

    @Override
    public void remove(int index) {
        MyNode<T> nodeToRemove = getNode(index);
        if (nodeToRemove.prev == null) {
            head = nodeToRemove.next;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
        }
        if (nodeToRemove.next == null) {
            tail = nodeToRemove.prev;
        } else {
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new IndexOutOfBoundsException("List is empty");
        remove(0);
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new IndexOutOfBoundsException("List is empty");
        remove(size - 1);
    }

    @Override
    public void sort() {
        if (size <= 1) return;

        boolean swapped;
        do {
            swapped = false;
            MyNode<T> current = head;
            while (current.next != null) {
                if (((Comparable<T>) current.data).compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (MyNode<T> current = head; current != null; current = current.next) {
            if (object.equals(current.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (MyNode<T> current = tail; current != null; current = current.prev) {
            if (object.equals(current.data)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (MyNode<T> current = head; current != null; current = current.next) {
            array[index++] = current.data;
        }
        return array;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private MyNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        MyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}
