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
        Board board = new Board();
        BoardView boardView = new BoardView();
        board.loadFen("1q5R/8/4k3/8/8/8/1K6/8");

        GameController controller = new GameController(board, boardView);

        Scene scene = new Scene(boardView.getGridPane());
        stage.setScene(scene);
        stage.show();
    }
}
