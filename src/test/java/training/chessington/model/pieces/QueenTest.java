package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class QueenTest {


    @Test
    public void queenCanMoveOrthogonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-3, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(-4, 0)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, 3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -3)));
        assertThat(moves).contains(new Move(coords, coords.plus(0, -4)));
    }

    @Test
    public void queenCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(3, 3)));
        assertThat(moves).contains(new Move(coords, coords.plus(1, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(3, -3)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-3, 3)));
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -2)));
        assertThat(moves).contains(new Move(coords, coords.plus(-3, -3)));
        assertThat(moves).contains(new Move(coords, coords.plus(-4, -4)));
    }

    @Test
    public void queenCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);


        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4, 4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4, -4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4, 4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-5, -5)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-5, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -5)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4, 0)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, 4)));
    }

    @Test
    public void queenCantMoveThroughPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        Piece queen2 = new Queen(PlayerColour.BLACK);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, queen2);

        Piece queen3 = new Queen(PlayerColour.BLACK);
        Coordinates coords3 = new Coordinates(5, 5);
        board.placePiece(coords3, queen3);

        Piece queen4 = new Queen(PlayerColour.BLACK);
        Coordinates coords4 = new Coordinates(3, 5);
        board.placePiece(coords4, queen4);

        Piece queen5 = new Queen(PlayerColour.BLACK);
        Coordinates coords5 = new Coordinates(5, 3);
        board.placePiece(coords5, queen5);

        Piece queen6 = new Queen(PlayerColour.BLACK);
        Coordinates coords6 = new Coordinates(4, 5);
        board.placePiece(coords6, queen5);

        Piece queen7 = new Queen(PlayerColour.BLACK);
        Coordinates coords7 = new Coordinates(4, 3);
        board.placePiece(coords7, queen5);

        Piece queen8 = new Queen(PlayerColour.BLACK);
        Coordinates coords8 = new Coordinates(5, 4);
        board.placePiece(coords8, queen5);

        Piece queen9 = new Queen(PlayerColour.BLACK);
        Coordinates coords9 = new Coordinates(3, 4);
        board.placePiece(coords9, queen5);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).isEmpty();
    }

    @Test
    public void queenCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        Piece queen2 = new Queen(PlayerColour.BLACK);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, queen2);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);
        List<Move> moves2 = queen2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves2).contains(new Move(coords2, coords2.plus(1, 1)));
    }

    @Test
    public void queenCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        Piece queen2 = new Queen(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, queen2);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);
        List<Move> moves2 = queen2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves2).doesNotContain(new Move(coords2, coords2.plus(1, 1)));
    }
}
