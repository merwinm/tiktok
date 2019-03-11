package main;

import client.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import server.Server;

import java.awt.*;

public class main extends Application {

    public static void main(String[] args) {

        int port = 6000;
        String host = "192.168.0.6";

        if (args[0].equals("server")) {
            Server server = new Server(port);
            server.runServer();
            launch(args);
        } else {
            Client client = new Client(host, port);
            client.run();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

