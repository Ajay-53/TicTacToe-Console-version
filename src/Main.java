import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    public static void main(String[] args) {

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };


        printGameBoard(gameBoard);

        while (true) {
            System.out.println("Enter your placement (1-9)");
            Scanner scanner = new Scanner(System.in);

            int playerPiece = scanner.nextInt();
            while(playerPositions.contains(playerPiece) || cpuPositions.contains(playerPiece)) {
                System.out.println("Position taken! Enter a valid position.");
                playerPiece = scanner.nextInt();
            }
            placePiece(gameBoard, playerPiece, "player");

            String result = checkWinner();
            if(result.length() > 0){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPiece = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPiece) || cpuPositions.contains(cpuPiece)) {
                cpuPiece = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPiece, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard) {
            for(char col: row){
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int placement, String user){

        char symbol = ' ';
        if(user.equals("player")) {
            playerPositions.add(placement);
            symbol = 'X';
        }
        else if(user.equals("cpu")) {
            cpuPositions.add(placement);
            symbol = 'O';
        }

        switch(placement) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftcol = Arrays.asList(1, 4, 7);
        List midcol = Arrays.asList(2, 5, 8);
        List rightcol = Arrays.asList(3, 6, 9);
        List leftDiag = Arrays.asList(1, 5, 9);
        List rightDiag = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftcol);
        winningConditions.add(midcol);
        winningConditions.add(rightcol);
        winningConditions.add(leftDiag);
        winningConditions.add(rightDiag);

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congratulation! You won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins! Sorry ;-;";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Its a tie!";
            }
        }
        return "";
    }
}