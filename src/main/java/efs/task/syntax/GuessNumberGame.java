package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {
    private final int upperBound;
    private final int guessesMax;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            upperBound = Integer.parseInt(argument);
            if (upperBound < 1 || upperBound > UsefulConstants.MAX_UPPER_BOUND) {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        guessesMax = (int)(Math.log(upperBound) / Math.log(2)) + 1;
    }

    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + upperBound + ">");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numberInputted;
        int guesses;
        int number = random.nextInt(upperBound) + 1;
        String input;

        for(guesses = 1; guesses <= guessesMax; guesses++) {
            System.out.println("Twoje próby: " + getProgressBar(guesses, guessesMax - guesses));
            System.out.println(UsefulConstants.GIVE_ME + " liczbę:");
            input = scanner.nextLine();

            try {
                numberInputted = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Hmm, '" + input + "' to " + UsefulConstants.NOT_A_NUMBER);
                continue;
            }

            if(numberInputted == number) {
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + guesses + " - tyle prób zajęło Ci odgadnięcie liczby " + number);
                return;
            }
            else if(numberInputted > number) {
                System.out.println("To " + UsefulConstants.TO_MUCH);
            }
            else {
                System.out.println("To " + UsefulConstants.TO_LESS);
            }
        }

        System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób (" + guesses + ") do odgadnięcia liczby" + number);
    }

    public String getProgressBar(int s, int d) {
        String bar = "[";
        for(int i = 0; i < s; i++) bar = bar.concat("*");
        for(int i = 0; i < d; i++) bar = bar.concat(".");
        bar = bar.concat("]");

        return bar;
    }
}
