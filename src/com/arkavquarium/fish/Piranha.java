package com.arkavquarium.fish;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.item.Coin;
import com.arkavquarium.movable.Movable;
import com.arkavquarium.movable.TypeMove;
import com.arkavquarium.utility.Utility;
import com.linkedlist.LinkedList;

import java.io.Serializable;

/**
 * Represents Piranha in the game.
 * <p>Piranha inherits Fish class and implements Movable</p>
 *
 * <p>Piranha eats Guppy to survive
 * <br>If Piranha does not eat in a defined duration, Piranha will die.
 * </p>
 *
 * @author Joseph Salimin
 * @version 1.1
 * @see Fish
 * @see Movable
 * @see Guppy
 */
public class Piranha extends Fish implements Movable, Serializable {

  private static final int PIRANHA_EXP = 7;
  private static final int PIRANHA_RADIUS = 25;
  private static final int PIRANHA_VALUE = 75;
  private static final double PIRANHA_SPEED = 6;
  private static int PIRANHA_ID;
  private int id;
  private Guppy nearestGuppy = null;

  /**
   * Creates a new Piranha with the given position and speed.
   *
   * @param x     horizontal position
   * @param y     vertical position
   * @param speed speed
   */
  public Piranha(int x, int y, double speed) {
    super(x, y, speed);
    incrementId();
    this.id = Piranha.PIRANHA_ID;
  }

  /**
   * Returns the default Piranha experience value every time Piranha eats.
   *
   * @return Piranha experience
   */
  public static int getPiranhaExp() {
    return PIRANHA_EXP;
  }

  /**
   * Returns the default Piranha radius of eating.
   *
   * @return Piranha default radius
   */
  public static int getPiranhaRadius() {
    return PIRANHA_RADIUS;
  }

  /**
   * Returns the Piranha's produced coin default value.
   *
   * @return Piranha coin value
   */
  public static int getPiranhaValue() {
    return PIRANHA_VALUE;
  }

  /**
   * Returns the default Piranha moving speed.
   *
   * @return Piranha moving speed
   */
  public static double getPiranhaSpeed() {
    return PIRANHA_SPEED;
  }

  /**
   * Increment global Piranha ID.
   */
  private static void incrementId() {
    PIRANHA_ID = PIRANHA_ID + 1;
  }

  /**
   * Returns the id that Piranha holds.
   *
   * @return Piranha ID
   */
  public int getPiranhaId() {
    return this.id;
  }

  /**
   * Returns this's nearest guppy.
   *
   * @return nearest guppy
   */
  public Guppy getNearestGuppy() {
    return nearestGuppy;
  }

  /**
   * Changes the nearest guppy to the given Guppy input.
   *
   * @param nearestGuppy
   */
  public void setNearestGuppy(Guppy nearestGuppy) {
    this.nearestGuppy = nearestGuppy;
  }

  /**
   * Find and set this nearest Guppy attribute to the latest nearest Guppy.
   * <p>If the food list is empty, return null,
   * <br>Otherwise,  change the nearest Guppy attribute to the current
   * nearest Guppy from the list of food
   * </p>
   *
   * @param listObjFood list of food
   */
  public void findNearestFood(LinkedList<? extends AquariumObject> listObjFood) {
    if (listObjFood.getSize() > 0 && this.getHungerStatus() == Hunger.HUNGRY) {
      Guppy nearestGuppy = (Guppy) listObjFood.get(0);
      double minDist = this.getDistance(nearestGuppy);

      if (nearestGuppy.getStatus() == StatusFish.DEAD) {
        nearestGuppy = null;
        minDist = 1e+10;
      }

      for (int idx = 1; idx < listObjFood.getSize(); idx++) {
        Guppy guppy = (Guppy) listObjFood.get(idx);

        if (guppy.getStatus() != StatusFish.DEAD) {
          double dist = this.getDistance(guppy);
          if (minDist > dist) {
            minDist = dist;
            nearestGuppy = guppy;
          }
        }
      }
      this.setNearestGuppy(nearestGuppy);
    } else {
      this.setNearestGuppy(null);
    }
  }

