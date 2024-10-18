package chess;

import boardgame.Board;
import boardgame.Position;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return  "H";
    }

    public boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().getPiece(position);
        return  p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];

        Position p = new Position(0,0);

        //up left
        p.setValues(position.getRow()-2,position.getCollumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //up right
        p.setValues(position.getRow()-2,position.getCollumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //left up
        p.setValues(position.getRow()-1,position.getCollumn()-2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //left down
        p.setValues(position.getRow()+1,position.getCollumn()-2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //down left
        p.setValues(position.getRow()+2,position.getCollumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //down right
        p.setValues(position.getRow()+2,position.getCollumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //right down
        p.setValues(position.getRow()+1,position.getCollumn()+2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //right up
        p.setValues(position.getRow()-1,position.getCollumn()+2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        return mat;
    }
}
