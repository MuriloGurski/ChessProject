package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
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
        initialSetup();
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

    private void placeNewPiece(char collumn, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(row,collumn).toPosition());
    }

    public void initialSetup(){
        placeNewPiece('a',1, new Rook(board,Color.WHITE));
        placeNewPiece('b',1, new Knight(board,Color.WHITE));
        placeNewPiece('c',1, new Bishop(board,Color.WHITE));
        placeNewPiece('d',1, new Queen(board,Color.WHITE));
        placeNewPiece('e',1, new King(board,Color.WHITE));
        placeNewPiece('f',1, new Bishop(board,Color.WHITE));
        placeNewPiece('g',1, new Knight(board,Color.WHITE));
        placeNewPiece('h',1, new Rook(board,Color.WHITE));
        placeNewPiece('a',2, new Pawn(board,Color.WHITE));
        placeNewPiece('b',2, new Pawn(board,Color.WHITE));
        placeNewPiece('c',2, new Pawn(board,Color.WHITE));
        placeNewPiece('d',2, new Pawn(board,Color.WHITE));
        placeNewPiece('e',2, new Pawn(board,Color.WHITE));
        placeNewPiece('f',2, new Pawn(board,Color.WHITE));
        placeNewPiece('g',2, new Pawn(board,Color.WHITE));
        placeNewPiece('h',2, new Pawn(board,Color.WHITE));

        placeNewPiece('a',8, new Rook(board,Color.BLACK));
        placeNewPiece('b',8, new Knight(board,Color.BLACK));
        placeNewPiece('c',8, new Bishop(board,Color.BLACK));
        placeNewPiece('d',8, new Queen(board,Color.BLACK));
        placeNewPiece('e',8, new King(board,Color.BLACK));
        placeNewPiece('f',8, new Bishop(board,Color.BLACK));
        placeNewPiece('g',8, new Knight(board,Color.BLACK));
        placeNewPiece('h',8, new Rook(board,Color.BLACK));
        placeNewPiece('a',7, new Pawn(board,Color.BLACK));
        placeNewPiece('b',7, new Pawn(board,Color.BLACK));
        placeNewPiece('c',7, new Pawn(board,Color.BLACK));
        placeNewPiece('d',7, new Pawn(board,Color.BLACK));
        placeNewPiece('e',7, new Pawn(board,Color.BLACK));
        placeNewPiece('f',7, new Pawn(board,Color.BLACK));
        placeNewPiece('g',7, new Pawn(board,Color.BLACK));
        placeNewPiece('h',7, new Pawn(board,Color.BLACK));
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position sourcePos = sourcePosition.toPosition();
        Position targetPos = targetPosition.toPosition();
        validateSourcePosition(sourcePos);
        Piece capturedPiece = makeMove(sourcePos,targetPos);
        return (ChessPiece) capturedPiece;
    }

    public Piece makeMove(Position sourcePos, Position targetPos){
        Piece p = board.removePiece(sourcePos);
        Piece capturedPiece = board.removePiece(targetPos);
        board.placePiece(p,targetPos);
        return capturedPiece;
    }

    public void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("No piece found in " + position);
        }
    }

/*
    public boolean[][] possibleMoves(ChessPosition sourcePosition){

    }

    public ChessPiece replacePromotedPiece(String type){

    }

 */
}
