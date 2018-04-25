package test

import com.arkavquarium.AquariumObject
import com.arkavquarium.utility.Utility
import org.junit.Assert
import org.junit.Test

class UtilityTest extends GroovyTestCase {

    @Test
    void testGetDistanceObj() {
        AquariumObject aqObj1 = new AquariumObject(10, 10, 10)
        AquariumObject aqObj2 = new AquariumObject(5, 10, 10)

        int dist = (int) Utility.getDistanceObj(aqObj1.getPosX(), aqObj1.getPosY(),
                aqObj2.getPosX(), aqObj2.getPosY())

        Assert.assertEquals(dist, 5)
    }

    @Test
    void testGetMovementDirection() {

        AquariumObject aqObj1 = new AquariumObject(10, 10, 10)
        AquariumObject aqObj2 = new AquariumObject(5, 10, 10)

        double atan = Utility.getMovementDirection(aqObj1.getPosX(), aqObj1.getPosY(),
                aqObj2.getPosX(), aqObj2.getPosY())

        Assert.assertEquals(atan, 3, 1)
    }

    @Test
    void testMoveXByRadian() {
        int x1 = 10, y1 = 10, x2 = 5, y2 = 10
        double atan = Utility.getMovementDirection(x1, y1, x2, y2)
        x1 = (int) Utility.moveXByRadian(x1, 1, atan, 1)
        Assert.assertNotEquals(x1, 10)
    }

    @Test
    void testMoveYByRadian() {
        int x1 = 10, y1 = 10, x2 = 5, y2 = 6
        double atan = Utility.getMovementDirection(x1, y1, x2, y2)
        y1 = (int) Utility.moveYByRadian(y1, 1, atan, 1)
        Assert.assertNotEquals(y1, 10)
    }
}
