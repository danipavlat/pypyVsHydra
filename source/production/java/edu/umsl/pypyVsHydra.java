package edu.umsl;
import java.util.Scanner;

public class pypyVsHydra {

    //main driver method
    public static void main(String[] args) {
        //introduction: set the stage here
        kingdomInChaos();
        //gameplay
        gameplay();
    }

    public static void gameplay() {
        Scanner scanner = new Scanner(System.in);
        int heads, tails, choice;

        //Here comes the Hydra!
        System.out.println("PyPy can see the hydra now. How many heads can PyPy see?");
        heads = scanner.nextInt();
        System.out.println("PyPy can see its tails, now. How many tails are there?");
        tails = scanner.nextInt();
        Hydra hydra = new Hydra(heads, tails);

        hydra.displayCounts();

        //gameplay goes here, do while with nested switch for move choice
        do {
            PyPysChoice(hydra);
            choice = scanner.next().charAt(0);

            switch (choice) {
                case '1' : hydra.firstMove();
                    break;
                case '2' : hydra.secondMove();
                    break;
                case '3' : hydra.thirdMove();
                    break;
                case '4' : hydra.fourthMove();
                    break;
                case 's' :
                case 'S' : hydra.displayMinMoveCount();
                    break;
                case 'x' :
                case 'X' : System.out.println();
                           System.out.println("PyPy has chosen to retreat!");
                           System.out.println("Thanks for playing!");
                           System.exit(0);
                default :
                    System.out.println("Invalid selection.");

            }
        } while (!hydra.getDefeated() && hydra.getMinMovesToWin() != -1); //while Hydra is undefeated

        playAgain(hydra);
    }

    //Intro to the game, optional information on Hydras
    public static void kingdomInChaos() {
        Scanner scanner = new Scanner(System.in);
        char choice;
        System.out.println("Princess Perly has been kidnapped by the magical Hydra! The kingdom is in chaos.");
        System.out.println("Now only PyPy — the bravest Knight of the country — can save the day!");

            do {
                System.out.println();
                System.out.println("PyPy, hearing the news of Princess Perly says: (Select a or b) ");
                System.out.println("a: \"Wait, what's a Hydra?\"");
                System.out.println("b: \"Bring it on.\"");
                choice = Character.toLowerCase(scanner.next().charAt(0));
            } while (choice != 'a' && choice != 'b');

        if (choice == 'a') {
            System.out.println("The Hydra is a powerful magical creature that can breathe fire from each of its heads, ");
            System.out.println("and can shoot poison from each of its tails. ");
            System.out.println("The only way to kill Hydra is to cut off ALL heads and ALL tails. ");

            System.out.println("PyPy: \"Okay, I'm ready. Bring it on!\"");
            System.out.println();
        }
        else {
            System.out.println("PyPy: \"Bring it on.\"");
            System.out.println();
        }

    }

    public static void PyPysChoice(Hydra hydra) {
        System.out.println("What will PyPy do?");

        //if Hydra can still be defeated
        if (hydra.getHeads() >= 1 || hydra.getTails() >= 1) {
            System.out.println("(Select" + ((hydra.getHeads() >= 1) ? " 1," : "")
                                         + ((hydra.getTails() >= 1) ? " 2," : "")
                                         + ((hydra.getHeads() >= 2) ? " 3," : "")
                                         + ((hydra.getTails() >= 2) ? " 4," : "")
                                 + " or enter S to find out the fewest moves needed to defeat the Hydra!)");
            System.out.println();
            System.out.println("You can also enter x at any time to exit the game.");
            System.out.println();

            if (hydra.getHeads() >= 1)
                System.out.println("1: Knight PyPy will cut off exactly one of Hydra’s heads.");

            if (hydra.getTails() >= 1)
                System.out.println("2: Knight PyPy will cut off exactly one of Hydra’s tails.");

            if (hydra.getHeads() >= 2)
                System.out.println("3: Knight PyPy will cut off exactly two of Hydra’s heads.");

            if (hydra.getTails() >= 2)
                System.out.println("4: Knight PyPy can cut off exactly two of Hydra’s tails.");
        }

        else {  //if it can no longer be defeated
            hydra.displayMinMoveCount(); //minMovesToWin == -1
            //play again?
            playAgain(hydra);
        }
    }

    public static void playAgain(Hydra hydra) {
        Scanner scanner = new Scanner(System.in);
        char replay;

        System.out.println("Will PyPy fight again?");
        System.out.println("Enter y for yes or any other character to exit:");
        replay = Character.toLowerCase(scanner.next().charAt(0));

        if (replay == 'a'){
            System.out.println("PyPy feels ready to face the Hydra again...");
            gameplay();
        }
        else{
            if (!hydra.getDefeated()) {
                System.out.println("PyPy decides to rest until the next battle.");
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("No time for another fight, it's time to celebrate!");
                System.out.println("Great job!");
            }
        }

    }
}
