package org.example;

// Import list

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Class
public class Enemy {

    // x and y axis for position for enemies
    public double x,y;

    // enemies speed
    public double speed;

    // For uploading chosen enemy image
    private BufferedImage enemyImage;

    // Enemy width set in integers
    private int width;
    // Enemy height set in integers
    private int height;

    // Setting enemy direction
    private double direction = 4;

    // Method for grabbing a and y axis and speed for Enemy movement
    public Enemy(double x, double y, double speed, String imagePath){
        this.x = x;
        this.y = y;
        this.speed = speed;

        try {
            // Reading enemy image
            enemyImage = ImageIO.read(new File(imagePath));
            // Return output is image is successfully loading
            System.out.println("Image loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(double dt){
        x += speed * direction * dt;
    }

    // Drawing enemy image, includes x and y axis and width and height
    public void draw(Graphics g, int scaledWidth, int scaledHeight){
        g.drawImage(enemyImage, (int) x, (int) y, scaledWidth, scaledHeight, null);

    }

    // Returning x axis for the enemies
    public double getX() {
        return x;
    }

    // Setting x axis
    public void setX(double x) {
        this.x = x;
    }

    // Returning y axis for the enemies
    public double getY() {
        return y;
    }

    // Setting y axis
    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    // Setting width
    public double getWidth() {
        return width;
    }

    // Setting height
    public double getHeight() {
        return height;
    }

    // Setting direction for enemies when the reach certain break point on screen
    public void reverseDirection() {
        direction *= -1;
    }
}
