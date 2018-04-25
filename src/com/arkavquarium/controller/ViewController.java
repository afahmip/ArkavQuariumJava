package com.arkavquarium.controller;

import com.arkavquarium.fish.Guppy;
import com.arkavquarium.fish.Piranha;
import com.arkavquarium.item.Coin;
import com.arkavquarium.item.Food;
import com.arkavquarium.pet.Snail;
import com.linkedlist.LinkedList;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the controller that controls the view of the game.
 * <p>
 * Views include the image of the Guppies, Piranhas, Coins, and Foods.
 * </p>
 *
 * @author Ahmad Fahmi Pratama
 * @version 1.1
 * @see Guppy
 * @see Piranha
 * @see Coin
 * @see Food
 * @see Snail
 * @see LinkedList
 */
public class ViewController extends JPanel {

  private static final String ABS_PATH = ViewController.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ");
  private static final String PARENT_FOLDER = "../../../";

  private JFrame windowFrame;

  private LinkedList<Guppy> listOfGuppy = new LinkedList<>();
  private LinkedList<Piranha> listOfPiranha = new LinkedList<>();
  private LinkedList<Snail> listOfSnail = new LinkedList<>();
  private LinkedList<Food> listOfFood = new LinkedList<>();
  private LinkedList<Coin> listOfCoin = new LinkedList<>();
  private LinkedList<ImageSnail> listOfImgSnail = new LinkedList<>();
  private LinkedList<ImageGuppy> listOfImgGuppy = new LinkedList<>();
  private LinkedList<ImagePiranha> listOfImgPiranha = new LinkedList<>();
  private LinkedList<ImageFood> listOfImgFood = new LinkedList<>();
  private LinkedList<ImageCoin> listOfImgCoin = new LinkedList<>();
  private int eggState = 1;
  private int gameMoney;
  private int eggPrice;
  private int guppyPrice;
  private int piranhaPrice;
  private GameState gameState = GameState.MENU;

  /**
   * Creates a new view controller with the given width and height.
   *
   * @param width  width of the window
   * @param height height of the window
   */
  public ViewController(int width, int height) {
    // Initialize JFrame
    System.out.println(ABS_PATH);
    windowFrame = new JFrame();
    windowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    windowFrame.setSize(width, height);
    windowFrame.setVisible(true);
    windowFrame.setContentPane(this);
  }

  /**
   * Returns JFrame window the controller has.
   *
   * @return window frame
   */
  public JFrame getWindowFrame() {
    return this.windowFrame;
  }

  /**
   * Updates the list of objects for the view controller from model to be drawn.
   *
   * @param listOfGuppy   list of guppy
   * @param listOfPiranha list of piranha
   * @param listOfSnail   list of snail
   * @param listOfFood    list of food
   * @param listOfCoin    list of coin
   */
  public void setListOfObjects(
          LinkedList<Guppy> listOfGuppy,
          LinkedList<Piranha> listOfPiranha,
          LinkedList<Snail> listOfSnail,
          LinkedList<Food> listOfFood,
          LinkedList<Coin> listOfCoin
  ) {
    if (this.listOfImgSnail.getSize() < listOfSnail.getSize()) {
      ImageSnail imgSnail = new ImageSnail();
      this.listOfImgSnail.add(imgSnail);
      this.listOfSnail = listOfSnail;
    }

    // Handling guppy
    if (this.listOfImgGuppy.getSize() < listOfGuppy.getSize()) {
      for (int i = 0; i < listOfGuppy.getSize(); i++) {
        boolean isExist = false;
        for (int j = 0; j < this.listOfImgGuppy.getSize(); j++) {
          if (listOfGuppy.get(i).getGuppyId() == this.listOfImgGuppy.get(j).getId()) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          ImageGuppy imgGuppy = new ImageGuppy(listOfGuppy.get(i));
          this.listOfImgGuppy.add(imgGuppy);
          break;
        }
      }
      this.listOfGuppy = listOfGuppy;
    }

    // Handling piranha
    if (this.listOfImgPiranha.getSize() < listOfPiranha.getSize()) {
      for (int i = 0; i < listOfPiranha.getSize(); i++) {
        boolean isExist = false;
        for (int j = 0; j < this.listOfImgPiranha.getSize(); j++) {
          if (listOfPiranha.get(i).getPiranhaId() == this.listOfImgPiranha.get(j).getId()) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          ImagePiranha imgPiranha = new ImagePiranha(listOfPiranha.get(i));
          this.listOfImgPiranha.add(imgPiranha);
          break;
        }
      }
      this.listOfPiranha = listOfPiranha;
    }

    // Handling food
    if (this.listOfImgFood.getSize() < listOfFood.getSize()) {
      for (int i = 0; i < listOfFood.getSize(); i++) {
        boolean isExist = false;
        for (int j = 0; j < this.listOfImgFood.getSize(); j++) {
          if (listOfFood.get(i).getFoodId() == this.listOfImgFood.get(j).getId()) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          ImageFood imgFood = new ImageFood(listOfFood.get(i));
          this.listOfImgFood.add(imgFood);
          break;
        }
      }
      this.listOfFood = listOfFood;
    }

    // Handling coin
    if (this.listOfImgCoin.getSize() < listOfCoin.getSize()) {
      for (int i = 0; i < listOfCoin.getSize(); i++) {
        boolean isExist = false;
        for (int j = 0; j < this.listOfImgCoin.getSize(); j++) {
          if (listOfCoin.get(i).getCoinId() == this.listOfImgCoin.get(j).getId()) {
            isExist = true;
            break;
          }
        }
        if (!isExist) {
          ImageCoin imgCoin = new ImageCoin(listOfCoin.get(i));
          this.listOfImgCoin.add(imgCoin);
          break;
        }
      }
      this.listOfCoin = listOfCoin;
    }
  }

