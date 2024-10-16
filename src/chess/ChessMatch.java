package chess;

import boardgame.Board;
import boardgame.Position;

public class ChessMatch {

    private int turn;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    public ChessMatch() {
        board = new Board(8,8);
        InitialSetup();
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

    public void InitialSetup(){
        board.placePiece(new Rook(board,Color.WHITE), new Position(0,0));
        board.placePiece(new Knight(board,Color.WHITE), new Position(0,1));
        board.placePiece(new King(board,Color.WHITE), new Position(0,3));
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
