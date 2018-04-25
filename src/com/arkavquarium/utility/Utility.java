package com.arkavquarium.utility;

/**
 * Utility class is a class that contains static methods to help calculating things more easily.
 *
 * <p> This includes:
 * <ul>
 * <li>Calculating Euclidean distance from one point to another</li>
 * <li>Determining movement direction </li>
 * <li>Changing object position based on the radian the object is facing</li>
 * </ul>
 * </p>
 *
 * @author Adylan Roaffa
 * @version 1.1
 */
public class Utility {

  /**
   * Returns the Euclidean distance using the formula sqrt( (x2-x1)^2 + (y2-y1)^2 ).
   *
   * @param x1 horizontal position of point 1
   * @param y1 vertical position of point 1
   * @param x2 horizontal position of point 2
   * @param y2 vertical position of point 2
   * @return Euclidean distance between the defined points
   */
  public static double getDistanceObj(int x1, int y1, int x2, int y2) {
    double dx = (x2 - x1) * (x2 - x1);
    double dy = (y2 - y1) * (y2 - y1);

    return Math.sqrt(dx + dy);
  }

  /**
   * Returns the angle in radian from the defined two points relative to the first points.
   *
   * @param x1 horizontal position of point 1
   * @param y1 vertical position of point 1
   * @param x2 horizontal position of point 2
   * @param y2 vertical position of point 2
   * @return angle in radian
   */
  public static double getMovementDirection(int x1, int y1, int x2, int y2) {
    int dx = (x2 - x1);
    int dy = (y2 - y1);
    return Math.atan2(dy, dx);
  }

  /**
   * Change the horizontal position value based on the moving direction and speed.
   *
   * @param x   horizontal position
   * @param v   speed
   * @param rad moving direction in radian
   * @param t   time
   * @return new horizontal position
   */
  public static double moveXByRadian(int x, double v, double rad, double t) {
    return x + (v * Math.cos(rad) * t);
  }

  /**
   * Change the vertical position value based on the moving direction and speed.
   *
   * @param y   vertical position
   * @param v   speed
   * @param rad moving direction in radian
   * @param t   time
   * @return new vertical position
   */
  public static double moveYByRadian(int y, double v, double rad, double t) {
    return y + (v * Math.sin(rad) * t);
  }
}
