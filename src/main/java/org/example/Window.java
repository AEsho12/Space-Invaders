package org.example;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    private int score = 0;

    public static Window window = null;

    public boolean isRunning;
    public int currentState;
    public Stage currentStage;

    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Window(int width, int height, String title){
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);


        isRunning = true;
        changeState(0);
    }

    public static Window getWindow(){
        if(Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }

        return Window.window;
    }

    public void close(){
        isRunning = false; // foe exiting game method

    }

    // changing game stage from menu to game
    public void changeState(int newState){
        currentState = newState;
        switch(currentState){
            case 0:
                currentStage = new MenuStage(keyListener,mouseListener);
                break;
            case 1:
                currentStage = new GameStage();
                break;
            default:
                System.out.println("Unknown Stage");
                currentStage = null;
                break;

        }
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentStage.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentStage.draw(g);
    }

    public void increaseScore(int points){
        score += points;
    } // scoring method

    public int getScore(){
        return score;
    }


    @Override
    public void run() {
        double lastFrameTime = 0.0;
        try{
            while (isRunning){
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        } catch(Exception e){
            e.printStackTrace();
        }



    }
}
