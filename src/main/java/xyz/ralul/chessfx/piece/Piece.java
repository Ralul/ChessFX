package xyz.ralul.chessfx.piece;

import javafx.scene.image.ImageView;
import xyz.ralul.chessfx.ChessPieceType;
import xyz.ralul.chessfx.GameController;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private boolean isWhite;
    private int row;
    private int col;
    private ChessPieceType type;
    private boolean isCatchable;

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    private boolean isMoved;

    public Piece(boolean isWhite, ChessPieceType type, boolean isCatchable) {
        this.isWhite = isWhite;
        this.type = type;
        this.isCatchable = isCatchable;
    }

    public boolean isWhite() {
        return isWhite;
    }
    public void setWhite(boolean white) {
        isWhite = white;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public ChessPieceType getType() {
        return type;
    }
    public void setType(ChessPieceType type) {
        this.type = type;
    }
    public ImageView getImageView() {
        return getType().getImageView(isWhite);
    }

    public abstract List<Integer[]> getValidMoves(boolean kingIsCapture);

    public boolean isValidMove(int row, int col, boolean kingIsCapture) {
        List<Integer[]> validMoves = getValidMoves(kingIsCapture);

        for (int i = 0; i < validMoves.size(); i++) {
            if (validMoves.get(i)[0] == row && validMoves.get(i)[1] == col) {
                return true;
            }
        }
        return false;
    }

    public boolean isCatchbleBy(Piece opponent, boolean kingIsCapture) {
        if (opponent == null) {
            return false;
        }
        if(opponent.isWhite() != isWhite) {
            if(kingIsCapture) {
                return true;
            }
            return isCatchable;
        }
        return false;
    }

    public List<Integer[]> getMovesByDirections(int[][] directions,boolean kingIsCapture) {
        List<Integer[]> PiecesInRange = new ArrayList<>();
        int startRow = getRow();
        int startCol = getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;

            while (true) {
                currentRow += direction[0];
                currentCol += direction[1];

                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break;
                }

                Piece targetPiece = GameController.getBoard().getPiece(currentRow, currentCol);
                if (targetPiece == null || targetPiece.isCatchbleBy(this,kingIsCapture)) {
                    PiecesInRange.add(new Integer[]{currentRow, currentCol});
                } else {
                    break;
                }
            }
        }
        return PiecesInRange;
    }
}
