package com.arkavquarium.controller;

import com.arkavquarium.fish.Direction;
import com.arkavquarium.fish.Guppy;
import com.arkavquarium.fish.Hunger;

/**
 * Represents the view of the Guppy.
 */
public class ImageGuppy {

  private int imgLoopHealthy;
  private int imgLoopSick;
  private int id;

  /**
   * Creates a new Guppy view.
   *
   * @param guppy guppy model
   */
  public ImageGuppy(Guppy guppy) {
    imgLoopHealthy = 1;
    imgLoopSick = 1;
    id = guppy.getGuppyId();
  }

  public int getId() {
    return this.id;
  }

  /**
   * Returns the image path of this object from the given guppy model.
   *
   * @param guppy guppy model
   * @return image path
   */
  public String render(Guppy guppy) {
    // Change guppy phase
    String phase = "small";
    if (guppy.getLevel() == 1) {
      phase = "small";
    } else if (guppy.getLevel() == 2) {
      phase = "medium";
    } else if (guppy.getLevel() == 3) {
      phase = "large";
    }

    // Change guppy hunger status
    String imagePath;
    if (guppy.getHungerStatus() == Hunger.FULL) {
      if (guppy.getDirection() == Direction.RIGHT) {
        imagePath = "img/guppy/" + phase + "/right/healthy/" + Integer.toString(imgLoopHealthy)
                + ".png";
        imgLoopHealthy++;
        if (imgLoopHealthy == 11) {
          imgLoopHealthy = 1;
        }
      } else {
        imagePath =
                "img/guppy/" + phase + "/left/healthy/" + Integer.toString(imgLoopHealthy) + ".png";
        imgLoopHealthy++;
        if (imgLoopHealthy == 11) {
          imgLoopHealthy = 1;
        }
      }
    } else {
      if (guppy.getDirection() == Direction.RIGHT) {
        imagePath =
                "img/guppy/" + phase + "/right/sick/" + Integer.toString(imgLoopSick) + ".png";
        imgLoopSick++;
        if (imgLoopSick == 11) {
          imgLoopSick = 1;
        }
      } else {
        imagePath =
                "img/guppy/" + phase + "/left/sick/" + Integer.toString(imgLoopSick) + ".png";
        imgLoopSick++;
        if (imgLoopSick == 11) {
          imgLoopSick = 1;
        }
      }
    }

    return imagePath;
  }
}