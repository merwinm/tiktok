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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class GameGUI extends Application {

    static final int WIDTH = 300;
    static final int HEIGHT = 300;
    public static boolean symbol;
    public static Scene scene;
    public static Stage classStage = new Stage();
    public static GraphicsContext gc;
    public static Logger logger = Logger.getLogger("GameGUI Logger");
    public static Image cross;
    public static Image circle;
    public static int posx;
    public static int posy;

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
        classStage.show();
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                posx = (int) (event.getX()/100)*100;
                posy = (int) (event.getY()/100)*100;
                logger.info("Mousepos X: "+ posx + "Y: "+ posy);
            }
        });
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

        try {
            FileInputStream fileInputStream1 = new FileInputStream(new File("src/main/resources/Sprites/circle.png"));
            FileInputStream fileInputStream2 = new FileInputStream(new File("src/main/resources/Sprites/cross.png"));
            cross = scale(new Image(fileInputStream2),100,100,true);
            circle = scale(new Image(fileInputStream1),100,100,true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(symbol)
                gc.drawImage(cross,posx,posy);
                else gc.drawImage(circle,posx,posy);
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
}
