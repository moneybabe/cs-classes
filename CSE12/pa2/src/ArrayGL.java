public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    // Fill in all required methods here

    public E[] toArray() {
        return this.elements;
    };
    public void transformAll(MyTransformer mt) {
        return;
    };
    public void chooseAll(MyChooser mc) {
        return;
    };
    public boolean isEmpty() {
        return this.size == 0;
    };
}