  /**
   * Eats Guppy if there is a Guppy in the same position as this.
   * <p> If Piranha eats Guppy:
   * <ul>
   * <li>hunger status is set to FULL</li>
   * <li>experience is added based on PIRANHA_EXP</li>
   * <li>current hunger time changes to current time</li>
   * </ul>
   * </p>
   *
   * <p> Returns true if Piranha successfully ate Guppy
   * <br> Otherwise, returns false
   * </p>
   *
   * @return true if Piranha eats, otherwise returns false
   * @see Hunger
   */
  public boolean eat() {
    if (this.getNearestGuppy() != null) {
      double dist = this.getDistance(this.getNearestGuppy());

      if (dist < PIRANHA_RADIUS * this.getLevel() &&
              this.getNearestGuppy().getStatus() != StatusFish.DEAD) {
        this.getNearestGuppy().setStatus(StatusFish.DEAD);
        this.setHungerStatus(Hunger.FULL);
        this.addExperience(PIRANHA_EXP);
        this.setFullTime(System.currentTimeMillis());
        return true;
      }
      return false;
    } else {
      return false;
    }
  }

  /**
   * Returns the movement type of this object.
   * <p>If this hunger status is full or there is no nearest guppy, returns RANDOM
   * <br>Otherwise, returns NOT_RANDOM
   * </p>
   *
   * @return TypeMove of this object
   * @see TypeMove
   */
  public TypeMove getTypeMove() {
    if (this.getHungerStatus() == Hunger.FULL || this.getNearestGuppy() == null) {
      return TypeMove.RANDOM;
    } else {
      if (this.getNearestGuppy().getStatus() != StatusFish.DEAD) {
        return TypeMove.NOT_RANDOM;
      } else {
        this.setNearestGuppy(null);
        return TypeMove.RANDOM;
      }

    }
  }

  /**
   * Moves this Piranha to the desired location.
   * <p> Steps are:
   * <ol>
   * <li>Check the movement type</li>
   * <li>If random then do the random move</li>
   * <li>If not random then move to this's nearest guppy</li>
   * </ol>
   * </p>
   *
   * @see Guppy
   * @see TypeMove
   */
  public void move() {
    // Check the type of move
    // If random then do the random move
    // Else move to nearest guppy
    if (this.getTypeMove() == TypeMove.RANDOM) {
      this.randomMove(1);

    } else {
      // Get the nearest Guppy
      Guppy nearestGuppy = this.getNearestGuppy();
      // Get the food point
      int foodX = nearestGuppy.getPosX();
      int foodY = nearestGuppy.getPosY();

      // Set the radian
      double radian = Utility.getMovementDirection(getPosX(), getPosY(), foodX, foodY);
      this.setCurrentRadian(radian);

      // Get the newX and newY
      int newX = (int) Utility.moveXByRadian(getPosX(), getSpeed(), this.getCurrentRadian(), 1);
      int newY = (int) Utility.moveYByRadian(getPosY(), getSpeed(), this.getCurrentRadian(), 1);

      if (newX >= this.getPosX()) {
        this.setDirection(Direction.RIGHT);
      } else {
        this.setDirection(Direction.LEFT);
      }

      this.setPosX(newX);
      this.setPosY(newY);
    }
  }

  /**
   * Updates this piranha current state.
   * <p>
   * The updates are as following:
   * <ol>
   * <li>Find and set the nearest Guppy</li>
   * <li>Eat Guppy if possible</li>
   * <li>If a Guppy is successfully eaten, this produces coin and adds it to the list of coin</li>
   * <li>Updates the hunger status of this</li>
   * </ol>
   *
   * @param listOfCoin
   * @param listObjFood
   */
  public void updateStatus(LinkedList<Coin> listOfCoin,
                           LinkedList<? extends AquariumObject> listObjFood) {
    this.findNearestFood(listObjFood);
    if (this.eat()) {
      int value = (this.getNearestGuppy().getLevel() + 1) * PIRANHA_VALUE;
      Coin newCoin = new Coin(this.getPosX(), this.getPosY(), value);
      listOfCoin.add(newCoin);
    }
    this.move();
    this.updateHungerStatus();
  }
}
