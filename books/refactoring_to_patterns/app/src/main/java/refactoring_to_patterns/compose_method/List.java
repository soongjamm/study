package refactoring_to_patterns.compose_method;

public class List {

    private Object[] elements;
    private int newSize;
    private int size;

    public void add(Object element) {
        boolean readonly = false;
        if (!readonly) {
            newSize = size + 1;
            if (newSize > elements.length) {
                Object[] newElements = new Object[elements.length + 10];
                for (int i = 0; i < size; i++) {
                    newElements[i] = elements[i];
                }
                elements = newElements;
            }
            elements[size++] = element;
        }
    }
}
