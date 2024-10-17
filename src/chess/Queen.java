package chess;

import boardgame.Board;
import boardgame.Position;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return  "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];

        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow()-1,position.getCollumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setRow(p.getRow()-1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //below
        p.setValues(position.getRow()+1,position.getCollumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setRow(p.getRow()+1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //left
        p.setValues(position.getRow(),position.getCollumn()-1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setCollumn(p.getCollumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //right
        p.setValues(position.getRow(),position.getCollumn()+1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setCollumn(p.getCollumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }

        //diagonal up left
        p.setValues(position.getRow()-1,position.getCollumn()-1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setValues(p.getRow()-1,p.getCollumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //diagonal down left
        p.setValues(position.getRow()+1,position.getCollumn()-1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setValues(p.getRow()+1,p.getCollumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //diagonal up right
        p.setValues(position.getRow()-1,position.getCollumn()+1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setValues(p.getRow()-1,p.getCollumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        //diagonal down right
        p.setValues(position.getRow()+1,position.getCollumn()+1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
            p.setValues(p.getRow()+1,p.getCollumn()+1);
        }
        if (getBoard().positionExists(p) && isThereOponentPiece(p)){
            mat[p.getRow()][p.getCollumn()] = true;
        }
        return mat;
    }
}
