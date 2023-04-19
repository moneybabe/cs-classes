public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    // Fill in all required methods here

    @Override
    public E[] toArray() {
        return this.elements;
    };
    
    @Override
    @SuppressWarnings("rawtypes")
    public void transformAll(MyTransformer mt) {
        for (int i = 0; i < this.size; i++) {   
            this.elements[i] = (E) mt.transformElement(this.elements[i]);
        }
        return;
    };

    @SuppressWarnings("rawtypes")
    public void chooseAll(MyChooser mc) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (mc.chooseElement(this.elements[i])) {
                count++;
            }
        }

        E[] temp = (E[]) new Object[count];
        int index = 0;
        for (int i = 0; i < this.size; i++) {
            if (mc.chooseElement(this.elements[i])) {
                temp[index] = this.elements[i];
                index++;
            }
        }

        this.elements = temp;
        this.size = count;
        
        return;
    };
    
    public boolean isEmpty() {
        return this.size == 0;
    };
}