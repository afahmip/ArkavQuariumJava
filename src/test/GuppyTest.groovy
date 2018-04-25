package test

import com.arkavquarium.AquariumObject
import com.arkavquarium.fish.Guppy
import com.arkavquarium.fish.Hunger
import com.arkavquarium.item.Coin
import com.arkavquarium.item.Food
import com.arkavquarium.movable.TypeMove
import com.linkedlist.LinkedList
import org.junit.Assert
import org.junit.Test

class GuppyTest extends GroovyTestCase {

    private int DEFAULT_X = 5
    private int DEFAULT_Y = 5
    private int DEFAULT_SPEED = 5

    @Test
    void testGetTypeMove() {
        Guppy guppy = new Guppy(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        TypeMove t = guppy.getTypeMove()

        Assert.assertEquals(t, TypeMove.RANDOM)
    }

    @Test
    void testMove() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)

        Guppy guppy = new Guppy(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        int xPosition = guppy.getPosX()

        long start = System.currentTimeMillis()
        long passed = System.currentTimeMillis()

        while (passed - start <= 10 * 1000) {
            guppy.move()
            passed = System.currentTimeMillis()
        }

        Assert.assertNotEquals(xPosition, guppy.getPosX())
    }

    @Test
    void testUpdateStatus() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)

        Guppy guppy = new Guppy(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        LinkedList<Food> listOfFood = new LinkedList<>()
        LinkedList<Coin> listOfCoin = new LinkedList<>()
        Food f = new Food(DEFAULT_X * 10, DEFAULT_Y * 10, 5)

        listOfFood.add(f)

        int experience = guppy.getExperience()

        long start = System.currentTimeMillis()
        long passed = System.currentTimeMillis()


        while (passed - start <= 10 * 1000) {
            guppy.updateStatus(listOfCoin, listOfFood)
            passed = System.currentTimeMillis()
        }

        Assert.assertEquals(Hunger.FULL, guppy.getHungerStatus())
        Assert.assertEquals(experience + Guppy.getGuppyExp(), guppy.getExperience())
        Assert.assertEquals(guppy.getLevel() * Guppy.getGuppyCoin(), listOfCoin.get(0).getValue())
    }
}
