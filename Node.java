package cs1302.genlist;

/**
 * Node class represting Nodes in a list.
 */
public class Node<T> {
    
    private Node<T> nextNode; //instance variable showing the next Node
    private T currentElement; //instance variable showing element in Node

    
    /**
     * Constructor that stores its current Element and next value.
     *
     * @param e Parameter for current element
     * @param node Parameter for a Node
     */
    public Node(T e, Node<T> node) {
        nextNode = node;
        currentElement = e;
    }

    /**
     * Constructor that stores its current Element.
     *
     *@param e Parameter for current element 
     */
    public Node(T e) {
        nextNode = null;
        currentElement  = e;
    }
    
    /**
     * Constructor that creates an empty node with null element and next.
     */
    public Node() {
        nextNode = null;
        currentElement = null;
    }

    /**
     * Sets the next to the node Parameter.
     *
     * @param node Parameter for a Node
     */
    public void setNext(Node<T> node) {
        nextNode = node;
    }

    /**
     * Sets the current nodes Element.
     *
     * @param e Parameter for current element
     */
    public void setCurrentElement(T e) {
        currentElement = e;
    }

    /**
     * Returns a reference to the next Node.
     *
     * @return The next Node
     */
    public Node<T> getNext() {
        return nextNode;
    }

    /**
     * Returns the Element in the current Node.
     *
     * @return The current Element in current Node   
     */
    public T getCurrentElement() {
        return currentElement;
    }
    
} //OwnNode
