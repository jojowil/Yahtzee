import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

import java.util.Arrays;

public class YahtzeeGUI extends Application {

    // All the beautiful labels!
    Label rollsLeftLBL, diceTotalLBL, subtotalLBL, bonusLBL, upperTotalLBL, totalLBL;
    
    // And, of course, our essential text fields!
    TextField rollsLeftTF, diceTotalTF, onesTF, twosTF, threesTF, foursTF, fivesTF, sixesTF,
            threeOKTF, fourOKTF, fullHouseTF, smStrTF, lgStrTF, yahtzeeTF, chanceTF,
            subtotalTF, bonusTF, upperTotalTF, totalTF;
            
    // But where would we be without our buttons?
    Button rollBTN, onesBTN, twosBTN, threesBTN, foursBTN, fivesBTN, sixesBTN,
            threeOKBTN, fourOKBTN, fullHouseBTN, smStrBTN, lgStrBTN, yahtzeeBTN, chanceBTN;

    // ToggleButtons for the dice!
    ToggleButton[] diceTB = new ToggleButton[5];

    // Dice and rolls
    int[] dice = new int[5];
    int rolls = 3;

    public void createObjects() {
        final int minWidth = 100;

        for (int x = 0 ; x < 5; x++) {
            diceTB[x] = new ToggleButton("");
            diceTB[x].setMinWidth(minWidth / 3);
        }

        // One ping only...
        ButtonHandler bh = new ButtonHandler();

        // everything related to the dice!
        diceTotalLBL = new Label("Dice Total:");
        diceTotalTF = new TextField("20");
        diceTotalTF.setEditable(false);
        rollsLeftLBL = new Label("Rolls Left:");
        rollsLeftTF = new TextField("3");
        rollsLeftTF.setEditable(false);
        rollBTN = new Button("Roll"); rollBTN.setMinWidth(minWidth/2);
        rollBTN.setOnAction(bh);

        // scoring pane - upper section buttons
        onesBTN = new Button("1's"); onesBTN.setMinWidth(minWidth);
        onesBTN.setOnAction(bh);
        twosBTN = new Button("2's"); twosBTN.setMinWidth(minWidth);
        twosBTN.setOnAction(bh);
        threesBTN = new Button("3's"); threesBTN.setMinWidth(minWidth);
        threesBTN.setOnAction(bh);
        foursBTN = new Button("4's"); foursBTN.setMinWidth(minWidth);
        foursBTN.setOnAction(bh);
        fivesBTN = new Button("5's"); fivesBTN.setMinWidth(minWidth);
        fivesBTN.setOnAction(bh);
        sixesBTN = new Button("6's"); sixesBTN.setMinWidth(minWidth);
        sixesBTN.setOnAction(bh);

        // scoring pane - upper section labels
        subtotalLBL = new Label("Subtotal:"); GridPane.setHalignment(subtotalLBL, HPos.RIGHT);
        bonusLBL = new Label("Bonus:"); GridPane.setHalignment(bonusLBL, HPos.RIGHT);
        upperTotalLBL = new Label("Upper Total:"); GridPane.setHalignment(upperTotalLBL, HPos.RIGHT);

        // scoring pane - upper section test fields
        onesTF = new TextField(); onesTF.setMaxWidth(minWidth); onesTF.setEditable(false);
        twosTF = new TextField(); twosTF.setMaxWidth(minWidth); twosTF.setEditable(false);
        threesTF = new TextField(); threesTF.setMaxWidth(minWidth); threesTF.setEditable(false);
        foursTF = new TextField(); foursTF.setMaxWidth(minWidth); foursTF.setEditable(false);
        fivesTF = new TextField(); fivesTF.setMaxWidth(minWidth); fivesTF.setEditable(false);
        sixesTF = new TextField(); sixesTF.setMaxWidth(minWidth); sixesTF.setEditable(false);
        subtotalTF = new TextField(); subtotalTF.setMaxWidth(minWidth); subtotalTF.setEditable(false);
        bonusTF = new TextField(); bonusTF.setMaxWidth(minWidth); bonusTF.setEditable(false);
        upperTotalTF = new TextField(); upperTotalTF.setMaxWidth(minWidth); upperTotalTF.setEditable(false);

        // scoring pane - lower section buttons
        threeOKBTN = new Button("3 of a kind"); threeOKBTN.setMinWidth(minWidth);
        threeOKBTN.setOnAction(bh);
        fourOKBTN = new Button("4 of a kind"); fourOKBTN.setMinWidth(minWidth);
        fourOKBTN.setOnAction(bh);
        fullHouseBTN = new Button("Full House"); fullHouseBTN.setMinWidth(minWidth);
        fullHouseBTN.setOnAction(bh);
        smStrBTN = new Button("Small Straight"); smStrBTN.setMinWidth(minWidth);
        smStrBTN.setOnAction(bh);
        lgStrBTN = new Button("Large Straight"); lgStrBTN.setMinWidth(minWidth);
        lgStrBTN.setOnAction(bh);
        yahtzeeBTN = new Button("Yahtzee"); yahtzeeBTN.setMinWidth(minWidth);
        yahtzeeBTN.setOnAction(bh);
        chanceBTN = new Button("Chance"); chanceBTN.setMinWidth(minWidth);
        chanceBTN.setOnAction(bh);

        // scoring pane - lower section labels
        totalLBL = new Label("Total Score"); GridPane.setHalignment(totalLBL, HPos.RIGHT);

        // scoring pane - lower section text fields
        threeOKTF = new TextField(); threeOKTF.setMaxWidth(minWidth); threeOKTF.setEditable(false);
        fourOKTF = new TextField(); fourOKTF.setMaxWidth(minWidth); fourOKTF.setEditable(false);
        fullHouseTF = new TextField(); fullHouseTF.setMaxWidth(minWidth); fullHouseTF.setEditable(false);
        smStrTF = new TextField(); smStrTF.setMaxWidth(minWidth); smStrTF.setEditable(false);
        lgStrTF = new TextField(); lgStrTF.setMaxWidth(minWidth); lgStrTF.setEditable(false);
        yahtzeeTF = new TextField(); yahtzeeTF.setMaxWidth(minWidth); yahtzeeTF.setEditable(false);
        chanceTF = new TextField(); chanceTF.setMaxWidth(minWidth); chanceTF.setEditable(false);
        totalTF = new TextField(); totalTF.setMaxWidth(minWidth); totalTF.setEditable(false);
    }

