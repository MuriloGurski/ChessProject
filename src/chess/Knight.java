package chess;

import boardgame.Board;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return  "H";
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[0][];
    }
}
