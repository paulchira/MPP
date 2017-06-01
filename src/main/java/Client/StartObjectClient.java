package Client;

import Client.ams.ControllerObserverAMS;
import Client.gui.ControllerAngajat;
import Client.gui.ControllerObserver;
import Service.IServiceServer;
import Service.ams.IServiceServerAMS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StartObjectClient extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("spring-client.xml");
        //IServiceServerAMS server=(IServiceServerAMS)factory.getBean("serviceServer");
        ControllerObserverAMS ctrl=(ControllerObserverAMS)factory.getBean("ctrlObserver");
        System.out.println("Obtained a reference to remote chat server");
        //System.out.println(server.getAllZbor().length);


        //Parent root = FXMLLoader.aload(getClass().getResource("AngajatGUI_login.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AngajatGUI_login.fxml"));
        BorderPane bp = (BorderPane)loader.load();
        ControllerAngajat ctr = loader.getController();
        ctr.setCtr(ctrl);
        //ctrl.addObserver(ctr.getCtrZbor());
        Scene scene = new Scene(bp,700,500);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
