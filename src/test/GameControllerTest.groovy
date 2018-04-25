package test

import com.arkavquarium.AquariumObject
import com.arkavquarium.controller.GameController
import org.junit.Assert
import org.junit.Test

class GameControllerTest extends GroovyTestCase {

    @Test
    void testIsWin() {
        GameController gc = new GameController(1024, 728)
        Assert.assertFalse(gc.isWin())
    }

    @Test
    void testIsLose() {
        GameController gc = new GameController(1024, 728)
        Assert.assertFalse(gc.isLose())
    }

    @Test
    void testBuyGuppy() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)
        GameController gc = new GameController(1024, 728)
        gc.buyGuppy()
        Assert.assertEquals(gc.getListOfGuppy().getSize(), 1)
    }

    @Test
    void testBuyPiranha() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)
        GameController gc = new GameController(1024, 728)
        gc.buyPiranha()
        Assert.assertEquals(gc.getListOfPiranha().getSize(), 1)
    }

    @Test
    void testBuyFood() {
        AquariumObject.setBottom(500)
        AquariumObject.setRight(500)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)
        GameController gc = new GameController(1024, 728)
        gc.buyFood(100, 100)
        Assert.assertEquals(gc.getListOfFood().getSize(), 1)
    }

    @Test
    void testBuyEgg() {
        GameController gc = new GameController(1024, 728)
        gc.buyEgg()
        Assert.assertEquals(gc.getEgg(), 1)
    }
}
