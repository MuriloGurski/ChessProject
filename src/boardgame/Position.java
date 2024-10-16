package boardgame;

import utils.NumToLetter;

public class Position {

   private int row,collumn;

   public Position(int row, int collumn){
       setValues(row,collumn);
   }

    public void setValues(int row, int collumn){

        this.row = row;
        this.collumn = collumn;

    }

    public int getCollumn() {
        return collumn;
    }

    public void setCollumn(int collumn) {
        this.collumn = collumn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString(){
       return (8 - row) + NumToLetter.numToLetter(collumn);
    }
}
