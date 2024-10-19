package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private BufferedImage playerImage;
    private double x, y;  // Player position
    private double speed;  // Player movement speed
    private ArrayList<Bullet> bullets;
    private boolean spaceKeyReleased = true;
    private double bulletSpeed = 500;
    private int width;
    private int height;
    private double maxWidth;
    private double maxHeight;




    public Player(double x, double y, double speed, String imagePath, double maxWidth) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.bullets = new ArrayList<>();
        this.width = width;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;




        try {
            playerImage = ImageIO.read(new File(imagePath));

            this.x = (Constants.SCREEN_WIDTH - playerImage.getWidth()) / 1;

            // Place the player at the bottom of the screen
            this.y = Constants.SCREEN_HEIGHT - playerImage.getHeight() / 5;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(double dt, KL keyListener) {
        // Update player position based on key input
        if (keyListener.isKeyPressed(KeyEvent.VK_LEFT) && x - speed * dt >= 0) {
            x -= speed * dt;
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT) && x + speed * dt * 100 >= 0) {
            x += speed * dt;
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_UP) && y - speed * dt >= 0) {
            y -= speed * dt;
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN) && y + speed * dt >= 0) {
            y += speed * dt;
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (spaceKeyReleased) {
                shoot();
                spaceKeyReleased = false;
            }
        } else {
            spaceKeyReleased = true;
        }

        // Update bullet positions
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update(dt);
            if (bullet.getY() < 0) {
                // Remove bullets that go off-screen
                iterator.remove();
            }
        }

    }

    public void draw(Graphics g, int scaledWidth, int scaledHeight) {
        // Draw the player image at its current position
        g.drawImage(playerImage, (int) x, (int) y, scaledWidth, scaledHeight, null);

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    private void shoot() {
        // Create a new bullet and add it to the list
        Bullet bullet = new Bullet(x + playerImage.getWidth() / 20, y - 100, bulletSpeed);
        bullets.add(bullet);
        System.out.println("Bullet created at: " + bullet.getX() + ", " + bullet.getY());
    }
}
