package dataStructures;

import node.Node;

public class MyLinkedList<T> {
    private Node<T> head;           //It is the beginning of linked list
    private Node<T> iter;           //It is used to iterate the list

    public MyLinkedList() {
        iter = head;
    }

    public Node<T> getHead() {
        return head;
    }        //getter for head

    public void setHead(Node<T> node) {
        head = node;
    }   //setter for head

    public Node<T> getIter() {
        return iter;
    }                //Getter for iter

    public void setIter(Node<T> iter) {
        this.iter = iter;
    }  //Setter for iter

    //method to insert new node
    public void insert(Node<T> node) {
        if (getHead() == null) {
            setHead(node);
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(node);
        }
    }

    //method to delete node from beginning
    public Node<T> delete() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return null;
        }
        Node<T> temp = getHead();
        setHead(getHead().getNext());
        return temp;
    }

    //method to delete node at particular position
    public Node<T> delete(int i) {
        Node<T> deletedNode = new Node<>();
        Node<T> temp = getHead();
        if (isEmpty()) {
            System.out.println("Underflow");
            return temp;
        }
        if (i == 1) {
            deletedNode = getHead();
            setHead(null);
        } else {
            for (int j = 1; j < i - 1; j++) {
                temp = temp.getNext();
            }
            deletedNode = temp.getNext();
            temp.setNext(temp.getNext().getNext());
        }
        return deletedNode;
    }

    //Helper method to check if linked list is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    //Method to fetch object one by one
    public Node<T> getObject() {

        Node<T> temp = new Node<>();

        if (getIter() == null && getHead() == null) {
            System.out.println("List is empty");
            return null;
        } else if (getIter() == null) {
            setIter(getHead());
        }
        temp = getIter();
        setIter(getIter().getNext());
        return temp;
    }
}
