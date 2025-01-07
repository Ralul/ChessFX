package xyz.ralul.chessfx;

public class GameController {

    public static Board getBoard() {
        return board;
    }

    private static Board board;
    private static BoardView boardView;

    private static int startRow, startCol;
    private static int endRow, endCol;
    private static boolean validStartPosition  = false;
    private static boolean validEndPosition = false;
    private static boolean playerIsWhite = true;

    public GameController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;

        boardView.setCellClickListener(this::hadelCellClick);
        updateView();
    }

    private void hadelCellClick(int row, int col) {
        System.out.println("Piece clicked at: " + row + ", " + col);
        if ((board.getPiece(row, col) != null) && board.getPiece(row, col).isWhite() == playerIsWhite) {
            startRow = row;
            startCol = col;
            validStartPosition = true;
            System.out.println("GameLogic valid starting position");
        }
        else if (validStartPosition) {
            endRow = row;
            endCol = col;
            validEndPosition = true;
            System.out.println("GameLogic valid ending position");
        }

        if (validEndPosition && validStartPosition) {
            if (board.getPiece(startRow, startCol).isValidMove(endRow, endCol)) {
                System.out.println("GameLogic valid ending position");
                board.movePiece(startRow, startCol, endRow, endCol);
                validStartPosition = false;
                validEndPosition = false;
                playerIsWhite = !playerIsWhite;
                updateView();
            }
        }
    }

    private void updateView() {
        boardView.renderBoard(board);
    }
}
