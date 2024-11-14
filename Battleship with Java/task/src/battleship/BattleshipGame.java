package battleship;

public class BattleshipGame {

    private final int lengthField = 10;
    private final int widthField = 10;

    private final UserInterface userInterface = new UserInterface();

    BattleField player1Field = new BattleField();
    BattleField player2Field = new BattleField();

    public BattleshipGame(){}

    public void game(){
        userInterface.displayPlayer1PlaceShipMessage();
        prepareField(player1Field);

        userInterface.displayPassMoveMessage();

        userInterface.displayPlayer2PlaceShipMessage();
        prepareField(player2Field);

        do {
            userInterface.displayPassMoveMessage();
            player2Field.printHiddenField();;
            userInterface.displayFieldsSeparator();
            player1Field.printField();

            userInterface.displayPlayer1TurnMessage();
            fire(player2Field);

            if(isTheGameFinished()){
                break;
            }

            userInterface.displayPassMoveMessage();
            player1Field.printHiddenField();;
            userInterface.displayFieldsSeparator();
            player2Field.printField();

            userInterface.displayPlayer2TurnMessage();
            fire(player1Field);
        } while(!isTheGameFinished());
        userInterface.displayWinnerPrompt();
    }

    public int[] getShot() {
        return userInterface.getUserShot();
    }

    public void prepareField(BattleField field){
        field.createEmptyField(lengthField,widthField);
        field.printField();
        field.setAllShips();
    }

    public void fire(BattleField field){
        char myShotResult = 'U';
//        do {
            int[] myShotCoordinates = getShot();
            myShotResult = field.getShotResult(myShotCoordinates);
            field.updateField(myShotCoordinates, myShotResult);
            field.displayShotResult(myShotResult);
//        }while(myShotResult != 'M' && !isTheGameFinished());
    }

    private boolean isTheGameFinished(){
        return player1Field.isAllShipsSunk() || player2Field.isAllShipsSunk();
    }
}
