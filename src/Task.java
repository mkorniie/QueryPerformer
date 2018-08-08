import java.io.IOException;
import java.util.ArrayList;

public class Task {
    public class Environment {

    }

    public void setUpEnvironment(ArrayList<Line> lines) {

    }

    public static void main(String[] args) {
        setUpEnvironment();


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

        QuickQueriesPerformer qPerformer = new QuickQueriesPerformer();
        qPerformer.performQueries(lines);
    }
}
