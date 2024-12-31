package xyz.ralul.chessfx;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.ralul.chessfx.piece.Piece;

public class BoardView {
    private GridPane gridPane;

    public BoardView() {
        gridPane = new GridPane();
        initializeBoard();
    }

    private void initializeBoard() {
        Color dark = new Color(0.8, 0.5, 0.3, 1);
        Color white = new Color(1, 0.8, 0.6, 1);

        Board board = GameLogic.getBoard(); // Get the board from GameLogic

        boolean color = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create the rectangle (background)
                Rectangle cell = new Rectangle(50, 50, color ? dark : white);
                color = !color;

                // Create a StackPane for this cell
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(cell); // Add the rectangle as the base

                // Add the piece's ImageView if there's a piece
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    stackPane.getChildren().add(piece.getImageView());
                }

                // Attach a click event to the StackPane
                final int ROW = row; // Capture the current row
                final int COL = col; // Capture the current column
                stackPane.setOnMouseClicked(event -> {
                    System.out.println("Mouse clicked on cell: (" + ROW + ", " + COL + ")");
                    GameLogic.handleCellClick(ROW, COL);
                    refreshBoard();
                });

                // Add the StackPane to the GridPane
                gridPane.add(stackPane, col, row);
            }
            color = !color; // Alternate starting color for each row
        }
    }

    public void refreshBoard() {
        gridPane.getChildren().clear(); // Clear all existing nodes

        // Reinitialize the board (re-render cells and pieces)
        initializeBoard();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

}
