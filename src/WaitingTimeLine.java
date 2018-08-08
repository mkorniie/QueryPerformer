import java.text.ParseException;
import java.util.Date;

public class WaitingTimeLine {

    private Integer     service_ID;
    private Integer     serviceVariation_ID;
    private Integer     question_ID;
    private Integer     questionCategory_ID;
    private Integer     questionSubcategory_ID;
    private char        responseType;
    private Date        responseDate;
    private Integer     waitingTime;

    private WaitingTimeLine(){}

    public static class Builder {
        private WaitingTimeLine newWTL;

        public Builder() {
            newWTL = new WaitingTimeLine();
        }

        public Builder withServiceIndex(Integer serviceIndex){
            if (serviceIndex == null)
                throw new IllegalArgumentException("Service index can not be null");
            if (serviceIndex <= 0 || serviceIndex > Constants.NUMBER_OF_SERVICES)
                throw new IllegalArgumentException("Service index must be between 1 and " + Constants.NUMBER_OF_SERVICES);
            newWTL.service_ID = serviceIndex;
            return this;
        }

        public Builder withServiceVariationIndex(Integer serviceVariationIndex){
            if (serviceVariationIndex != null)
                if (serviceVariationIndex <= 0 || serviceVariationIndex > Constants.NUMBER_OF_SERVICE_VARIATIONS)
                    throw new IllegalArgumentException("Service variations index must be between 1 and " + Constants.NUMBER_OF_SERVICE_VARIATIONS + " or null");
            newWTL.serviceVariation_ID = serviceVariationIndex;
            return this;
        }

        public Builder withQuestionIndex(Integer questionIndex){
            if (questionIndex == null)
                throw new IllegalArgumentException("Question index can not be null");
            if (questionIndex <= 0 || questionIndex > Constants.NUMBER_OF_QUESTION_TYPES)
                throw new IllegalArgumentException("Question index must be between 1 and " + Constants.NUMBER_OF_QUESTION_TYPES);
            newWTL.question_ID = questionIndex;
            return this;
        }

        public Builder withQuestionCategoryIndex(Integer questionCategoryIndex){
            if (questionCategoryIndex != null){
                if (questionCategoryIndex <= 0 || questionCategoryIndex > Constants.NUMBER_OF_QUESTION_CATEGORIES)
                    throw new IllegalArgumentException("Question category index must be between 1 and " + Constants.NUMBER_OF_QUESTION_CATEGORIES + " or null");
            }
            newWTL.questionCategory_ID = questionCategoryIndex;
            return this;
        }

        public Builder withQuestionSubcategoryIndex(Integer questionSubcategoryIndex){
            if (questionSubcategoryIndex != null){
                if (questionSubcategoryIndex <= 0 || questionSubcategoryIndex > Constants.NUMBER_OF_QUESTION_SUBCATEGORIES)
                    throw new IllegalArgumentException("Question subcategory index must be between 1 and " + Constants.NUMBER_OF_QUESTION_SUBCATEGORIES + " or null");
            }
            newWTL.questionSubcategory_ID = questionSubcategoryIndex;
            return this;
        }

        //TO ENUM
        public Builder withResponceType(char type){
            if (type != 'P' && type != 'N')
                throw new IllegalArgumentException("Type must be between P or N: " + type);
            newWTL.responseType = type;
            return this;
        }

        public Builder withResponseDate(String date){
            if (date == null)
                throw new IllegalArgumentException("Date string can not be null");
            try {
                newWTL.responseDate = Constants.DATE_FORMAT.parse(date);
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
            newWTL.waitingTime = waitingTime;
            return this;
        }

        public WaitingTimeLine build() { return newWTL; }
    }

    public Integer getService_ID() { return service_ID; }

    public Integer getServiceVariation_ID() { return serviceVariation_ID; }

    public Integer getQuestion_ID() { return question_ID; }

    public Integer getQuestionCategory_ID() { return questionCategory_ID; }

    public Integer getQuestionSubcategory_ID() { return questionSubcategory_ID; }

    public char getResponseType() { return responseType; }

    public Date getResponseDate() { return responseDate; }

    public Integer getWaitingTime() { return waitingTime; }

    @Override
    public String toString() {
        return "WaitingTimeLine{" +
                "\n\tservice_ID = " + service_ID +
                ", \n\tserviceVariation_ID = " + serviceVariation_ID +
                ", \n\tquestion_ID = " + question_ID +
                ", \n\tquestionCategory_ID = " + questionCategory_ID +
                ", \n\tquestionSubcategory_ID = " + questionSubcategory_ID +
                ", \n\tresponseType = " + responseType +
                ", \n\tresponseDate = " + Constants.DATE_FORMAT.format(responseDate) +
                ", \n\twaitingTime = " + waitingTime +
                "\n}";
    }
}
