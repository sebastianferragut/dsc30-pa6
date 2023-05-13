import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BSTree testing suite for constructor and methods,
 * as well as testing for BSTree_Iterator
 *
 * @author Sebastian Ferragut, David Tsukamoto
 * @since  {05-10-2023}
 */
public class BSTreeTester {
    
    @Test
    public void bSTreeTest() {
        BSTree cTree = new BSTree();
        assertEquals(0, cTree.getSize());
        assertEquals(null, cTree.getRoot());
        assertEquals(-1, cTree.findHeight());
        assertThrows(NullPointerException.class, () -> {
            cTree.findKey("key");
        });
    }
    @Test
    public void getRootTest() {
        BSTree cTree = new BSTree();
        assertEquals(null, cTree.getRoot());
        cTree.insert(2);
        assertEquals(2, cTree.getRoot().getKey());
        cTree.insert(1);
        assertEquals(2, cTree.getRoot().getKey());
    }

    @Test
    public void getSizeTest() {
        BSTree cTree = new BSTree();
        assertEquals(0, cTree.getSize());
        cTree.insert(5);
        assertEquals(1,cTree.getSize());
        cTree.insert(3);
        assertEquals(2, cTree.getSize());
    }

    @Test
    public void insertTest() {
        BSTree cTree = new BSTree();
        assertThrows(NullPointerException.class, () -> {
            cTree.insert(null);
        });
        cTree.insert(2);
        assertEquals(2, cTree.getRoot().getKey());
        assertFalse(cTree.insert(2));
        assertTrue(cTree.insert(3));
        assertEquals(2, cTree.getSize());
    }

    @Test
    public void findKeyTest() {
        BSTree cTree = new BSTree();
        assertThrows(NullPointerException.class, () -> {
            cTree.findKey(null);
        });
        cTree.insert(4);
        assertFalse(cTree.findKey(1));
        cTree.insert(1);
        assertTrue(cTree.findKey(1));
        cTree.insert(2);
        assertTrue(cTree.findKey(2));
    }

    @Test
    public void insertDataTest() {
        BSTree cTree = new BSTree();
        assertThrows(NullPointerException.class, () -> {
            cTree.insertData(1,null);
        });
        assertThrows(NullPointerException.class, () -> {
            cTree.insertData(null,1);
        });
        assertThrows(NullPointerException.class, () -> {
            cTree.insertData(1,1);
        });
        cTree.insert(1);
        cTree.insertData(1,1);
        LinkedList testList = new LinkedList();
        testList.add(1);
        assertEquals(testList, cTree.findDataList(1));
        cTree.insertData(1,2);
        testList.add(2);
        assertEquals(testList, cTree.findDataList(1));
    }

    @Test
    public void findDataListTest() {
        BSTree cTree = new BSTree();
        assertThrows(NullPointerException.class, () -> {
            cTree.findDataList(null);
        });
        assertThrows(NullPointerException.class, () -> {
            cTree.findDataList(1);
        });
        LinkedList testList = new LinkedList();
        testList.add(1);
        cTree.insert(1);
        cTree.insertData(1,1);
        assertEquals(testList, cTree.findDataList(1));
        testList.add(2);
        cTree.insertData(1,2);
        assertEquals(testList, cTree.findDataList(1));
    }

    @Test
    public void findHeightTest() {
        BSTree cTree = new BSTree();
        assertEquals(-1, cTree.findHeight());
        cTree.insert(1);
        assertEquals(0, cTree.findHeight());
        cTree.insert(5);
        assertEquals(1, cTree.findHeight());
        cTree.insert(3);
        assertEquals(2, cTree.findHeight());
        cTree.insert(2);
        assertEquals(3, cTree.findHeight());
        cTree.insert(6);
        assertEquals(3, cTree.findHeight());
    }

    @Test
    public void bSTree_IteratorTest() {
        BSTree cTree = new BSTree();
        cTree.insert(8);
        cTree.insert(3);
        cTree.insert(1);
        cTree.insert(10);
        cTree.insert(6);
        cTree.insert(14);
        cTree.insert(13);
        cTree.insert(4);
        cTree.insert(7);
        Iterator<String> iter = cTree.iterator();
        assertTrue(iter.hasNext());
        assertEquals("[1, 3, 8]", iter.toString());
    }

    @Test
    public void hasNextTest() {
        BSTree cTree = new BSTree();
        cTree.insert(8);
        cTree.insert(7);
        cTree.insert(6);
        Iterator<String> iter = cTree.iterator();
        assertTrue(iter.hasNext());
        BSTree bTree = new BSTree();
        Iterator<String> iterTwo = bTree.iterator();
        assertFalse(iterTwo.hasNext());
        BSTree aTree = new BSTree();
        Iterator<String> iterThree = aTree.iterator();
        assertFalse(iterThree.hasNext());
        aTree.insert(1);
        Iterator<String>iterFour = aTree.iterator();
        assertTrue(iterFour.hasNext());
    }

    @Test
    public void nextTest() {
        BSTree aTree = new BSTree();
        Iterator<String>iter = aTree.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
        BSTree bTree = new BSTree();
        bTree.insert(3);
        bTree.insert(2);
        bTree.insert(1);

        Iterator<String>iterTwo = bTree.iterator();
        assertEquals(1, iterTwo.next());
        assertEquals(2, iterTwo.next());
        assertEquals(3, iterTwo.next());
        assertThrows(NoSuchElementException.class, () -> {
            iterTwo.next();
        });
    }

    @Test
    public void toStringTest() {
        BSTree cTree = new BSTree();
        cTree.insert(8);
        cTree.insert(7);
        cTree.insert(6);
        Iterator<String> iter = cTree.iterator();
        assertEquals("[6, 7, 8]", iter.toString());
        BSTree bTree = new BSTree();
        bTree.insert(2);
        bTree.insert(4);
        bTree.insert(1);
        Iterator<String> iterTwo = bTree.iterator();
        assertEquals("[1, 2]", iterTwo.toString());
        BSTree aTree = new BSTree();
        aTree.insert(5);
        aTree.insert(3);
        Iterator<String> iterThree = aTree.iterator();
        assertEquals("[3, 5]", iterThree.toString());
        BSTree dTree = new BSTree();
        Iterator<String> iterFour = dTree.iterator();
        assertEquals("[]", iterFour.toString());
    }

}