package client.GUI;

import client.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import packets.chat.PacketchatMessage;
import packets.chat.PacketsetUsername;

public class ClientController {
    public Button send_button;
    public TextField chat_textfield;
    public TextField ip_text_field;
    public TextField port_textfield;
    private Client client;
    public TextArea text_area;
    public Button login_button;
    public TextField enter_user_name_field;
    public Button start_game_button;
    public String username;

    public void setClient(Client client){
        this.client = client;
    }

    public void login_action_button(ActionEvent actionEvent) {
        if(enter_user_name_field.getCharacters().length()==0){
            text_area.appendText("PLease enter a username");
        }

        else{
            this.username = enter_user_name_field.getCharacters().toString();
            client.setUsername(this.username + "\n");
            client.sendPacket(new PacketchatMessage(username + ": JOINED" ));
            client.sendPacket(new PacketsetUsername(username));
            Thread runner = new Thread(client);
            runner.start();
            System.out.println("Running");
        }
    }

    public void send_on_action(ActionEvent actionEvent) {

        if(chat_textfield.getCharacters().length()==0){
            text_area.appendText("Please enter a Message ");
        }

        else{
            client.sendPacket(new PacketchatMessage(enter_user_name_field.getText()));
            enter_user_name_field.clear();
        }
    }
    public void setchatlog(String message){
        text_area.appendText(message+"\n");
    }

    public void start_game_action(ActionEvent actionEvent) {
    }
}
