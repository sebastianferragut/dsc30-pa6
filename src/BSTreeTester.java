import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTester {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void bSTreeTest() {

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
    }

    @Test
    public void nextTest() {
    }


}