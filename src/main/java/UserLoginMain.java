import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserLoginMainView.fxml"));
        primaryStage.setTitle("Grocery Shopping Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

   /* private void showMainView() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(UserLogin_Main.class.getResource("/view/UserLoginMainView.fxml"));

        mainLayout=loader.load();

        Scene scene=new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();


    }*/

}
