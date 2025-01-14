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
        board.loadFen("8/1k4p1/8/8/8/8/1Q3K2/8");

        GameController controller = new GameController(board, boardView);

        Scene scene = new Scene(boardView.getGridPane());
        stage.setScene(scene);
        stage.show();
    }

}
