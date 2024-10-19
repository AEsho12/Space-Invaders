package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {

    // Creating bullet speed
    private static final int SPEED = 500;  // Bullet speed

    // BulletImage
    private static BufferedImage bulletImage;

    // Bullet width
    private int width = bulletImage.getWidth();
    // Bullet height
    private int height = bulletImage.getHeight(); // Add this line

    // x and y axis for bullet
    private double x, y;  // Bullet position

    // Static method to for game to read bulletImage
    static {
        try {
            // Reading bullet image from file location path in assets location
            bulletImage = ImageIO.read(new File("assets/Bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    // Bullet method
    public Bullet(double x, double y, double bulletSpeed) {
        this.x = Math.max(0, Math.min(x, Constants.SCREEN_WIDTH - bulletImage.getWidth()));
        this.y = y;
        this.width = bulletImage.getWidth();
        this.height = bulletImage.getHeight();
    }

    public void update(double dt) {
        // Update bullet position
        y -= SPEED * dt;
    }

    // Drawing bullet
    public void draw(Graphics g) {
        // Draw the bullet image at its current position
        g.drawImage(bulletImage, (int) x, (int) y, null);
    }


    // Returning y axis for the bullet
    public final double getY() {
        return y;
    }

    // Returning x axis for the bullet

    public final double getX(){
        return x;
    }

    // Returning width for the bullet
    public double getWidth() {
        return width;
    }

    // Returning Height for the bullet
    public double getHeight() {
        return height;
    }

    public Rect getRect() {
        return new Rect(x,y,width,height);
    }


    // Method for bullet intersects with enemy
    public boolean intersects(Rect other) {
        return this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y;
    }
}