    /**
     * Sum the total of dice with value of dtype.
     * @param dtype the die value to sum.
     * @return int as the sum of dice matching dtype.
     */
    public int totalOf(int dtype) {
        int count = 0;

        // count the dtype dice
        for (int x = 0; x < 5; x++)
            if (dice[x] == dtype)
                count++;

        // number of dice times dtype
        return count * dtype;
    }

    /**
     * One handler to rule them all, and in the darkness bind them...
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Button b = (Button)e.getSource();
            boolean off = true;
            boolean r = false; // do I need to reset?

            // if it's not the rollBTN, might as well sort them.
            // we're done here...
            if (b != rollBTN)
                Arrays.sort(dice);

            if (b == rollBTN) {
                if ( rolls > 0 )
                    roll();
                off = false;
            } else if (b == onesBTN) {
                onesTF.setText("" + totalOf(1));
            } else if (b == twosBTN) {
                twosTF.setText("" + totalOf(2));
            } else if (b == threesBTN) {
                threesTF.setText("" + totalOf(3));
            } else if (b == foursBTN) {
                foursTF.setText("" + totalOf(4));
            } else if (b == fivesBTN) {
                fivesTF.setText("" + totalOf(5));
            } else if (b == sixesBTN) {
                sixesTF.setText("" + totalOf(6));
            } else if (b == threeOKBTN) {
                threeOKTF.setText(is3OfAKind(dice) ? diceTotalTF.getText() : "0");
            } else if (b == fourOKBTN) {
                fourOKTF.setText(is4OfAKind(dice) ? diceTotalTF.getText() : "0");
            } else if (b == fullHouseBTN) {
                fullHouseTF.setText(isFullHouse(dice) ? "25" : "0");
            } else if (b == smStrBTN) {
                smStrTF.setText(isSmallStraight(dice) ? "30" : "0");
            } else if (b == lgStrBTN) {
                lgStrTF.setText(isLargeStraight(dice) ? "40" : "0");
            } else if (b == yahtzeeBTN) {
                yahtzeeTF.setText(isYahtzee(dice) ? "50" : "0");
            } else if (b == chanceBTN) {
                chanceTF.setText(diceTotalTF.getText());
            }

            b.setDisable(off);
            if (b != rollBTN) {
                resetDice();
                updateTotal();
            }
        }
    }

    /**
     * Reset the number of rolls and the dice to the beginning.
     * show the dice and update the rolls.
     */
    public void resetDice() {
        rolls = 3;
        for (int x = 0 ; x < 5; x++) {
            dice[x] = 0;
            diceTB[x].setSelected(false);
        }
        showDice();
        updateRolls();
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane detailPane = new GridPane();
        GridPane pane = new GridPane();
        GridPane dicePane = new GridPane();
        
        // And then our main attraction. The VBox holds everything!
        VBox mainView = new VBox(detailPane, dicePane, pane);
        Scene scene = new Scene(mainView, 500, 750);

        createObjects();

        GridPane.setHalignment(subtotalLBL, HPos.RIGHT);
        //GridPane.setHalignment(lsrc, HPos.CENTER);
        //GridPane.setHalignment(ldst, HPos.CENTER);

        // detail pane
        detailPane.setHgap(5);
        detailPane.setVgap(5);
        detailPane.setAlignment(Pos.CENTER);
        detailPane.add(rollsLeftLBL, 0, 1); detailPane.add(rollsLeftTF, 1, 1);
        detailPane.add(diceTotalLBL, 0, 2); detailPane.add(diceTotalTF, 1, 2);

        // dice pane
        dicePane.setHgap(5);
        dicePane.setVgap(5);
        dicePane.setAlignment(Pos.CENTER);
        dicePane.add(rollBTN, 8, 5);
        for ( int x = 0; x < 5; x++ )
            dicePane.add(diceTB[x], x, 5);
        // scoring pane - upper section
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(onesBTN, 0, 4); pane.add(onesTF, 1, 4);
        pane.add(twosBTN, 0, 5); pane.add(twosTF, 1, 5);
        pane.add(threesBTN, 0, 6); pane.add(threesTF, 1, 6);
        pane.add(foursBTN, 0, 7); pane.add(foursTF, 1, 7);
        pane.add(fivesBTN, 0, 8); pane.add(fivesTF, 1, 8);
        pane.add(sixesBTN, 0, 9); pane.add(sixesTF, 1, 9);

        pane.add(subtotalLBL, 0, 10); pane.add(subtotalTF, 1, 10);
        pane.add(bonusLBL, 0, 11); pane.add(bonusTF, 1, 11);
        pane.add(upperTotalLBL, 0, 12); pane.add(upperTotalTF, 1, 12);

        // lower section
        pane.add(threeOKBTN, 2, 13); pane.add(threeOKTF, 3, 13);
        pane.add(fourOKBTN, 2, 14); pane.add(fourOKTF, 3, 14);
        pane.add(fullHouseBTN, 2, 15); pane.add(fullHouseTF, 3, 15);
        pane.add(smStrBTN, 2, 16); pane.add(smStrTF, 3, 16);
        pane.add(lgStrBTN, 2, 17); pane.add(lgStrTF, 3, 17);
        pane.add(yahtzeeBTN, 2, 18); pane.add(yahtzeeTF, 3, 18);
        pane.add(chanceBTN, 2, 19); pane.add(chanceTF, 3, 19);
        pane.add(totalLBL, 2, 20); pane.add(totalTF, 3, 20);
        // tfdst.setEditable(false);

        System.out.println(pane);
        primaryStage.setTitle("Button Event Inner Class");
        primaryStage.setScene(scene);
        primaryStage.show();


        // YEAH! NOW THINGS BEGIN TO HAPPEN!

        showDice();
    }

