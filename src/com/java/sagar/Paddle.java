package com.java.sagar;

import java.awt.*;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private boolean left;
    private boolean right;

    public Paddle() {
        x = 350;
        y = 550;
        width = 100;
        height = 10;
        speed = 5;
    }

    public void move() {
        if (left && x > 0) {
            x -= speed;
        }
        if (right && x < 700 - width) {
            x += speed;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resetPosition() {
        x = 350;
        y = 550;
    }
}
