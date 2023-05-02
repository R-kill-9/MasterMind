
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear una instancia de MastermindJuego
        MastermindJuego mastermind = new MastermindJuego();
        mastermind.start(primaryStage);
    }

}
