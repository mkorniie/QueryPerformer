import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QueriesReader {

    /*
    *  QueriesReader has two options - you can get the lines from command line by using readFromCommandLine() function,
    *  or read from a file using readFromFile(String fileName) function.
    */

    private static ArrayList<String> readInputLines(Integer numberOfLines){
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

    private static ArrayList<String> readInputLines(Integer numberOfLines, BufferedReader bufferedReader) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String line;

        for (int i = 0; i < numberOfLines; i++) {
            if((line = bufferedReader.readLine()) == null)
                throw new InputMismatchException("Less number of lines read then specified. Read: " + i + " | Expected: " + numberOfLines);
            else
                result.add(line);
        }
        return result;
    }


    private Integer readNumberOfLines(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter number of lines:");
        if (scan.hasNextInt()) {
            int result = scan.nextInt();
            if (result > 0 && result <= Constants.MAX_NUMBER_OF_LINES)
                return result;
            else
                throw new InvalidParameterException("Number of lines should be between 1 and " + Constants.MAX_NUMBER_OF_LINES + " . You entered: " + result);
        }
        else
            throw new IllegalArgumentException("You didn't enter integer value!");
    }

    private Integer readNumberOfLines(BufferedReader bufferedReader) throws IOException {
        String line;
        if((line = bufferedReader.readLine()) == null)
            throw new InvalidParameterException("File has wrong format!");
        return Integer.parseInt(line);
    }


    public ArrayList<String> readFromCommandLine(){
        int numberOfLines = readNumberOfLines();
        return (readInputLines(numberOfLines));
    }

    public ArrayList<String> readFromFile(String fileName) throws FileNotFoundException, IOException{
         FileReader fr = new FileReader(fileName);
         BufferedReader bufferedReader = new BufferedReader(fr);

        Integer numberOfLines = readNumberOfLines(bufferedReader);
        ArrayList<String> result = readInputLines(numberOfLines, bufferedReader);
        bufferedReader.close();
        fr.close();
        return (result);
    }

}
