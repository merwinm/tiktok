package server.GUI;

import game.GUI.Sprite;

import java.util.Arrays;

public class GameLogic {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 300;
    private int value;
    private int[][]spritelist;

    public GameLogic(){
        spritelist = new int[HEIGHT/100][WIDTH/100];
    }

    public void addToSpriteList(int[][]spritelist, int x, int y,int value){
        spritelist[y][x] = value;
    }


    public boolean validate(int[][]spritelist){
        int counterRow = 0;
        int counterCol = 0;

       for(int x = 0; x<3;x++){
           for(int y = 0; y < 3; y++){
               if(spritelist[x][y]==1){
                   counterRow++;
               }
               if(counterRow ==3){
                   return true;
               }

           }
           counterRow = 0;

       }

        for(int y = 0; y<3;y++){
            for(int x = 0; x < 3; x++){
                if(spritelist[x][y]==1){
                    counterCol++;
                }
                if(counterCol ==3){
                    return true;
                }

            }
            counterCol = 0;

        }
        return false;
    }
}
