package xyz.ralul.chessfx.piece;

import javafx.scene.image.ImageView;
import xyz.ralul.chessfx.GameController;
import xyz.ralul.chessfx.Move;
import xyz.ralul.chessfx.Position;

import java.util.List;

public abstract class Piece implements Cloneable {
    protected boolean isWhite;
    protected int row;
    protected int col;
    protected List<Move> moves;
    protected ChessPieceType type;
    protected boolean isCatchable;
    protected boolean isMoved;
    protected int[][] directions;
    protected boolean isSliding;

    public Piece(boolean isWhite, ChessPieceType type, boolean isCatchable, int[][] directions, boolean isSliding) {
        this.isWhite = isWhite;
        this.type = type;
        this.isCatchable = isCatchable;
        this.directions = directions;
        this.isSliding = isSliding;
    }

    public Piece(boolean isWhite, ChessPieceType type, boolean isCatchable, boolean isSliding) {
        this.isWhite = isWhite;
        this.type = type;
        this.isCatchable = isCatchable;
        this.isSliding = isSliding;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
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

    public boolean isCatchableBy(Piece opponent, boolean kingIsCapture) {
        if (opponent == null) {
            return false;
        }
        if (opponent.isWhite() != isWhite) {
            if (kingIsCapture) {
                return true;
            }
            return isCatchable;
        }
        return false;
    }

    public void updateMoves() {
        moves.clear();
        for (int[] direction : directions) {
            int currentRow = row;
            int currentCol = col;

            while (true) {
                currentRow += direction[0];
                currentCol += direction[1];

                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break;
                }

                Piece targetPiece = GameController.getBoard().getPiece(new Position(currentRow, currentCol));

                if (targetPiece == null) {
                    moves.add(new Move(new Position(currentRow, currentCol), Move.MoveType.NORMAL));
                } else if (targetPiece.isCatchableBy(this, true)) {
                    moves.add(new Move(new Position(currentRow, currentCol), Move.MoveType.CAPTURE));
                } else if (targetPiece.isCatchableBy(this, true)) {
                    moves.add(new Move(new Position(currentRow, currentCol), Move.MoveType.KING_CAPTURE));
                } else {
                    break;
                }

                if (!isSliding) {
                    break;
                }
            }
        }
    }

    @Override
    public Piece clone() {
        try {
            Piece pieceClone = (Piece) super.clone();

            return pieceClone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
