package com.arkavquarium.fish;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.item.Coin;
import com.arkavquarium.utility.Utility;
import com.linkedlist.LinkedList;

import java.util.Random;

/**
 * An abstract class that represents a Fish.
 *
 * <p>
 * Fish inherits AquariumObject
 * </p>
 *
 * <p>
 * Fish is inherited by Guppy and Piranha
 * </p>
 *
 * @author Adylan Roaffa
 * @version 1.1
 * @see AquariumObject
 */
abstract public class Fish extends AquariumObject {

  static final int HUNGRY_DURATION = 10;
  static final int FULL_DURATION = 5;
  static final int RANDOM_DURATION = 3;
  static final int MAX_EXP = 25;

  private int level;
  private int experience;
  private Hunger hungerStatus;
  private StatusFish status;
  private double currentRadian = 0;
  private long hungerTime = 0;
  private long fullTime = 0;

  /**
   * Creates a new fish with the given position and speed.
   *
   * @param x     horizontal position
   * @param y     vertical position
   * @param speed fish speed
   */
  public Fish(int x, int y, double speed) {
    super(x, y, speed);
    this.hungerTime = System.currentTimeMillis();
    this.fullTime = System.currentTimeMillis();
    this.hungerStatus = Hunger.FULL;
    this.experience = 0;
    this.status = StatusFish.ALIVE;
    this.level = 1;
  }

  /**
   * Returns the default random move duration.
   *
   * @return random move duration
   */
  public static int getRandomDuration() {
    return RANDOM_DURATION;
  }

  /**
   * An abstract method to find the nearest food of this.
   *
   * @param listObjFood list of food
   */
  public abstract void findNearestFood(LinkedList<? extends AquariumObject> listObjFood);

  /**
   * An abstract method to update the states of this.
   *
   * @param listOfCoin  list of coin
   * @param listObjFood list of food
   */
  public abstract void updateStatus(LinkedList<Coin> listOfCoin,
                                    LinkedList<? extends AquariumObject> listObjFood);

  /**
   * Adds this experience based on the given input.
   *
   * @param exp experience
   */
  public void addExperience(int exp) {
    this.setExperience(this.getExperience() + exp);

    if (this.getLevel() < 3) {
      if (this.getExperience() >= MAX_EXP * this.getLevel()) {
        this.setLevel(this.getLevel() + 1);
        this.setExperience(0);
      }
    } else {
      this.setExperience(MAX_EXP);
    }
  }

  /**
   * Returns true if the position of the given input is valid, otherwise returns false.
   *
   * @param x horizontal position
   * @param y vertical position
   * @return true if position valid, else returns false
   */
  private boolean isPositionValid(int x, int y) {
    return ((x >= AquariumObject.getLeft() && x <= AquariumObject.getRight())
            && (y >= AquariumObject.getTop() && y <= AquariumObject.getBottom()));
  }

  /**
   * Changes the position randomly every several seconds, based on the given random duration.
   *
   * @param t time
   */
  public void randomMove(double t) {
    // Variable
    double randomRad;
    int newX = this.getPosX();
    int newY = this.getPosY();

    this.setTimeNow(System.currentTimeMillis());

    if ((this.getTimeNow() - this.getMoveTimePassed() >= getRandomDuration() * 1000
            && isPositionValid(newX, newY)) || !isPositionValid(newX, newY)) {
      Random rand = new Random();
      randomRad = (rand.nextInt(360) / (2 * Math.PI));
      this.setCurrentRadian(randomRad);
    }

    if ((this.getTimeNow() - this.getMoveTimePassed() >= getRandomDuration() * 1000
            && isPositionValid(newX, newY))) {
      this.setMoveTimePassed(this.getTimeNow());
    }

    newX = (int) Utility.moveXByRadian(this.getPosX(),
            this.getSpeed(), this.getCurrentRadian(), t);
    newY = (int) Utility.moveYByRadian(this.getPosY(),
            this.getSpeed(), this.getCurrentRadian(), t);

    if (newX > AquariumObject.getRight()) {
      newX = AquariumObject.getRight();
    }
    if (newX < AquariumObject.getLeft()) {
      newX = AquariumObject.getLeft();
    }
    if (newY < AquariumObject.getTop()) {
      newY = AquariumObject.getTop();
    }
    if (newY > AquariumObject.getBottom()) {
      newY = AquariumObject.getBottom();
    }

    if (newX >= this.getPosX()) {
      this.setDirection(Direction.RIGHT);
    } else {
      this.setDirection(Direction.LEFT);
    }

    this.setPosX(newX);
    this.setPosY(newY);
  }

