package xyz.ralul.chessfx;

public class GameController {

    private static Board board;
    private static BoardView boardView;
    private static int startRow, startCol;
    private static int endRow, endCol;
    private static boolean validStartPosition = false;
    private static boolean validEndPosition = false;
    private static boolean playerIsWhite = true;
    private static boolean playerBlackIsCheck = false;
    private static boolean playerWhiteIsCheck = false;
    private static SearchValidMoves searchValidMoves;

    public GameController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
        boardView.setCellClickListener(this::hadelCellClick);
        updateView();
        searchValidMoves = new SearchValidMoves(board);
        searchValidMoves.updateMoves();
    }

    public static Board getBoard() {
        return board;
    }

    public static boolean isPlayerIsWhite() {
        return playerIsWhite;
    }

    private void hadelCellClick(int row, int col) {
        System.out.println("Piece clicked at: " + row + ", " + col);

        if ((board.getPiece(row, col) != null) && board.getPiece(row, col).isWhite() == playerIsWhite) {
            startRow = row;
            startCol = col;
            validStartPosition = true;
            System.out.println("GameLogic valid starting position");
        } else if (validStartPosition) {
            endRow = row;
            endCol = col;
            validEndPosition = true;
            System.out.println("GameLogic valid ending position");
        }

        if (validEndPosition && validStartPosition) {
            movePiece();
        }
        Position kingPos = board.getKing(playerIsWhite);
        System.out.println("row: " + kingPos.getRow() + "| col: " + kingPos.getCol());
    }

    private void movePiece() {
        if (board.getPiece(startRow, startCol).isValidMove(endRow, endCol, false)) {
            System.out.println("GameLogic valid ending position");
            board.movePiece(startRow, startCol, endRow, endCol);
            validStartPosition = false;
            validEndPosition = false;
            playerIsWhite = !playerIsWhite;
            updateView();
            searchValidMoves.updateMoves();

        }
    }

    private void updateView() {
        boardView.renderBoard(board);
    }
}
