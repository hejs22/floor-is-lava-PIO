package front.main.java.com.pio.floorislavafront;

import front.main.java.com.pio.floorislavafront.ClientSocket.ClientApplication;
import front.main.java.com.pio.floorislavafront.soundtrack.SoundtrackManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FloorIsLavaApp extends Application {
    private static Stage primaryStage;
    private final SoundtrackManager soundtrackManager = new SoundtrackManager();

    private static void setPrimaryStage(Stage stage) {
        FloorIsLavaApp.primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return FloorIsLavaApp.primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {

        setPrimaryStage(stage);
        FloorIsLavaController.setScene("initial-screen-scene.fxml");
        stage.setTitle("Floor is Lava!");
        stage.show();
    }

    public static void main(String[] args) {
        new ClientApplication("localhost", 8080, "MyNickname");
        //launch();
    }
}