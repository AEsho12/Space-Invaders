package org.example;



public class Main {

    // main class for running game
    public static void main(String[] args) {
        Window window = Window.getWindow();

        Thread thread = new Thread(window);
        thread.start();

    }


}