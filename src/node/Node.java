package node;

//Generic Node class
public class Node<T> {
    private T data;         //Data of node
    private Node<T> next;   //Self referential data

    //Getter for data
    public T getData() {
        return data;
    }

    //Setter for data
    public void setData(T data) {
        this.data = data;
    }

    //Getter for self referential data
    public Node<T> getNext() {
        return next;
    }

    //Setter for self referential data
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
