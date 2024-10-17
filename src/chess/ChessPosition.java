package chess;

import boardgame.Position;

public class ChessPosition {

    private char collumn;
    private int row;

    public ChessPosition(int row, char collumn){
        if (row < 0 || row > 8 ||collumn < 'a' || collumn > 'h'){
            throw new ChessException("Error instanciating ChessPosition: Invalid position values.");
        }
        this.row = row;
        this.collumn = collumn;
    }


    protected Position toPosition(){
        return new Position(8 - row, collumn - 'a');
    }

    protected ChessPosition fromPosition(Position position){
        return new ChessPosition((8 - position.getRow()), (char) ('a' - position.getCollumn()));
    }


    public char getCollumn() {
        return collumn;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString(){
        return ("" + collumn + row);
    }

}
