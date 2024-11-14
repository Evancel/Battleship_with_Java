package battleship;

import java.util.List;

public class BattleField {
    private char[][] field;
    private final UserInterface userInterface = new UserInterface();

    public BattleField() {
    }

    public void createEmptyField(int lengthField, int widthField ) {
        field = new char[lengthField][widthField];
        for (int i = 0; i < lengthField; i++) {
            for (int j = 0; j < widthField; j++) {
                field[i][j] = '~';
            }
        }
    }

    public void placeShip(List<int[]> cells) {
        for (int[] cell : cells) {
            field[cell[0]][cell[1]] = 'O';
        }
    }

    public void updateField(int[] shotCoordinates, char shotResult) {
        if (shotResult == 'S') {
            shotResult = 'X';
        }
        field[shotCoordinates[0]][shotCoordinates[1]] = shotResult;
    }

    private boolean isShipLocationValid(List<int[]> shipCells) {
        for (int[] cell : shipCells) {
            int i = cell[0];
            int j = cell[1];
            if (field[i][j] != '~'
                    || field[Math.min(i + 1, field.length - 1)][j] != '~'
                    || field[Math.max(i - 1, 0)][j] != '~'
                    || field[i][Math.min(j + 1, field.length - 1)] != '~'
                    || field[i][Math.max(j - 1, 0)] != '~') {
                return false;
            }
        }
        return true;
    }

    public void printField() {
        userInterface.printField(field);
    }

    public void printHiddenField(){
        userInterface.printHiddenField(field);
    }

    public void setAllShips() {
        for (TypeShips type : TypeShips.values()) {
            Ship ship = addNewShip(type.getName(), type.getSize());
            if (ship == null) {
                System.out.println("NPE! The created object of the Ship equals to null.");
            }
        }
    }

    private Ship addNewShip(String name, int expectedSize) {
        List<int[]> currentCoordinates;
        Ship ship = null;
        userInterface.displayInputShipCoordinatesPrompt(name, expectedSize);
        do {
            currentCoordinates = userInterface.inputCoordinates(expectedSize);

            if (currentCoordinates != null) {
                ship = new Ship(currentCoordinates);
                ship.setSize();
                ship.setPartsInFieldCoordinates();
                List<int[]> cells = ship.getPartsInFieldCoordinates();
                if (isShipLocationValid(cells)) {
                    placeShip(cells);
                    printField();
                } else {
                    userInterface.displayInputInvalidPlace();
                    ship = null;
                    currentCoordinates = null;
                }
            }
        } while (currentCoordinates == null);
        return ship;
    }

    public char getShotResult(int[] shotCoordinates) {
        char result = 'U';
        switch (field[shotCoordinates[0]][shotCoordinates[1]]) {
            case '~':
                field[shotCoordinates[0]][shotCoordinates[1]] = 'M';
                result = 'M';
                break;
            case 'O':
                int i = shotCoordinates[0];
                int j = shotCoordinates[1];
                if ((field[Math.min(i + 1, 9)][j] == 'X'
                        || field[Math.max(i - 1, 0)][j] == 'X'
                        || field[i][Math.min(j + 1, 9)] == 'X'
                        || field[i][Math.max(j - 1, 0)] == 'X') &&
                        ((field[Math.min(i + 1, 9)][j] != 'O' || Math.min(i + 1, 9) == i)
                                && (field[Math.max(i - 1, 0)][j] != 'O' || Math.max(i - 1, 0) == i)
                                && (field[i][Math.min(j + 1, 9)] != 'O' || Math.min(j + 1, 9) == j)
                                && (field[i][Math.max(j - 1, 0)] != 'O' || Math.max(j - 1, 0) == j))) {
                    result = 'S';
                } else {
                    result = 'X';
                }

                field[shotCoordinates[0]][shotCoordinates[1]] = 'X';
                break;
            case 'X':
                result = 'X';
                break;
            case 'M':
                result = 'M';
                break;
            default:
                userInterface.displayInputInvalidShot();
                break;
        }
        return result;
    }

    public void displayShotResult(char result) {
        switch (result) {
            case 'M' -> userInterface.displayMissPrompt();
            case 'X' -> userInterface.displayHitShipPrompt();
            case 'S' -> userInterface.displayShipSankPrompt();
        }
    }

    public boolean isAllShipsSunk() {
        int counterX = 0;
        int totalShipsSize = 0;
        for (TypeShips ship : TypeShips.values()) {
            totalShipsSize += ship.getSize();
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 'X') {
                    ++counterX;
                }

                if (counterX == totalShipsSize) {
                    return true;
                }
            }
        }
        return false;
    }
}