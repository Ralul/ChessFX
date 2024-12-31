package xyz.ralul.chessfx;

import javafx.scene.image.ImageView;

public enum ChessPieceType {
    PAWN("/xyz/ralul/chessfx/figures/White_Pawn.png","/xyz/ralul/chessfx/figures/Black_Pawn.png"),
    ROOK("/xyz/ralul/chessfx/figures/White_Rook.png","/xyz/ralul/chessfx/figures/Black_Rook.png"),
    KNIGHT("/xyz/ralul/chessfx/figures/White_Knight.png","/xyz/ralul/chessfx/figures/Black_Knight.png"),
    BISHOP("/xyz/ralul/chessfx/figures/White_Bishop.png","/xyz/ralul/chessfx/figures/Black_Bishop.png"),
    QUEEN("/xyz/ralul/chessfx/figures/White_Queen.png","/xyz/ralul/chessfx/figures/Black_Queen.png"),
    KING("/xyz/ralul/chessfx/figures/White_King.png","/xyz/ralul/chessfx/figures/Black_King.png");

    private final String whiteImagePath;
    private final String blackImagePath;

    ChessPieceType(String whiteImagePath, String blackImagePath) {
        this.whiteImagePath = whiteImagePath;
        this.blackImagePath = blackImagePath;
    }
    public String getImagePath(boolean isWhite) {
        if(isWhite) {
            return whiteImagePath;
        }
        else {
            return blackImagePath;
        }
    }

    public ImageView getImageView(boolean isWhite) {
        ImageView imageView = new ImageView(ChessPieceType.this.getImagePath(isWhite));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }
}

