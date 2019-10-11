import java.util.*;
import java.util.ArrayList;
/**
 * Jillian Baggett
 * 10/10/2019
 */
public class Sorter <E extends Comparable<? super E>> 
{
    public int size;
    protected int largestSize; 
    protected ArrayList<Node> heap;
    public static void main(String[] args)
    {   Sorter<Integer> example = new Sorter<Integer>();
        example.insert(10);
        example.insert(0);
        example.insert(5);
        example.insert(24);
        example.insert(6);
        example.quicksort();
        for (int i = 0; i < example.heap.size(); i++)
        {
         System.out.println("\n" + example.heap.get(i).element.toString());
        }
    }   
    
    /**
     * Constructor for objects of class Sorter
     */
    public Sorter()
    {
        this.largestSize = largestSize;
        this.size = 0;
        this.heap = new ArrayList<Node>();

    }
        public void insert(E element)
    { //Increase the size of the heap
      this.size++;
      //Create new node with the given element 
      Node toInsert = new Node(element);
      //Insert that element to the tail end of the filled elements
      heap.add(toInsert);
      int currentIndex = size-1;
      //Check to make sure the heap is still a valid max heap 
      if (this.size > 1)
      {
             while (heap.get(currentIndex).element.compareTo(heap.get(getParent(currentIndex)).element) > 0)
      {   switchPosition(currentIndex, getParent(currentIndex));
          currentIndex = getParent(currentIndex);
        }
        }
 
      
    }
        //Takes in a node's index and uses relations from homework to find the parent index
    public int getParent(int currIndex)
    {
        return (int)currIndex/2; 
    }
    //Following three classes are necessary for the heapsort method)
    public void switchPosition(int currIndex, int newPosIndex)
    {
        //Save current nodes information into a temporary node
        Node tempNode = new Node(heap.get(currIndex).element);
        //Switch the element at current index with that of the parent
        heap.get(currIndex).element = heap.get(newPosIndex).element;
        //insert the element into the new position from the temp element
        heap.get(newPosIndex).element = tempNode.element;
    }

    public void heapify(ArrayList<Node> array, int size, int rootInd)
    { Node currRoot = heap.get(rootInd);
      int leftInd = rootInd * 2 + 1;
      int rightInd = rootInd * 2 + 2;
      int largestInd = rootInd;
      
      if (leftInd < size && (array.get(leftInd).element.compareTo(array.get(rootInd).element) > 0))
      {     largestInd = leftInd;
        }
      
              if (rightInd < size && (array.get(rightInd).element.compareTo(array.get(rootInd).element) > 0))
      {     largestInd = rightInd;
        }
        
       if (largestInd != rootInd)
       { switchPosition(largestInd,rootInd);
          heapify(array, size, largestInd);
        }
    }
    
    
    public void heapsort()
    {
        int size = heap.size();
        
        //Creating the heap - must call heapify on all nodes not leaves, aka less than n/2 - 1
        for (int i = size/2 - 1; i >= 0; i--)
        { heapify(heap, size, i);
        }
        
        //Extracting the largest element and placing it at end of array
        for (int i = size-1; i>=0; i--)
        {
            Node temp = new Node(heap.get(0).element);
            heap.get(0).element = heap.get(i).element;
            heap.get(i).element = temp.element;
            
            heapify(heap, i, 0);
        }
    }
    
    //Following code pertains to mergesort 
    public void mergesort()
    {   sort(this.heap, 0, heap.size());
    }
    
    public void sort(ArrayList<Node> array, int left, int n)
    {
        //Verify that left index is still less than length n 
        if (left < n)
        {
        int newSplit = (left + n)/2;
        
        sort(array, left, newSplit);
        sort(array, newSplit + 1, n);
        
        merge(array, left, newSplit, n);
    }
    }
    
    public void merge(ArrayList<Node> array, int left, int split, int n)
    {   ArrayList<Node> leftList = new ArrayList<Node>();
        ArrayList<Node> rightList = new ArrayList<Node>();
        
        //Left list goes from left to split, right list goes from split to n
        //Copy data into rightList and leftList for readability & ease of comparison
        for (int i = 0; i < (split - left + 1); i++)
        {  Node tempNode = new Node(array.get(left + i).element);
            leftList.add(tempNode);
            tempNode = null; 
        }
        
        for (int i = 0; i < (n - split); i++)
        {  Node tempNode = new Node(array.get(split + i).element);
            rightList.add(tempNode);
            tempNode = null; 
        }
        
        //To merge the two arrays
        int pointerLeft = 0;
        int pointerRight = 0;
        
        int arrInd = left;
        
        //Compare element by element and use the 'two pointer method' discussed in class
        while(pointerLeft < (split - left + 1) && pointerRight < (n-split))
        {
            if (leftList.get(pointerLeft).element.compareTo(rightList.get(pointerRight).element) <= 0 )
            {   array.get(arrInd).element = leftList.get(pointerLeft).element;
                pointerLeft++;
            }
            else
            { array.get(arrInd).element = rightList.get(pointerRight).element;
                pointerRight++;
            }
            arrInd++;
        }
        
        //Once exited while, it means one list has been completely merged in and the tail of the 
        //unmerged one needs to be tacked on the end 
        
        while (pointerLeft < (split - left))
        {
            array.get(arrInd).element = leftList.get(pointerLeft).element;
            pointerLeft++;
            arrInd++;
        }
        
        while (pointerRight < (n - split -1))
        {
            array.get(arrInd).element = rightList.get(pointerRight).element;
            pointerRight++;
            arrInd++;
        }
    }
    //Following methods are used to implement the quicksort
    //Because multiple different pivots can be used, this one implements picking the first value in 
    //the list as a pivot to sort around 
    public void quicksort()
    {  quicksort(this.heap, 0, heap.size()-1);
    }
    public void quicksort(ArrayList<Node> array, int low, int high)
    { 
      //Define value of node that is being pivoted around
      Node pivot = new Node(array.get(low).element);
      //Start value for pivot index
      int pivotIndex = low;
      //Define index in list where values start being higher than the pivot 
      int higherThan = -1;
      //Begin the for loop after the first element
      for (int i = low + 1; i <= high; i++)
      
      {   int gap = Math.abs(pivotIndex - i);
          if (array.get(i).element.compareTo(pivot.element) <= 0 && gap == 1)
          {  switchPosition(i,pivotIndex);
             pivotIndex = i;
            }
          else if (array.get(i).element.compareTo(pivot.element) <= 0 && gap > 1)
          { //higherThan = i;
            switchPosition(i, pivotIndex);
            switchPosition(i, pivotIndex + 1);  
            //pivotIndex = higherThan;
            //higherThan++;
            }
          else // (array.get(i).element.compareTo(pivot.element) > 0 )
          {  //Do nothing, element should stay in its position greater than the pivot
            }
           
        }
       if ((pivotIndex - 1) >= 1)
        {quicksort(array, low, pivotIndex - 1);}
       if ((high - pivotIndex) >= 1)
       {
        quicksort(array, pivotIndex + 1, high-1); 
    }
    }
    protected class Node {
        // since this is a private inner class, and the outer AVLTree class
        // will need to freely modify the connections and update the height
        // of its nodes, the following three variables are not private.
        Node left;
        Node right;
        int height;
        E element;

        /**
         * Construct an AVLTreeNode. At instantiation, each node has no
         * children and therefore a height of 0.
         * @param element the element that this node contains
         */
        public Node(E element) {
            this.left = null;
            this.right = null;
            this.height = 0;
            this.element = element;
        }
    }
}
