import java.util.NoSuchElementException;

public class ArrayList<E> {
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            illegalArgument(capacity);
        }
        elements = (E[]) new Object[capacity];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E toReturn = elements[index];
        elements[index] = element;
        return toReturn;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        E toRemove = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return toRemove;
    }

    public E remove(E element) {
        int index = indexOf(element);
        if (index == ELEMENT_NOT_FOUND) {
            noSuchElement(element);
        }
        return remove(index);
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // newCapacity = 1.5 * oldCapacity
        int newCapacity;
        if (oldCapacity == 0) {
            newCapacity = Math.max(capacity, DEFAULT_CAPACITY);
        } else {
            newCapacity = oldCapacity + (oldCapacity >> 1);
        }
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("Resize: from " + oldCapacity + " to new " + newCapacity + " .");
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void noSuchElement(E element) {
        throw new NoSuchElementException("Element not found: " + element);
    }

    private void illegalArgument(int capacity) {
        throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}