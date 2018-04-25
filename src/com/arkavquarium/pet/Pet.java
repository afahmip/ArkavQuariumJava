package com.arkavquarium.pet;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.StatusItem;
import com.linkedlist.LinkedList;


/**
 * Pet is an abstract class that defines the behaviour of a pet.
 * <p> This is the base class of the Snail class </p>
 *
 * @author Joseph Salimin
 * @version 1.1
 * @see Snail
 * @see AquariumObject
 */
public abstract class Pet extends AquariumObject {

  protected final static int OFFSET = 35;
  private Coin nearestCoin;

  /**
   * An abstract constructor of a new Pet object with the given position and speed.
   *
   * @param x     horizontal position
   * @param y     horizontal position
   * @param speed speed
   */
  Pet(int x, int y, double speed) {
    super(x, y, speed);
    nearestCoin = null;
  }

  /**
   * Return the nearest coin.
   *
   * @return nearest coin
   */
  public Coin getNearestCoin() {
    return nearestCoin;
  }

  /**
   * Change the value of the nearest coin.
   *
   * @param nearestCoin new nearest coin
   */
  void setNearestCoin(Coin nearestCoin) {
    this.nearestCoin = nearestCoin;
  }

  /**
   * Find and set the subclass's nearest coin.
   *
   * @param listOfCoin list of coin
   * @return nearest coin
   */
  protected abstract Coin findNearestCoin(LinkedList<Coin> listOfCoin);

  void takeCoin(double radius) {
    if (nearestCoin != null) {

      double dist = this.getDistancePet(nearestCoin);
      if (dist < radius) {
        //System.out.println("Ambil koin");
        nearestCoin.setStatus(StatusItem.DESTROYED);
        this.setNearestCoin(null);
      }
    }
  }

  protected double getDistancePet(AquariumObject aqObj) {
    this.setPosY(this.getPosY() + OFFSET);
    this.setPosX(this.getPosX() + OFFSET);
    double dist = this.getDistance(aqObj);
    this.setPosY(this.getPosY() - OFFSET);
    this.setPosX(this.getPosX() - OFFSET);
    return dist;
  }
}
