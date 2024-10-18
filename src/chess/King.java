package chess;

import boardgame.Board;
import boardgame.Position;

public class King extends ChessPiece{

    public ChessMatch match;

    public King(Board board, Color color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString(){
        return  "K";
    }

    public boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return  p == null || p.getColor() != getColor();
    }

    public boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];
        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow() -1, position.getCollumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //down
        p.setValues(position.getRow() +1, position.getCollumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //left
        p.setValues(position.getRow(), position.getCollumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //right
        p.setValues(position.getRow(), position.getCollumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //up left
        p.setValues(position.getRow() -1, position.getCollumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //down left
        p.setValues(position.getRow() +1, position.getCollumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //up right
        p.setValues(position.getRow() -1, position.getCollumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //up left
        p.setValues(position.getRow() +1, position.getCollumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //castling
        if (getMoveCount() == 0 && !match.getCheck()){
            Position posT1 = new Position(position.getRow(),position.getCollumn()+3);
            if (testRookCastling(posT1)){
                Position p1 = new Position(position.getRow(),position.getCollumn()+1);
                Position p2 = new Position(position.getRow(),position.getCollumn()+2);
                if(getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null){
                    mat[position.getRow()][position.getCollumn()+2] = true;
                }
            }
            Position posT2 = new Position(position.getRow(),position.getCollumn()-4);
            if (testRookCastling(posT2)){
                Position p1 = new Position(position.getRow(),position.getCollumn()-1);
                Position p2 = new Position(position.getRow(),position.getCollumn()-2);
                Position p3 = new Position(position.getRow(),position.getCollumn()-3);
                if (getBoard().getPiece(p1) == null && getBoard().getPiece(p2) == null && getBoard().getPiece(p3) == null){
                    mat[position.getRow()][position.getCollumn()-2] = true;
                }
            }
        }

        return mat;
    }
}
