package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return (p != null && p.getColor() != color);
    }

}
