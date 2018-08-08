import java.io.IOException;
import java.util.ArrayList;

public class Task {

    public static void main(String[] args) {

        QueriesReader qReader = new QueriesReader();

        /*
        **       You can read input from command line using:
        **
        **       ArrayList<String> lines = qReader.readFromCommandLine();
        */

        ArrayList<String> lines = null;
        try {
            lines = qReader.readFromFile("../input");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        QueriesPerformer qPerformer = new QueriesPerformer();
        qPerformer.performQueries(lines);
    }
}
