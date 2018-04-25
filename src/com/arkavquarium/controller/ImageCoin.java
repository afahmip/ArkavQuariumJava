package com.arkavquarium.controller;

import com.arkavquarium.item.Coin;

/**
 * Represents the coin view.
 */
public class ImageCoin {

  private int imgState;
  private int id;

  /**
   * Creates a new coin view with the given coin model.
   *
   * @param coin coin model.
   */
  public ImageCoin(Coin coin) {
    this.imgState = 1;
    this.id = coin.getCoinId();
  }

  /**
   * Returns the image path of the object with the given coin input.
   *
   * @return image path
   */
  public String render() {
    String imagePath;
    imagePath = "img/coin/" + Integer.toString(imgState) + ".png";
    imgState++;
    if (imgState == 11) {
      imgState = 1;
    }
    return imagePath;
  }

  public int getId() {
    return this.id;
  }
}
