package chess;

import boardgame.Board;
import boardgame.Position;

public class Pawn extends ChessPiece{

    private ChessMatch match;

    public Pawn(Board board, Color color, ChessMatch match) {
        super(board, color);
        this.match = match;
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

                //enpassant
                if (position.getRow() == 3){
                    Position left = new Position(position.getRow(),position.getCollumn()-1);
                    if (getBoard().positionExists(left) && isThereOponentPiece(left) && getBoard().getPiece(left) == match.getEnPassantVulnerable()){
                        mat[left.getRow()-1][left.getCollumn()] = true;
                    }
                    Position right = new Position(position.getRow(),position.getCollumn()+1);
                    if (getBoard().positionExists(right) && isThereOponentPiece(right) && getBoard().getPiece(right) == match.getEnPassantVulnerable()){
                        mat[right.getRow()-1][right.getCollumn()] = true;
                    }
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

                //enpassant
                if (position.getRow() == 4){
                    Position left = new Position(position.getRow(),position.getCollumn()-1);
                    if (getBoard().positionExists(left) && isThereOponentPiece(left) && getBoard().getPiece(left) == match.getEnPassantVulnerable()){
                        mat[left.getRow()+1][left.getCollumn()] = true;
                    }
                    Position right = new Position(position.getRow(),position.getCollumn()+1);
                    if (getBoard().positionExists(right) && isThereOponentPiece(right) && getBoard().getPiece(right) == match.getEnPassantVulnerable()){
                        mat[right.getRow()+1][right.getCollumn()] = true;
                    }
                }

                break;
            }
        }
        return mat;
    }
}
