package game.GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Sprite implements Serializable {
    private int value;
    private Image image;
    private int positonX;
    private int positonY;

    public Sprite(Image image,int x, int y,int value){
        this.image = image;
        this.positonX = x;
        this.positonY = y;
        this.value = value;
    }



    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void render(GraphicsContext gc,int x, int y){
        gc.drawImage(image,x,y);
    }

    public void setPositons(int x, int y ){
        this.positonX =x;
        this.positonY = y;
    }

    public int getXposition(){
        return this.positonX;
    }

    public int getYPositon(){
        return this.positonY;
    }

    public Sprite getSprite(){
        return this;
    }
}
