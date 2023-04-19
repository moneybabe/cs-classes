import java.lang.reflect.Array;

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
    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];
        Node current = this.front;
        for (int i = 0; i < this.size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    };

    @SuppressWarnings("rawtypes")
    public void transformAll(MyTransformer mt) {
        Node current = this.front;
        for (int i = 0; i < this.size; i++) {
            current.value = (E) mt.transformElement(current.value);
            current = current.next;
        }
        return;
    };

    @SuppressWarnings("rawtypes")
    public void chooseAll(MyChooser mc) {

        if (this.size == 0) {
            return;
        }

        // find the first element that is true
        Node current = this.front;
        while (mc.chooseElement(current.value) == false) {
            current = current.next;
            this.size--;

            if (current == null) {
                this.front = new Node(null, null);
                this.size = 0;
                return;
            }
        }

        this.front = current;

        // start looping
        while (current.next != null) {
            if (mc.chooseElement(current.next.value) == false) {
                current.next = current.next.next;
                this.size--;
            } else {
                current = current.next;
            }
        }

        return;
    };

    public boolean isEmpty() {
        return this.size == 0;
    };


    public static void main(String[] args) {
        String[][] a = {{"asdf", "dsfgdsf"}, {"Sdfdsf", "Fdsf"}, {"fsdf"}, {"sdfdsf"}};
        LinkedGL<String[]> lgl = new LinkedGL<String[]>(a);
        String[][] b = lgl.toArray();

    }
}