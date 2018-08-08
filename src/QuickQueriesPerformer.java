import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class QuickQueriesPerformer {

    private class WaitingTimeLine {
        private String  serviceLine;
        private String  questionLine;
        private String  responseType;
        private Date    responseDate;
        private Integer waitingTime;

        private WaitingTimeLine(String line) {
            String [] split = line.split(" ");
            if (split.length == 5) {
                this.serviceLine = Validator.addServiceIfValid(split[0]);
                this.questionLine = Validator.addQuestionIfValid(split[1]);
                this.responseType = Validator.addResponseTypeIfValid(split[2]);
                try {
                    this.responseDate = Constants.DATE_FORMAT.parse(split[3]);
                } catch (ParseException e) {
                    System.out.println("Wrong date format: " + split[3]);
                }
                this.waitingTime = Integer.parseInt(split[4]);
            }
            else
                throw new IllegalArgumentException("Wrong format of waiting time line: " + "C " + line);
        }

    }

    private class QueryLine {
        private String  serviceLine;
        private String  questionLine;
        private String  responseType;
        private Date    dateFrom;
        private Date    dateTo;

        private void parseDates(String dates) throws ParseException {
            String [] split = dates.split("-");

            if (split.length == 1) {
                this.dateFrom = Constants.DATE_FORMAT.parse(split[0]);
                this.dateTo = null;
            }
            else if (split.length == 2) {
                this.dateFrom = Constants.DATE_FORMAT.parse(split[0]);
                this.dateTo = Constants.DATE_FORMAT.parse(split[1]);
            }
            else
                throw new IllegalArgumentException("Invalid date line: " + dates);
        }

        private QueryLine(String line) {
            String [] split = line.split(" ");
            if (split.length == 4) {
                this.serviceLine = split[0].equals("*") ? split[0] : Validator.addServiceIfValid(split[0]);
                this.questionLine = split[1].equals("*") ? split[1] :  Validator.addQuestionIfValid(split[1]);
                this.responseType = Validator.addResponseTypeIfValid(split[2]);
                try {
                    parseDates(split[3]);
                } catch (ParseException e) {
                    System.out.println("Wrong date format: " + split[3]);
                }
            }
            else
                throw new IllegalArgumentException("Wrong format of waiting time line: " + "D " + line);
        }

    }

    public boolean matchesParameter(String timeLine, String query) {
        if (query.equals("*"))
            return true;
        if (timeLine.startsWith(query))
            return true;
        return false;
    }

    public boolean matchLine(WaitingTimeLine waitingTimeLine, QueryLine query){
        if (matchesParameter(waitingTimeLine.serviceLine, query.serviceLine)) {
            if (matchesParameter(waitingTimeLine.questionLine, query.questionLine)) {
                if (waitingTimeLine.responseType.equals(query.responseType)) {
                    if (waitingTimeLine.responseDate.after(query.dateFrom) || waitingTimeLine.responseDate.equals(query.dateFrom)) {
                        if (query.dateTo == null)
                            return true;
                        else if (waitingTimeLine.responseDate.before(query.dateTo) || waitingTimeLine.responseDate.equals(query.dateTo))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public void quickQueryExecute(ArrayList<WaitingTimeLine> timeLines, QueryLine query){
        if (timeLines == null || timeLines.size() == 0) {
            System.out.println("-");
        }
        else {
            Long totalTime = (long)0;
            Integer numberOfMatchingQueries = 0;
            for (WaitingTimeLine waitingTimeLine: timeLines) {
                if (matchLine(waitingTimeLine, query))
                {
                    totalTime += waitingTimeLine.waitingTime;
                    numberOfMatchingQueries++;
                }
            }
            System.out.println(numberOfMatchingQueries == 0 ? "-" : totalTime/numberOfMatchingQueries);
        }
    }

    public void performQueries(ArrayList<String> allInputLines){
        ArrayList<WaitingTimeLine> quickTimeLines = new ArrayList<>();

        for (String inputLine: allInputLines) {

            if (inputLine.startsWith("C ")){
                quickTimeLines.add(new WaitingTimeLine(inputLine.substring(2)));
            }
            else if (inputLine.startsWith("D ")){
                QueryLine newQQL = new QueryLine(inputLine.substring(2));
                quickQueryExecute(quickTimeLines, newQQL);
            }
            else
                throw new IllegalArgumentException("The input line has wrong format: " + inputLine);
        }
    }

}
