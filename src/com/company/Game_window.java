package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Game_window  extends JFrame {

    private  static final int window_weigth = 1000;
    private  static final int window_height = 700;
    private  static final int drop_diam = 100;
    private  static Game_window game_window;
    private  static long last_frame_time;
    private  static Image background;
    private  static Image drop;
    private  static Image  game_over;
    private  static double drop_left = 200;
    private  static double drop_top = 200;
    private static  double top_speed = 200;
    private static  double left_speed = 200;
    private static  double max_speed = 400;
    private static  double top_speed_mod = 1;
    private static double left_speed_mod = 1;
    private  static  int score = 0;


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
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                double drop_right = drop_left+drop_diam;
                double drop_bottom = drop_top+drop_diam;
                boolean is_drop = (x>=drop_left && x <= drop_right && y >= drop_top && y<= drop_bottom);
                if (is_drop){
                    drop_top=(int)(Math.random()*(window_height/10*8));
                    drop_left=(int)(Math.random()*(window_weigth/10*8));
                    left_speed=(Math.random()*max_speed+250);
                    top_speed=Math.random()*max_speed+250;
                    score=score+5;
                }else {
                    score=score-2;
                }
                game_window.setTitle("Score: "+score);

            }
        });

        game_window.add(gameField);

        game_window.setVisible(true);

    }

    private static void onRepaint(Graphics graphics){
        long parent_time =System.nanoTime();
        float delta_time = (parent_time-last_frame_time) * 0.000000001f;
        last_frame_time = parent_time;


        drop_top=drop_top+top_speed*delta_time*top_speed_mod;
        drop_left=drop_left+left_speed*delta_time*left_speed_mod;


        if (drop_top>window_height-drop_diam||drop_top<0){
           top_speed_mod=-top_speed_mod;
           top_speed=Math.random()*max_speed+250;
            left_speed=Math.random()*max_speed+250;
            score--;
        }
        if(drop_left<0||drop_left>window_weigth-drop_diam){
            left_speed_mod=-left_speed_mod;
            left_speed=Math.random()*max_speed+250;
            top_speed=Math.random()*max_speed+250;
            score--;
        }

        graphics.drawImage(background, 0 , 0 , null);
        graphics.drawImage(drop, (int)drop_left, (int)drop_top, drop_diam,drop_diam,null);
        game_window.setTitle("Score: "+score);
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
