package edu.umsl;
import lombok.Getter;

public class Hydra {
    @Getter
    private int heads;
    @Getter
    private int tails;
    @Getter
    private int minMovesToWin;  //for S choice in gameplay
    @Getter
    private Boolean defeated = false;

    //constructor: user determines number of heads & tails
    public Hydra(int heads, int tails) {
        this.heads = heads;
        this.tails = tails;
    }

    //current Hydra head & tail counts
    public void displayCounts() {

        if (!( heads == 0 && tails == 0)) { //if undefeated

            System.out.print("The Hydra now has " + heads);
            System.out.print(heads == 1 ? " head" : " heads");

            System.out.print(" and " + tails);
            System.out.print(tails == 1 ? " tail." : " tails.");
            System.out.println();

            if (heads == 0 && tails >= 1) {
                System.out.println("The Hydra is headless, but it is still alive and dangerous!");
            }

        } else {    //PyPy wins!
                System.out.println();
                System.out.println("The Hydra is defeated! Princess Perly is saved!");
                defeated = true;
        }
    }

    //gameplay: Knight PyPy vs. the Hydra
    public void firstMove() {
        //With the first move, Knight PyPy can cut off exactly one of Hydra’s heads.
        //If PyPy cuts off exactly one head, a new head grows immediately.
        if (heads >= 1) {
            System.out.println("PyPy cut off one head, but a new one grew back!");
            displayCounts();
            System.out.println();
        }
    }

    public void secondMove() {
        //With the second move, Knight PyPy can cut off exactly one of Hydra’s tails.
        //If PyPy cuts off exactly one tail, two new tails grow immediately.
        if (tails >= 1) {
            tails++;
            System.out.println("PyPy cut off one tail, but two new tails grew back!");
            displayCounts();
            System.out.println();
        }
    }

    public void thirdMove() {
        //With the third move, Knight PyPy can cut off exactly two of Hydra’s heads.
        //If PyPy cuts off exactly two heads, nothing happens.
        if (heads >= 2) {
            heads -= 2;
            System.out.println("PyPy cut off two heads! This time, none grew back!");
            displayCounts();
            System.out.println();
        }

    }

    public void fourthMove() {
        //With the fourth move, Knight PyPy can cut off exactly two of Hydra’s tails.
        //If PyPy cuts off exactly two tails, a new head grows immediately.
        if (tails >= 2) {
            tails -= 2;
            heads++;
            System.out.println("PyPy cut off two tails! But the Hydra grew another head!");
            displayCounts();
            System.out.println();
        }
    }

    public void displayMinMoveCount() {
        movesToWin();
        if (minMovesToWin == -1) {  //if PyPy can't win

            System.out.println();
            System.out.println("Move 1 alone can not defeat the Hydra. PyPy can not win.");
            System.out.println("Better luck next time, PyPy!");
            System.out.println();

        } else { //display the minimum moves needed to win
            System.out.println();
            System.out.println("It will take PyPy a minimum of " + minMovesToWin + " moves to defeat the Hydra.");
        }
    }

    public void movesToWin() {
        //modified counts if minimum moves were used
        int headCount = heads,
            tailCount = tails;

        //if no tails:
        if (tails == 0) {

            if (heads == 1) {
                minMovesToWin = -1; //counts can't change & Hydra can't lose
            }
            if (heads >= 2 && (heads % 2 == 0)) {
                //for every 2 heads when even:
                //  need only move 3 for win
                minMovesToWin = 1;
                minMovesToWin *= heads / 2; // (move 3) * every 2 heads
            }
        }

        //if no heads:
        if (heads == 0) {
            if (tailCount == 1) {
                //FIRST needs at least move 2 to add one tail, for h:0 t:2
                minMovesToWin = 1;
                tailCount++; //tailCount now == 2, jumps to first else if{}
            }
            if (tailCount % 2 == 1) {
                //for every 1 tail when odd number of tails > 1:
                //  need move 2 three times
                minMovesToWin += 3;
                minMovesToWin *= tailCount; // (3 * move 2) * num of tails
                tailCount = minMovesToWin + 1;

            } else if (tailCount >= 2) {
                //for every 2 tails when even:
                //  FIRST needs move 2 twice
                minMovesToWin += 2;
                minMovesToWin *= (tailCount / 2); // (2 * move 2) * every 2 tails
                tailCount += minMovesToWin;
                //continues to the following if statement (now tailCount >= 4)
            }
            if (tailCount >= 4 && (tailCount % 4 == 0)) {
                //for every 4 tails (or every 4 in new tailCount):
                //  needs move 4 twice (minMovesToWin += 2)
                //  needs move 3 once (minMovesToWin += 2 + 1)
                minMovesToWin += 3;
                minMovesToWin *= (tailCount / 4); // (3 moves min) * every 4 tails
            }
        }

        if (heads >= 3 && (heads % 2 == 1) && tails >= 1){
            //if odd number of heads > 1, and at least one tail
            if (tails == 1) {
                //need move 2 once to get h:3 t:2 (minMovesToWin = 1)
                //need move 4 once to get even number of heads, 0 tails (minMovesToWin = 1 + 1)
                minMovesToWin = 2;
                headCount = heads + 1; //move 4 adds 1 head
                //for every 2 heads:
                    //need move 3 once for h:0 t:0
                minMovesToWin += (headCount / 2);
            } else { //odd number heads >= 3 , tails > 1)
                if (tailCount % 2 == 1) {   //if odd number of tails
                    //move 2 once, adds one tail & makes even tailCount
                    minMovesToWin += 1;
                    tailCount += 1;
                }
                if (tailCount % 2 == 0) { //if even tailCount (tailCount >= 2)
                    int tailsDivByTwo = tailCount / 2;
                    if (tailsDivByTwo % 2 == 0) {
                        //if 2 goes into the tail count an even number of times
                        //move 2 twice, adds two tails
                        minMovesToWin += 2;
                        tailCount += 2;
                    }
                    //move 4 once for every 2 tails, adds 1 head each time
                    minMovesToWin += (tailCount / 2);
                    headCount += (tailCount / 2);

                    //move 3 once for every two heads
                    minMovesToWin += (headCount / 2);
                }
            }
        }

    }

}
