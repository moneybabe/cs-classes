import java.util.*;

class StackWorklist implements SearchWorklist {
	
	Stack<Square> stack = new Stack<Square>();

    public void add(Square c) {
        this.stack.push(c);
    }

    public Square remove() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}

class QueueWorklist implements SearchWorklist {

    LinkedList<Square> queue = new LinkedList<Square>();

    public void add(Square c) {
        this.queue.add(c);
    }

    public Square remove() {
        return queue.remove();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

public interface SearchWorklist {
	void add(Square c);
	Square remove();
	boolean isEmpty();
}
