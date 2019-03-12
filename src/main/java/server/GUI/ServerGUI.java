package server.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class ServerGUI extends Application {

    static private ServerController serverController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/server_gui_sheet.fxml"));
        Parent root = loader.load(fileInputStream);
        Scene scene = new Scene(root, 600, 400);

        serverController = loader.getController();

        primaryStage.setTitle("TicTac Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     public static ServerController getServerController(){
        return serverController;
    }
}
