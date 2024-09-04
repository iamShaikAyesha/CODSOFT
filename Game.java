
import java.util.*;
public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;
        int totalScore = 0;
        int roundsPlayed = 0;

        do {
            totalScore += playGame(scanner);
            roundsPlayed++;

           
            System.out.print("Would you like to play another round? (y/n): ");
            playAgain = scanner.next();
        } while (playAgain.equalsIgnoreCase("y"));

        
        System.out.println("Game over! You played " + roundsPlayed + " round(s) with a total score of " + totalScore + ".");
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static int playGame(Scanner scanner) {
        Random random = new Random();
        int low = 1;
        int high = 100;
        int numberToGuess = random.nextInt(high - low + 1) + low;
        int maxAttempts = 10;
        int attempts = 0;
        int score = 0;

        System.out.println("\nWelcome to the Number Game!\nGuess a number between " + low + " and " + high + ".");

        while (attempts < maxAttempts) {
            System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ":\nEnter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Your guess is too low!");
            } else if (userGuess > numberToGuess) {
                System.out.println("Your guess is too high!");
            } else {
                System.out.println("Congratulations! You've guessed the correct number " + numberToGuess + " in " + attempts + " attempts.");
                score = 1; 
                break;
            }
        }

        if (attempts == maxAttempts && score == 0) {
            System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + numberToGuess + ".");
        }

        return score;
    }
}