  /**
   * Set current egg state.
   *
   * @param egg egg state
   */
  public void setEggState(int egg) {
    this.eggState = egg;
  }

  /**
   * Set current game state.
   *
   * @param state game state
   */
  public void setGameState(GameState state) {
    this.gameState = state;
  }

  /**
   * Set every price and current money to be shown later.
   *
   * @param guppyPrice    guppy price
   * @param piranhaPrice  piranha price
   * @param eggPrice      egg price
   * @param gameMoney     current money game
   */
  public void setGameCurrency(int guppyPrice, int piranhaPrice, int eggPrice, int gameMoney) {
    this.guppyPrice = guppyPrice;
    this.piranhaPrice = piranhaPrice;
    this.eggPrice = eggPrice;
    this.gameMoney = gameMoney;
  }

  /**
   * Read image from given path and return the image object.
   *
   * @param path image path
   */
  private Image readImage(String path) {
    ImageIcon icon = new ImageIcon(path);
    Image image = icon.getImage();
    return image;
  }

  /**
   * Renders game view according to game state.
   *
   * @param g graphics
   */
  public void renderGameState(Graphics g) {
    if (this.gameState == GameState.GAME) {
      renderGame(g);
    } else if (this.gameState == GameState.MENU) {
      renderMenu(g);
    } else if (this.gameState == GameState.WIN) {
      renderWin(g);
    } else if (this.gameState == GameState.LOSE) {
      renderLose(g);
    }
  }

  private void renderMenu(Graphics g) {
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/back/main.jpg"), 0, 0, this);
  }

  private void renderWin(Graphics g) {
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/back/win.jpg"), 0, 0, this);
  }

  private void renderLose(Graphics g) {
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/back/lose.jpg"), 0, 0, this);
  }

  private void renderGame(Graphics g) {
    // Draw every menu game element on screen
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/back/1.jpg"), 0, 0, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/back/menu.png"), 0, 0, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/util/slot.png"), 20, 0, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/util/slot.png"), 340, 0, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/util/slot.png"), 690, 0, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/guppy/small/left/healthy/1.png"), 50, 20, this);
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/piranha/left/healthy/1.png"), 355, 8, this);
    renderEgg(this.eggState + 1, g);

    // Draw every text on screen
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Comic Sans", Font.PLAIN, 15));
    g2.drawString(Integer.toString(this.guppyPrice), 60, 95);
    g2.drawString(Integer.toString(this.piranhaPrice), 383, 95);
    g2.drawString(Integer.toString(this.eggPrice), 730, 95);
    g2.drawString("$" + Integer.toString(this.gameMoney), 880, 85);

    // Draw every animated object on screen
    renderSnail(this.listOfSnail, g);
    renderGuppy(g);
    renderPiranha(g);
    renderFood(g);
    renderCoin(g);
  }

  private void renderEgg(int egg, Graphics g) {
    g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + "img/util/egg" + Integer.toString(egg) + ".png"), 690, 0, this);
  }

  private void renderSnail(LinkedList<Snail> listOfSnail, Graphics g) {
    for (int i = 0; i < this.listOfImgSnail.getSize(); i++) {
      Snail snail = listOfSnail.get(i);
      ImageSnail imgSnail = this.listOfImgSnail.get(i);
      g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + imgSnail.render(snail)), snail.getPosX(), snail.getPosY(), this);
    }
  }

  private void renderGuppy(Graphics g) {
    for (int i = 0; i < this.listOfGuppy.getSize(); i++) {
      Guppy guppy = this.listOfGuppy.get(i);
      ImageGuppy imgGuppy = this.listOfImgGuppy.get(i);
      if (guppy.getGuppyId() == imgGuppy.getId()) {
        g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + imgGuppy.render(guppy)), guppy.getPosX(), guppy.getPosY(), this);
      } else {
        this.listOfImgGuppy.remove(imgGuppy);
      }
    }
  }

  private void renderPiranha(Graphics g) {
    for (int i = 0; i < this.listOfPiranha.getSize(); i++) {
      Piranha piranha = this.listOfPiranha.get(i);
      ImagePiranha imgPiranha = this.listOfImgPiranha.get(i);
      if (piranha.getPiranhaId() == imgPiranha.getId()) {
        g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + imgPiranha.render(piranha)), piranha.getPosX(), piranha.getPosY(),
                this);
      } else {
        this.listOfImgPiranha.remove(imgPiranha);
      }
    }
  }

  private void renderFood(Graphics g) {
    for (int i = 0; i < this.listOfFood.getSize(); i++) {
      Food food = this.listOfFood.get(i);
      ImageFood imgFood = this.listOfImgFood.get(i);
      if (food.getFoodId() == imgFood.getId()) {
        g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + imgFood.render()), food.getPosX(), food.getPosY(), this);
      } else {
        this.listOfImgFood.remove(imgFood);
      }
    }
  }

  private void renderCoin(Graphics g) {
    for (int i = 0; i < this.listOfCoin.getSize(); i++) {
      Coin coin = this.listOfCoin.get(i);
      ImageCoin imgCoin = this.listOfImgCoin.get(i);
      if (coin.getCoinId() == imgCoin.getId()) {
        g.drawImage(readImage(ABS_PATH + PARENT_FOLDER + imgCoin.render()), coin.getPosX(), coin.getPosY(), this);
      } else {
        this.listOfImgCoin.remove(imgCoin);
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    renderGameState(g);
  }

}
