package com.arkavquarium.controller;

import com.arkavquarium.item.Food;

/**
 * Represents the view of the food.
 */
public class ImageFood {

  private int imgState;
  private int id;

  /**
   * Creates a new food view.
   *
   * @param food food model.
   */
  public ImageFood(Food food) {
    imgState = 1;
    id = food.getFoodId();
  }

  /**
   * Returns the image path of the object with the current food model input.
   *
   * @param food food model
   * @return image path
   */
  public String render() {
    String imagePath;
    imagePath = "img/food/" + Integer.toString(imgState) + ".png";
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
