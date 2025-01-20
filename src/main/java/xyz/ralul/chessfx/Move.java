package xyz.ralul.chessfx;

public class Move {

    public enum MoveType {
        NORMAL,
        CAPTURE,
        CASTLING,
        EN_PASSANT,
        PROMOTION
    }

    private MoveType moveType;
    private Position from;
    private Position to;

    public Move(Position from, Position to, MoveType moveType) {
        this.to = to;
        this.moveType = moveType;
    }

    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        this.from = from;
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
