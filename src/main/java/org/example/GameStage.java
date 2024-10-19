package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// Stage for Game
public class GameStage extends Stage {

    // List for enemies to allow multiple together
    private List<Enemy> enemies = new ArrayList<>();

    // List for Bullets firing
    private List<Bullet> bullets = new ArrayList<>();

    // importing player
    private Player player;

    // importing key movement
    private KL keyListener;

    // importing window
    private Window window;

    // import for lives
    private BufferedImage background, lives;

    // for displaying images
    private Rect backgroundRect, livesRect, livesRect2, livesRect3;

    public GameStage() {
        keyListener = new KL(); // Initialize keyListener
        this.window = Window.getWindow();
        Window.getWindow().addKeyListener(keyListener); // Register keyListener with the window
        // player function with x and y axis for sizing and spped for player with image location
        player = new Player(600,800,200,  "assets/spaceship2.png",100);




        try {
            // importing images
            BufferedImage backgroundsheet = ImageIO.read(new File("assets/GameBackground.png"));
            BufferedImage livessheet = ImageIO.read(new File ("assets/lives.png"));
            background = backgroundsheet.getSubimage(0,0,800,600);
            lives = livessheet.getSubimage(0,0,200,150);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // images position and sizing
        backgroundRect = new Rect(0,0,800,600);
        livesRect = new Rect(650,40,40,30);
        livesRect2 = new Rect(690,40,40,30);
        livesRect3 = new Rect(730,40,40,30);

        int startX = 50; // enemy x axis
        int startY = 40; // enemy y axis
        double speed = 8.5;  // enemy speed
        int rows = 3; // Number of rows of enemies
        int cols = 10; // Number of columns of enemies
        int spacingX = 50; // enemy spacing for x axis
        int spacingY = 40; // enemy spacing for y axis


        // importing different enemies
        String[] enemyImagePaths = {
                "assets/GameEnemy2.png",
                "assets/GameEnemy.png",
                "assets/GameEnemy3.png"
        };

        // loop for enemies
        for (int row = 0; row < rows; row++) {
            String imagePath = enemyImagePaths[row % enemyImagePaths.length];
            for (int col = 0; col < cols; col++) {
                enemies.add(new Enemy(startX + col * spacingX, startY + row * spacingY, speed, imagePath));
            }
        }
    }

    @Override
    public void update(double dt) {
        player.update(dt, keyListener); // update for player movement
        updateEnemies(dt); // update for enemies
        updateBullets(dt); // update for bullets
    }

    // method for creation enemies block on edge of screen
    private void updateEnemies(double dt) {
        for (Enemy enemy : enemies) {
            enemy.update(dt);

            double maxX = 700 - enemy.getWidth(); // break point set
            double minX = 0;

            // Check if the enemy reaches the edge and change direction
            if (enemy.getX() >= maxX || enemy.getX() <= minX) {
                enemy.reverseDirection();
                enemy.setY(enemy.getY() + 20); //  vertical shift
            }
        }

    }

    private void updateBullets(double dt) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update(dt);

            // Check for collisions with enemies
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                Rect bulletRect = new Rect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
                Rect enemyRect = new Rect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

                if (bulletRect.intersects(enemyRect)) {
                    bulletIterator.remove();
                    enemyIterator.remove(); // remove enemy if collision
                    window.increaseScore(10); // increase score if collision is successfully
                    System.out.println("Collision detected!"); // return statement is collision is successfully
                }
            }
        }
    }





    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


        g.drawImage(background,(int)backgroundRect.x, (int)backgroundRect.y,(int)backgroundRect.width, (int)backgroundRect.height, null);
        player.draw(g, 60, 60);
        for(Enemy enemy : enemies){
            enemy.draw(g, 120,120);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        if(window != null){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + window.getScore(), 15, 60);
        }
        if(window != null){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Lives: "  , 590, 60);
        }
        g.drawImage(lives,(int)livesRect.x, (int)livesRect.y,(int)livesRect.width, (int)livesRect.height, null);
        g.drawImage(lives,(int)livesRect2.x, (int)livesRect2.y,(int)livesRect2.width, (int)livesRect2.height, null);
        g.drawImage(lives,(int)livesRect3.x, (int)livesRect3.y,(int)livesRect3.width, (int)livesRect3.height, null);



    }
}
