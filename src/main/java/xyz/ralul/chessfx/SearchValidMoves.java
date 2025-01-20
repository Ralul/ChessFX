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

    public SearchValidMoves(Board board, Map<Position, List<Position>> validEnds, List<Position> validStarts) {
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
        List<Position> ownStartPositions = board.getAllPieces(playerIsWhite);
        Map<Position, List<Position>> ownEndPositions = new HashMap<>();

        for (int i = 0; i < ownStartPositions.size(); i++) {


            ownEndPositions.put(ownStartPositions.get(i), board.getPiece(ownStartPositions.get(i).getRow(), ownStartPositions.get(i).getCol()).getValidMoves(false));
        }
        for (int i = ownEndPositions.size()-1; i >= 0 ; i--) {
            for (int j = ownEndPositions.get(ownStartPositions.get(i)).size()-1; j >= 0 ; j--) {

                tempBoard = board.clone();

                tempBoard.movePiece(ownStartPositions.get(i),ownEndPositions.get(ownStartPositions.get(i)).get(j));
                tempBoard.printBoard();

                List<Position> otherPieces = tempBoard.getAllPieces(!playerIsWhite);

                for (int k = 0; k < otherPieces.size(); k++) {
                    List<Position> possibleCatches = tempBoard.getPiece(otherPieces.get(k).getRow(), otherPieces.get(k).getCol()).getValidMoves(true);
                    Position posKing = tempBoard.getKing(playerIsWhite);
                    for (Position position : possibleCatches) {
                        if (position.equals(posKing)) {

                            ownEndPositions.get(ownStartPositions.get(i)).remove(j);
                            System.out.println("ilegal move");
                            break;
                        }
                    }
                }
                tempBoard.clean();
            }
        }

        validStarts.clear();
        validStarts = ownStartPositions;
        validEnds.clear();
        validEnds = ownEndPositions;
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
    public boolean isMoveValid(Position start, Position end) {
        for(Position validStart : validStarts) {
            if (validStart.equals(start)) {
                for(Position validEnd : validEnds.get(validStart)) {
                    if (validEnd.equals(end)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
