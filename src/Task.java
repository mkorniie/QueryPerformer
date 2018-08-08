import java.util.ArrayList;

public class Task {

    public static void main(String[] args) {
        QueriesReader qReader = new QueriesReader();
        ArrayList<String> lines = qReader.inputLines();
//
        QueriesPerformer qPerformer = new QueriesPerformer(lines);
        qPerformer.performQueries();

//        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("dd.MM.yyyy");
//
//        Date d = DATE_FORMAT.parse("15.10.2012");
//        System.out.println(DATE_FORMAT.format(d));
//        WaitingTimeLine n = QueriesPerformer.newWaitingTimeLine("1.1 8.15.1 P 15.10.2012 83");
//        System.out.println(n);

//        Integer i = Integer.parseInt("1");
//        System.out.println(i);
    }
}
