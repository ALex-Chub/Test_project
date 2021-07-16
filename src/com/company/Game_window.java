package com.company;
import javax.swing.*;
import java.awt.*;

public class Game_window  extends JFrame {

    private  static Game_window game_window;

    public static void main(String[] args) {
        game_window = new Game_window();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 150);
        game_window.setSize(800,600);
        game_window.setResizable(false);
        GameField gameField = new GameField();
        game_window.add(gameField);

        game_window.setVisible(true);

    }

    private static void onRepaint(Graphics graphics){
        graphics.fillOval(100,20, 200, 200);

    }

    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            onRepaint(graphics);
        }

    }
}
