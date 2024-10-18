package chess;

import boardgame.Board;
import boardgame.Position;

public class Pawn extends ChessPiece{

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return  "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];

        Position p = new Position(0,0);

        //above
        switch (this.getColor()){
            case WHITE -> {
                p.setValues(position.getRow()-1,position.getCollumn());
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                    mat[p.getRow()][p.getCollumn()] = true;
                    if (getMoveCount() == 0) {
                        p.setValues(position.getRow() - 2, position.getCollumn());
                        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                            mat[p.getRow()][p.getCollumn()] = true;
                        }
                    }
                }
                p.setValues(position.getRow()-1,position.getCollumn()-1);
                if (getBoard().positionExists(p) && isThereOponentPiece(p)){
                    mat[p.getRow()][p.getCollumn()] = true;
                }
                p.setValues(position.getRow()-1,position.getCollumn()+1);
                if (getBoard().positionExists(p) && isThereOponentPiece(p)){
                    mat[p.getRow()][p.getCollumn()] = true;
                }
                break;
            }
            case BLACK -> {
                p.setValues(position.getRow()+1,position.getCollumn());
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                    mat[p.getRow()][p.getCollumn()] = true;
                    if (getMoveCount() == 0) {
                        p.setValues(position.getRow() + 2, position.getCollumn());
                        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                            mat[p.getRow()][p.getCollumn()] = true;
                        }
                    }
                }
                p.setValues(position.getRow()+1,position.getCollumn()-1);
                if (getBoard().positionExists(p) && isThereOponentPiece(p)){
                    mat[p.getRow()][p.getCollumn()] = true;
                }
                p.setValues(position.getRow()+1,position.getCollumn()+1);
                if (getBoard().positionExists(p) && isThereOponentPiece(p)){
                    mat[p.getRow()][p.getCollumn()] = true;
                }
                break;
            }
        }
        return mat;
    }
}
