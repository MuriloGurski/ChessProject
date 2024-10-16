package chess;

import boardgame.Board;

public class ChessMatch {

    private int turn;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch() {
        board = new Board(8,8);
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCollumns()];

        for (int i=0; i< board.getRows();i++){
            for (int j=0; j<board.getCollumns(); j++){
                mat[i][j] = (ChessPiece) board.getPiece(i,j);
            }
        }
        return mat;
    }
/*
    public boolean[][] possibleMoves(ChessPosition sourcePosition){

    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){


    }

    public ChessPiece replacePromotedPiece(String type){

    }

 */
}
