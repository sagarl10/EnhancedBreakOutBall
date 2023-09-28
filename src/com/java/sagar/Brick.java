package com.java.sagar;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 20;
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
}
