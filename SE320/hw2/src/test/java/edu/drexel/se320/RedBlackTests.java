package edu.drexel.se320;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.rules.ExpectedException;

import com.pholser.junit.quickcheck.generator.GenerationStatus.Key;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anyOf;
import org.junit.Rule;
import org.junit.Test;

public class RedBlackTests {
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    //IsEmpty
    
    @Test
    public void testTreeIsEmpty() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();

        assertEquals(tree.isEmpty(), true);
    }
    
    @Test
    public void testTreeIsNotEmpty() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1, "Hello");
        
        assertEquals(tree.isEmpty(), false);
    }

    
    //Put and Get Tests
    
    @Test
    public void testPutNullKey() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("first argument to put() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();

        tree.put(null, "Hello");
    }
    
    @Test
    public void testPutNullValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1, null);
        tree.put(2, "test");
        tree.put(2, null);
        assertEquals(tree.get(1),null);
        assertEquals(tree.get(2),null);
    }
    
    @Test
    public void testGetNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument to get() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        tree.get(null);
    }
    
    @Test
    public void testGetFromEmptyTree() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        assertEquals(tree.get(1), null);
    }

    @Test
    public void testPutAndGetOne() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1, "Hello");

        assertEquals(tree.get(1), "Hello");
    }
    
    @Test
    public void testPutAndGetMultiple() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(2, "Two");
        tree.put(1, "One");
        tree.put(4, "Four");
        tree.put(7, "Seven");
        
        assertEquals(tree.get(2),"Two");
        assertEquals(tree.get(1),"One");
        assertEquals(tree.get(4),"Four");
        assertEquals(tree.get(7),"Seven");
    }
    
   @Test
   public void testPutAndGetDuplicateKey() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(10, "First");
       tree.put(10, "Second");
       tree.put(10, "Third");
       
       assertEquals(tree.get(10),"Third");
   }
   
   @Test
   public void testPutAndGet0Key() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(11, "Eleven");
       tree.put(12, "Twelve");
       tree.put(0, "Zero");
       
       assertEquals(tree.get(0),"Zero");
       assertEquals(tree.get(11),"Eleven");
       assertEquals(tree.get(12),"Twelve");
   }
   
   @Test
   public void testPutAndGetNegativeKeys() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(20, "Twenty");
       tree.put(40, "Fourty");
       tree.put(0, "Zero");
       tree.put(-10, "NTen");
       tree.put(-20, "NTwenty");
       
       assertEquals(tree.get(20),"Twenty");
       assertEquals(tree.get(40),"Fourty");
       assertEquals(tree.get(0),"Zero");
       assertEquals(tree.get(-10),"NTen");
       assertEquals(tree.get(-20),"NTwenty");
   }
   
   @Test
   public void testPutAndGetMaxValueKeys() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(Integer.MAX_VALUE, "Max");
       tree.put(Integer.MIN_VALUE, "Min");
       
       assertEquals(tree.get(Integer.MAX_VALUE),"Max");
       assertEquals(tree.get(Integer.MIN_VALUE),"Min");
   }
   
   //Contains Tests
   
   @Test
   public void testContainsValue() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(1, "One");
       
       assertEquals(tree.contains(1), true);
   }
   
   @Test
   public void testNotContainsValue() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(20, "Twenty");
       
       assertEquals(tree.contains(0), false);
   }
   
   @Test
   public void testContainsNull() {
       thrown.expect(IllegalArgumentException.class);
       thrown.expectMessage("argument to get() is null");
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(20, "Twenty");
       
       tree.contains(null);
   }
   
   //Delete Tests, basically make a tree, delete one at time, and make sure the tree is empty
   
   @Test
   public void testDeleteMin() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(1, "One");
       tree.put(2, "Two");
       tree.put(3, "Three");
       tree.put(4, "Four");
       tree.put(5, "Five");
       tree.put(6, "Six");
       tree.put(7, "Seven");
       tree.put(8, "Eight");
       tree.put(-2, "NTwo");
       tree.put(-1, "NOne");
       tree.put(0, "Zero");
       
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       tree.deleteMin();
       
       assertEquals(tree.size(), 0);
   }
     
   @Test
   public void testDeleteMax() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(-5, "NFive");
       tree.put(-10,"NTen");
       tree.put(5, "Five");
       tree.put(10, "Ten");
       tree.put(0, "Zero");
       
       tree.deleteMax();
       tree.deleteMax();
       tree.deleteMax();
       tree.deleteMax();
       tree.deleteMax();
       
       assertEquals(tree.size(),0);
   }
   
   @Test
   public void testDeleteKey() {
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(7, "Seven");
       tree.put(3, "Three");
       tree.put(10, "Ten");
       tree.put(-1, "NOne");
       tree.put(-10, "NTen");
       tree.put(2, "Two");
       tree.put(-15,"NFifteen");
       tree.put(80, "Eighty");
       tree.put(100, "OneHundred");
       tree.put(50, "Fifty");
       
       tree.delete(7);
       tree.delete(3);
       tree.delete(10);
       tree.delete(50);
       tree.delete(2);
       tree.delete(-10);
       tree.delete(-1);
       tree.delete(80);
       tree.delete(100);
       tree.delete(-15);
       
       assertEquals(tree.size(),0);
   }
   
   @Test
   public void testDeleteNullKey() {
       thrown.expect(IllegalArgumentException.class);
       thrown.expectMessage("argument to delete() is null");
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       tree.put(1, "One");
       
       tree.delete(null);
   }
   
   @Test
   public void testDeleteMinFromEmptyTree() {
	   thrown.expect(NoSuchElementException.class);
       thrown.expectMessage("BST underflow");
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       
       tree.deleteMin();
   }
   
   @Test
   public void testDeleteMaxFromEmptyTree() {
	   thrown.expect(NoSuchElementException.class);
       thrown.expectMessage("BST underflow");
       RedBlackBST<Integer,String> tree = new RedBlackBST<>();
       
       tree.deleteMax();
   }
   
   //Tree Min
   
    @Test
    public void testMinOfEmptyTree() {
 	    thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("called min() with empty symbol table");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        tree.min();        
    }
    
    @Test
    public void testMinSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1, "One");
        
        assertEquals(tree.min(),(Integer) 1);
    }
    
    @Test
    public void testMinMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(10, "Ten");
        tree.put(0, "Zero");
        tree.put(20,"Twenty");
        tree.put(30, "Thirty");
        
        assertEquals(tree.min(),(Integer) 0);
    }
    
    @Test
    public void testMinWithMinValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
    	tree.put(Integer.MIN_VALUE, "Min");
    	
    	assertEquals(tree.min(), (Integer) Integer.MIN_VALUE);
    	
    	
    }
    
    //Max
    
    @Test
    public void testMaxOfEmptyTree() {
 	    thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("called max() with empty symbol table");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        tree.max();    
    }
    
    @Test
    public void testMaxSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(10, "Ten");
        
        assertEquals(tree.max(), (Integer) 10);  
    }
    
    @Test
    public void testMaxMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(-30, "NThirty");
        tree.put(-40, "NFourty");
        tree.put(-50, "NFifty");
        tree.put(100,"OneHundred");
        
        assertEquals(tree.max(), (Integer) 100);  
    }
    
    @Test
    public void testMaxWithMaxValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(Integer.MIN_VALUE, "Min");
        tree.put(Integer.MAX_VALUE, "Max");
        
        assertEquals(tree.max(), (Integer) Integer.MAX_VALUE);  
    }
    
    //Floor

    @Test
    public void testFloorOfEmptyTree() {
 	    thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("called floor() with empty symbol table");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        tree.floor(0);    
    }
    
    @Test
    public void testFloorOfNullKey() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument to floor() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        tree.floor(null); 
    }
    
    @Test
    public void testFloorOfSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        assertEquals(tree.floor(1),(Integer) 1); 
    }
    
    @Test
    public void testFloorOfMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(10,"Ten");
        tree.put(20, "Twenty");
        
        assertEquals(tree.floor(18),(Integer) 10); 
    }
    
    @Test
    public void testNoValueLessThanKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(0, "Zero");
        
        assertEquals(tree.floor(-2), null); 
    }
    
    @Test
    public void testFloorOfMaxKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "two");
        tree.put(3, "Three");
        
        assertEquals(tree.floor(Integer.MAX_VALUE),(Integer) 3); 
    }
    
    @Test
    public void testFloorOfZero() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(-1, "NOne");
        tree.put(-2, "NTwo");
        tree.put(-3, "NThree");
        
        assertEquals(tree.floor(0),(Integer) (-1)); 
    }
    
    @Test
    public void testFloorOfNegativeValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(-10,"NTen");
        tree.put(-20, "NTwenty");
        
        assertEquals(tree.floor(-15),(Integer) (-20)); 
    }
    
    //Ceiling

    @Test
    public void testCeilingOfEmptyTree() {
 	    thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("called ceiling() with empty symbol table");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        tree.ceiling(0);    
    }
    
    @Test
    public void testCeilingOfNullKey() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument to ceiling() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        tree.ceiling(null); 
    }
    
    @Test
    public void testCeilingOfSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        assertEquals(tree.ceiling(1),(Integer) 1); 
    }
    
    @Test
    public void testCeilingOfMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(10,"Ten");
        tree.put(20, "Twenty");
        
        assertEquals(tree.ceiling(18),(Integer) 20); 
    }
    
    @Test
    public void testNoValueMoreThanKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(0, "Zero");
        
        assertEquals(tree.ceiling(2), null); 
    }
    
    @Test
    public void testCeilingOfMinKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "two");
        tree.put(3, "Three");
        
        assertEquals(tree.ceiling(Integer.MIN_VALUE),(Integer) 1); 
    }
    
    @Test
    public void testCeilingOfZero() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(-1, "NOne");
        tree.put(-2, "NTwo");
        tree.put(-3, "NThree");
        
        assertEquals(tree.ceiling(-5),(Integer) (-3)); 
    }
    
    @Test
    public void testCeilingOfNegativeValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(-10,"NTen");
        tree.put(-20, "NTwenty");
        
        assertEquals(tree.ceiling(-5),(Integer) (1)); 
    }

    //Rank
    
    @Test
    public void testRankOfEmptyTree() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        
        assertEquals(tree.rank(0), 0);    
    }
    
    @Test
    public void testRankOfNullKey() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("argument to rank() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        tree.rank(null); 
    }
    
    @Test
    public void testRankOfSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        assertEquals(tree.rank(1), 0); 
        assertEquals(tree.rank(2), 1); 
    }
    
    @Test
    public void testRankOfMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(10,"Ten");
        tree.put(20, "Twenty");
        
        assertEquals(tree.rank(22), 4);
        assertEquals(tree.rank(15), 3);
        assertEquals(tree.rank(8), 2);
        assertEquals(tree.rank(2), 1);
    }
    
    @Test
    public void testRankNoValueLessThanKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(0, "Zero");
        
        assertEquals(tree.rank(-2), 0); 
    }
    
    @Test
    public void testRankOfMinAndMaxKey() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "two");
        tree.put(3, "Three");
        
        assertEquals(tree.rank(Integer.MIN_VALUE), 0); 
        assertEquals(tree.rank(Integer.MAX_VALUE), 3); 
    }
    
    @Test
    public void testRankOfZero() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(-1, "NOne");
        tree.put(-2, "NTwo");
        tree.put(-3, "NThree");
        
        assertEquals(tree.rank(0),3); 
    }
    
    @Test
    public void testRankOfNegativeValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(-10,"NTen");
        tree.put(-20, "NTwenty");
        
        assertEquals(tree.rank(-5), 2); 
    }
    
    //Keys
    
    @Test
    public void testKeysOfEmptyTree() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        

        assertEquals(tree.keys(0,0), new LinkedList<Key>());    
    }
    
    @Test
    public void testKeysOfNullLo() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("first argument to keys() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(3, "Three");
        
        tree.keys(null, 3); 
    }
    
    @Test
    public void testKeysOfNullHi() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("second argument to keys() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(3, "Three");
        
        tree.keys(1, null); 
    }
    
    @Test
    public void testKeysOfSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        for(Integer key : tree.keys()) {
        	assertEquals(key, (Integer) 1);
        }
    }
    
    @Test
    public void testKeysOfSingleValueBadHiLo() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");

        int i = 0;
        for(Integer key : tree.keys(-1,0)) {
        	i++;
        }
        
    	assertEquals(i, 0);
    	
        for(Integer key : tree.keys(3,5)) {
        	i++;
        }
        
        assertEquals(i,0);
    }
    
    @Test
    public void testKeysOfMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(0,"Zero");
        tree.put(1, "One");
        tree.put(2,"Two");
        tree.put(3, "Three");
        
        Integer i = 0;
        for(Integer key : tree.keys()) {
        	assertEquals(key, i);
        	i++;
        }
    }
    
    @Test
    public void testKeysOfMultipleBadHiLo() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(0,"Zero");
        tree.put(1, "One");
        tree.put(2,"Two");
        tree.put(3, "Three");
        
        int i = 0;
        for(Integer key : tree.keys(-2,-1)) {
        	i++;
        }
        
    	assertEquals(i, 0);
    	
        for(Integer key : tree.keys(5,6)) {
        	i++;
        }
        
        assertEquals(i,0);
    }
    
    //Size
    
    @Test
    public void testSizeOfEmptyTree() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        

        assertEquals(tree.size(0,0), 0);    
    }
    
    @Test
    public void testSizeOfNullLo() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("first argument to size() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(3, "Three");
        
        tree.size(null, 3); 
    }
    
    @Test
    public void testSizeOfNullHi() {
 	    thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("second argument to size() is null");
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        tree.put(2, "Two");
        tree.put(3, "Three");
        
        tree.size(1, null); 
    }
    
    @Test
    public void testSizeOfSingleValue() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");
        
        assertEquals(tree.size(), 1);
        assertEquals(tree.size(1,1), 1);
        assertEquals(tree.size(0,2), 1);
        
    }
    
    @Test
    public void testSizeOfSingleValueBadHiLo() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(1,"One");

        assertEquals(tree.size(-1,0),0);
    }
    
    @Test
    public void testSizeOfMultipleValues() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(0,"Zero");
        tree.put(1, "One");
        tree.put(2,"Two");
        tree.put(3, "Three");
        
        assertEquals(tree.size(), 4);
        assertEquals(tree.size(0,2), 3);
        assertEquals(tree.size(2,5), 2);
    }
    
    @Test
    public void testSizeOfMultipleBadHiLo() {
        RedBlackBST<Integer,String> tree = new RedBlackBST<>();
        tree.put(0,"Zero");
        tree.put(1, "One");
        tree.put(2,"Two");
        tree.put(3, "Three");
        
        assertEquals(tree.size(-5,-1), 0);
        assertEquals(tree.size(20,10), 0);
    }
    
}
 
