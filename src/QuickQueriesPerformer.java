import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class QuickQueriesPerformer {

    private class QuickWaitingTimeLine {
        private String  serviceLine;
        private String  questionLine;
        private String  responseType;
        private Date    responseDate;
        private Integer waitingTime;

        private QuickWaitingTimeLine (String line) {
            String [] split = line.split(" ");
            if (split.length == 5) {
                this.serviceLine = Validator.addServiceIfValid(split[0]);
                this.questionLine = Validator.addQuestionIfValid(split[1]);
                this.responseType = split[2];
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

    private class QuickQueryLine {
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

        private QuickQueryLine (String line) {
            String [] split = line.split(" ");
            if (split.length == 4) {
                this.serviceLine = split[0].equals("*") ? split[0] : Validator.addServiceIfValid(split[0]);
                this.questionLine = split[1].equals("*") ? split[1] :  Validator.addQuestionIfValid(split[1]);
                this.responseType = split[2];
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

    public boolean matchLine(QuickWaitingTimeLine waitingTimeLine, QuickQueryLine query){
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

    public void quickQueryExecute(ArrayList<QuickWaitingTimeLine> timeLines, QuickQueryLine query){
        if (timeLines == null || timeLines.size() == 0) {
            System.out.println("-");
        }
        else {
            Long totalTime = (long)0;
            Integer numberOfMatchingQueries = 0;
            for (QuickWaitingTimeLine waitingTimeLine: timeLines) {
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
        ArrayList<QuickWaitingTimeLine> quickTimeLines = new ArrayList<>();

        for (String inputLine: allInputLines) {

            if (inputLine.startsWith("C ")){
                quickTimeLines.add(new QuickWaitingTimeLine(inputLine.substring(2)));
            }
            else if (inputLine.startsWith("D ")){
                QuickQueryLine newQQL = new QuickQueryLine(inputLine.substring(2));
                quickQueryExecute(quickTimeLines, newQQL);
            }
            else
                throw new IllegalArgumentException("The input line has wrong format: " + inputLine);
        }
    }

}
