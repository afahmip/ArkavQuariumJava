package test

import com.linkedlist.LinkedList
import org.junit.Assert
import org.junit.Test

class LinkedListTest extends GroovyTestCase {

    @Test
    void testGetSize() {
        LinkedList<Integer> listOfInt = new LinkedList<>()
        listOfInt.add(5)
        listOfInt.add(10)

        Assert.assertEquals(listOfInt.getSize(), 2)
    }

    @Test
    void testFind() {
        LinkedList<Integer> listOfInt = new LinkedList<>()
        listOfInt.add(5)
        listOfInt.add(10)

        Assert.assertEquals(listOfInt.find(5), 0)
    }

    @Test
    void testIsEmpty() {
        LinkedList<Integer> listOfInt = new LinkedList<>()
        Assert.assertTrue(listOfInt.isEmpty())
    }

    @Test
    void testRemove() {
        LinkedList<Integer> listOfInt = new LinkedList<>()
        listOfInt.add(5)
        listOfInt.add(10)
        listOfInt.add(15)

        listOfInt.remove(15)

        Assert.assertEquals(listOfInt.getSize(), 2)
    }

    @Test
    void testGet() {
        LinkedList<Integer> listOfInt = new LinkedList<>()
        listOfInt.add(5)
        listOfInt.add(10)
        Assert.assertEquals(listOfInt.get(0), 5)
    }
}
