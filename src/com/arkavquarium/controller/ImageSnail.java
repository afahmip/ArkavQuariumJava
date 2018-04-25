package com.arkavquarium.controller;

import com.arkavquarium.fish.Direction;
import com.arkavquarium.pet.Snail;

/**
 * Represents the view of the snail.
 */
public class ImageSnail {

  private int imgState;

  public ImageSnail() {
    imgState = 1;
  }

  /**
   * Returns the image path of this snail.
   *
   * @param snail snail model
   * @return image path
   */
  public String render(Snail snail) {
    String imagePath;
    if (snail.getDirection() == Direction.LEFT) {
      imagePath = "img/snail/left/" + Integer.toString(imgState) + ".png";
      imgState++;
      if (imgState == 11) {
        imgState = 1;
      }
    } else {
      imagePath = "img/snail/right/" + Integer.toString(imgState) + ".png";
      imgState++;
      if (imgState == 11) {
        imgState = 1;
      }
    }
    return imagePath;
  }
}
