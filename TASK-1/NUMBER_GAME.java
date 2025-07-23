import java.util.Scanner;
import java.util.Random;

public class NUMBER_GAME {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    System.out.println("Welcome To The Number Guessing Game !!!");
    System.out.println("The number I'm thinking is between 1 and 100.");

    int totalRound = 0;
    int totalWins = 0;
    int bestScore = Integer.MAX_VALUE;

    boolean playAgain = true;
    while (playAgain) {
      totalRound++;
      int secretNumber = random.nextInt(100) + 1;
      int attempts = 0;
      final int maxAttempts = 10;
      boolean hasWon = false;

      System.out.println("\n--- Round " + totalRound + " ---");
      System.out.println("You have " + maxAttempts + " attempts to guess the number.");

      while (attempts < maxAttempts && !hasWon) {
        System.out.println("\nEnter your guess (1-100): ");

        try {
          int guess = scanner.nextInt();

          if (guess < 1 || guess > 100) {
            System.out.println("Please enter a number between 1 and 100.");
            continue;
          }

          attempts++;

          if (guess == secretNumber) {
            System.out.println("Congratulations! You guessed the number in " + attempts + " attempts!");
            totalWins++;
            hasWon = true;
            
            if (attempts < bestScore) {
              bestScore = attempts;
            }
          } 
          else if (guess < secretNumber) {
            int remaining = maxAttempts - attempts;
            System.out.println("Too low! You have " + remaining + (remaining == 1 ? " attempt" : " attempts") + " left.");
          } 
          else {
            int remaining = maxAttempts - attempts;
            System.out.println("Too high! You have " + remaining + (remaining == 1 ? " attempt" : " attempts") + " left.");
          }
        } 

        catch (Exception e) {
          System.out.println("Please enter a valid number.");
          scanner.next();
        }
      }

      if (!hasWon) {
        System.out.println("\nSorry, you've used all your attempts. The number was " + secretNumber + "."); 
      }

      System.out.println("\nWould you like to play again? (yes/no): ");
      String playAgainInput = scanner.next().toLowerCase();
      playAgain = playAgainInput.equals("yes") ||playAgainInput.equals("y");
    }

    System.out.println("Game Summary: ");
    System.out.println("Total rounds played: " + totalRound);
    System.out.println("Rounds won: " + totalWins);

    if (totalWins > 0) {
      System.out.println("Best score (fewest guesses): " + bestScore);
    }
    System.out.println("\nThanks for playing!");

    scanner.close();
  }
}