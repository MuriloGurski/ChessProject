package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){
        return checkMate;
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
        piecesOnTheBoard.add(piece);
    }

    public void initialSetup(){
        placeNewPiece('a',1, new Rook(board,Color.WHITE));
        placeNewPiece('b',1, new Knight(board,Color.WHITE));
        placeNewPiece('c',1, new Bishop(board,Color.WHITE));
        placeNewPiece('d',1, new Queen(board,Color.WHITE));
        placeNewPiece('e',1, new King(board,Color.WHITE,this));
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
        placeNewPiece('e',8, new King(board,Color.BLACK,this));
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
        validateTargetPosition(sourcePos,targetPos);
        Piece capturedPiece = makeMove(sourcePos,targetPos);

        if (testCheck(currentPlayer)){
            undoMove(sourcePos,targetPos,capturedPiece);
            throw new ChessException("You cant put yourself in check");
        }

        check = testCheck(opponent(currentPlayer));

        if (testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    public Piece makeMove(Position sourcePos, Position targetPos){
        ChessPiece p = (ChessPiece) board.removePiece(sourcePos);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(targetPos);
        board.placePiece(p,targetPos);

        if (capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        //castling
        if (p instanceof King && targetPos.getCollumn() == sourcePos.getCollumn()+2){
            Position sourceT = new Position(sourcePos.getRow(),sourcePos.getCollumn()+3);
            Position targetT = new Position(sourcePos.getRow(),sourcePos.getCollumn()+1);
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
            board.placePiece(rook,targetT);
            rook.increaseMoveCount();
        }
        if (p instanceof King && targetPos.getCollumn() == sourcePos.getCollumn()-2){
            Position sourceT = new Position(sourcePos.getRow(),sourcePos.getCollumn()-4);
            Position targetT = new Position(sourcePos.getRow(),sourcePos.getCollumn()-1);
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
            board.placePiece(rook,targetT);
            rook.increaseMoveCount();
        }

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece captured){
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p,source);

        if (captured != null){
            board.placePiece(captured,target);
            capturedPieces.remove(captured);
            piecesOnTheBoard.add(captured);
        }
        //castling
        if (p instanceof King && target.getCollumn() == source.getCollumn()+2){
            Position sourceT = new Position(source.getRow(),source.getCollumn()+3);
            Position targetT = new Position(source.getRow(),source.getCollumn()+1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            board.placePiece(rook,sourceT);
            rook.decreaseMoveCount();
        }
        if (p instanceof King && target.getCollumn() == source.getCollumn()-2){
            Position sourceT = new Position(source.getRow(),source.getCollumn()-4);
            Position targetT = new Position(source.getRow(),source.getCollumn()-1);
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            board.placePiece(rook,sourceT);
            rook.decreaseMoveCount();
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.getPiece(position).possibleMoves();
    }

    public void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("No piece found in " + position);
        }
        if (currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()){
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.getPiece(position).isThereAnyPossibleMoves()){
            throw new ChessException("No possible moves for the chosen piece");
        }
    }
    public void validateTargetPosition(Position source, Position target){
        if (!board.getPiece(source).possibleMove(target)){
            throw new ChessException("Chosen piece cannot move to target location");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer ==  Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).toList();
        for (Piece p : list){
            if (p instanceof King){
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no" + color + "king on the board");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).toList();
        for (Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getCollumn()]){
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if (!testCheck(color)){
            return false;
        }
        List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).toList();
        for (Piece p : pieces){
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i <board.getRows(); i++){
                for (int j = 0; j<board.getCollumns(); j++){
                    if (mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source,target);
                        boolean testCheck = testCheck(color);
                        undoMove(source,target,capturedPiece);
                        if (!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;

    }


/*

    public ChessPiece replacePromotedPiece(String type){

    }

 */
}
