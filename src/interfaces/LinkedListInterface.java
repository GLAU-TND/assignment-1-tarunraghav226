package interfaces;

import node.Node;

public interface LinkedListInterface<T> {
    /**
     * Method inserts a new node in linked list
     *
     * @param node It is the node to be inserted
     */
    public void insert(Node<T> node);

    /**
     * Method deletes the first node of linked list
     *
     * @return Returns the deleted node
     */
    public Node<T> delete();

    /**
     * Method deletes the node of linked list
     *
     * @param i It is the position of node which is to be deleted
     * @return Returns the deleted node
     */
    public Node<T> delete(int i);

    /**
     * Method tells whether linked list is empty or not
     *
     * @return Returns true if empty else false
     */
    public boolean isEmpty();

    /**
     * Method iterates the linked list
     *
     * @return Returns object to which node is pointing
     */
    public Node<T> getObject();

    /**
     * Method sorts the linked list
     */
    public void sort();

}
