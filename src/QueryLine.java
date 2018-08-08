import java.text.ParseException;
import java.util.Date;

/**
 * Created by Maria on 08.08.18.
 */
public class QueryLine {

    private Integer     service_ID;
    private Integer     serviceVariation_ID;
    private Integer     question_ID;
    private Integer     questionCategory_ID;
    private Integer     questionSubcategory_ID;
    private char        responseType;
    private Date        dateFrom;
    private Date        dateTo;

    private QueryLine() {};

    public static class Builder {
        private QueryLine newQueryLine;

        public Builder() {
            newQueryLine = new QueryLine();
        }

        public Builder withServiceIndex(Integer serviceIndex){
            if (serviceIndex == null)
                throw new IllegalArgumentException("Service index can not be null");
            if (serviceIndex <= 0 || serviceIndex > Constants.NUMBER_OF_SERVICES)
                throw new IllegalArgumentException("Service index must be between 1 and " + Constants.NUMBER_OF_SERVICES);
            newQueryLine.service_ID = serviceIndex;
            return this;
        }

        public Builder withServiceVariationIndex(Integer serviceVariationIndex){
            if (serviceVariationIndex != null)
                if (serviceVariationIndex <= 0 || serviceVariationIndex > Constants.NUMBER_OF_SERVICE_VARIATIONS)
                    throw new IllegalArgumentException("Service variations index must be between 1 and " + Constants.NUMBER_OF_SERVICE_VARIATIONS + " or null");
            newQueryLine.serviceVariation_ID = serviceVariationIndex;
            return this;
        }

        public Builder withQuestionIndex(Integer questionIndex){
            if (questionIndex == null)
                throw new IllegalArgumentException("Question index can not be null");
            if (questionIndex <= 0 || questionIndex > Constants.NUMBER_OF_QUESTION_TYPES)
                throw new IllegalArgumentException("Question index must be between 1 and " + Constants.NUMBER_OF_QUESTION_TYPES);
            newQueryLine.question_ID = questionIndex;
            return this;
        }

        public Builder withQuestionCategoryIndex(Integer questionCategoryIndex){
            if (questionCategoryIndex != null){
                if (questionCategoryIndex <= 0 || questionCategoryIndex > Constants.NUMBER_OF_QUESTION_CATEGORIES)
                    throw new IllegalArgumentException("Question category index must be between 1 and " + Constants.NUMBER_OF_QUESTION_CATEGORIES + " or null");
            }
            newQueryLine.questionCategory_ID = questionCategoryIndex;
            return this;
        }

        public Builder withQuestionSubcategoryIndex(Integer questionSubcategoryIndex){
            if (questionSubcategoryIndex != null){
                if (questionSubcategoryIndex <= 0 || questionSubcategoryIndex > Constants.NUMBER_OF_QUESTION_SUBCATEGORIES)
                    throw new IllegalArgumentException("Question subcategory index must be between 1 and " + Constants.NUMBER_OF_QUESTION_SUBCATEGORIES + " or null");
            }
            newQueryLine.questionSubcategory_ID = questionSubcategoryIndex;
            return this;
        }

        //TO ENUM
        public Builder withResponceType(char type){
            if (type != 'P' && type != 'N')
                throw new IllegalArgumentException("Type must be between P or N: " + type);
            newQueryLine.responseType = type;
            return this;
        }

        public Builder withResponseDate(String date){
            if (date == null)
                throw new IllegalArgumentException("Date string can not be null");
            try {
                newQueryLine.responseDate = Constants.DATE_FORMAT.parse(date);
            } catch (ParseException e) {
                System.out.println("Wrong date format: " + date + ". " + Constants.DATE_FORMAT.toPattern() + " expected");
            }
            return this;
        }

        public Builder withWaitingTime(Integer waitingTime){
            if (waitingTime == null)
                throw new IllegalArgumentException("Waiting time can not be null");
            if (waitingTime < 0)
                throw new IllegalArgumentException("Waiting time can not be less than zero: " + waitingTime);
            newQueryLine.waitingTime = waitingTime;
            return this;
        }

        public WaitingTimeLine build() { return newQueryLine; }
    }
}
