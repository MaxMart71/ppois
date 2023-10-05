package Game_fifteen;
import java.util.*;

/**
 * This class implements the game <b><i>fifteen</i></b>.
 * @author <b>Maxim Martysiuk</b>
 */
class GameSolver {
    public int[][] board;
    private int size;
    private int emptyRow, emptyCol;

    public GameSolver(int[][] board) {
        this.board = board;
    }

    public void setSize(int size){
        this.size = size;
    }

    /**
     * Finds the row and column indices of the empty space on the game board.
     */
    private void findEmptyCell() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }
    /**
     * Shuffles the tiles on the game board using Fisher-Yates algorithm
     */
    public void shuffleWithRandom() {
        Random random = new Random();
        for (int i = board.length - 1; i > 0; i--) {
            for (int j = board[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                // Обмен значений
                int temp = board[i][j];
                board[i][j] = board[m][n];
                board[m][n] = temp;
            }
        }
    }
    /**
     *  Moves the tile below the empty space one position up if possible
     */
    public void caseUp(){
        findEmptyCell();
        if (emptyRow < 3){
            board[emptyRow][emptyCol] =  board[emptyRow+1][emptyCol];
            board[emptyRow+1][emptyCol] = 0;
        }
        else{
            System.out.println("you can't move like that");
        }
    }

    /**
     *  Moves the tile above the empty space one position down if possible.
     */public void caseDown(){
        findEmptyCell();
        if (emptyRow > 0){
            board[emptyRow][emptyCol] =  board[emptyRow-1][emptyCol];
            board[emptyRow-1][emptyCol] = 0;
        }
        else{
            System.out.println("you can't move like that");
        }
    }


    /**
     * Moves the tile to the right of the empty space one position left if possible.
     */
    public void caseLeft(){
        findEmptyCell();
        if (emptyCol < 3){
            board[emptyRow][emptyCol] =  board[emptyRow][emptyCol+1];
            board[emptyRow][emptyCol+1] = 0;
        }
        else{
            System.out.println("you can't move like that");
        }
    }
    /**
     * Moves the tile to the left of the empty space one position right if possible.
     */
    public void caseRight(){
        findEmptyCell();
        if (emptyCol > 0){
            board[emptyRow][emptyCol] =  board[emptyRow][emptyCol-1];
            board[emptyRow][emptyCol-1] = 0;
        }
        else{
            System.out.println("you can't move like that");
        }
    }
    /**
     * Checks if the current state of the game board is solved (tiles arranged in ascending order).
     * @return true - if current state of the game board equals to a winning case one.
     */
    public boolean isSolved() {
        // Проверка, является ли текущее состояние пазла решением.
        int value = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != value && (i != size - 1 || j != size - 1)) {
                    return false;
                }
                value++;
            }
        }
        return true;
    }
    /**
     * Checks if the current state of the game board is solvable (can be solved from the current state).
     * @return true - if the transmitted board has a solution.
     */
    public boolean isSolvable() {
        // Проверка, можно ли решить данный пазл.
        int inversionCount = 0;
        int[] boardArray = new int[size * size];

        // Преобразовываем двумерный массив в одномерный.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardArray[i * size + j] = board[i][j];
            }
        }

        for (int i = 0; i < size * size - 1; i++) {
            for (int j = i + 1; j < size * size; j++) {
                if (boardArray[i] != 0 && boardArray[j] != 0 && boardArray[i] > boardArray[j]) {
                    inversionCount++;
                }
            }
        }

        // Если размер доски (size) четный, то наличие четного числа инверсий обязательно.
        if (size % 2 == 0) {
            return (inversionCount % 2 == 0);
        }

        // Если размер доски нечетный, то сумма номера строки, в которой находится пустая плитка (emptyRow),
        // и числа инверсий должна быть четной.
        return ((inversionCount + emptyRow) % 2 == 0);
    }

    /**
     * Print the current state of the game board.
     */
    public void display() {
        // Вывод текущего состояния пазла на экран.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public boolean shuffle(){
        this.shuffleWithRandom();
        while(!this.isSolvable()){
            this.shuffleWithRandom();
        }
        return true;
    }

}
