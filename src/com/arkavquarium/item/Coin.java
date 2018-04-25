package com.arkavquarium.item;

import java.io.Serializable;

/**
 * Represents the coin that is going to be produced by Piranha and Guppy.
 * <p> It inherits the behaviour of Item class. </p>
 *
 * @author Adylan Roaffa Ilmy
 * @version 1.1
 * @see com.arkavquarium.fish.Piranha
 * @see com.arkavquarium.fish.Guppy
 * @see Item
 */
public class Coin extends Item implements Serializable {

  private static int COIN_ID;
  private int value;
  private int id;


  /**
   * Creates a new coin from the given position and value.
   *
   * @param x     horizontal position
   * @param y     vertical position
   * @param value coin value
   */
  public Coin(int x, int y, int value) {
    super(x, y);
    incrementId();
    this.id = Coin.COIN_ID;
    this.value = value;
  }

  /**
   * Increment global Coin ID.
   */
  private static void incrementId() {
    COIN_ID = COIN_ID + 1;
  }

  public int getCoinId() {
    return this.id;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
