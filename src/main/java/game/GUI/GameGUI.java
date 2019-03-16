package game.GUI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.GUI.GameLogic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class GameGUI extends Application {

    static final int WIDTH = 300;
    static final int HEIGHT = 300;
    public static boolean symbol;
    public static Scene scene;
    public static Stage classStage = new Stage();
    public static GraphicsContext gc;
    public static Logger logger = Logger.getLogger("GameGUI Logger");
    public static int posx;
    public static int posy;
    public static int arrayposx;
    public static int arrayposy;
    public static FileInputStream fileInputStream1;
    public static FileInputStream fileInputStream2;
    public static int[][] spritelist;


    @Override
    public void start(Stage primaryStage) {
        classStage = primaryStage;
        classStage.setTitle("Tic Tac Toe");
        Group root = new Group();
        scene = new Scene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        drawGrid(gc);
        root.getChildren().add(canvas);
        classStage.setScene(scene);
        setModel();
        spritelist = new int[3][3];
        classStage.show();
        handleMouseInput();
        animate();

    }

    private void drawGrid(GraphicsContext gc) {

        int step = WIDTH/3;
        for (int x = 0; x < WIDTH ; x = x + step){
            gc.moveTo(x, 0);
            gc.lineTo(x, HEIGHT);
            gc.stroke();
        }
        step = HEIGHT/3;
        for (int y = 0; y < HEIGHT ; y = y + step){
            gc.moveTo(0, y);
            gc.lineTo(WIDTH, y);
            gc.stroke();
        }
    }

    public void animate(){

        Sprite circle = new Sprite(scale(new Image(fileInputStream1),100,100,true),posx,posy,1); //This object is only for rendering the circkes, it has nothin to do with arraylist

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                circle.render(gc,posx,posy);

            }
        }.start();

    }

    public Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
        ImageView imageView = new ImageView(source);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        return imageView.snapshot(null, null);
    }

    public void setModel() {

        try {
            fileInputStream1 = new FileInputStream(new File("src/main/resources/img/circle.png"));
            fileInputStream2 = new FileInputStream(new File("src/main/resources/img/cross.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleMouseInput(){
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                posx = (int) (event.getX()/100)*100;
                posy = (int) (event.getY()/100)*100;
                logger.info("Mousepos X: "+ posx + "Y: "+ posy);
                Image image = scale(new Image(fileInputStream1),100,100,true);
                Sprite circle = new Sprite(image,posx,posy,1);
                addToSpriteList(circle,posx,posy);
            }
        });
    }

    public void addToSpriteList(Sprite sprite,int x, int y){
            arrayposx= x/100;
            arrayposy = y/100;
            spritelist[arrayposy][arrayposx] = sprite.getValue();
            logger.info(Arrays.deepToString(spritelist));
    }


}
