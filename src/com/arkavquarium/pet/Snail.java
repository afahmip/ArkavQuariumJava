package com.arkavquarium.pet;

import com.arkavquarium.fish.Direction;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.StatusItem;
import com.arkavquarium.movable.Movable;
import com.arkavquarium.movable.TypeMove;
import com.linkedlist.LinkedList;

import java.io.Serializable;

/**
 * Snail is a class that acts as the snail in the game.
 * <p>Snails is positioned in the bottom of the aquarium, and can only move left and right.</p>
 * <p> Snail's job is to collect coins that is produced by the Fish
 * <br>
 * Snail inherits Pet class and implements Movable
 * </p>
 *
 * @author Joseph Salimin
 * @version 1.1
 * @see Pet
 * @see Movable
 */

public class Snail extends Pet implements Movable, Serializable {

  private final static double DEFAULT_SNAIL_SPEED = 3;
  private final static double RADIUS_SNAIL = 40.0;


  /**
   * Creates a new Snail with the given position.
   *
   * @param x horizontal position
   * @param y vertical position
   */
  public Snail(int x, int y) {
    super(x, y, Snail.DEFAULT_SNAIL_SPEED);
  }

  /**
   * Creates a new Snail with the given position and speed.
   *
   * @param x     horizontal position
   * @param y     vertical position
   * @param speed speed ot the snail
   */
  public Snail(int x, int y, double speed) {
    super(x, y, speed);
  }

  /**
   * Take coin based on the given radius.
   *
   * @see Pet#takeCoin(double)
   */
  private void takeCoin() {
    super.takeCoin(RADIUS_SNAIL);
  }

  /**
   * Returns a list all of the coins that is located at the bottom of the aquarium.
   *
   * @param listOfCoin list of coins in the aquarium
   * @return List of coins located at the bottom of the aquarium
   */
  private LinkedList<Coin> getBottomCoin(LinkedList<Coin> listOfCoin) {
    int length = listOfCoin.getSize();
    LinkedList<Coin> bottomCoins = new LinkedList<>();

    for (int i = 0; i < length; i++) {
      Coin coin = listOfCoin.get(i);

      if (coin.isBottom() && (coin.getStatus() != StatusItem.DESTROYED)) {
        bottomCoins.add(coin);
      }
    }

    return bottomCoins;
  }

  /**
   * Returns the move type of this snail.
   * <p>
   * Returns STOP if there is no coins at the bottom of aquarium,
   * otherwise returns NOT_RANDOM
   * </p>
   *
   * @return TypeMove of this snail
   * @see TypeMove
   */
  public TypeMove getTypeMove() {
    // Variable
    Coin nearestCoin = getNearestCoin();
    // Algorithm
    if (nearestCoin == null) {
      return TypeMove.STOP;
    } else {
      if (Math.abs(this.getPosX() + OFFSET - nearestCoin.getPosX()) < RADIUS_SNAIL / 3) {
        System.out.println("Mirip");
        return TypeMove.STOP;
      }
      return TypeMove.NOT_RANDOM;
    }
  }

  /**
   * Returns the nearest coin in the aquarium.
   * <p>If there are coins at the bottom, get the nearest coin at bottom</p>
   * If there are no coin at the bottom, get the nearest coin that in the aquarium
   *
   * @param listOfCoin list of coins
   * @return the nearest coin from this snail
   */

  protected Coin findNearestCoin(LinkedList<Coin> listOfCoin) {
    // Variable Function
    double minDistance = 9999999;
    double distance;
    boolean first = true;

    LinkedList<Coin> bottomCoins = getBottomCoin(listOfCoin);
    LinkedList<Coin> listOfFoundCoins;

    if (bottomCoins.getSize() > 0) {
      listOfFoundCoins = bottomCoins;
    } else {
      listOfFoundCoins = listOfCoin;
    }

    int length = listOfFoundCoins.getSize();
    Coin coin;
    Coin nearestCoin = null;

    for (int idx = 0; idx < length; idx++) {
      coin = listOfFoundCoins.get(idx);

      // Ignore the coin if the status is DESTROYED
      if (coin.getStatus() == StatusItem.DESTROYED) {
        continue;
      }

      // Only for the first occurence
      if (first) {
        nearestCoin = coin;
        minDistance = this.getDistancePet(nearestCoin);
        first = false;
        continue;
      }

      // Get the distance
      distance = this.getDistancePet(coin);

      // Check if the distance is smaller than minimum distance now
      // If smaller, nearestCoin point to coin and set minDistance to distance
      if (distance < minDistance) {
        nearestCoin = coin;
        minDistance = distance;
      }
    }
    return nearestCoin;
  }

  /**
   * Update the state of the snail.
   * <p>Updates include:
   * <ul>
   * <li> The position of this snail</li>
   * <li> Taking coin </li>
   * </ul>
   * </p>
   *
   * @param listOfCoin list of coins
   */
  public void updateStatus(LinkedList<Coin> listOfCoin) {
    Coin nearestCoin = this.findNearestCoin(listOfCoin);
    this.setNearestCoin(nearestCoin);
    // Take the coin if possible and return the value of the coin
    // If not possible, do nothing
    this.takeCoin();
    this.move();
  }

  /**
   * Change the current position of this snail.
   * <p> If the movement type of this snail is STOP, position is not changing
   * <br> otherwise, go to the nearest coin
   * </p>
   */
  public void move() {
    TypeMove move = getTypeMove();

    if (move == TypeMove.STOP) {
      this.setDirection(this.getDirection());
    } else {
      Coin nearestCoin = this.getNearestCoin();

      if (this.isRightOf(nearestCoin)) {
        this.setPosX(this.getPosX() - (int) this.getSpeed());
        this.setDirection(Direction.LEFT);
      } else {
        this.setPosX(this.getPosX() + (int) this.getSpeed());
        this.setDirection(Direction.RIGHT);
      }
    }
  }
}
