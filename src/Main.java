import dataStructures.MyLinkedList;
import node.Node;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        for (int i = 1; i <= 10; i++) {
            Node<String> node = new Node<>();
            node.setData("Tarun " + i);
            myLinkedList.insert(node);
        }

        System.out.println("delete(5) --> " + myLinkedList.delete(5));

        for (int i = 0; i < 10; i++) {
            System.out.println("delete --> " + myLinkedList.delete());
        }

    }
}
