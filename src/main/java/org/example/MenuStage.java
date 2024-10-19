package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class MenuStage extends Stage {

    public KL keyListener;
    public ML mouseListener;
    public BufferedImage  title,title2,alien, play, exit, playPressed, exitPressed, invador,web, enemy, spaceship, stars, background;
    public Rect  titleRect,title2Rect, alienRect,playRect, exitRect, invadorRect, webRect, enemyRect, spaceshipRect, starsRect, invador2Rect, backgroundRect;
    public BufferedImage playCurrentImage, exitCurrentImage;
    public BufferedImage backgroundImage;
    private Clip backgroundMusic;



    public MenuStage(KL keyListener, ML mouseListener){
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        // importing images for menu scene
        try{
            backgroundImage = ImageIO.read(new File("assets/background.png"));
            BufferedImage titlesheet = ImageIO.read(new File("assets/title.png"));
            BufferedImage title2sheet = ImageIO.read(new File("assets/newTitle.png"));
            BufferedImage introsheet = ImageIO.read(new File("assets/startGame.png"));
            BufferedImage exitsheet = ImageIO.read(new File("assets/exitPressed.png"));
            BufferedImage playPressedsheet = ImageIO.read(new File("assets/StartGamePressed.png"));
            BufferedImage exitPressedsheet = ImageIO.read(new File("assets/exit.png  "));
            BufferedImage invadorsheet = ImageIO.read(new File("assets/invador1.png"));
            BufferedImage websheet = ImageIO.read(new File("assets/spaceweb.png"));
            BufferedImage enemysheet = ImageIO.read(new File("assets/enemyMain.png"));
            BufferedImage spaceshipsheet = ImageIO.read(new File("assets/spaceship2.png"));
            BufferedImage starssheet = ImageIO.read(new File("assets/stars.png"));
            BufferedImage backgroundsheet = ImageIO.read(new File("assets/background.png"));
            BufferedImage aliensheet = ImageIO.read(new File("assets/alienMain.png"));
            title = titlesheet.getSubimage(0,0,230,400);
            invador = invadorsheet.getSubimage(0,0,360,360);
            spaceship =  spaceshipsheet.getSubimage(0,0,200,200);
            stars =  starssheet.getSubimage(0,0,300,300);
            enemy = enemysheet.getSubimage(0,0,800,800);
            web = websheet.getSubimage(0,0,360,360);
            title2 = title2sheet.getSubimage(0,0,1200,600);
            play = introsheet.getSubimage(0,0,340,360);
            exit =  exitsheet.getSubimage(0,0,500,210);
            playPressed =  playPressedsheet.getSubimage(0,0,340,360);
            exitPressed =  exitPressedsheet.getSubimage(0,0,500,210);
            background =  backgroundsheet.getSubimage(0,0,700,500);
            alien =  aliensheet.getSubimage(0,0,800,600);
            File musicFile = new File("assets/music.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInput);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

        playCurrentImage = play;
        exitCurrentImage = exit;

        // images location and sizing
        titleRect = new Rect(260, 40,200,110);
        playRect = new Rect(290,250,250,250);
        exitRect = new Rect(310, 450, 200,100);
        title2Rect = new Rect(220, 80, 400, 220);
        invadorRect = new Rect(190,230,100,100);
        invador2Rect = new Rect(550,230,100,100);
        enemyRect = new Rect(-300,-300,500,500);
        webRect = new Rect(360,5,300,300);
        spaceshipRect = new Rect(400,350,300,300);
        starsRect = new Rect(360,200,300,300);
        backgroundRect = new Rect(0,0,800,600);
        alienRect = new Rect(-200,200,800,600);


    }

    // start game onclick method
    @Override
    public void update(double dt) {
        if(mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width &&
                mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height ) {
            playCurrentImage = playPressed;
            if(mouseListener.isPressed()){
                Window.getWindow().changeState(1);
            }
        } else {
            playCurrentImage = play;
        }
        // exit game onclick method

        if(mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width &&
                mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height ) {
            exitCurrentImage = exitPressed;
            if(mouseListener.isPressed()){
                Window.getWindow().close();
            }
        } else {
            exitCurrentImage = exit;
        }


    }

    // drawing images onto game menu screen
    @Override
    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


        g.drawImage(background,(int)backgroundRect.x, (int)backgroundRect.y,(int)backgroundRect.width, (int)backgroundRect.height, null);
        g.drawImage(title2,(int)title2Rect.x, (int)title2Rect.y,(int)title2Rect.width, (int)title2Rect.height, null);
        g.drawImage(playCurrentImage,(int)playRect.x, (int)playRect.y,(int)playRect.width, (int)playRect.height, null);
        g.drawImage(exitCurrentImage,(int)exitRect.x, (int)exitRect.y,(int)exitRect.width, (int)exitRect.height, null);
        g.drawImage(title,(int)titleRect.x, (int)titleRect.y,(int)titleRect.width, (int)titleRect.height, null);
        g.drawImage(invador,(int)invadorRect.x, (int)invadorRect.y,(int)invadorRect.width, (int)invadorRect.height, null);
        g.drawImage(invador,(int)invador2Rect.x, (int)invador2Rect.y,(int)invador2Rect.width, (int)invador2Rect.height, null);
        g.drawImage(web,(int)webRect.x, (int)webRect.y,(int)webRect.width, (int)webRect.height, null);
        g.drawImage(enemy,(int)enemyRect.x, (int)enemyRect.y,(int)enemyRect.width, (int)enemyRect.height, null);
        g.drawImage(spaceship,(int)spaceshipRect.x, (int)spaceshipRect.y,(int)spaceshipRect.width, (int)spaceshipRect.height, null);
        g.drawImage(stars,(int)starsRect.x, (int)starsRect.y,(int)starsRect.width, (int)starsRect.height, null);
        g.drawImage(alien,(int)alienRect.x, (int)alienRect.y,(int)alienRect.width, (int)alienRect.height, null);

    }
}
