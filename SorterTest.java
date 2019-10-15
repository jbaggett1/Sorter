

import static org.junit.Assert.*;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Jillian Baggett
 * 
 * **/
public class SorterTest
{   
    protected Sorter<Integer> example;
    protected Sorter<Integer> example2;
    protected Sorter<Integer> example3;
    protected Sorter<Integer> example4;
    /**
     * Default constructor for test class SorterTest
     */
    public SorterTest()
    {
       
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {  example = new Sorter<Integer>();
       example2 = new Sorter<Integer>();
       example3 = new Sorter<Integer>();
       example4 = new Sorter<Integer>();
    }
    @Test 
    public void testMergeSort()
    {
    }
   @Test public void testQuicksort()
   {
        //Testing case where numbers increase
        example2.insertAsList(1);
        example2.insertAsList(2);
        example2.insertAsList(3);
        example2.insertAsList(4);
        example2.insertAsList(5);
        example2.quicksort();
        String testAns2 = example2.getArray().toString();
        String ans2 = "[1, 2, 3, 4, 5]";
	assertEquals(ans2,testAns2);
       
       
       //Testing case where numbers strictly decrease
        example3.insertAsList(10);
        example3.insertAsList(9);
        example3.insertAsList(8);
        example3.insertAsList(7);
        example3.insertAsList(6);
        example3.quicksort();
        String testAns3 = example3.getArray().toString();
        String ans3 = "[6, 7, 8, 9, 10]";
	assertEquals(ans3,testAns3);
       
       
       
       //Testing case where numbers decrease and increase 
        example4.insertAsList(10);
        example4.insertAsList(0);
        example4.insertAsList(5);
        example4.insertAsList(24);
        example4.insertAsList(6);
        example4.quicksort();
        String testAns4 = example4.getArray().toString();
        String ans4 = "[0, 5, 6, 10, 24]";
	assertEquals(ans4,testAns4);
}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    { example = null;
      example2 = null;
      example3 = null;
      example4 = null;
    }
}
