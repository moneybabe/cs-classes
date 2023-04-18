public class LinkedGL<E> implements MyList<E> {

    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node front;
    int size;

    public LinkedGL(E[] contents) {
        // Fill in this constructor
        this.size = contents.length;
        if (this.size == 0) {
            this.front = null;
            return;
        }

        this.front = new Node(contents[0], null);
        Node current = this.front;
        for (int i = 1; i < contents.length; i++) {
            current.next = new Node(contents[i], null);
            current = current.next;
        }
    }

    // Fill in all methods
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];
        Node current = this.front;
        for (int i = 0; i < this.size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
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