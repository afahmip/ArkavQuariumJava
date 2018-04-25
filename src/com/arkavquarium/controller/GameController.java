package com.arkavquarium.controller;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.fish.Guppy;
import com.arkavquarium.fish.Piranha;
import com.arkavquarium.fish.StatusFish;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.Food;
import com.arkavquarium.item.StatusItem;
import com.arkavquarium.pet.Snail;
import com.linkedlist.LinkedList;

import java.io.*;
import java.util.Random;

/**
 * Represents the model controller of the game.
 * <p>
 * Controls the model of the objects on the game.
 * </p>
 */
public class GameController implements Serializable {

  private static final int DEFAULT_MONEY = 1000;
  private static final int GUPPY_PRICE = 100;
  private static final int PIRANHA_PRICE = 500;
  private static final int FOOD_PRICE = 5;
  private static final int SNAIL_PRICE = 150;
  private static final int EGG_PRICE = 750;

  private transient LinkedList<Guppy> listOfGuppy = new LinkedList<>();
  private transient LinkedList<Piranha> listOfPiranha = new LinkedList<>();
  private transient LinkedList<Snail> listOfSnail = new LinkedList<>();
  private transient LinkedList<Coin> listOfCoin = new LinkedList<>();
  private transient LinkedList<Food> listOfFood = new LinkedList<>();

  private int egg;
  private int money;

  public GameController() {
    this.egg = 0;
    this.money = DEFAULT_MONEY;
  }

  /**
   * Creates a new game controller with the given window width and height.
   *
   * @param width  window width
   * @param height window height
   */
  public GameController(int width, int height) {

    this.egg = 0;
    this.money = DEFAULT_MONEY;

    Snail snail = new Snail(500, AquariumObject.getBottom() - 50);
    this.listOfSnail.add(snail);
  }

  public static int getGuppyPrice() {
    return GUPPY_PRICE;
  }

  public static int getPiranhaPrice() {
    return PIRANHA_PRICE;
  }

  public static int getEggPrice() {
    return EGG_PRICE;
  }

  public LinkedList<Guppy> getListOfGuppy() {
    return listOfGuppy;
  }

  public void setListOfGuppy(LinkedList<Guppy> listOfGuppy) {
    this.listOfGuppy = listOfGuppy;
  }

  public LinkedList<Piranha> getListOfPiranha() {
    return listOfPiranha;
  }

  public void setListOfPiranha(LinkedList<Piranha> listOfPiranha) {
    this.listOfPiranha = listOfPiranha;
  }

  public LinkedList<Snail> getListOfSnail() {
    return listOfSnail;
  }

  public void setListOfSnail(LinkedList<Snail> listOfSnail) {
    this.listOfSnail = listOfSnail;
  }

  public LinkedList<Coin> getListOfCoin() {
    return listOfCoin;
  }

  public void setListOfCoin(LinkedList<Coin> listOfCoin) {
    this.listOfCoin = listOfCoin;
  }

  public LinkedList<Food> getListOfFood() {
    return listOfFood;
  }

  public void setListOfFood(LinkedList<Food> listOfFood) {
    this.listOfFood = listOfFood;
  }

  public int getEgg() {
    return egg;
  }

