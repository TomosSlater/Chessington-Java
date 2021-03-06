package training.chessington.model;

import training.chessington.model.pieces.*;

public class Board {

    private Piece[][] board = new Piece[8][8];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < 8; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public boolean checkEmpty(Coordinates coords) {
        return (get(coords) == null) ? true : false;
    }

    public boolean checkEmptyBetween(Coordinates from, int distance, boolean direction){
        if (direction){ //when true, direction is horizontal
            for (int i = 1; i < distance; i++){
                if (!checkEmpty(from.plus(0, i))) return false;
            }
        } else {
            for (int i = 1; i < distance; i++){
                if (!checkEmpty(from.plus(i, 0))) return false;
            }
        }

        return true;
    }

    public boolean checkEmptyBetweenDiagonal(Coordinates from, int distance, boolean direction){
        if (direction){ //when true direction is from Top-left to Bottom-right
            for(int i = 1; i < distance; i++){
                if (!checkEmpty(from.plus(i, i))) return false;
            }
        } else { //when false, direction is from Top-right to Bottom-left
            for(int i = 1; i < distance; i++){
                if (!checkEmpty(from.plus(i, -i))) return false;
            }
        }
        return true;
    }

    public boolean checkFriendlyFire(Coordinates coords, PlayerColour friendlyColour){
        if (checkEmpty(coords)) return true;
        else if (checkColour(coords) != friendlyColour) return true;
        return false;
    }

    public PlayerColour checkColour(Coordinates coords){
        return get(coords).getColour();
    }

    public void move(Coordinates from, Coordinates to) {
        board[to.getRow()][to.getCol()] = board[from.getRow()][from.getCol()];
        board[from.getRow()][from.getCol()] = null;
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }
}