  /**
   * Returns this current level.
   *
   * @return level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Changes this level value to the given input.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Returns this current experience.
   *
   * @return current experience
   */
  public int getExperience() {
    return experience;
  }

  /**
   * Changes this experience based on the given input.
   *
   * @param experience fish experience
   */
  public void setExperience(int experience) {
    this.experience = experience;
  }

  /**
   * Returns the life status of this.
   *
   * @return status
   * @see StatusFish
   */
  public StatusFish getStatus() {
    return status;
  }

  /**
   * Changes the life status of this to the given input.
   *
   * @param status fish life status
   */
  public void setStatus(StatusFish status) {
    this.status = status;
  }

  /**
   * Returns the hunger status of this.
   *
   * @return hunger status
   * @see Hunger
   */
  public Hunger getHungerStatus() {
    return hungerStatus;
  }

  /**
   * Changes the hunger status of this to the given input.
   *
   * @param hungerStatus fish hunger status
   */
  public void setHungerStatus(Hunger hungerStatus) {
    this.hungerStatus = hungerStatus;
  }


  /**
   * Returns the current angle in radian this is moving towards to.
   *
   * @return radian
   */
  public double getCurrentRadian() {
    return currentRadian;
  }

  /**
   * Changes the current angle in radian this is moving towards to to the given input.
   *
   * @param currentRadian radian
   */
  public void setCurrentRadian(double currentRadian) {
    this.currentRadian = currentRadian;
  }

  /**
   * Returns the last hunger time in millisecond of this.
   *
   * @return hunger duration
   */
  public long getHungerTime() {
    return hungerTime;
  }

  /**
   * Change the last hunger time of this to the given input in millisecond.
   *
   * @param hungerTime hunger time
   */
  public void setHungerTime(long hungerTime) {
    this.hungerTime = hungerTime;
  }

  /**
   * Returns the last full time in millisecond of this.
   *
   * @return full time
   */
  public long getFullTime() {
    return fullTime;
  }

  /**
   * Change the last full time of this to the given input in millisecond.
   *
   * @param fullTime full time
   */
  public void setFullTime(long fullTime) {
    this.fullTime = fullTime;
  }

  /**
   * Update the hunger status of this.
   * <p>
   * Updates are as following:
   * <ol>
   * <li>Get the current time</li>
   * <li>
   * If this hunger status is FULL, check if the difference between hunger time and now
   * is more than the maximum hungry duration.
   * <br> If yes, change this life status to DEAD
   * </li>
   * <li>
   * If this hunger status is HUNGRY, check if the difference between full time and
   * now is more than the maximum full duration.
   * <br> If yes, change this life status to HUNGRY and change the hunger time to now
   * </li>
   * </ol>
   * </p>
   *
   * @see Hunger
   * @see StatusFish
   */
  public void updateHungerStatus() {
    this.setTimeNow(System.currentTimeMillis());
    if (this.getHungerStatus() == Hunger.HUNGRY) {
      if (this.getTimeNow() - this.getHungerTime() >= HUNGRY_DURATION * 1000) {
        this.setStatus(StatusFish.DEAD);
      }
    } else {
      if (this.getTimeNow() - this.getFullTime() >= FULL_DURATION * 1000) {
        this.setHungerStatus(Hunger.HUNGRY);
        this.setHungerTime(System.currentTimeMillis());
      }
    }

  }
}
