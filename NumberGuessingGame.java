import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        
        int maxAttempts = 10;
        int attempts = 0;
        
        int rounds = 0;
        int score = 0;
        
        while (true) {
            System.out.println("Round " + (rounds + 1));
            
            while (attempts < maxAttempts) {
                System.out.print("Guess the number between " + lowerBound + " and " + upperBound + ": ");
                
                try {
                    int userGuess = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    
                    if (userGuess < lowerBound || userGuess > upperBound) {
                        System.out.println("Please enter a number within the specified range.");
                        continue;
                    }
                    
                    if (userGuess == targetNumber) {
                        System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                        score++;
                        break;
                    } else if (userGuess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }
                    
                    attempts++;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            
            rounds++;
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();
            
            if (!playAgain.equals("yes")) {
                break;
            } else {
                targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                attempts = 0;
            }
        }
        
        System.out.println("You played " + rounds + " round(s) and your score is " + score + ".");
        
        scanner.close();
    }
}