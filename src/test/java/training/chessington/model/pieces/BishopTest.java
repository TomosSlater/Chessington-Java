package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {

    @Test
    public void bishopCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

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
    public void bishopCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);


        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4, 4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4, -4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4, 4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-5, -5)));
    }

    @Test
    public void bishopCantMoveThroughPieces() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        Piece bishop2 = new Rook(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, bishop2);

        Piece bishop3 = new Rook(PlayerColour.WHITE);
        Coordinates coords3 = new Coordinates(5, 5);
        board.placePiece(coords3, bishop3);

        Piece bishop4 = new Rook(PlayerColour.WHITE);
        Coordinates coords4 = new Coordinates(3, 5);
        board.placePiece(coords4, bishop4);

        Piece bishop5 = new Rook(PlayerColour.WHITE);
        Coordinates coords5 = new Coordinates(5, 3);
        board.placePiece(coords5, bishop5);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, 2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, -2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, 2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2, -2)));
    }

    @Test
    public void bishopCanTakeEnemyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        Piece bishop2 = new Bishop(PlayerColour.BLACK);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, bishop2);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);
        List<Move> moves2 = bishop2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves2).contains(new Move(coords2, coords2.plus(1, 1)));
    }

    @Test
    public void bishopCantTakeFriendlyPieces() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        Piece bishop2 = new Bishop(PlayerColour.WHITE);
        Coordinates coords2 = new Coordinates(3, 3);
        board.placePiece(coords2, bishop2);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);
        List<Move> moves2 = bishop2.getAllowedMoves(coords2, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-1, -1)));
        assertThat(moves2).doesNotContain(new Move(coords2, coords2.plus(1, 1)));
    }
}
