package xyz.ralul.chessfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchValidMoves {

    private Map<Position, List<Position>> validEnds = new HashMap<>();
    private List<Position> validStarts = new ArrayList<>();
    private Board board;
    private Board tempBoard;

    public SearchValidMoves(Board board) {
        this.board = board;
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
        List<Position> ownPiecesPosition = board.getAllPieces(playerIsWhite);
        Map<Position, List<Position>> ownPiecesPositionWithMoves = new HashMap<>();

        for (int i = 0; i < ownPiecesPosition.size(); i++) {
            ownPiecesPositionWithMoves.put(ownPiecesPosition.get(i), board.getPiece(ownPiecesPosition.get(i).getRow(), ownPiecesPosition.get(i).getCol()).getValidMoves(false));
        }
        for (int i = ownPiecesPositionWithMoves.size()-1; i > 0 ; i--) {
            for (int j = ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).size()-1; j > 0 ; j--) {

                tempBoard = board.clone();

                int startRow = ownPiecesPosition.get(i).getRow();
                int startCol = ownPiecesPosition.get(i).getCol();
                int endRow = ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).get(j).getRow();
                int endCol = ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).get(j).getCol();

                tempBoard.movePiece(startRow, startCol, endRow, endCol);
                tempBoard.printBoard();

                List<Position> otherPieces = tempBoard.getAllPieces(!playerIsWhite);

                for (int k = 0; k < otherPieces.size(); k++) {
                    List<Position> possibleCatches = tempBoard.getPiece(otherPieces.get(k).getRow(), otherPieces.get(k).getCol()).getValidMoves(true);
                    Position posKing = tempBoard.getKing(playerIsWhite);
                    for (Position position : possibleCatches) {
                        if (position.equals(posKing)) {

                            ownPiecesPositionWithMoves.get(ownPiecesPosition.get(i)).remove(j);
                            System.out.println("ilegal move");
                            break;
                        }
                    }
                }
                tempBoard.clean();
            }
        }

        validStarts.clear();
        validStarts = ownPiecesPosition;
        validEnds.clear();
        validEnds = ownPiecesPositionWithMoves;
        printMoves();
    }
    public void printMoves() {
        for(int i = 0; i < validStarts.size(); i++) {
            for(int j = 0; j < validEnds.get(validStarts.get(i)).size(); j++) {
                int startRow = validStarts.get(i).getRow();
                int startCol = validStarts.get(i).getCol();
                int endRow = validEnds.get(validStarts.get(i)).get(j).getRow();
                int endCol = validEnds.get(validStarts.get(i)).get(j).getCol();

                System.out.println("Start Row: " + startRow + "| Start Col: " + startCol + "| End   Row: " + endRow + "| End   Col: " + endCol);

            }
        }
    }
}
