package com.java.sagar;

import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int radius;
    private int xSpeed;
    private int ySpeed;

    public Ball() {
        x = 390;
        y = 290;
        radius = 10;
        xSpeed = 1;
        ySpeed = 1;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        // Ball collisions with walls
        if (x <= 0 || x >= 780) {
            xSpeed = -xSpeed;
        }
        if (y <= 0) {
            ySpeed = -ySpeed;
        }
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void resetPosition() {
        x = 390;
        y = 290;
        xSpeed = 1;
        ySpeed = 1;
    }
}
