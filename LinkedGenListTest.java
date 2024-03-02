package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.Comparator;
import java.util.function.*;

/**
 * A LinkedGenListTest class that stores Demo Methods and a main method.
 */
public class LinkedGenListTest {
    
    /**
     * Does a demo to test the Map method.
     *
     * @param integer A integer GenList
     * @param string A string GenList
     */
    public static void demoMap(GenList<Integer> integer, GenList<String> string) {
        //Adds 11.1 to the Integer
        Function<Integer, Double> integerMap = (Integer i) -> i + 11.1;

        System.out.println("demoMap for Integer: (add 11.1 and changed type)");
        System.out.println("Original: " + integer.makeString(", "));
        System.out.println("Mapped: " + integer.map(integerMap).makeString(", "));
        System.out.println();
        
        //added the letter 'z' to the end of each String
        Function<String, String> stringMap = (String st) -> st + "z";

        System.out.println("demoMap for String: (add 'z' to end of each String)");
        System.out.println("Original: " + string.makeString(", "));
        System.out.println("Mapped: " + string.map(stringMap).makeString(", "));
        System.out.println();
    } //demoMap
    
    /**
     * Does a demo to test the Reduce method.
     *
     * @param integer A integer GenList
     * @param string A string GenList
     */
    public static void demoReduce(GenList<Integer> integer, GenList<String> string) {
        //adds two parameters together
        BinaryOperator<Integer> integerReduce = (Integer one, Integer two) -> one + two;

        System.out.println("demoReduce for Integer: (adds all Integers in list together)");
        System.out.println("Original: " + integer.makeString(", "));
        System.out.println("Reduced: " + integer.reduce(0, integerReduce));
        System.out.println();

        //concatenates String parameters
        BinaryOperator<String> stringReduce = (String a, String b) -> a.concat(b);

        System.out.println("demoReduce for String: (Concatenates all Strings in list)");
        System.out.println("Original: " + string.makeString(", "));
        System.out.println("Reduction: " + string.reduce("", stringReduce));
        System.out.println();
    } //demoReduce

    /**
     * Does a demo to test the Filter method.
     *    
     * @param integer A integer GenList
     * @param string A string GenList
     */
    public static void demoFilter(GenList<Integer> integer, GenList<String> string) {
        //True if integer parameter is Even
        Predicate<Integer> integerFilter = (Integer i) -> i % 2 == 0;

        System.out.println("demoFilter for Integer: (Filters out Even Integers from list)");
        System.out.println("Original: " + integer.makeString(", "));
        System.out.println("Filtered: " + integer.filter(integerFilter).makeString(", "));
        System.out.println();
        
        //True if the String has a length of 3 or more
        Predicate<String> stringFilter = (String st) -> st.length() >= 3;

        System.out.println("demoFilter for String: (Contains String elements with a length >= 3)");
        System.out.println("Original: " + string.makeString(", "));
        System.out.println("Filtered: " + string.filter(stringFilter).makeString(", "));
        System.out.println();
    } //demoFilter
    
    /**
     * Does a demo to test the Min method.
     *
     * @param integer A integer GenList
     * @param string A string GenList
     */
    public static void demoMin(GenList<Integer> integer, GenList<String> string) {
        //Compares 2 Integers Parameters based on their values
        Comparator<Integer> integerMin = (Integer one, Integer two) -> {
            if (one > two) {
                return 1;
            } else if (one == two) {
                return 0;
            } else {
                return -1;
            }
        };
        
        System.out.println("demoMin for Integer: (Returns minimum value)");
        System.out.println("Original: " + integer.makeString(", "));
        System.out.println("Min: " + integer.min(integerMin));
        System.out.println();

        //Compares 2 Strings Parameters based on their lengths
        Comparator<String> stringMin = (String a, String b) -> {
            if (a.length() > b.length()) {
                return 1;
            } else if (a.length() == b.length()) {
                return 0;
            } else {
                return -1;
            }
        };
        
        System.out.println("demoMin for String: (Returns minimum lengthed String)");
        System.out.println("Original: " + string.makeString(", "));
        System.out.println("Min: " + string.min(stringMin));
        System.out.println();
    } //demoMin

    /**
     * Does a demo to test the allMatch method.
     *    
     * @param integer A integer GenList
     * @param string A string GenList
     */
    public static void demoAllMatch(GenList<Integer> integer, GenList<String> string) {
        //True if Integer is below a value of 10
        Predicate<Integer> integerMatch = (Integer i) -> i < 10;
        
        System.out.println("demoAllMatch for Integer: (Returns true if all values are < 10)");
        System.out.println("Original: " + integer.makeString(", "));
        System.out.println("AllMatch: " + integer.allMatch(integerMatch));
        System.out.println();

        //True if all String are less than 10 in length
        Predicate<String> stringMatch = (String st) -> st.length() < 10;

        System.out.println("demoAllMatch for String: (Returns true if all Strings are < 10 length");
        System.out.println("Original: " + string.makeString(", "));
        System.out.println("AllMatch: " + string.allMatch(stringMatch));
        System.out.println();
    } //demoAllMatch

    /**
     * Main Method to test demo Methods.
     *
     * @param args Arguments passed through a String Array
     */
    public static void main(String[] args) {
        //creating LinkedGenList<T> objects with different types
        GenList<Integer> integerList = new LinkedGenList<Integer>();
        GenList<String> stringList = new LinkedGenList<String>();

        //adding values to integerList
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        
        //adding values to stringList
        stringList.add("Dev");
        stringList.add("is");
        stringList.add("a");
        stringList.add("UGA");
        stringList.add("Student");

        //testing demo methods
        LinkedGenListTest.demoMap(integerList, stringList);
        LinkedGenListTest.demoReduce(integerList, stringList);
        LinkedGenListTest.demoFilter(integerList, stringList);
        LinkedGenListTest.demoMin(integerList, stringList);
        LinkedGenListTest.demoAllMatch(integerList, stringList);
    } //main   
} // LinkedGenListTest
