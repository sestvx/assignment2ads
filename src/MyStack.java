import java.util.NoSuchElementException;

public class MyStack<T> {
    private final MyLinkedList<T> list;

    public MyStack() {
        this.list = new MyLinkedList<>();
    }

    public void push(T item) {
        list.addFirst(item);
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}