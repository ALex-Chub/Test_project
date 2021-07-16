package com.company;
import javax.swing.*;

public class Game_window  extends JFrame {

    private  static Game_window game_window;

    public static void main(String[] args) {
        game_window = new Game_window();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 150);
        game_window.setSize(800,600);
        game_window.setResizable(false);

        game_window.setVisible(true);

    }
}
