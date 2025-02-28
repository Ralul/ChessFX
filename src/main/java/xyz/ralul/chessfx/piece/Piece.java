package xyz.ralul.chessfx.piece;

import javafx.scene.image.ImageView;
import xyz.ralul.chessfx.GameController;
import xyz.ralul.chessfx.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements Cloneable {
    protected boolean isWhite;
    protected Position position;
    protected ChessPieceType type;
    protected boolean isCatchable;
    protected boolean isMoved;

    public Piece(boolean isWhite, ChessPieceType type, boolean isCatchable) {
        this.isWhite = isWhite;
        this.type = type;
        this.isCatchable = isCatchable;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public abstract List<Position> getValidMoves(boolean kingIsCapture);

    public boolean isValidMove(int row, int col, boolean kingIsCapture) {
        List<Position> validMoves = getValidMoves(kingIsCapture);

        for (int i = 0; i < validMoves.size(); i++) {
            if (validMoves.get(i).getRow() == row && validMoves.get(i).getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean isCatchbleBy(Piece opponent, boolean kingIsCapture) {
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

    public List<Position> getMovesByDirections(int[][] directions, boolean isSliding, boolean kingIsCapture) {
        List<Position> piecesInRange = new ArrayList<>();
        int startRow = position.getRow();
        int startCol = position.getCol();

        for (int[] direction : directions) {
            int currentRow = startRow;
            int currentCol = startCol;


            while (true) {
                currentRow += direction[0];
                currentCol += direction[1];

                if (currentRow < 0 || currentRow >= 8 || currentCol < 0 || currentCol >= 8) {
                    break;
                }

                Piece targetPiece = GameController.getBoard().getPiece(new Position(currentRow, currentCol));

                if (targetPiece == null || targetPiece.isCatchbleBy(this, kingIsCapture)) {
                    piecesInRange.add(new Position(currentRow, currentCol));
                } else {
                    break;
                }

                if (!isSliding) {
                    break;
                }
            }
        }
        return piecesInRange;
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