  public void setEgg(int egg) {
    this.egg = egg;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public boolean isWin() {
    return egg == 3;
  }

  public boolean isLose() {
    return egg < 3 && this.money < GUPPY_PRICE &&
            this.listOfGuppy.getSize() == 0 && this.listOfPiranha.getSize() == 0;
  }

  /**
   * Update the states of the game.
   */
  public void update() {
    this.updateGuppy();
    this.updatePiranha();
    this.updateSnail();
    this.updateCoin();
    this.updateFood();

    this.deleteDeadGuppy();
    this.deleteDeadPiranha();
    this.deleteDestroyedFood();
    this.deleteDestroyedCoin();
  }

  private void updateGuppy() {
    for (int i = 0; i < this.listOfGuppy.getSize(); i++) {
      Guppy guppy = listOfGuppy.get(i);
      guppy.updateStatus(this.listOfCoin, this.listOfFood);
    }
  }

  private void updatePiranha() {
    for (int i = 0; i < this.listOfPiranha.getSize(); i++) {
      Piranha piranha = listOfPiranha.get(i);
      piranha.updateStatus(this.listOfCoin, this.listOfGuppy);
    }
  }

  private void updateSnail() {
    for (int i = 0; i < this.listOfSnail.getSize(); i++) {
      Snail snail = listOfSnail.get(i);
      snail.updateStatus(this.listOfCoin);
    }
  }

  private void updateFood() {
    for (int i = 0; i < this.listOfFood.getSize(); i++) {
      Food food = listOfFood.get(i);
      food.move();
      food.updateFoodStatus();
    }
  }

  private void updateCoin() {
    for (int i = 0; i < this.listOfCoin.getSize(); i++) {
      Coin coin = listOfCoin.get(i);
      coin.move();
    }
  }

  private void deleteDestroyedFood() {
    for (int i = 0; i < this.listOfFood.getSize(); i++) {
      Food f = (Food) this.listOfFood.get(i);
      if (f.getStatus() == StatusItem.DESTROYED) {
        listOfFood.remove(f);
      }
    }
  }

  private void deleteDestroyedCoin() {
    for (int i = 0; i < this.listOfCoin.getSize(); i++) {
      Coin c = this.listOfCoin.get(i);
      if (c.getStatus() == StatusItem.DESTROYED) {
        this.setMoney(this.getMoney() + c.getValue());
        listOfCoin.remove(c);
      }
    }
  }

  private void deleteDeadGuppy() {
    for (int i = 0; i < this.listOfGuppy.getSize(); i++) {
      Guppy g = this.listOfGuppy.get(i);
      if (g.getStatus() == StatusFish.DEAD) {
        listOfGuppy.remove(g);
      }
    }
  }

  private void deleteDeadPiranha() {
    for (int i = 0; i < this.listOfPiranha.getSize(); i++) {
      Piranha p = this.listOfPiranha.get(i);
      if (p.getStatus() == StatusFish.DEAD) {
        listOfPiranha.remove(p);
      }
    }
  }

  /**
   * Buys a new guppy and add it to the list of Guppy.
   */
  public void buyGuppy() {
    if (this.getMoney() >= GUPPY_PRICE) {
      int top = AquariumObject.getTop();
      int left = AquariumObject.getLeft();
      int right = AquariumObject.getRight();
      int bottom = AquariumObject.getBottom();

      Random rand = new Random();
      int x = rand.nextInt(right) + left;
      int y = rand.nextInt(bottom) + top;

      setMoney(this.money - GUPPY_PRICE);
      Guppy g = new Guppy(x, y, Guppy.getGuppySpeed());
      this.listOfGuppy.add(g);
    }
  }

  /**
   * Buys a new piranha and add it to the list of Piranha.
   */
  public void buyPiranha() {
    if (this.getMoney() >= PIRANHA_PRICE) {
      int top = AquariumObject.getTop();
      int left = AquariumObject.getLeft();
      int right = AquariumObject.getRight();
      int bottom = AquariumObject.getBottom();

      Random rand = new Random();
      int x = rand.nextInt(right) + left;
      int y = rand.nextInt(bottom) + top;

      setMoney(this.money - PIRANHA_PRICE);
      Piranha p = new Piranha(x, y, Piranha.getPiranhaSpeed());
      this.listOfPiranha.add(p);
    }
  }

  /**
   * Buys a new food and add it to the list of food with the given position.
   *
   * @param x horizontal position
   * @param y vertical position
   */
  public void buyFood(int x, int y) {
    if (this.getMoney() >= FOOD_PRICE) {
      setMoney(this.money - FOOD_PRICE);
      Food f = new Food(x, y, Food.getDefaultHungerPoint());
      this.listOfFood.add(f);
    }
  }

  /**
   * Buys a new egg and increment the egg number.
   */
  public void buyEgg() {
    if (this.getMoney() >= EGG_PRICE) {
      setMoney(this.money - EGG_PRICE);
      this.egg++;
    }
  }


  /**
   * Save the game state.
   */
  public void saveState() {
    String directoryName = "D:\\HMIF\\K-01\\Semester 4\\OOP\\Tubes 3\\ArkavQuariumJava\\src\\savegame";
    File saveDirectory = new File(directoryName);
    if (!saveDirectory.exists()) {
      saveDirectory.mkdir();
    }

    // Save game state
    try {
      String gameFilename = "game.ser";
      FileOutputStream fileOut = new FileOutputStream(directoryName + "/" + gameFilename);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save Guppies
    try {
      String guppyFilename = "guppy-";
      for (int i = 0; i < listOfGuppy.getSize(); i++) {
        FileOutputStream fileOut = new FileOutputStream(
                directoryName + "/" + guppyFilename + (i + 1) + ".ser");
        Guppy guppy = listOfGuppy.get(i);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(guppy);
        out.close();
        fileOut.close();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save Piranha
    try {
      String piranhaFilename = "piranha-";
      for (int i = 0; i < listOfPiranha.getSize(); i++) {
        FileOutputStream fileOut = new FileOutputStream(
                directoryName + "/" + piranhaFilename + (i + 1) + ".ser");
        Piranha piranha = listOfPiranha.get(i);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(piranha);
        out.close();
        fileOut.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save Coins
    try {
      String coinFilename = "coin-";
      for (int i = 0; i < listOfCoin.getSize(); i++) {
        FileOutputStream fileOut = new FileOutputStream(
                directoryName + "/" + coinFilename + (i + 1) + ".ser");
        Coin coin = listOfCoin.get(i);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(coin);
        out.close();
        fileOut.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public void loadState() {

  }


}
