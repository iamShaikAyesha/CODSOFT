import java.util.*;
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizApplication {
    private static List<Question> quizQuestions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static final int TIME_LIMIT = 10;  
    private static boolean answerSubmitted = false;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
        displayResults();
    }

    private static void initializeQuestions() {
        quizQuestions.add(new Question("What is the capital of India?", new String[]{"1. London", "2. Paris", "3. New Delhi", "4. Rome"}, 3));
        quizQuestions.add(new Question("What is 2 * 5?", new String[]{"1. 7", "2. 10", "3. 5", "4. 6"}, 2));
        quizQuestions.add(new Question("where is Taj Mahal located?", new String[]{"1.Hyderabad", "2. Mumbai", "3. Agra", "4. Delhi"}, 3));
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < quizQuestions.size()) {
            Question currentQuestion = quizQuestions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestionText());

            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answerSubmitted) {
                        System.out.println("Time's up! Moving to the next question.");
                        currentQuestionIndex++;
                        startQuiz();
                    }
                }
            }, TIME_LIMIT * 1000);

            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            answerSubmitted = true;
            timer.cancel();

            if (answer == currentQuestion.getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was option " + currentQuestion.getCorrectAnswer() + ".");
            }

            currentQuestionIndex++;
            answerSubmitted = false;
        }

        scanner.close();
    }

    private static void displayResults() {
        System.out.println("\nQuiz Complete!");
        System.out.println("Your final score: " + score + "/" + quizQuestions.size());
        System.out.println("Summary of answers:");

        for (int i = 0; i < quizQuestions.size(); i++) {
            Question question = quizQuestions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.println("Correct Answer: " + question.getOptions()[question.getCorrectAnswer() - 1]);
        }
    }
}

    
