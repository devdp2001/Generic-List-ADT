package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A LinkedGenListIterator class that implements the Iterator class.
 */
public class LinkedGenListIterator<T> implements Iterator<T> {

    private Node<T> linkedListNode; //instance variable showing the current Node

    
    /**
     * Constructor that iterates through a Node list.
     * @param list the linked list used to iterate. 
     */
    public LinkedGenListIterator(Node<T> list) {
        linkedListNode = list;
    } //LinkedGenListIterator
    
    /**
     * {@inheritDoc}
     */
    public boolean hasNext() {
        //if there is a next Node, then it returns true, else, returns false
        if (linkedListNode.getNext() != null) {
            return true;
        } else {
            return false;
            
        }
    } //hasNext

    /**
     * {@inheritDoc}
     */
    public T next() {
        //Throw error if there is not a next Node, throws error statement
        if (hasNext() == false) {
            throw new NoSuchElementException("The iteration has no more elements.");
        } else {
            T nextNode = linkedListNode.getNext().getCurrentElement();
            linkedListNode = linkedListNode.getNext();

            return nextNode;
        }
    } //next
} //LinkedGenListIterator
