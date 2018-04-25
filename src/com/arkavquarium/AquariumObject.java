package com.arkavquarium;

import com.arkavquarium.fish.Direction;
import com.arkavquarium.utility.Utility;

/**
 * AquariumObject class is the base class of all objects in the game.
 * <p>All objects in the aquarium inherits this class.</p>
 *
 * @author Joseph Salimin
 * @version 1.1
 */

public class AquariumObject {
  /**
   * Bottom boundary of the aquarium.
   */
  private static int bottom;

  /**
   * Right boundary of the aquarium.
   */
  private static int right;

  /**
   * Top boundary of the aquarium.
   */
  private static int top;
  /**
   * Left     boundary of the aquarium.
   */
  private static int left;
  /**
   * This class stores the object information.
   * <p> The information includes: </p>
   * <ul>
   * <li>The position of the object</li>
   * <li>The speed of the object</li>
   * <li>The current time (important for scheduling) </li>
   * <li>How long the object has been moving randomly </li>
   * </ul>
   */
  private int posX;
  private int posY;
  private double speed;
  private Direction direction;
  private long timeNow = 0;
  private long moveTimePassed = 0;


  /**
   * The AquariumObject constructor, constructs based on the position and the speed of the object.
   *
   * @param posX  horizontal position
   * @param posY  vertical position
   * @param speed speed of the object
   */
  public AquariumObject(int posX, int posY, double speed) {
    this.posX = posX;
    this.posY = posY;
    this.speed = speed;
  }

  /**
   * Returns the bottom limit of the aquarium.
   *
   * @return bottom limit of the aquarium
   */
  public static int getBottom() {
    return bottom;
  }

  /**
   * Registers the value of the bottom limit of the aquarium.
   *
   * @param bottom bottom limit value of the aquarium
   */
  public static void setBottom(int bottom) {
    AquariumObject.bottom = bottom;
  }

  /**
   * Returns the right limit of the aquarium.
   *
   * @return right limit of the aquarium
   */
  public static int getRight() {
    return right;
  }

  /**
   * Registers the value of the right limit of the aquarium.
   *
   * @param right right limit value of the aquarium
   */
  public static void setRight(int right) {
    AquariumObject.right = right;
  }

  /**
   * Returns the top limit of the aquarium.
   *
   * @return top limit of the aquarium
   */
  public static int getTop() {
    return top;
  }

  /**
   * Registers the value of the top limit of the aquarium.
   *
   * @param top top limit value of the aquarium
   */
  public static void setTop(int top) {
    AquariumObject.top = top;
  }

  /**
   * Returns the left limit of the aquarium.
   *
   * @return left limit of the aquarium
   */
  public static int getLeft() {
    return left;
  }

  /**
   * Registers the value of the left limit of the aquarium.
   *
   * @param left left limit value of the aquarium
   */
  public static void setLeft(int left) {
    AquariumObject.left = left;
  }

  /**
   * Returns the current time.
   *
   * @return current time
   */
  protected long getTimeNow() {
    return timeNow;
  }

  /**
   * Register the current time to a certain input.
   *
   * @param timeNow the input time in millisecond
   */
  protected void setTimeNow(long timeNow) {
    this.timeNow = timeNow;
  }

  /**
   * Returns the time passed since the object starts to move randomly.
   *
   * @return time passed doing random move
   */
  protected long getMoveTimePassed() {
    return moveTimePassed;
  }

  /**
   * Register the random move time to a certain input.
   *
   * @param moveTimePassed random move time
   */
  protected void setMoveTimePassed(long moveTimePassed) {
    this.moveTimePassed = moveTimePassed;
  }

  /**
   * Returns the horizontal position of the object relative to the top-left position  (0,0).
   *
   * @return horizontal position
   */
  public int getPosX() {
    return posX;
  }

  /**
   * Change the horizontal position of the object according to the input.
   *
   * @param posX new horizontal position
   */
  protected void setPosX(int posX) {
    this.posX = posX;
  }


  /**
   * Returns the vertical position of the object relative to the top-left position (0,0).
   *
   * @return vertical position
   */
  public int getPosY() {
    return posY;
  }

  /**
   * Change the vertical position of the object according to the input.
   *
   * @param posY new vertical position
   */
  protected void setPosY(int posY) {
    this.posY = posY;
  }

  /**
   * Returns the current speed of the object.
   *
   * @return speed of the object
   */
  protected double getSpeed() {
    return speed;
  }

  /**
   * Change the speed of the object according to the input.
   *
   * @param speed new speed
   */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
   * Returns the direction an object currently facing.
   *
   * @return moving direction of the object
   * @see Direction
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * Change the moving direction the object currently facing.
   *
   * @param direction direction input
   * @see Direction
   */
  protected void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * Returns the Euclidean distance to another object.
   *
   * @param aq aquarium object
   * @return distance to object
   */
  protected double getDistance(AquariumObject aq) {
    return Utility.getDistanceObj(this.getPosX(), this.getPosY(), aq.getPosX(), aq.getPosY());
  }

  /**
   * Returns true if the current object is in the right side of another object.
   *
   * @param aq aquarium object
   * @return true if this object is in the right side of other object, otherwise return false.
   */
  protected boolean isRightOf(AquariumObject aq) {
    return this.getPosX() >= aq.getPosX();
  }

}
