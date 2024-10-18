package chess;

import boardgame.Board;
import boardgame.Position;

public class King extends ChessPiece{

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return  "K";
    }

    public boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return  p == null || p.getColor() != getColor();
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

        return mat;
    }
}
