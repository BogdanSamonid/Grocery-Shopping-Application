package UserLogin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.security.auth.callback.ConfirmationCallback;
import java.awt.*;
import java.io.IOException;

public class UserLogin_Main extends Application{

    private Stage primaryWindow;
    private BorderPane mainLayout;

    private void showMainView() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(UserLogin_Main.class.getResource("view/UserLogin_fxmlfile.fxml"));
        mainLayout=loader.load();

        Scene scene=new Scene(mainLayout);

        primaryWindow.setScene(scene);
        primaryWindow.show();
    }

    public void start(Stage stage) throws Exception {
        this.primaryWindow=stage;
        this.primaryWindow.setTitle("User Login");

        showMainView();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
