import java.util.ArrayList;

public class QueriesPerformer {
    private ArrayList<String> allInputLines;
    private ArrayList<WaitingTimeLine> waitingTimeLinesList;

    public QueriesPerformer(ArrayList<String> allInputLines){
        if (allInputLines != null)
            this.allInputLines = allInputLines;
        else
            throw new IllegalArgumentException("Lines argument can not be null");
    }

    public static Integer[] parseIDs(String argumentsLine, int numberOfArguments) {
        Integer [] result = new Integer[numberOfArguments];
        String [] serviceArgumentsArray = argumentsLine.split("\\.");

        if (serviceArgumentsArray.length > numberOfArguments || serviceArgumentsArray.length < 1)
            throw new IllegalArgumentException("Invalid argument line: " + argumentsLine + ". Number of arguments should be " + numberOfArguments);
        else
            for (int i = 0; i < serviceArgumentsArray.length; i++)
                result[i] = Integer.parseInt(serviceArgumentsArray[i]);
        return (result);
    }

    public ArrayList<String> getAllInputLines() {
        return allInputLines;
    }

    public static WaitingTimeLine newWaitingTimeLine(String line){
        String [] arr = line.split(" ");
        if (arr.length == 5) {
            Integer [] serviceArguments = parseIDs(arr[0], 2);
            Integer [] questionArguments = parseIDs(arr[1], 3);

            // arguments TO OBJECT
            WaitingTimeLine newLine = new WaitingTimeLine.Builder()
                    .withServiceIndex(serviceArguments[0])
                    .withServiceVariationIndex(serviceArguments[1])
                    .withQuestionIndex(questionArguments[0])
                    .withQuestionCategoryIndex(questionArguments[1])
                    .withQuestionSubcategoryIndex(questionArguments[2])
                    .withResponceType(arr[2].charAt(0))
                    .withResponseDate(arr[3])
                    .withWaitingTime(Integer.parseInt(arr[4]))
                    .build();
            return newLine;
        }
        else
            throw new IllegalArgumentException("Wrong format of waiting time line: " + "C " + line);
    }

    public ArrayList<WaitingTimeLine> findMatchingLines(QueryLine query) {
        ArrayList<WaitingTimeLine> result = new ArrayList<>();

        result = ;
    }

    public Long calculateAverageTime(ArrayList<WaitingTimeLine> matchingLines) {
        if (matchingLines == null || matchingLines.size() == 0)
            return null;
        else {
            Long totalTime = (long)0;
            for (WaitingTimeLine line: matchingLines) {
                     totalTime += line.getWaitingTime();
            }
            return (totalTime / matchingLines.size());
        }
    }

    public void printResult(Integer result) {
        if (result == null)
            System.out.println("-");
        else
            System.out.println(result);
    }

    public void performQueries(){
        waitingTimeLinesList = new ArrayList<>();

        for (String inputLine: allInputLines) {
            if (inputLine.startsWith("C"))
            {
                WaitingTimeLine newWTL = newWaitingTimeLine(inputLine.substring(2));
                waitingTimeLinesList.add(newWTL);
            }
            else if (inputLine.startsWith("D")) {
                QueryLine newQueryLine = newQueryLine(inputLine.substring(2));
                ArrayList<WaitingTimeLine> matchingLines = findMatchingLines(newQueryLine);
                Integer result = calculateAverageTime(matchingLines);
                printResult(result);
            }
        }
    }
}
