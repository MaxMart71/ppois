package Game_fifteen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private int [][] boardForTestCase;
    private int [][] boardForShuffleCase;
    private int[][] solvedBoard;
    private int[][] unsolvableBoard;
    private GameSolver solver;

    @Before
    public void setUp() {
        solvedBoard = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        unsolvableBoard = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 12, 11}, {13, 14, 15, 0}};
        boardForTestCase = new int[][]{{2, 4, 6, 8},{3, 1, 0, 12},{9, 15, 14, 7 },{11, 5, 10, 13}};
        boardForShuffleCase = new int[][]{{2, 4, 6, 8},{3, 1, 0, 12},{9, 15, 14, 7 },{11, 5, 10, 13}};

        solver = new GameSolver(boardForTestCase);
        solver.setSize(4);
    }

    @Test
    public void testIsSolved() {
        solver = new GameSolver(solvedBoard);
        assertTrue(solver.isSolved());
    }

    @Test
    public void testIsSolvable() {
        assertTrue(solver.isSolvable());
    }

    @Test
    public void testIsUnsolvable() {
        solver = new GameSolver(unsolvableBoard);
        solver.setSize(4);
        assertFalse(solver.isSolvable());
    }

    @Test
    public void testShuffle() {
        solver = new GameSolver(boardForShuffleCase);
        solver.shuffleWithRandom();
        assertNotEquals(solver.board, boardForTestCase);
    }

    @Test
    public void testCaseUp() {
        solver.caseUp();
        assertEquals(0, solver.board[2][2]);
        assertEquals(14, solver.board[1][2]);
    }

    @Test
    public void testCaseDown() {
        solver.caseDown();
        assertEquals(0, solver.board[0][2]);
        assertEquals(6, solver.board[1][2]);
    }

    @Test
    public void testCaseLeft() {
        solver.caseLeft();
        assertEquals(0, solver.board[1][3]);
        assertEquals(12, solver.board[1][2]);
    }

    @Test
    public void testCaseRight() {
        solver.caseRight();
        assertEquals(0, solver.board[1][1]);
        assertEquals(1, solver.board[1][2]);
    }
}