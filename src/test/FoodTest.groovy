package test

import com.arkavquarium.AquariumObject
import com.arkavquarium.item.Food
import com.arkavquarium.item.StatusItem
import org.junit.Assert
import org.junit.Test

class FoodTest extends GroovyTestCase {

    @Test
    void testGetHungerPoint() {
        Food food = new Food(10, 10, 10)
        Assert.assertEquals(food.getHungerPoint(), 10)
    }

    @Test
    void testSetHungerPoint() {
        Food food = new Food(10, 10, 10)
        food.setHungerPoint(20)
        Assert.assertEquals(food.getHungerPoint(), 20)
    }

    @Test
    void testUpdateFoodStatus() {
        AquariumObject.setBottom(20)
        AquariumObject.setRight(20)
        AquariumObject.setTop(0)
        AquariumObject.setLeft(0)
        Food food = new Food(10, 10, 10)
        for (int i = 0; i < 10; i++) {
            food.move()
            food.updateFoodStatus()
        }

        Assert.assertEquals(food.getStatus(), StatusItem.DESTROYED)
    }
}
