package com.packtpublishing.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Problem: TicTacToe
 * <p>
 * 1. A piece can be placed on any empty space of a 3×3 board.
 * 2. There should be a way to find out which player should play next.
 * 3. A player wins by being the first to connect a line of friendly
 * pieces from one side or corner of the board to the other.
 * 4. The result is a draw when all the boxes are filled.
 */

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    /**
     * 1. A piece can be placed on any empty space of a 3×3 board.
     */
    @Test
    public void whenXOutsideThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(4, 1);
    }

    @Test
    public void whenYOutsideThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(1, 0);
    }

    @Test
    public void whenCellNotEmptyThenRuntimeException() {
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    /**
     * 2. There should be a way to find out which player should play next.
     */
    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    /*
    // Discarded because test run successfully and no changes required in code.
    @Test
    public void givenLastTurnWasOThenNextPlayerThenX() {
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        assertEquals('X', ticTacToe.nextPlayer());
    }*/

    /**
     * 3. A player wins by being the first to connect a line of friendly
     * pieces from one side or corner of the board to the other.
     */
    @Test
    public void whenPlayNoWinner() {
        assertEquals("No winner", ticTacToe.play(2, 1));
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(1, 2); // X
        ticTacToe.play(2, 2); // O
        String actual = ticTacToe.play(1, 3); // X
        assertEquals("X is winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(3, 1); // O
        ticTacToe.play(2, 3); // X
        String actual = ticTacToe.play(1, 1); // O
        assertEquals("O is winner", actual);
    }

    @Test
    public void whenPlayAndWholeTopLeftDiagonalLineThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(3, 1); // O
        String actual = ticTacToe.play(3, 3); // X
        assertEquals("X is winner", actual);
    }

    @Test
    public void whenPlayAndWholeBottomRightDiagonalThenWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 3); // O
        ticTacToe.play(1, 2); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(2, 1); // X
        String actual = ticTacToe.play(3, 1); // O
        assertEquals("O is winner", actual);
    }

    @Test
    public void whenAllCellsAreFilledThenDraw() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(1, 3); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(2, 3); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(3, 3); // O
        String actual = ticTacToe.play(3, 2); // X
        assertEquals("The result is draw", actual);
    }

}
