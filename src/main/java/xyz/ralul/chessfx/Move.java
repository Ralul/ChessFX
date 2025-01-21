package xyz.ralul.chessfx;

public class Move {

    public enum MoveType {
        NORMAL,
        CAPTURE,
        CASTLING,
        EN_PASSANT,
        PROMOTION,
        KING_CAPTURE
    }

    private MoveType moveType;
    private Position to;

    public Move(Position to, MoveType moveType) {
        this.to = to;
        this.moveType = moveType;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }
}
