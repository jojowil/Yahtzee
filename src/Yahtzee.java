import java.util.Arrays;
import java.util.Scanner;

public class Yahtzee {

    static Scanner kb = new Scanner(System.in);

    public static void showDice(int[] d) {
        System.out.println("\nYou currently have:");
        System.out.print("Die# 1 2 3 4 5\n     ");
        for ( int x = 0; x < 5; x++ )
            System.out.print(d[x] + " ");
    }

    public static boolean playerReroll (int[] d) {
        int x;
        String reroll;
        String digits = "123456";
        String[] parts;

        System.out.print("\n\nDo you wish to reroll any dice? (Y/N)");
        char c = kb.nextLine().toUpperCase().charAt(0);
        if ( c != 'Y')
            return false;
        System.out.println("\nWhich dice would you like to re-roll.");
        reroll = kb.nextLine();

        parts = reroll.split(" ");
        System.out.println("You selected:");
        for ( x = 0 ; x < parts.length; x++ )
            System.out.print(parts[x] + " ");

        for ( x = 0 ; x < parts.length; x++ )
            d[digits.indexOf(parts[x])] = (int)(Math.random() * 6 + 1);

        return true;
    }

    public static boolean isLowerStraight (int[] d) {
        int v = d[0];
        for (int x = 1; x <=3; x++)
            if (d[x] != v+x)
                return false;
        return true;
    }

    public static boolean isUpperStraight (int[] d) {
        int v = d[1];
        for (int x = 2; x <=4; x++)
            if (d[x] != v+x-1)
                return false;
        return true;
    }

    public static boolean isSmallStraight (int[] d) {
        return isLowerStraight(d) || isUpperStraight(d);
    }

    public static boolean isLargeStraight (int[] d) {
        int v = d[0];
        for (int x = 1; x <=4; x++)
            if (d[x] != v+x)
                return false;
        return true;
    }

    public static boolean isFullHouse (int[] d) {
        return ( (d[0] != d[4]) &&
                ( ((d[0] == d[1]) && (d[2] == d[4])) ||
                ((d[0] == d[2]) && (d[3] == d[4])) ) );
    }

    public static boolean is3OfAKind (int[] d) {
        return (d[0] == d[2]) || (d[1] == d[3]) || (d[2] == d[4]);
    }

    public static boolean is4OfAKind (int[] d) {
        return (d[0] == d[3]) || (d[1] == d[4]);
    }

    public static boolean isYahtzee (int[] d) {
        return d[0] == d[4];
    }

    public static void main(String[] args) {

        int[] dice = new int[5];
        int x;

        System.out.println("Rolling dice...");
        for ( x = 0; x < 5; x++ )
            dice[x] = (int)(Math.random() * 6 + 1);

        showDice(dice);
        // second roll?
        if (playerReroll(dice)){
            showDice(dice);
            // third roll!
            playerReroll(dice);
            showDice(dice);
        }

        Arrays.sort(dice);
        System.out.println("\n\n");
        if (is3OfAKind(dice))
            System.out.println("You have 3 of a kind!");
        if (is4OfAKind(dice))
            System.out.println("You have 4 of a kind!");
        if (isSmallStraight(dice))
            System.out.println("You have a Small Straight");
        if (isLargeStraight(dice))
            System.out.println("You have a Large Straight");
        if (isFullHouse(dice))
            System.out.println("You have a Full House!");
        if (isYahtzee(dice))
            System.out.println("You have Yahtzee!");

    }
}
