package Client.gui;

import Client.ams.ControllerObserverAMS;
import Service.ams.NotificationSubscriber;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Chira Paul on 3/18/2017.
 */
public class ControllerAngajat implements Initializable{

    @FXML
    private BorderPane bp;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button login;

    private ControllerObserverAMS ctr;

    private ControllerZbor ctrZbor;

    public void setCtr(ControllerObserverAMS ctr) {
        this.ctr = ctr;
    }

    public ControllerZbor getCtrZbor() {
        return ctrZbor;
    }

    public void login() throws IOException{
        String username = txt_username.getText();
        String password = txt_password.getText();

        try{
            ctr.login(username,password);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ControllerAngajat.class.getResource("ZborGUI.fxml"));
            BorderPane bp2 = (BorderPane)loader.load();
            ctrZbor = loader.getController();
            ctrZbor.setControllerObserverAMS(ctr);
            int id = ctr.getIdAngajat(username,password);
            //ctr.addObserver(ctrZbor);
            ctrZbor.setIdAngajat(ctr.getIdAngajat(username,password));
            bp.getChildren().setAll(bp2);
        }catch (Exception e){
            System.out.println(e);
        }


        /**if (ctr.validateAccount(username, password) == true){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.ControllerZborVechi.class.getResource("ZborGUI.fxml"));
            BorderPane bp2 = (BorderPane)loader.load();
            Controller.ControllerZborVechi ctrZbor = loader.getController();
            ctrZbor.setIdAngajat(ctr.getIdAngajat(username,password));
            ctrZbor.setControllerZbor();
            bp.getChildren().setAll(bp2);
        }else
        {
            showErrorMessage("invalid");
        }*/
    }

    public void showErrorMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
