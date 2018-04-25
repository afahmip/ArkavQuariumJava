package com.arkavquarium.controller;

import com.arkavquarium.fish.Direction;
import com.arkavquarium.fish.Hunger;
import com.arkavquarium.fish.Piranha;

/**
 * Represents the view of the piranha.
 */
public class ImagePiranha {

  private int imgLoopHealthy;
  private int imgLoopSick;
  private int id;


  /**
   * Creates a new piranha view.
   *
   * @param piranha piranha model.
   */
  public ImagePiranha(Piranha piranha) {
    imgLoopHealthy = 1;
    imgLoopSick = 1;
    id = piranha.getPiranhaId();
  }

  public int getId() {
    return this.id;
  }

  /**
   * Returns the image path of this piranha according to the state of the piranha.
   *
   * @param piranha piranha model
   * @return image path
   */
  public String render(Piranha piranha) {
    // Change piranha hunger status
    String imagePath;
    if (piranha.getHungerStatus() == Hunger.FULL) {
      if (piranha.getDirection() == Direction.RIGHT) {
        imagePath =
                "img/piranha/" + "right/healthy/" + Integer.toString(imgLoopHealthy) + ".png";
        imgLoopHealthy++;
        if (imgLoopHealthy == 11) {
          imgLoopHealthy = 1;
        }
      } else {
        imagePath =
                "img/piranha/" + "left/healthy/" + Integer.toString(imgLoopHealthy) + ".png";
        imgLoopHealthy++;
        if (imgLoopHealthy == 11) {
          imgLoopHealthy = 1;
        }
      }
    } else {
      if (piranha.getDirection() == Direction.RIGHT) {
        imagePath = "img/piranha/" + "right/sick/" + Integer.toString(imgLoopSick) + ".png";
        imgLoopSick++;
        if (imgLoopSick == 11) {
          imgLoopSick = 1;
        }
      } else {
        imagePath = "img/piranha/" + "left/sick/" + Integer.toString(imgLoopSick) + ".png";
        imgLoopSick++;
        if (imgLoopSick == 11) {
          imgLoopSick = 1;
        }
      }
    }

    return imagePath;
  }
}
