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
import server.Server;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class ServerGUI extends Application {

    private String host;
    private int port;
    private Server server;

    private ServerController serverController;

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
        Parameters param = getParameters();
        List<String> list = param.getRaw();
        port = Integer.parseInt(list.get(1));
        host = list.get(0);

        server = new Server(port,serverController);
        serverController.setServer(server);

    }
}
