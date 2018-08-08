import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QueriesReader {

    private static ArrayList<String> readInputLines(int numberOfLines){
        ArrayList<String> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the lines:");
        for (int i = 0; i < numberOfLines; i++) {
            if (sc.hasNextLine()){
                result.add(sc.nextLine());
            }
            else
                throw new InputMismatchException("Less number of lines read then specified. Read: " + (i+1) + " | Expected: " + numberOfLines);
        }
        return result;
    }


    private int readNumberOfLines(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter number of lines:");
        if (scan.hasNextInt()) {
            int result = scan.nextInt();
            if (result > 0 && result <= Constants.MAX_NUMBER_OF_LINES)
                return result;
            else
                throw new InvalidParameterException("Number of lines should be between 1 and 100000. You entered: " + result);
        }
        else
            throw new IllegalArgumentException("You didn't enter integer value!");
    }

    public ArrayList<String> inputLines(){
        int numberOfLines = readNumberOfLines();
        return (readInputLines(numberOfLines));
    }
}
