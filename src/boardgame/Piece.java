package boardgame;

public class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board){
        this.board = board;
    }
/*
    public boolean[][] possibleMoves(){

        boolean[][] possibleMoves;

        return possibleMoves;
    }
    public boolean possibleMove(Position position){
        boolean possible = false;

        return possible;
    }
    public boolean isThereAnyPossibleMoves(){
        boolean check = false;

        return check;
    }

 */

    protected Board getBoard() {
        return board;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
