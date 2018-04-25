package com.arkavquarium.fish;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.Food;
import com.arkavquarium.item.StatusItem;
import com.arkavquarium.movable.Movable;
import com.arkavquarium.movable.TypeMove;
import com.arkavquarium.utility.Utility;
import com.linkedlist.LinkedList;

import java.io.Serializable;

/**
 * Represents Guppy in the game.
 * <p> Guppy inherits Fish and implements Movable </p>
 * <p>
 * Guppy eats Food to survive
 * <br>
 * If Guppy does not eat in a defined duration, Piranha will die.
 * <br>
 * Guppy can be eaten by a Piranha
 * </p>
 *
 * @see Piranha
 * @see Food
 * @see Fish
 * @see Movable
 */
public class Guppy extends Fish implements Movable, Serializable {

  private final static int GUPPY_EXP = 5;
  private final static int GUPPY_COIN = 5;
  private final static int GUPPY_RADIUS = 20;
  private final static int COIN_INTERVAL = 3;
  private final static double GUPPY_SPEED = 5;
  private static int GUPPY_ID;
  private long coinTimePassed = 0;
  private Food nearestFood = null;
  private int id;

  /**
   * Creates a new Guppy with the given position and speed.
   *
   * @param x     horizontal position
   * @param y     vertical position
   * @param speed speed
   */
  public Guppy(int x, int y, double speed) {
    super(x, y, speed);
    incrementId();
    this.id = Guppy.GUPPY_ID;
  }

  /**
   * Increment global Guppy ID.
   */
  private static void incrementId() {
    GUPPY_ID = GUPPY_ID + 1;
  }

  public static int getCoinInterval() {
    return COIN_INTERVAL;
  }

  /**
   * Returns the coin value that is produced by Guppy.
   *
   * @return guppy coin value
   */
  public static int getGuppyCoin() {
    return GUPPY_COIN;
  }

  /**
   * Returns the experience every time a Guppy has successfully eaten.
   *
   * @return default guppy experience
   */
  public static int getGuppyExp() {
    return GUPPY_EXP;
  }

  /**
   * Returns the default Guppy speed.
   *
   * @return Guppy default speed
   */
  public static double getGuppySpeed() {
    return GUPPY_SPEED;
  }

  /**
   * Returns the movement type of this object.
   * <p> If this hunger status is full or there is no nearest guppy, returns RANDOM
   * <br> Otherwise, returns NOT_RANDOM
   * </p>
   *
   * @return TypeMove of this object
   * @see TypeMove
   */
  public TypeMove getTypeMove() {
    if (this.getHungerStatus() == Hunger.FULL || this.getNearestFood() == null) {
      return TypeMove.RANDOM;
    } else {
      return TypeMove.NOT_RANDOM;
    }
  }

  /**
   * Moves this Guppy to the desired location.
   * <p>
   * Steps are:
   * <ol>
   * <li>Check the movement type</li>
   * <li>If random then do the random move</li>
   * <li>If not random then move to this's nearest food</li>
   * </ol>
   * </p>
   *
   * @see Guppy
   * @see TypeMove
   */
  public void move() {
    if (this.getTypeMove() == TypeMove.RANDOM) {
      this.randomMove(1);
    } else {
      Food nearestFood = this.getNearestFood();
      int foodX = nearestFood.getPosX();
      int foodY = nearestFood.getPosY();
      this.setCurrentRadian(Utility.getMovementDirection(getPosX(), getPosY(), foodX, foodY));

      int nextX = (int) Utility.moveXByRadian(getPosX(), getSpeed(), this.getCurrentRadian(), 1);
      int nextY = (int) Utility.moveYByRadian(getPosY(), getSpeed(), this.getCurrentRadian(), 1);

      if (nextX < this.getPosX()) {
        this.setDirection(Direction.LEFT);
      } else {
        this.setDirection(Direction.RIGHT);
      }

      this.setPosX(nextX);
      this.setPosY(nextY);
    }
  }

  public int getGuppyId() {
    return this.id;
  }

  /**
   * Returns this Guppy's nearest food.
   *
   * @return nearest food
   * @see Food
   */
  public Food getNearestFood() {
    return nearestFood;
  }

