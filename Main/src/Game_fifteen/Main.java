package Game_fifteen;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] board = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15 ,0},
        };
        GameSolver solver = new GameSolver(board);
        solver.setSize(4);
        if (solver.isSolvable()){
            solver.shuffle();
        }
        else{
            System.out.println("unsolvable solution");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean isGone = true;
        while (isGone){
            solver.display();
            System.out.println("Choose a direction:\n"+
                    "1-up\n"+
                    "2-down\n"+
                    "3-left\n"+
                    "4-right\n"+
                    "5-to exit"
            );
            System.out.print("you choose:");
            int move = scanner.nextInt();
            switch(move)
            {
                case 1 -> {
                    solver.caseUp();
                    break;
                }
                case 2 -> {
                    solver.caseDown();
                    break;
                }
                case 3 -> {
                    solver.caseLeft();
                    break;
                }
                case 4 -> {
                    solver.caseRight();
                    break;
                }
                case 5 -> isGone = false;
            }
        }

    }
}


