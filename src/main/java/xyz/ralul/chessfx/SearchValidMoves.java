package xyz.ralul.chessfx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchValidMoves {

    private Map<Position, List<Position>> validMoves = new HashMap<>();
    private Board board;
    private Board tempBoard;

    public SearchValidMoves(Board board) {
        this.board = board;
        this.validMoves = validMoves;
    }

    public void updateMoves() {
        /*
        How to find all valid Moves:
        1. Find All own pieces
        2. Calculate all possible moves without considering their legality
        3. Made All moves on a deep Copy of the board
        4. Calculate all possible catches for the other player
        5. If a catch matches with the position of the king the move is not legal
        */

        boolean playerIsWhite = GameController.isPlayerIsWhite();
        validMoves.clear();
        List<Position> ownPiecesPosition =  board.getAllPieces(playerIsWhite);
        Map<Position, List<Position>> ownPiecesPositionWithMoves = new HashMap<>();

        for (int i = 0; i < ownPiecesPosition.size(); i++) {
            ownPiecesPositionWithMoves.put(ownPiecesPosition.get(i),board.getPiece(ownPiecesPosition.get(i).getRow(),ownPiecesPosition.get(i).getCol()).getValidMoves(false));
        }
        for (int i = 0; i < ownPiecesPositionWithMoves.size(); i++) {
            System.out.println(ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)));
            for (int j = 0; j < ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).size(); j++) {

                System.out.println(ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).get(j));

                tempBoard = board.clone();

                int startRow = ownPiecesPosition.get(i).getRow();
                int startCol = ownPiecesPosition.get(i).getCol();
                int endRow = ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).get(j).getRow();
                int endCol = ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).get(j).getCol();

                tempBoard.movePiece(startRow, startCol, endRow, endCol);


                List<Position> otherPieces = tempBoard.getAllPieces(!playerIsWhite);

                for (int k = 0; k < otherPieces.size(); k++) {
                    List<Position> posibelCatches = tempBoard.getPiece(otherPieces.get(k).getRow(), otherPieces.get(k).getCol()).getValidMoves(true);
                    for (Position position : posibelCatches) {
                        Position posKing = tempBoard.getKing(playerIsWhite);
                        if (posKing.getRow() == position.getRow() && posKing.getCol() == position.getCol()) {
                            ownPiecesPositionWithMoves.get(ownPiecesPosition.get(k)).remove(j);
                            break;
                        }
                    }
                }
                tempBoard.clean();
            }
        }

        //Move all pices and show if an enemy piec can capture the own king
        //But first create an copy of board to perform the move

        validMoves = ownPiecesPositionWithMoves;
    }
}
