package xyz.ralul.chessfx.piece;

import javafx.scene.image.ImageView;
import xyz.ralul.chessfx.ChessPieceType;

import java.util.List;

public abstract class Piece {
    private boolean isWhite;
    private int row;
    private int col;
    private ChessPieceType type;
    private boolean isCatchable;


    public Piece(boolean isWhite, int row, int col , ChessPieceType type, boolean isCatchable) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
        this.type = type;
        this.isCatchable = isCatchable;
    }
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

    public abstract List<Integer[]> getValidMoves();

    public abstract List<Integer[]> getValidCatches();

    public boolean isValidMove(int row, int col) {
        List<Integer[]> validMoves = getValidMoves();

        for (int i = 0; i < validMoves.size(); i++) {
            if (validMoves.get(i)[0] == row && validMoves.get(i)[1] == col) {
                return true;
            }
        }

        List<Integer[]> validCatches = getValidCatches();
        for (int i = 0; i < validCatches.size(); i++) {
            if (validCatches.get(i)[0] == row && validCatches.get(i)[1] == col) {
                return true;
            }
        }
        return false;
    }

    public boolean isCatchbleBy(Piece opponent) {
        if (opponent == null) {
            return false;
        }

        if(opponent.isWhite() != isWhite) {
            if(isCatchable) {
                return true;
            }
        }
        return false;
    }

}
