
public class Validator {

    public static String addServiceIfValid(String line)
    {
        if (line == null)
            throw new IllegalArgumentException("Line can not be null!");

        String[] split = line.split("\\.");

        if (split.length == 0 || split.length > 2)
            throw new IllegalArgumentException("Wrong service line: " + line);
        else {
            Integer serviceID = Integer.parseInt(split[0]);
            if (serviceID <= 0 || serviceID > Constants.NUMBER_OF_SERVICES)
                throw new IllegalArgumentException("Service index must be between 1 and " + Constants.NUMBER_OF_SERVICES);
            if (split.length == 2) {
                Integer serviceVariationID = Integer.parseInt(split[1]);
                if (serviceVariationID <= 0 || serviceVariationID > Constants.NUMBER_OF_SERVICE_VARIATIONS)
                    throw new IllegalArgumentException("Service variation index must be between 1 and " + Constants.NUMBER_OF_SERVICE_VARIATIONS);
            }
        }
        return line;
    }

    public static String addQuestionIfValid(String line){
        if (line == null)
            throw new IllegalArgumentException("Line can not be null!");

        String[] split = line.split("\\.");
        if (split.length == 0 || split.length > 3)
            throw new IllegalArgumentException("Wrong question line: " + line);
        else {
            Integer questionID = Integer.parseInt(split[0]);
            if (questionID <= 0 || questionID > Constants.NUMBER_OF_QUESTION_TYPES)
                throw new IllegalArgumentException("Question index must be between 1 and " + Constants.NUMBER_OF_QUESTION_TYPES);
            if (split.length == 2 || split.length == 2)
            {
                Integer questionCategoryID = Integer.parseInt(split[1]);
                if (questionCategoryID <= 0 || questionCategoryID > Constants.NUMBER_OF_QUESTION_CATEGORIES)
                    throw new IllegalArgumentException("Question category index must be between 1 and " + Constants.NUMBER_OF_QUESTION_CATEGORIES);

                if (split.length == 3) {
                    Integer questionSubcategoryID = Integer.parseInt(split[2]);
                    if (questionSubcategoryID <= 0 || questionSubcategoryID > Constants.NUMBER_OF_QUESTION_SUBCATEGORIES)
                        throw new IllegalArgumentException("Service variation index must be between 1 and " + Constants.NUMBER_OF_QUESTION_SUBCATEGORIES);
                }
            }
        }
        return line;
    }

    public static String addResponseTypeIfValid(String line){
        if (line == null)
            throw new IllegalArgumentException("Line can not be null!");

        for (int i = 0; i < Constants.RESPONSE_TYPES.length; i++) {
            if (line.equals(Constants.RESPONSE_TYPES[i]))
                return line;
        }
        throw new IllegalArgumentException("Wrong response type: " + line);
    }
}
