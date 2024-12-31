package xyz.ralul.chessfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameLogic.initializeGame();
        BoardView boardView = new BoardView();

        Scene scene = new Scene(boardView.getGridPane());
        stage.setScene(scene);
        stage.show();
    }

}
