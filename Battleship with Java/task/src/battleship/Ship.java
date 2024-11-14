package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<int[]> coordinates = new ArrayList<>();
    private int size;
    private List<String> partsInUserCoordinates = new ArrayList<>();
    private List<int[]> partsInFieldCoordinates = new ArrayList<>();

    public Ship(){};
    public Ship(List<int[]> coordinates){
        this.coordinates = coordinates;
    };
    public int getSize(){
        return size;
    }
    public void setSize(){
        if(coordinates.getFirst()[0] == coordinates.getLast()[0]){
            size = Math.abs(coordinates.getFirst()[1] - coordinates.getLast()[1]) + 1;
        }else if(coordinates.getFirst()[1] == coordinates.getLast()[1]){
            size = Math.abs(coordinates.getFirst()[0] - coordinates.getLast()[0]) + 1;
        }else{
            size = -1;
        }
    }

    public void printSize(){
        System.out.println("Length: " + size);
    }

    public List<String> getPartsInUserCoordinates(){
        return partsInUserCoordinates;
    }

    public void setPartsInUserCoordinates(){
        if(coordinates.getFirst()[0] == coordinates.getLast()[0]){
            char letter = (char)(coordinates.getFirst()[0] + 65);
            if(coordinates.getFirst()[1] < coordinates.getLast()[1]){
                for(int i = 0; i < size; i++){
                    int number = coordinates.getFirst()[1] + 1 + i;
                    partsInUserCoordinates.add("" + letter + number);
                }
            } else{
                for(int i = 0; i < size; i++) {
                    int number = coordinates.getFirst()[1] + 1 - i;
                    partsInUserCoordinates.add("" + letter + number);
                }
            }
        }else if(coordinates.getFirst()[1] == coordinates.getLast()[1]){
            int number = coordinates.getFirst()[1] + 1;
            if(coordinates.getFirst()[0] < coordinates.getLast()[0]){
                for(int i = 0; i < size; i++){
                    char letter = (char)(coordinates.getFirst()[0] + 65 + i);
                    partsInUserCoordinates.add("" + letter + number);
                }
            }else{
                for(int i = 0; i < size; i++){
                    char letter = (char)(coordinates.getFirst()[0] + 65 - i);
                    partsInUserCoordinates.add("" + letter + number);
                }
            }
        }
    }

    public void printParts(){
        System.out.print("Parts:");
        for(String part : partsInUserCoordinates){
            System.out.print(" " + part);
        }
        System.out.println();
    }

    public List<int[]> getPartsInFieldCoordinates(){
        return partsInFieldCoordinates;
    }

    public void setPartsInFieldCoordinates(){
        if(coordinates.getFirst()[0] == coordinates.getLast()[0]){
            int letter = coordinates.getFirst()[0];
            if(coordinates.getFirst()[1] < coordinates.getLast()[1]){
                for(int i = 0; i < size; i++){
                    int number = coordinates.getFirst()[1] + i;
                    partsInFieldCoordinates.add(new int[]{letter,number});
                }
            } else{
                for(int i = 0; i < size; i++) {
                    int number = coordinates.getFirst()[1] - i;
                    partsInFieldCoordinates.add(new int[]{letter,number});
                }
            }
        }else if(coordinates.getFirst()[1] == coordinates.getLast()[1]){
            int number = coordinates.getFirst()[1];
            if(coordinates.getFirst()[0] < coordinates.getLast()[0]){
                for(int i = 0; i < size; i++){
                    int letter = coordinates.getFirst()[0] + i;
                    partsInFieldCoordinates.add(new int[]{letter,number});
                }
            }else{
                for(int i = 0; i < size; i++){
                    int letter = coordinates.getFirst()[0] - i;
                    partsInFieldCoordinates.add(new int[]{letter,number});
                }
            }
        }
    }
}
