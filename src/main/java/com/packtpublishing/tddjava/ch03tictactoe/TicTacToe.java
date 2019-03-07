package com.packtpublishing.tddjava.ch03tictactoe;

/**
 * Problem: TicTacToe
 * <p>
 * 1. A piece can be placed on any empty space of a 3×3 board.
 * 2. There should be a way to ind out which player should play next.
 * 3. A player wins by being the first to connect a line of friendly
 * pieces from one side or corner of the board to the other.
 * 4. The result is a draw when all the boxes are filled.
 */

class TicTacToe {

    private static final int SIZE = 3;

    private final char[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}
    };

    private char player;

    String play(int x, int y) {
        player = nextPlayer();

        isCellOccupied(x, y);
        setCell(x, y, player);

        if (isWin(x, y)) {
            return player + " is winner";
        } else if (isDraw()) {
            return "The result is draw";
        } else {
            return "No winner";
        }
    }

    private boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin(int x, int y) {
        return isHorizontalWin(x) || isVerticalWin(y) || isTopLeftDiagonalWin() || isBottomRightDiagonalWin();
    }

    private boolean isTopLeftDiagonalWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isBottomRightDiagonalWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isHorizontalWin(int x) {
        for (int i = 0; i < SIZE; i++) {
            if (board[x - 1][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isVerticalWin(int y) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][y - 1] != player) {
                return false;
            }
        }
        return true;
    }

    private void setCell(int x, int y, char player) {
        board[x - 1][y - 1] = player;
    }

    private void isCellOccupied(int x, int y) {
        checkAxis(x);
        checkAxis(y);

        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Cell already occupied");
        }
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("Axis is outside board.");
        }
    }

    char nextPlayer() {
        if (player == 'X') {
            return 'O';
        }
        return 'X';
    }
}
