package xyz.ralul.chessfx;

public class Move implements Cloneable {

    public enum MoveType {
        NORMAL,
        CAPTURE,
        CASTLING,
        EN_PASSANT,
        PROMOTION,
        KING_CAPTURE,
        PAWN_SPRINT
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

    @Override
    public Move clone() {
        try {
            Move clone = (Move) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
