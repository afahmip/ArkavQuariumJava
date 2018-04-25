package test

import com.arkavquarium.item.Coin
import org.junit.Assert
import org.junit.Test

class CoinTest extends GroovyTestCase {

    @Test
    void testGetValue() {
        Coin coin = new Coin(10, 10, 10)
        Assert.assertEquals(coin.getValue(), 10)
    }

    @Test
    void testSetValue() {
        Coin coin = new Coin(10, 10, 10)
        coin.setValue(20)
        Assert.assertEquals(coin.getValue(), 20)

    }
}