  /**
   * Changes this Guppy's nearest food to the given Food.
   *
   * @param nearestFood
   * @see Food
   */
  public void setNearestFood(Food nearestFood) {
    this.nearestFood = nearestFood;
  }

  /**
   * Find and set this nearest Guppy attribute to the latest nearest Food.
   * <p>
   * If the food list is empty, return null,
   * <br> Otherwise, change the nearest food attribute to
   * the current nearest Food from the list of food
   * </p>
   *
   * @param listObjFood list of food
   */
  public void findNearestFood(LinkedList<? extends AquariumObject> listObjFood) {
    // Check the size of the list
    // If > 0, then find the nearest food
    // Else set the food to null
    if (listObjFood.getSize() > 0 && this.getHungerStatus() == Hunger.HUNGRY) {
      Food nearestFood = (Food) listObjFood.get(0);
      double minDist = this.getDistance(nearestFood);

      // Check if the first food is destroyed
      // If it is, then set the nearestFood to null and min distance to max
      if (nearestFood.getStatus() == StatusItem.DESTROYED) {
        nearestFood = null;
        minDist = 1e+10;
      }

      for (int idx = 1; idx < listObjFood.getSize(); idx++) {
        Food food = (Food) listObjFood.get(idx);
        // Check if the food is not destroyed
        if (food.getStatus() != StatusItem.DESTROYED) {
          double dist = this.getDistance(food);
          if (dist < minDist) {
            minDist = dist;
            nearestFood = food;
          }
        }
      }
      this.setNearestFood(nearestFood);
    } else {
      this.setNearestFood(null);
    }
  }

  /**
   * Eats Food if there is a Food in the same position as this.
   * <p>
   * If this eats Good:
   * <ul>
   * <li>hunger status is set to FULL</li>
   * <li>experience is added based on GUPPY_EXP</li>
   * <li>current hunger time changes to current time</li>
   * </ul>
   * </p>
   *
   * <p>
   * Returns true if Piranha successfully ate Guppy
   * <br>Otherwise, returns false
   * </p>
   *
   * @see Hunger
   */
  public void eat() {
    if (this.getNearestFood() != null) {
      double dist = this.getDistance(this.getNearestFood());

      if (dist < GUPPY_RADIUS * this.getLevel() &&
              this.getNearestFood().getStatus() != StatusItem.DESTROYED) {
        this.getNearestFood().setStatus(StatusItem.DESTROYED);
        this.setHungerStatus(Hunger.FULL);
        this.addExperience(GUPPY_EXP);
        this.setFullTime(System.currentTimeMillis());
      }
    }
  }

  /**
   * Returns the last time in millisecond the coin has been produced.
   *
   * @return coin time passed
   */
  public long getCoinTimePassed() {
    return coinTimePassed;
  }

  /**
   * Changes the last time coin has been produced to the given input in millisecond.
   *
   * @param coinTimePassed
   */
  public void setCoinTimePassed(long coinTimePassed) {
    this.coinTimePassed = coinTimePassed;
  }

  // Update the state of the Guppy
  // Methods
  // If guppy produce coin, add the coin to the list of coin


  /**
   * Updates this Guppy current state.
   *
   * <p>
   * The updates are as following:
   * <ol>
   * <li>Find and set the nearest Food</li>
   * <li>Eat Food if possible</li>
   * <li>Updates the hunger status of this</li>
   * <li>This produces coin and adds the coin to the list of coin based on the given interval</li>
   * </ol>
   * </p>
   */

  public void updateStatus(LinkedList<Coin> listOfCoin,
                           LinkedList<? extends AquariumObject> listObjFood) {
    this.findNearestFood(listObjFood);
    this.updateHungerStatus();
    this.eat();
    this.move();

    int xPos = this.getPosX();
    int yPos = this.getPosY();
    int level = this.getLevel();

    this.setTimeNow(System.currentTimeMillis());
    if (this.getTimeNow() - this.getCoinTimePassed() >= COIN_INTERVAL * 1000) {
      Coin c = new Coin(xPos, yPos, level * GUPPY_COIN);
      listOfCoin.add(c);
      this.setCoinTimePassed(this.getTimeNow());
    }


  }

}