    /**
     * Roll the dice, adjust rolls, and show the dice.
     */
    public void roll() {

        for ( int x = 0; x < 5; x++ ) {
            if ( rolls == 3 || !diceTB[x].isSelected())
                dice[x] = (int)(Math.random() * 6 + 1);
        }
        rolls--;
        showDice();
        updateRolls();
    }

    /**
     * Based on the value of dice[], change the face of
     * the dice and update the dice total.
     */
    public void showDice() {
        for (int x = 0; x < 5; x++)
            diceTB[x].setText((dice[x] == 0) ? "" : "" + dice[x]);
        updateDiceTotal();
    }

    /**
     * Update the number of rolls remaining.
     */
    public void updateRolls() {
        rollsLeftTF.setText("" + rolls);
    }

    /**
     * Update the dice total.
     */
    public void updateDiceTotal() {
        int sum = 0;
        for ( int x = 0; x < 5; x++ )
            sum = sum + dice[x];
        diceTotalTF.setText("" + sum);
    }

    /**
     * Update the upper total along with the bonus. This is not the most
     * efficient, but demonstrates a need to recognize we are dealing with
     * strings and ints everywhere.
     */
    public void updateUpperTotal() {
        int sum = 0;
        // if button is disabled, something was written in the TF
        sum = (onesBTN.isDisabled() ? Integer.parseInt(onesTF.getText()) : 0)
                + (twosBTN.isDisabled() ? Integer.parseInt(twosTF.getText()) : 0)
                + (threesBTN.isDisabled() ? Integer.parseInt(threesTF.getText()) : 0)
                + (foursBTN.isDisabled() ? Integer.parseInt(foursTF.getText()) : 0)
                + (fivesBTN.isDisabled() ? Integer.parseInt(fivesTF.getText()) : 0)
                + (sixesBTN.isDisabled() ? Integer.parseInt(sixesTF.getText()) : 0);

        subtotalTF.setText("" + sum);
        bonusTF.setText( sum >= 63 ? "35" : "0");
        upperTotalTF.setText(""+ (sum + Integer.parseInt(bonusTF.getText())));
    }

