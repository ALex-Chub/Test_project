package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game_window  extends JFrame {

    private  static Game_window game_window;
    private  static long last_frame_time;
    private  static Image background;
    private  static Image drop;
    private  static Image  game_over;
    private  static float drop_left = 200;
    private  static float drop_top = -200;
    private static  float speed = 200;


    public static void main(String[] args) throws IOException {

        background = ImageIO.read(Game_window.class.getResourceAsStream("back.jpg"));
        drop = ImageIO.read(Game_window.class.getResourceAsStream("drop.png"));
        game_window = new Game_window();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 150);
        game_window.setSize(800,600);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        GameField gameField = new GameField();
        game_window.add(gameField);

        game_window.setVisible(true);

    }

    private static void onRepaint(Graphics graphics){
        long parent_time =System.nanoTime();
        float delta_time = (parent_time-last_frame_time) * 0.000000001f;
        last_frame_time = parent_time;

        drop_top=drop_top+speed*delta_time;



        graphics.drawImage(background, 0 , 0 , null);
        graphics.drawImage(drop, (int)drop_left, (int)drop_top, 100,100,null);

    }

    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            onRepaint(graphics);
            repaint();
        }

    }
}
