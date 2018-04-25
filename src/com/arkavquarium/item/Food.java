package com.arkavquarium.item;

/**
 * Represents food that is given to the Guppy.
 * <p>Food is destroyed when it touches the bottom of the aquarium</p>
 * <p>It inherits the Item class</p>
 *
 * @author Adylan Roaffa
 * @version 1.1
 * @see com.arkavquarium.fish.Guppy
 * @see Item
 */
public class Food extends Item {

  private static final int DEFAULT_HUNGER_POINT = 1;
  private static int FOOD_ID;
  private int id;
  private int hungerPoint;


  /**
   * Creates a new food with the given position and hunger point.
   *
   * @param x           horizontal position
   * @param y           vertical position
   * @param hungerPoint hunger point
   */
  public Food(int x, int y, int hungerPoint) {
    super(x, y);
    this.hungerPoint = hungerPoint;
    incrementId();
    this.id = Food.FOOD_ID;
  }

  /**
   * Static method that returns the hunger point of a default food
   *
   * @return default hunger point
   */
  public static int getDefaultHungerPoint() {
    return DEFAULT_HUNGER_POINT;
  }

  /**
   * Increment global Food ID
   */
  private static void incrementId() {
    FOOD_ID = FOOD_ID + 1;
  }

  /**
   * Returns this item's ID.
   *
   * @return ID
   */
  public int getFoodId() {
    return this.id;
  }

  /**
   * Returns this item's current hunger point.
   *
   * @return hunger point
   */
  public int getHungerPoint() {
    return hungerPoint;
  }

  /**
   * Changes this current hunger point to the given hunger point.
   *
   * @param hungerPoint fish hunger point
   */
  public void setHungerPoint(int hungerPoint) {
    this.hungerPoint = hungerPoint;
  }

  /**
   * Do changes to the status of the food.
   * <p>
   * Changes include:
   * <ol>
   * <li>Changes the state to DESTROYED if it touches the bottom of the aquarium</li>
   * <li>Moves to the bottom of the aquarium</li>
   * </ol>
   * </p>
   */
  public void updateFoodStatus() {
    if (this.isBottom()) {
      this.setStatus(StatusItem.DESTROYED);
    }
    this.move();
  }
}