    public void updateTotal() {
        int sum = 0;

        updateUpperTotal();
        sum = (threeOKBTN.isDisabled() ? Integer.parseInt(threeOKTF.getText()) : 0)
                + (fourOKBTN.isDisabled() ? Integer.parseInt(fourOKTF.getText()) : 0)
                + (fullHouseBTN.isDisabled() ? Integer.parseInt(fullHouseTF.getText()) : 0)
                + (smStrBTN.isDisabled() ? Integer.parseInt(smStrTF.getText()) : 0)
                + (lgStrBTN.isDisabled() ? Integer.parseInt(lgStrTF.getText()) : 0)
                + (yahtzeeBTN.isDisabled() ? Integer.parseInt(yahtzeeTF.getText()) : 0)
                + (chanceBTN.isDisabled() ? Integer.parseInt(chanceTF.getText()) : 0);
        totalTF.setText("" + (sum + Integer.parseInt(upperTotalTF.getText())));
    }

    /**
     * Determine if the first four dice in the sorted array are sequential.
     * @param d as the array of dice
     * @return true if it's a lower straight.
     */
    public static boolean isLowerStraight (int[] d) {
        int v = d[0];
        for (int x = 1; x <=3; x++)
            if (d[x] != v+x)
                return false;
        return true;
    }

    /**
     * Determine if the last four dice in the sorted array are sequential.
     * @param d as the array of dice.
     * @return true if it's an upper straight.
     */
    public static boolean isUpperStraight (int[] d) {
        int v = d[1];
        for (int x = 2; x <=4; x++)
            if (d[x] != v+x-1)
                return false;
        return true;
    }

    /**
     * Determine if the sorted array of dice is a small straight.
     * @param d is the array of dice
     * @return true if it's a small straight
     */
    public static boolean isSmallStraight (int[] d) {
        return isLowerStraight(d) || isUpperStraight(d);
    }

    /**
     * Determine if the sorted array of dice is a large straight
     * @param d as the array of dice.
     * @return true if all values are sequential.
     */
    public static boolean isLargeStraight (int[] d) {
        int v = d[0];
        for (int x = 1; x <=4; x++)
            if (d[x] != v+x)
                return false;
        return true;
    }

    /**
     * Determine if the sorted array of dice is a fill house.
     * @param d as the array of dice.
     * @return true if the patter xxyyy or xxxyy can be discerned.
     */
    public static boolean isFullHouse (int[] d) {
        return ( (d[0] != d[4]) &&
                ( ((d[0] == d[1]) && (d[2] == d[4])) ||
                        ((d[0] == d[2]) && (d[3] == d[4])) ) );
    }

    /**
     * Determine if the sorted array is 3 of a kind
     * @param d as the sorted array of dice
     * @return true if 1&3, 2&4, or 3&5 match.
     */
    public static boolean is3OfAKind (int[] d) {
        return (d[0] == d[2]) || (d[1] == d[3]) || (d[2] == d[4]);
    }

    /**
     * Determine if the sorted array is 4 of a kind
     * @param d as the sorted array of dice.
     * @return true if 1&4 or 2&5 match.
     */
    public static boolean is4OfAKind (int[] d) {
        return (d[0] == d[3]) || (d[1] == d[4]);
    }

    /**
     * Determine if the sorted array is 5 of a kind
     * @param d as the sorted array of dice.
     * @return true if 1&5 match.
     */
    public static boolean isYahtzee (int[] d) {
        return d[0] == d[4];
    }

    public static void main(String[] args) {
        launch(args);
    }
}
