package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;

/**
 * LinkedGenList class that implements GenList.
 */
public class LinkedGenList<T> implements GenList<T> {

    private Node<T> genericList; //instance variable of the list
    
    /**
     * Constructor that creates a LinkedGenList object with an empty node.
     */
    public LinkedGenList() {
        genericList = new Node<T>();
    } //LinkedGenList
    
    /**
     * Constructor that created a LinkedGenList object and adds a list to it.
     *
     * @param <U> the lists' object type
     * @param other the list that will be added
     */
    public <U extends T> LinkedGenList(GenList<U> other) {
        genericList = new Node<T>();
        Node<T> placeHolder = genericList;

        for (int i = 0; i < other.size(); i++) {
            T element = other.get(i);
            
            placeHolder.setNext(new Node<T>(element, null));
            placeHolder = placeHolder.getNext();
        }
    } //LinkedGenList
        
    /**
     * {@inheritDoc}
     */
    public int size() {
        int size = 0;
        Node<T> placeHolder = genericList.getNext();

        //checking through list, and counting non null vlues to find size
        while (placeHolder != null) {
            placeHolder = placeHolder.getNext();
            size++;
        }
        return size;
    } //size
    
    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        boolean isEmpty = false;

        //checking size to see whether list is empty or not
        if (size() == 0) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty;
    } //isEmpty
    
    /**
     * {@inheritDoc}
     */
    public boolean contains(T o) {
        boolean containsObject = false;
        //if-statement checking for error
        if (o == null) {
            throw new NullPointerException("The Object is null.");
        }
        //for loop going through each object to see if it contains the parameter
        for (int i = 0; i < size(); i++) {
            if (o.equals(get(i))) {
                containsObject = true;
            }
        }
        return containsObject;
    } //contains

    /**
     * {@inheritDoc}
     */
    public T[] toArray(IntFunction<T[]> gen) {
        T[] toArray = gen.apply(size());

        for (int i = 0; i < size(); i++) {
            toArray[i] = get(i);
        }
        return toArray;
    } //toArray
    
    /**
     * {@inheritDoc}
     */
    public boolean add(T obj) {
        boolean add = false;
        //if-statement checking for error
        if (obj == null) {
            throw new NullPointerException("The Object is null.");
        } 
        Node<T> placeHolder = genericList;

        while (placeHolder.getNext() != null) {
            placeHolder = placeHolder.getNext();
        }
        Node<T> addedObj = new Node<T>(obj);

        placeHolder.setNext(addedObj);

        add = true;
        return add;
    } //add
    
    /**
     * {@inheritDoc}
     */
    public <U extends T> boolean add(GenList<U> list) {
        boolean add = false;
        //if-statement checking for error
        if (list == null) {
            throw new NullPointerException("The List is null.");
        }
        if (this == list) { //Handles self reference
            GenList<U> newList = new LinkedGenList<U>(list);

            add(newList);
        } else {
            Node<T> placeHolder = retrieveNode(size());

            //for loop going through @list to add it to the a
            for (int i = 0; i < list.size(); i++) {
                placeHolder.setNext(new Node<T>(list.get(i)));
                placeHolder = placeHolder.getNext();
            }
        }
        add = true;
        return add;
    } //add

    /**
     * {@inheritDoc}
     */
    public boolean add(int index, T obj) {
        boolean add = false;
        //if-statement checking for error
        if (obj == null) {
            throw new NullPointerException("The Object is null.");
        } else if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (index == size()) {
            //adding to end of list
            add(obj);
        } else if (index == 0) {
            Node<T> placeHolder = new Node<T>(obj, genericList.getNext());
            Node<T> listHead = new Node<T>();

            listHead.setNext(placeHolder);

            genericList = listHead;
        } else {
            Node<T> listHead = genericList.getNext();

            //for-loop that gets elements before adding
            for (int i = 0; i < index - 1; i++) {
                listHead = listHead.getNext();
            }
            //after element is added elements
            Node<T> listTail = new Node<T>(obj, listHead.getNext());

            //brings both together
            listHead.setNext(listTail);
        }
        add = true;
        return add;
    } //add

    /**
     * {@inheritDoc}
     */
    public <U extends T> boolean add(int index, GenList<U> list) {
        boolean add = false;
        //if-statement checking for error
        if (list == null) {
            throw new NullPointerException("The List is null.");
        }
        if (this == list) { 
            GenList<U> placeHolder = new LinkedGenList<U>(list);
            add(index, placeHolder);
        } else {
            //if-statement adding to the end
            if (index == size()) {
                add(list);
            } else {
                if (index == 0) { //if-statement for index being 0
                    Node<T> placeHolder = new Node<T>();
                    //creating a placeHolder list to store elements
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {   
                            placeHolder.setNext(new Node<T>(list.get(i)));
                        } else {
                            placeHolder.getNext().setNext(new Node<T>(list.get(i)));
                        }
                    }
                    Node<T> holderTwo = placeHolder.getNext();
                    //creating second placeHolder list to store more elements
                    for (int i = 0; i < list.size() - 1; i++) {
                        holderTwo = holderTwo.getNext();
                    }
                    Node<T> finalList = genericList.getNext();
                    //creating last list to store more elements
                    for (int i = 0; i < size(); i++) {
                        if (i == 0) {
                            holderTwo.setNext(new Node<T>(finalList.getCurrentElement()));
                        } else {
                            holderTwo.getNext().setNext(new Node<T>(finalList.getCurrentElement()));
                            holderTwo = holderTwo.getNext();
                        }
                        finalList = finalList.getNext();
                    }
                    genericList = placeHolder;
                } else {
                    Node<T> listHead = genericList.getNext();
                    //for-loop goes up to index
                    for (int i = 0; i < index - 1; i++) {
                        listHead = listHead.getNext();
                    }
                    Node<T> listTail = listHead.getNext(); //adds elements to list
                    //adds elements that should be placed after the list of elements added
                    for (int i = 0; i < list.size(); i++) {
                        listHead.setNext(new Node<T>(list.get(i)));
                        listHead = listHead.getNext();
                    }
                    listHead.setNext(listTail);
                }
            }
            add = true;
        }
        return add;    
    } //add
    
    /**
     * {@inheritDoc}
     */
    public T get(int index) {
        T referenceObject = null;
        //if-statement checking for error
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        Node<T> placeHolder = genericList.getNext();

        for (int i = 0; i < index; i++) {
            placeHolder = placeHolder.getNext();
        }
        referenceObject = placeHolder.getCurrentElement();

        return referenceObject;
    } //get

    /**
     * {@inheritDoc}
     */
    public T set(int index, T obj) {
        T previousObject = null;
        //if-statement checking for error
        if (obj == null) {
            throw new NullPointerException("The Object is null.");
        } else if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        Node<T> placeHolder = retrieveNode(index + 1);

        previousObject = placeHolder.getCurrentElement();
        placeHolder.setCurrentElement(obj);
        
        return previousObject;
    } //set

    /**
     * {@inheritDoc}
     */
    public T remove(int index) {
        T removedObject = null;
        //if-statement checking for error
        if ((index < 0) || (index >= size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        Node<T> listHead = genericList.getNext();
        
        if (index == 0) { 
            removedObject = listHead.getCurrentElement();
            genericList = listHead;
        } else {
            for (int i = 0; i < index - 1; i++) { //sets front to the node before the index node
                listHead = listHead.getNext();
            }
            removedObject = listHead.getNext().getCurrentElement();

            //Node object for after the object at index
            Node<T> listTail = listHead.getNext().getNext();
            
            listHead.setNext(listTail);
        }
        return removedObject;
    } //remove

    /**
     * {@inheritDoc}
     */
    public int indexOf(T obj) {
        int indexOf = -1;
        //if-statement checking for error
        if (obj == null) {
            throw new NullPointerException("The Object is null.");
        }
        for (int i = 0; i < size(); i++) {
            if (obj.equals(get(i))) {
                indexOf = i;
                return indexOf;
            }
        }
        return indexOf;
    } //indexOf

    /**
     * {@inheritDoc}
     */
    public void clear() {
        //empty list
        Node<T> clearList = new Node<T>();

        genericList = clearList;
    } //clear

    /**
     * {@inheritDoc}
     */
    public GenList<T> distinct() {
        GenList<T> newList = new LinkedGenList<T>();
        Node<T> placeHolder = genericList.getNext();

        //for-loop that goes through the list
        for (int i = 0; i < size(); i++) {
            if (newList.indexOf(placeHolder.getCurrentElement()) == -1) {
                newList.add(placeHolder.getCurrentElement());
            }
            placeHolder = placeHolder.getNext();
        }
        return newList;
    } //distinct

    /**
     * {@inheritDoc}
     */
    public GenList<T> reverse() {
        GenList<T> reverseList = new LinkedGenList<T>();

        //for-loop that starts from end of list and goes to start
        for (int i = size() - 1; i >= 0; i--) {
            reverseList.add(get(i));
        }
        return reverseList;
    } //reverse

    /**
     * {@inheritDoc}
     */
    public String makeString(String sep) {
        String makeString = "";

        if (size() != 0) {
            //for-loop that prints out each element in a list with a seperator
            for (int i = 0; i < size() - 1; i++) {
                makeString += get(i);
                makeString += sep;
            }
            makeString = makeString + get(size() - 1);
        }
        return makeString;
    } //makeString

    /**
     * {@inheritDoc}
     */
    public GenList<T> splice(int fromIndex, int toIndex) {
        GenList<T> spliceList = new LinkedGenList<T>();
        //if-statement checking for error
        if ((fromIndex < 0) || (toIndex > size()) || (fromIndex > toIndex)) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        for (int i = fromIndex; i < toIndex; i++) {
            spliceList.add(get(i));
        }
        return spliceList;       
    } //splice

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        Iterator<T> iterator = new LinkedGenListIterator<T>(genericList);

        return iterator;
    } //iterator

    /**
     * {@inheritDoc}
     */
    public T min(Comparator<T> c) {
        //if-statement checking for error
        if (c == null) {
            throw new NullPointerException("The Comparator is null.");
        }
        //sets min to first Object in list - initializing
        T minValue = get(0);

        //for-each statement that goes through each object in list
        for (T i : this) {
            if (c.compare(minValue, i) > 0) {
                minValue = i;
            }
        }
        return minValue;
    } //min

    /**
     * {@inheritDoc}
     */
    public T max(Comparator<T> c) {
        //if-statement checking for error
        if (c == null) {
            throw new NullPointerException("The Comparator is null.");
        }
        //sets max value to first object in list - initializing
        T maxValue = get(0);
        
        //for-each statement that goes through each object in list
        for (T i : this) {
            if (c.compare(maxValue, i) < 0) {
                maxValue = i;
            }
        }
        return maxValue;
    } //max

    /**
     * {@inheritDoc}
     */
    public GenList<T> filter(Predicate<T> p) {
        //if-statement checking for error
        if (p == null) {
            throw new NullPointerException("The Predicate is null.");
        }
        GenList<T> filterList = new LinkedGenList<T>();

        //for-loop that goes through each element in a list
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                filterList.add(get(i));
            }
        }
        return filterList;
    } //filter
    
    /**
     * {@inheritDoc}
     */
    public <R> GenList<R> map(Function<T, R> f) {
        //if-statement checking for error
        if (f == null) {
            throw new NullPointerException("The Specified Function is null.");
        }
        GenList<R> mappedList = new LinkedGenList<R>();

        for (T i : this) {
            if (f.apply(i) == null) {
                throw new NullPointerException("The Result of the function is null.");
            }
            mappedList.add(f.apply(i)); 
        }
        return mappedList;
    } //map
    
    /**
     * {@inheritDoc}
     */
    public T reduce(T start, BinaryOperator<T> f) {
        //if-statement checking for error
        if (f == null) {
            throw new NullPointerException("The Function is null.");
        }
        T reduce = start;

        //if-statement to check if the starting value is null
        if (reduce == null) {
            throw new NullPointerException("The Result of the function is null.");
        }
        //for-each statement going through each element in list
        for (T i : this) {
            reduce = f.apply(reduce, i);

            if (reduce == null) {
                throw new NullPointerException("The Result of the function is null.");
            }
        }
        return reduce;
    } //reduce

    /**
     * {@inheritDoc}
     */
    public boolean allMatch(Predicate<T> p) {
        boolean allMatch = true;
        //if-statement checking for error
        if (p == null) {
            throw new NullPointerException("The Predicate is null.");
        } else {
            //for-each statement going through each element in list
            for (T i : this) {
                //if it does not match the Predicate, then sets variable to false
                if (p.test(i) == false) {
                    allMatch = false;
                }
            }
        }
        return allMatch;
    } //allMatch

    /**
     * {@inheritDoc}
     */
    public boolean anyMatch(Predicate<T> p) {
        boolean oneMatch = false;
        //if-statement checking for error
        if (p == null) {
            throw new NullPointerException("The Predicate is null.");
        } else {
            //for-each statement going through each element in the list
            for (T i : this) {
                //if atleast one element in the list matches the Predicate, variable is set true
                if (p.test(i)) {
                    oneMatch = true;
                }
            }
        }
        return oneMatch;
    } //anyMatch

    /**
     * Returns a the Node at the index provides.                                      
     *    
     * @param index the index of the node       
     * @return the Node at the index    
     */
    private Node<T> retrieveNode(int index) {
        Node<T> placeHolder;

        if (size() != 0) {
            placeHolder = genericList.getNext();

            for (int i = 0; i < index - 1; i++) {
                placeHolder = placeHolder.getNext();
            }
        } else {
            placeHolder = genericList;
        }
        return placeHolder;
    } //getNode
} //LinkedGenList
