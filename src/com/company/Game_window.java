package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game_window  extends JFrame {

    private  static int window_weigth = 800;
    private  static int window_height = 700;
    private  static int drop_diam = 100;
    private  static Game_window game_window;
    private  static long last_frame_time;
    private  static Image background;
    private  static Image drop;
    private  static Image  game_over;
    private  static float drop_left = 200;
    private  static float drop_top = 200;
    private static  float speed = 200;
    private static  int top_speed_mod = 1;
    private static int left_speed_mod = 1;


    public static void main(String[] args) throws IOException {

        background = ImageIO.read(Game_window.class.getResourceAsStream("back.jpg"));
        drop = ImageIO.read(Game_window.class.getResourceAsStream("drop.png"));
        game_window = new Game_window();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 150);
        game_window.setSize(window_weigth,window_height);
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


        drop_top=drop_top+speed*delta_time*top_speed_mod;
        drop_left=drop_left+speed*delta_time*left_speed_mod;


        if (drop_top>window_height-drop_diam){
           top_speed_mod=0-top_speed_mod;
        }
        if (drop_top<0){
            top_speed_mod=0-top_speed_mod;
        }
        if(drop_left<0){
            left_speed_mod=0-left_speed_mod;
        }
        if(drop_left>window_weigth-drop_diam){
            left_speed_mod=0-left_speed_mod;
        }


        graphics.drawImage(background, 0 , 0 , null);
        graphics.drawImage(drop, (int)drop_left, (int)drop_top, drop_diam,drop_diam,null);

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
