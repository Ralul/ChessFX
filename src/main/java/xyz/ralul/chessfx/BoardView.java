package xyz.ralul.chessfx;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.ralul.chessfx.piece.Piece;

public class BoardView {
    private GridPane gridPane;
    private CellClickListener listener;

    public BoardView() {
        gridPane = new GridPane();
    }

    public void renderBoard(Board board) {
        gridPane.getChildren().clear(); // Clear all cells
        boolean color = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane cell = createCell(color, board.getPiece(row, col));
                int finalRow = row;
                int finalCol = col;
                cell.setOnMouseClicked(e -> {
                    if (listener != null) {
                        listener.onCellClick(finalRow, finalCol);
                    }
                });
                gridPane.add(cell, col, row);
                color = !color;
            }
            color = !color;
        }
    }

    private StackPane createCell(boolean color, Piece piece) {
        Rectangle rect = new Rectangle(50, 50, color ? Color.BROWN : Color.BEIGE);
        StackPane cell = new StackPane(rect);
        if (piece != null) {
            cell.getChildren().add(piece.getImageView());
        }
        return cell;
    }

    public void setCellClickListener(CellClickListener listener) {
        this.listener = listener;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public interface CellClickListener {
        void onCellClick(int row, int col);
    }
}
