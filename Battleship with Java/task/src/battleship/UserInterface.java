package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner userInputScanner = new Scanner(System.in);

    public final static String INPUT_SHIP_COORDINATES_FORMAT = "Enter the coordinates of the ";
    public final static String FIELDS_SEPARATOR = "---------------------";
    public final static String PLAYER1_TURN_MESSAGE = "Player 1, it's your turn:";
    public final static String PLAYER1_PLACE_SHIP_MESSAGE = "Player 1, place your ships on the game field";
    public final static String PLAYER2_TURN_MESSAGE = "Player 2, it's your turn:";
    public final static String PLAYER2_PLACE_SHIP_MESSAGE = "Player 2, place your ships on the game field";
    public final static String PASS_MOVE_MESSAGE1 = "Press Enter and pass the move to another player";
    public final static String HIT_SHIP_PROMPT = "You hit a ship!";
    public final static String MISS_PROMPT = "You missed!";
    public final static String WINNER_PROMPT = "You sank the last ship. You won. Congratulations!";
    public final static String SHIP_SANK_PROMPT = "You sank a ship!";
    public final static String INPUT_INVALID_COORDINATES = "Error. You input the invalid coordinates! Try again:\n";
    public final static String INPUT_INVALID_LOCATION = "Error! Wrong ship location! Try again:\n";
    public final static String INPUT_INVALID_LENGTH = "Error! Wrong length of the Submarine! Try again:\n";
    public final static String INPUT_INVALID_PLACE = "Error! You placed it too close to another one. Try again:\n";
    public final static String INPUT_INVALID_SHOT = "Error! You've already shot here. Try again: \n";


    public UserInterface() {
    }

    public void printField(char[][] field) {
        System.out.println();
        for (int i = 1; i <= field.length; i++) {
            if (i == 1) {
                System.out.print(" ");
            }
            System.out.print(" " + i);
        }
        System.out.println();
        char firstLetter = 'A';

        for (int i = 0; i < field.length; i++) {
            System.out.print(firstLetter);
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
            ++firstLetter;
        }
        System.out.println();
    }

    public void printHiddenField(char[][] field) {
        System.out.println();
        for (int i = 1; i <= field.length; i++) {
            if (i == 1) {
                System.out.print(" ");
            }
            System.out.print(" " + i);
        }
        System.out.println();
        char firstLetter = 'A';

        for (int i = 0; i < field.length; i++) {
            System.out.print(firstLetter);
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 'O'){
                    System.out.print(" ~");
                }else {
                    System.out.print(" " + field[i][j]);
                }
            }
            System.out.println();
            ++firstLetter;
        }
        System.out.println();
    }

    public List<int[]> inputCoordinates(int shipSize) {
        List<int[]> cellCoordinates = new ArrayList<>();
        String[] userInput = userInputScanner.nextLine().split(" ");

        if (!isInputCoordinatesValid(userInput)) {
            System.out.println(INPUT_INVALID_COORDINATES);
            return null;
        }

        for (int i = 0; i < userInput.length; i++) {
            cellCoordinates.add(new int[]{(int) userInput[i].charAt(0) - 65,
                    Integer.parseInt(userInput[i].substring(1)) - 1});
        }

        if (!isInputLocationValid(cellCoordinates)) {
            System.out.println(INPUT_INVALID_LOCATION);
            return null;
        }

        if (!isInputLengthValid(cellCoordinates, shipSize)) {
            System.out.println(INPUT_INVALID_LENGTH);
            return null;
        }

        return cellCoordinates;
    }

    public int[] getUserShot() {
        int[] shotCoordinates;
        do {
            String userShot;
            do {
                userShot = userInputScanner.nextLine();
            } while(userShot.isEmpty());
            shotCoordinates = new int[]{(int) userShot.charAt(0) - 65,
                    Integer.parseInt(userShot.substring(1)) - 1};
            if (!isInputShotCoordinatesValid(shotCoordinates)) {
                System.out.println(INPUT_INVALID_COORDINATES);
            }
        }while(!isInputShotCoordinatesValid(shotCoordinates));

        return shotCoordinates;
    }

    public void displayInputShipCoordinatesPrompt (String shipName, int shipSize){
        System.out.printf(INPUT_SHIP_COORDINATES_FORMAT + shipName + " (" + shipSize + " cells):\n\n");
    }

    public void displayPlayer1PlaceShipMessage() {
        System.out.println(PLAYER1_PLACE_SHIP_MESSAGE);
    }

    public void displayPlayer2PlaceShipMessage() {
        System.out.println(PLAYER2_PLACE_SHIP_MESSAGE);
    }

    public void displayPassMoveMessage() {
        System.out.print(PASS_MOVE_MESSAGE1);
        userInputScanner.nextLine();
    }

    public void displayFieldsSeparator() {
        System.out.println(FIELDS_SEPARATOR);
    }

    public void displayPlayer1TurnMessage() {
        System.out.println(PLAYER1_TURN_MESSAGE);
    }

    public void displayPlayer2TurnMessage() {
        System.out.println(PLAYER2_TURN_MESSAGE);
    }

    public void displayHitShipPrompt() {
        System.out.println(HIT_SHIP_PROMPT);
    }

    public void displayMissPrompt() {
        System.out.println(MISS_PROMPT);
    }

    public void displayWinnerPrompt() {
        System.out.println(WINNER_PROMPT);
    }

    public void displayShipSankPrompt() {
        System.out.println(SHIP_SANK_PROMPT);
    }

    public void displayInputInvalidPlace() {
        System.out.println(INPUT_INVALID_PLACE);
    }

    public void displayInputInvalidShot() {
        System.out.println(INPUT_INVALID_SHOT);
    }

    private boolean isInputCoordinatesValid(String[] userInput) {
        return userInput.length == 2
                && userInput[0].matches("[A-J]([1-9]|10)")
                && userInput[1].matches("[A-J]([1-9]|10)");
    }

    private boolean isInputLocationValid(List<int[]> coordinates) {
        return coordinates.getFirst()[0] == coordinates.getLast()[0]
                || coordinates.getFirst()[1] == coordinates.getLast()[1];
    }

    private boolean isInputLengthValid(List<int[]> coordinates, int expectedSize) {
        int currSize = 0;
        if (coordinates.getFirst()[0] == coordinates.getLast()[0]) {
            currSize = Math.abs(coordinates.getFirst()[1] - coordinates.getLast()[1]) + 1;
        } else if (coordinates.getFirst()[1] == coordinates.getLast()[1]) {
            currSize = Math.abs(coordinates.getFirst()[0] - coordinates.getLast()[0]) + 1;
        } else {
            currSize = -1;
        }

        return currSize == expectedSize;
    }

    private boolean isInputShotCoordinatesValid(int[] shotCoordinates) {
        return shotCoordinates.length == 2
                && shotCoordinates[0] >= 0 && shotCoordinates[0] <= 9
                && shotCoordinates[1] >= 0 && shotCoordinates[1] <= 9;
    }
}