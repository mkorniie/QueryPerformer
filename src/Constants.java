import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public final class Constants  {

    public static Parameter
    public static Parameter SERVICES = new Parameter();

    public class Line {
        Integer numberOfParameters;
        ArrayList<Parameter> lineParameters;

        public Line (Parameter ... parameters) {
            if (parameters.length == 0)
                throw new IllegalArgumentException("Line must have at least one parameter!");
            this.numberOfParameters = parameters.length;
            for (: parameters) {

            }
        }

    }

    public static int MAX_NUMBER_OF_LINES = 100000;
    public static int NUMBER_OF_SERVICES = 10;
    public static int NUMBER_OF_SERVICE_VARIATIONS = 3;
    public static int NUMBER_OF_QUESTION_TYPES = 10;
    public static int NUMBER_OF_QUESTION_CATEGORIES = 20;
    public static int NUMBER_OF_QUESTION_SUBCATEGORIES = 5;

    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("dd.MM.yyyy");

    private Constants(){
        throw new AssertionError();
    }

    private class Parameter {
        private String  name;
        private int     upperLimit;
        private int     lowerLimit;
        Parameter       subParameter;
    }


}
