import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

import java.util.ArrayList;
import java.util.Arrays;

/* The Yahtzee name is a registered trademark (R) and copyright (C) Hasbro */

public class YahtzeeGUI extends Application {

    // All the beautiful labels!
    Label rollsLeftLBL, diceTotalLBL, subtotalLBL, bonusLBL, upperTotalLBL, totalLBL;
    ArrayList<Label> labels = new ArrayList<>();

    // And, of course, our essential text fields!
    TextField rollsLeftTF, diceTotalTF, onesTF, twosTF, threesTF, foursTF, fivesTF, sixesTF,
            threeOKTF, fourOKTF, fullHouseTF, smStrTF, lgStrTF, yahtzeeTF, chanceTF,
            subtotalTF, bonusTF, upperTotalTF, totalTF;
    ArrayList<TextField> textFields = new ArrayList<>();

    // But where would we be without our buttons?
    Button rollBTN, onesBTN, twosBTN, threesBTN, foursBTN, fivesBTN, sixesBTN,
            threeOKBTN, fourOKBTN, fullHouseBTN, smStrBTN, lgStrBTN, yahtzeeBTN, chanceBTN;
    ArrayList<Button> buttons = new ArrayList<>();

    // ToggleButtons for the dice!
    ToggleButton[] diceTB = new ToggleButton[5];

    // Dice and rolls and stuff
    int[] dice = new int[5];
    int rolls = 3;
    ButtonHandler bh = new ButtonHandler();

    /**
     * Create a scoring Button and add it to the list.
     *
     * @param n name of button
     * @param w max width of the button
     * @return actioned button object
     */
    public Button scoreButton(String n, int w) {
        Button b = new Button(n);
        b.setMinWidth(w);
        b.setOnAction(bh);
        buttons.add(b);
        return b;
    }

    /**
     * Create a scoring TextField and add it to the list.
     *
     * @param w max width of the TextField
     * @return non-editable TextField
     */
    public TextField scoreTextField(int w) {
        TextField tf = new TextField();
        tf.setMaxWidth(w);
        tf.setEditable(false);
        textFields.add(tf);
        return tf;
    }

    /**
     * Create a scoring label and add it to the list.
     *
     * @param n name of the label
     * @return the label
     */
    public Label scoreLabel(String n) {
        Label l = new Label(n);
        labels.add(l);
        return l;
    }

    /**
     * This sets prompts in the TextFields for values that could be used.
     *
     * @param s state - true means show prompts, false mean clear them
     */
    public void setPrompts(boolean s) {
        if (!s) {
            // clear them all
            for (TextField tf : textFields)
                tf.setPromptText("");
        } else {
            int[] t = Arrays.copyOf(dice, dice.length);
            Arrays.sort(t);
            int t1 = totalOf(t, 1), t2 = totalOf(t, 2), t3 = totalOf(t, 3);
            int t4 = totalOf(t, 4), t5 = totalOf(t, 5), t6 = totalOf(t, 6);
            for (Button b : buttons) {
                if (!b.isDisabled()) {
                    if (b == onesBTN) {
                        onesTF.setPromptText((t1 >0) ? "" + t1 : "");
                    } else if (b == twosBTN) {
                        twosTF.setPromptText((t2 >0) ? "" + t2 : "");
                    } else if (b == threesBTN) {
                        threesTF.setPromptText((t3 > 0) ? "" + t3 : "");
                    } else if (b == foursBTN) {
                        foursTF.setPromptText((t4 > 0) ? "" + t4 : "");
                    } else if (b == fivesBTN) {
                        fivesTF.setPromptText((t5 > 0) ? "" + t5 : "");
                    } else if (b == sixesBTN) {
                        sixesTF.setPromptText((t6 > 0) ? "" + t6 : "");
                    } else if (b == threeOKBTN) {
                        threeOKTF.setPromptText(is3OfAKind(t) ? diceTotalTF.getText() : "");
                    } else if (b == fourOKBTN) {
                        fourOKTF.setPromptText(is4OfAKind(t) ? diceTotalTF.getText() : "");
                    } else if (b == fullHouseBTN) {
                        fullHouseTF.setPromptText(isFullHouse(t) ? "25" : "");
                    } else if (b == smStrBTN) {
                        smStrTF.setPromptText(isSmallStraight(t) ? "30" : "");
                    } else if (b == lgStrBTN) {
                        lgStrTF.setPromptText(isLargeStraight(t) ? "40" : "");
                    } else if (b == yahtzeeBTN) {
                        yahtzeeTF.setPromptText(isYahtzee(t) ? "50" : "");
                    } else if (b == chanceBTN) {
                        chanceTF.setPromptText(diceTotalTF.getText());
                    }
                }
            }
        }
    }

