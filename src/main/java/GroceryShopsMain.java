import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GroceryShopsMain extends Application {

    private Stage primaryStage;
    //private BorderPane mainLayout;

    public void start(Stage primaryStage) throws IOException {

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Grocery Shopping Application");
        showShopsListView(); // method to add .fxml file here
    }

    private void showShopsListView() throws IOException{

        Parent root = FXMLLoader.load(GroceryShopsMain.class.getResource("view/ShopsListView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
