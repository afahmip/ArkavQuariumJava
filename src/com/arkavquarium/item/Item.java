package com.arkavquarium.item;

import com.arkavquarium.AquariumObject;

/**
 * Represents item that exist in the aquarium.
 *
 * @author Joseph Salimin
 * @version 1.1
 */
public class Item extends AquariumObject {

  private static final double DEFAULT_ITEM_SPEED = 1;

  private StatusItem status;

  /**
   * Creates a new item with given position.
   *
   * @param x horizontal position
   * @param y vertical position
   */
  public Item(int x, int y) {
    super(x, y, Item.DEFAULT_ITEM_SPEED);
    this.status = StatusItem.NOT_DESTROYED;
  }

  /**
   * Returns the default speed of the item.
   *
   * @return default item speed
   */
  public static double getDefaultItemSpeed() {
    return DEFAULT_ITEM_SPEED;
  }

  /**
   * Returns the current status of this item.
   *
   * @return status
   */
  public StatusItem getStatus() {
    return status;
  }

  /**
   * Changes the current status to the given status.
   */
  public void setStatus(StatusItem status) {
    this.status = status;
  }

  /**
   * Returns true if the position is at the bottom of the aquarium, otherwise returns False.
   *
   * @return true if at the bottom, otherwise false
   */
  public boolean isBottom() {
    return this.getPosY() == AquariumObject.getBottom();
  }

  /**
   * Moves its position towards the bottom of the aquarium.
   */
  public void move() {
    if (!isBottom()) {
      this.setPosY(this.getPosY() + (int) this.getSpeed());
    }
  }


}
