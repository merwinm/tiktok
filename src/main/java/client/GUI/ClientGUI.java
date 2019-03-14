package client.GUI;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class ClientGUI extends Application {
    private Client client;
    private String host;
    private int port;
    private ClientController clientController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/client_gui_sheet.fxml"));
        Parent root = loader.load(fileInputStream);
        Scene scene = new Scene(root, 600, 400);

        clientController = loader.getController();

        primaryStage.setTitle("TicTac Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        Parameters param = getParameters();
        List<String> list = param.getRaw();
        port = Integer.parseInt(list.get(1));
        host = list.get(0);
        client = new Client(host,port,clientController); //passing the clientcontroller to the client works only if its passes via the constructor of the client
        clientController.setClient(client);
    }

}