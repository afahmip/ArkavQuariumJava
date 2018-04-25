package test

import com.arkavquarium.AquariumObject
import com.arkavquarium.item.Coin
import com.arkavquarium.movable.TypeMove
import com.arkavquarium.pet.Snail
import com.linkedlist.LinkedList
import org.junit.Assert
import org.junit.Test

class SnailTest extends GroovyTestCase {

    private int DEFAULT_X = 100
    private int DEFAULT_Y = 500
    private double DEFAULT_SPEED = 5

    @Test
    void testGetTypeMove() {
        Snail snail = new Snail(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        TypeMove t = snail.getTypeMove()
        Assert.assertEquals(t, TypeMove.STOP)
    }

    @Test
    void testFindNearestCoin() {
        Snail snail = new Snail(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        LinkedList<Coin> listOfCoin = new LinkedList<>()
        Coin coin = new Coin(100, 100, 10)
        listOfCoin.add(coin)

        snail.updateStatus(listOfCoin)
        Assert.assertEquals(snail.getNearestCoin(), coin)
    }

    @Test
    void testUpdateStatus() {

        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)

        Snail snail = new Snail(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        LinkedList<Coin> listOfCoin = new LinkedList<>()

        snail.updateStatus(listOfCoin)
        TypeMove t = snail.getTypeMove()

        Assert.assertEquals(snail.getNearestCoin(), null)
        Assert.assertEquals(t, TypeMove.STOP)

        Coin coin = new Coin(4, 4, 10)
        listOfCoin.add(coin)

        snail.updateStatus(listOfCoin)
        TypeMove t2 = snail.getTypeMove()

        Assert.assertEquals(snail.getNearestCoin(), coin)
        Assert.assertEquals(t2, TypeMove.NOT_RANDOM)
    }

    @Test
    void testMove() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)

        Snail snail = new Snail(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        LinkedList<Coin> listOfCoin = new LinkedList<>()
        Coin coin = new Coin(10, 10, 10)
        listOfCoin.add(coin)

        long start = System.currentTimeMillis()
        long passed = System.currentTimeMillis()

        while (passed - start <= 10 * 1000) {
            snail.updateStatus(listOfCoin)
            passed = System.currentTimeMillis()
        }

        Assert.assertEquals(snail.getPosX(), coin.getPosX())
    }
}
