import java.text.SimpleDateFormat;

/**
 * Created by Maria on 08.08.18.
 */

public final class Constants  {

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
}