    /**
     * Tell them they need to roll first
     */
    public void rollFirst() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Roll Alert");
        alert.setHeaderText("You haven't rolled.");
        alert.setContentText("You have to roll before you can record a score.");
        alert.showAndWait();
    }

    /**
     * Use the button state to determine if we've used all values.
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        for (Button b : buttons)
            if (b != rollBTN && !b.isDisabled())
                return false;
        return true;
    }

    public void gameOver() {
        if (isGameOver()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Congratulations!");
            alert.setContentText("You finished the game with a score of "
                + totalTF.getText() +"!");
            alert.showAndWait();
            Platform.exit();
        }
    }

    /**
     * Main routine to create everything!
     */
    public void createObjects() {

        final int minWidth = 100;

        for (int x = 0; x < 5; x++) {
            diceTB[x] = new ToggleButton("");
            diceTB[x].setMinWidth(minWidth / 3.0);
        }

        // everything related to the dice!
        diceTotalLBL = scoreLabel("Dice Total:");
        diceTotalTF = scoreTextField(minWidth);
        rollsLeftLBL = scoreLabel("Rolls Left:");
        rollsLeftTF = scoreTextField(minWidth);
        rollBTN = scoreButton("Roll", minWidth / 2);

        // scoring pane - upper section buttons
        onesBTN = scoreButton("1's", minWidth);
        twosBTN = scoreButton("2's", minWidth);
        threesBTN = scoreButton("3's", minWidth);
        foursBTN = scoreButton("4's", minWidth);
        fivesBTN = scoreButton("5's", minWidth);
        sixesBTN = scoreButton("6's", minWidth);

        // scoring pane - upper section labels
        subtotalLBL = scoreLabel("Subtotal:");
        GridPane.setHalignment(subtotalLBL, HPos.RIGHT);
        bonusLBL = scoreLabel("Bonus:");
        GridPane.setHalignment(bonusLBL, HPos.RIGHT);
        upperTotalLBL = scoreLabel("Upper Total:");
        GridPane.setHalignment(upperTotalLBL, HPos.RIGHT);

        // scoring pane - upper section test fields
        onesTF = scoreTextField(minWidth);
        twosTF = scoreTextField(minWidth);
        threesTF = scoreTextField(minWidth);
        foursTF = scoreTextField(minWidth);
        fivesTF = scoreTextField(minWidth);
        sixesTF = scoreTextField(minWidth);
        subtotalTF = scoreTextField(minWidth);
        bonusTF = scoreTextField(minWidth);
        upperTotalTF = scoreTextField(minWidth);

        // scoring pane - lower section buttons
        threeOKBTN = scoreButton("3 of a kind", minWidth);
        fourOKBTN = scoreButton("4 of a kind", minWidth);
        fullHouseBTN = scoreButton("Full House", minWidth);
        smStrBTN = scoreButton("Small Straight", minWidth);
        lgStrBTN = scoreButton("Large Straight", minWidth);
        yahtzeeBTN = scoreButton("Yahtzee", minWidth);
        chanceBTN = scoreButton("Chance", minWidth);

        // scoring pane - lower section labels
        totalLBL = scoreLabel("Total Score");
        GridPane.setHalignment(totalLBL, HPos.RIGHT);

        // scoring pane - lower section text fields
        threeOKTF = scoreTextField(minWidth);
        fourOKTF = scoreTextField(minWidth);
        fullHouseTF = scoreTextField(minWidth);
        smStrTF = scoreTextField(minWidth);
        lgStrTF = scoreTextField(minWidth);
        yahtzeeTF = scoreTextField(minWidth);
        chanceTF = scoreTextField(minWidth);
        totalTF = scoreTextField(minWidth);
    }

    /**
     * Sum the total of dice with value of dtype.
     *
     * @param d sorted dice set.
     * @param dtype the die value to sum.
     * @return int as the sum of dice matching dtype.
     */
    public int totalOf(int[] d, int dtype) {
        int count = 0;

        // count the dtype dice
        for (int x = 0; x < 5; x++)
            if (d[x] == dtype)
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

            if (b == rollBTN) {
                if (rolls > 0)
                    roll();
                setPrompts(true);
                return;
            }

            // if it's not the rollBTN, check if they've even rolled.
            if ( rolls == 3 ) {
                rollFirst();
                return;
            }

            // might as well sort them.
            // we're done here...
            Arrays.sort(dice);
            setPrompts(false);

            if (b == onesBTN) {
                onesTF.setText("" + totalOf(dice, 1));
            } else if (b == twosBTN) {
                twosTF.setText("" + totalOf(dice, 2));
            } else if (b == threesBTN) {
                threesTF.setText("" + totalOf(dice, 3));
            } else if (b == foursBTN) {
                foursTF.setText("" + totalOf(dice, 4));
            } else if (b == fivesBTN) {
                fivesTF.setText("" + totalOf(dice, 5));
            } else if (b == sixesBTN) {
                sixesTF.setText("" + totalOf(dice, 6));
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

            // disable button, then update the total.
            b.setDisable(true);
            updateTotal();
            resetDice();
            gameOver();
        }
    }

    /**
     * Reset the number of rolls and the dice to the beginning.
     * show the dice and update the rolls.
     */
    public void resetDice() {
        rolls = 3;
        for (int x = 0; x < 5; x++) {
            dice[x] = 0;
            diceTB[x].setSelected(false);
            //diceTB[x].setDisable(false);
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
        detailPane.add(rollsLeftLBL, 0, 1);
        detailPane.add(rollsLeftTF, 1, 1);
        detailPane.add(diceTotalLBL, 0, 2);
        detailPane.add(diceTotalTF, 1, 2);

        // dice pane
        dicePane.setHgap(5);
        dicePane.setVgap(5);
        dicePane.setAlignment(Pos.CENTER);
        dicePane.add(rollBTN, 8, 5);
        for (int x = 0; x < 5; x++)
            dicePane.add(diceTB[x], x, 5);
        // scoring pane - upper section
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(onesBTN, 0, 4);
        pane.add(onesTF, 1, 4);
        pane.add(twosBTN, 0, 5);
        pane.add(twosTF, 1, 5);
        pane.add(threesBTN, 0, 6);
        pane.add(threesTF, 1, 6);
        pane.add(foursBTN, 0, 7);
        pane.add(foursTF, 1, 7);
        pane.add(fivesBTN, 0, 8);
        pane.add(fivesTF, 1, 8);
        pane.add(sixesBTN, 0, 9);
        pane.add(sixesTF, 1, 9);

        pane.add(subtotalLBL, 0, 10);
        pane.add(subtotalTF, 1, 10);
        pane.add(bonusLBL, 0, 11);
        pane.add(bonusTF, 1, 11);
        pane.add(upperTotalLBL, 0, 12);
        pane.add(upperTotalTF, 1, 12);

        // lower section
        pane.add(threeOKBTN, 2, 13);
        pane.add(threeOKTF, 3, 13);
        pane.add(fourOKBTN, 2, 14);
        pane.add(fourOKTF, 3, 14);
        pane.add(fullHouseBTN, 2, 15);
        pane.add(fullHouseTF, 3, 15);
        pane.add(smStrBTN, 2, 16);
        pane.add(smStrTF, 3, 16);
        pane.add(lgStrBTN, 2, 17);
        pane.add(lgStrTF, 3, 17);
        pane.add(yahtzeeBTN, 2, 18);
        pane.add(yahtzeeTF, 3, 18);
        pane.add(chanceBTN, 2, 19);
        pane.add(chanceTF, 3, 19);
        pane.add(totalLBL, 2, 20);
        pane.add(totalTF, 3, 20);

        primaryStage.setTitle("Yahtzee (\u00AE Hasbro)");
        primaryStage.setScene(scene);
        primaryStage.show();

        resetDice();
        showDice();
    }

    /**
     * Roll the dice, adjust rolls, and show the dice.
     */
    public void roll() {

        for (int x = 0; x < 5; x++) {
            if (rolls == 3 || !diceTB[x].isSelected())
                dice[x] = (int) (Math.random() * 6 + 1);
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
        for (int x = 0; x < 5; x++)
            sum = sum + dice[x];
        diceTotalTF.setText("" + sum);
    }

    /**
     * Update the upper total along with the bonus. This is not the most
     * efficient, but demonstrates a need to recognize we are dealing with
     * strings and ints everywhere.
     */
    public void updateUpperTotal() {
        int sum;
        // if button is disabled, something was written in the TF
        sum = (onesBTN.isDisabled() ? Integer.parseInt(onesTF.getText()) : 0)
                + (twosBTN.isDisabled() ? Integer.parseInt(twosTF.getText()) : 0)
                + (threesBTN.isDisabled() ? Integer.parseInt(threesTF.getText()) : 0)
                + (foursBTN.isDisabled() ? Integer.parseInt(foursTF.getText()) : 0)
                + (fivesBTN.isDisabled() ? Integer.parseInt(fivesTF.getText()) : 0)
                + (sixesBTN.isDisabled() ? Integer.parseInt(sixesTF.getText()) : 0);

        subtotalTF.setText("" + sum);
        bonusTF.setText(sum >= 63 ? "35" : "0");
        upperTotalTF.setText("" + (sum + Integer.parseInt(bonusTF.getText())));
    }

    /**
     * Update total calling updateUpperTotal().
     */
    public void updateTotal() {
        int sum;

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
     *
     * @param d as the array of dice
     * @return true if it's a lower straight.
     */
    public static boolean isLowerStraight(int[] d) {
        int v = d[0];
        for (int x = 1; x <= 3; x++) {
            if (d[x] != v + x)
                return false;
        }
        return true;
    }

    /**
     * Determine if the last four dice in the sorted array are sequential.
     *
     * @param d as the array of dice.
     * @return true if it's an upper straight.
     */
    public static boolean isUpperStraight(int[] d) {
        int v = d[1];
        for (int x = 2; x <= 4; x++)
            if (d[x] != v + x - 1)
                return false;
        return true;
    }

    /**
     * Determine if the sorted array of dice is a small straight.
     *
     * @param d is the array of dice
     * @return true if it's a small straight
     */
    public static boolean isSmallStraight(int[] d) {
        // remove dupes to assess 3 4 4 5 6.
        // expensive but worth it.
        int[] t = Arrays.stream(d).distinct().toArray();
        return (t.length >= 4 && isLowerStraight(t)) || (t.length == 5 && isUpperStraight(t));
    }

    /**
     * Determine if the sorted array of dice is a large straight
     *
     * @param d as the array of dice.
     * @return true if all values are sequential.
     */
    public static boolean isLargeStraight(int[] d) {
        int v = d[0];
        for (int x = 1; x <= 4; x++)
            if (d[x] != v + x)
                return false;
        return true;
    }

    /**
     * Determine if the sorted array of dice is a fill house.
     *
     * @param d as the array of dice.
     * @return true if the patter xxyyy or xxxyy can be discerned.
     */
    public static boolean isFullHouse(int[] d) {
        return ((d[0] != d[4]) && // yahtzee is not a full house.
                (((d[0] == d[1]) && (d[2] == d[4])) ||
                        ((d[0] == d[2]) && (d[3] == d[4]))));
    }

    /**
     * Determine if the sorted array is 3 of a kind
     *
     * @param d as the sorted array of dice
     * @return true if 1&3, 2&4, or 3&5 match.
     */
    public static boolean is3OfAKind(int[] d) {
        return (d[0] == d[2]) || (d[1] == d[3]) || (d[2] == d[4]);
    }

    /**
     * Determine if the sorted array is 4 of a kind
     *
     * @param d as the sorted array of dice.
     * @return true if 1&4 or 2&5 match.
     */
    public static boolean is4OfAKind(int[] d) {
        return (d[0] == d[3]) || (d[1] == d[4]);
    }

    /**
     * Determine if the sorted array is 5 of a kind
     *
     * @param d as the sorted array of dice.
     * @return true if 1&5 match.
     */
    public static boolean isYahtzee(int[] d) {
        return d[0] == d[4];
    }
}
