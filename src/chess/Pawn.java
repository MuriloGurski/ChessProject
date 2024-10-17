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
                for (int i = 0; i < 2; i++){
                    if (getBoard().positionExists(p.getRow()-1,p.getCollumn()-1)){
                        if (isThereOponentPiece(new Position(p.getRow()-1,p.getCollumn()-1))){
                            mat[p.getRow()-1][p.getCollumn()-1] = true;
                        }
                    }
                    if (getBoard().positionExists(p.getRow()-1,p.getCollumn()+1)){
                        if (isThereOponentPiece(new Position(p.getRow()-1,p.getCollumn()+1))){
                            mat[p.getRow()-1][p.getCollumn()+1] = true;
                        }
                    }
                    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                        mat[p.getRow()][p.getCollumn()] = true;
                        p.setRow(p.getRow()-1);
                    }
                }
                break;
            }
            case BLACK -> {
                p.setValues(position.getRow()+1,position.getCollumn());
                for (int i = 0; i < 2; i++){
                    if (getBoard().positionExists(p.getRow()+1,p.getCollumn()-1)){
                        if (isThereOponentPiece(new Position(p.getRow()+1,p.getCollumn()-1))){
                            mat[p.getRow()+1][p.getCollumn()-1] = true;
                        }
                    }
                    if (getBoard().positionExists(p.getRow()+1,p.getCollumn()+1)){
                        if (isThereOponentPiece(new Position(p.getRow()+1,p.getCollumn()+1))){
                            mat[p.getRow()+1][p.getCollumn()+1] = true;
                        }
                    }
                    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                        mat[p.getRow()][p.getCollumn()] = true;
                        p.setRow(p.getRow()+1);
                    }
                }
                break;
            }
        }
        return mat;
    }
}
