package boardgame;

public class Board {

    private int rows, collumns;
    private Piece[][] pieces;

    public Board(int rows, int collumns){
        this.rows = rows;
        this.collumns = collumns;
        pieces = new Piece[rows][collumns];
    }

    public void placePiece(Piece piece, Position position){
        this.pieces[position.getRow()][position.getCollumn()] = piece;
        piece.position = position;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCollumns() {
        return collumns;
    }

    public void setCollumns(int collumns) {
        this.collumns = collumns;
    }

    public Piece getPiece(int row, int collumn){
         return pieces[row][collumn];
    }

    public Piece getPiece(Position position){
        return pieces[position.getRow()][position.getCollumn()];
    }
}
