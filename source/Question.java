public class Question{
    private final String question;
    private final String[] answers;
    private final String correct_answer;

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String correct_answer){
        this.question = question;
        this.answers = new String[] {answer1, answer2, answer3, answer4};
        this.correct_answer = correct_answer;
    }

    public String getQuestion() {return question;}
    public String[] guetAnswers() {return answers;}
    public String getCorrectAnswer() {return correct_answer;}
}