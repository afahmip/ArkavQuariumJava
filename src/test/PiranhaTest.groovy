package test

import com.arkavquarium.fish.Guppy
import com.arkavquarium.fish.Hunger
import com.arkavquarium.fish.Piranha
import com.arkavquarium.fish.StatusFish
import com.arkavquarium.item.Coin
import com.arkavquarium.movable.TypeMove
import com.linkedlist.LinkedList
import org.junit.Assert
import org.junit.Test

class PiranhaTest extends GroovyTestCase {

    private int DEFAULT_X = 5
    private int DEFAULT_Y = 5
    private double DEFAULT_SPEED = 5

    @Test
    void testFindNearestFood() {
        Guppy guppy = new Guppy(10, 10, DEFAULT_SPEED)
        LinkedList<Guppy> guppyList = new LinkedList<>()
        guppyList.add(guppy)
        Piranha piranha = new Piranha(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        piranha.findNearestFood(guppyList)
        Assert.assertEquals(piranha.getNearestGuppy(), guppy)
    }

    @Test
    void testEat() {
        Guppy guppy = new Guppy(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        LinkedList<Guppy> guppyList = new LinkedList<>()
        guppyList.add(guppy)
        Piranha piranha = new Piranha(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        piranha.findNearestFood(guppyList)

        piranha.setHungerStatus(Hunger.HUNGRY)
        boolean isEat = piranha.eat()
        Assert.assertEquals(isEat, true)

        boolean isEat2 = piranha.eat()
        Assert.assertEquals(isEat2, false)
    }

    @Test
    void testGetTypeMove() {
        Piranha piranha = new Piranha(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        TypeMove t = piranha.getTypeMove()
        Assert.assertEquals(t, TypeMove.RANDOM)
    }

    @Test
    void testMove() {
        Piranha piranha = new Piranha(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        Guppy guppy = new Guppy(10, 10, DEFAULT_SPEED)
        LinkedList<Guppy> guppyList = new LinkedList<>()
        guppyList.add(guppy)
        piranha.setHungerStatus(Hunger.HUNGRY)

        piranha.findNearestFood(guppyList)

        Assert.assertEquals(piranha.getTypeMove(), TypeMove.NOT_RANDOM)

        for (int idx = 0; idx < 10; idx++) {
            piranha.move()
        }
        Assert.assertEquals(piranha.getX(), guppy.getX())
    }

    @Test
    void testUpdateStatus() {
        Piranha piranha = new Piranha(DEFAULT_X, DEFAULT_Y, DEFAULT_SPEED)
        Guppy guppy = new Guppy(10, 10, DEFAULT_SPEED)
        LinkedList<Guppy> guppyList = new LinkedList<>()
        LinkedList<Coin> coinList = new LinkedList<>()
        guppyList.add(guppy)
        piranha.setHungerStatus(Hunger.HUNGRY)

        piranha.findNearestFood(guppyList)

        Assert.assertEquals(piranha.getTypeMove(), TypeMove.NOT_RANDOM)

        for (int idx = 0; idx < 10; idx++) {
            piranha.updateStatus(coinList, guppyList)
        }

        Assert.assertEquals(guppy.getStatus(), StatusFish.DEAD)
        Assert.assertEquals(coinList.getSize(), 1)
    }
}