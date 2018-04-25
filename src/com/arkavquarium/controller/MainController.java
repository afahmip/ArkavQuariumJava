package com.arkavquarium.controller;

import com.arkavquarium.AquariumObject;
import com.arkavquarium.fish.Guppy;
import com.arkavquarium.fish.Piranha;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.Food;
import com.arkavquarium.item.StatusItem;
import com.arkavquarium.pet.Snail;
import com.linkedlist.LinkedList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainController extends JPanel implements ActionListener {

  private GameController game;
  private ViewController view;
  private int mouseX;
  private int mouseY;
  private GameState state;

  /**
   * Creates a new game main controller.
   */
  public MainController() {
    // Set boundaries
    AquariumObject.setBottom(650);
    AquariumObject.setTop(140);
    AquariumObject.setLeft(30);
    AquariumObject.setRight(998);

    this.game = new GameController(1024, 768);
    this.view = new ViewController(1024, 768);
    this.state = GameState.MENU;
    addListener();
  }

  /**
   * Updates states of objects in game.
   */
  public void updateObject() {
    this.view.setEggState(this.game.getEgg());
    this.view.setGameCurrency(
            GameController.getGuppyPrice(),
            GameController.getPiranhaPrice(),
            GameController.getEggPrice(),
            this.game.getMoney()
    );
    this.game.update();
    LinkedList<Guppy> listOfGuppy = this.game.getListOfGuppy();
    LinkedList<Piranha> listOfPiranha = this.game.getListOfPiranha();
    LinkedList<Coin> listOfCoin = this.game.getListOfCoin();
    LinkedList<Food> listOfFood = this.game.getListOfFood();
    LinkedList<Snail> listOfSnail = this.game.getListOfSnail();
    this.view.setListOfObjects(listOfGuppy, listOfPiranha, listOfSnail, listOfFood, listOfCoin);

    if (this.game.isWin()) this.state = GameState.WIN;
    else if (this.game.isLose()) this.state = GameState.LOSE;
    this.view.setGameState(this.state);
  }

  /**
   * Adds click listener to the window.
   */
  public void addListener() {
    this.view.getWindowFrame().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        mouseX = e.getX();
        mouseY = e.getY();
        switchState(mouseX, mouseY);
        if (state == GameState.GAME) {
          interract(mouseX, mouseY);
        }
      }
    });
  }

  /**
   * Runs the game main loop.
   */
  public void executeMainLoop() {
    int DELAY = 60;
    Timer timer = new Timer(DELAY, this);
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    updateObject();
    JFrame jFrame = this.view.getWindowFrame();
    jFrame.invalidate();
    jFrame.validate();
    jFrame.repaint();
  }

  private void switchState(int x, int y) {
    if (this.state == GameState.MENU && x >= 572 && x <= 923 && y >= 70 && y <= 177)
      this.state = GameState.GAME;
    this.view.setGameState(this.state);
  }

  private void interract(int x, int y) {
    int checkCoin = isCoinExistOnCursor(x, y);
    if (checkCoin != -1) {
      LinkedList<Coin> L = this.game.getListOfCoin();
      L.get(checkCoin).setStatus(StatusItem.DESTROYED);
      this.game.update();
    } else if (y > 110 && y < 650) {
      this.game.buyFood(x, y);
    } else if (x > 709 && x < 782 && y >= 8 && y <= 109) {
      this.game.buyEgg();
    } else if (x > 35 && x < 112 && y >= 9 && y <= 96) {
      this.game.buyGuppy();
    } else if (x > 356 && x < 430 && y >= 9 && y <= 96) {
      this.game.buyPiranha();
    } else if (x > 856 && x < 988 && y > 20 && y < 92) {
      System.out.println("SAVED");
      this.game.saveState();
    }
  }

  private int isCoinExistOnCursor(int x, int y) {
    LinkedList<Coin> listOfCoin = game.getListOfCoin();
    for (int i = 0; i < listOfCoin.getSize(); i++) {
      Coin coin = listOfCoin.get(i);
      if (Math.abs(coin.getPosX() - x) <= 50 && Math.abs(coin.getPosY() - y) <= 50
              && coin.getStatus() == StatusItem.NOT_DESTROYED) {
        return i;
      }
    }
    return -1; // If coin doesn't exist on cursor location
  }

}
