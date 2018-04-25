package com.arkavquarium.movable;

/**
 * Movable is an interface that has to be used by objects that has a moving behaviour.
 *
 * @author Adylan Roaffa Ilmy
 * @version 1.1
 */

public interface Movable {

  /**
   * Moving behaviour of an object.
   */
  void move();

  /**
   * returns the movement type of an object.
   *
   * @return movement type of an object
   */
  TypeMove getTypeMove();
}