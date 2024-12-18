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

    int[] dice = new int[5];
    int rolls = 3;

    public int totalOf(int dtype) {
        int count = 0;

        // count the dtype dice
        for (int x = 0; x < 5; x++)
            if (dice[x] == dtype)
                count++;

        // number of dice times dtype
        return count * dtype;
    }

    // One handler to rule them all, and in the darkness bind them...
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Button b = (Button)e.getSource();
            boolean off = true;
            boolean r = false; // do I need to reset?

            if (b == rollBTN) {
                if ( rolls > 0 )
                    roll();
                off = false;

            } else if (b == onesBTN) {
                onesTF.setText("" + totalOf(1)); r = !r;
            } else if (b == twosBTN) {
                twosTF.setText("" + totalOf(2)); r = !r;
            } else if (b == threesBTN) {
                threesTF.setText("" + totalOf(3)); r = !r;
            } else if (b == foursBTN) {
                foursTF.setText("" + totalOf(4)); r = !r;
            } else if (b == fivesBTN) {
                fivesTF.setText("" + totalOf(5)); r = !r;
            } else if (b == sixesBTN) {
                sixesTF.setText("" + totalOf(6)); r = !r;
            } else if (b == threeOKBTN) {
            } else if (b == fourOKBTN) {
            } else if (b == fullHouseBTN) {
            } else if (b == smStrBTN) {
            } else if (b == lgStrBTN) {
            } else if (b == yahtzeeBTN) {
            } else if (b == chanceBTN) {
            }

            b.setDisable(off);
            if (r) resetDice();
        }
    }

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
        final int minWidth = 100;

        for (int x = 0 ; x < 5; x++) {
            diceTB[x] = new ToggleButton("");
            diceTB[x].setMinWidth(minWidth / 3);
        }
        //for (int x = 0 ; x < 5; x++)
        //    System.out.println(dice[x]);

        // One ping only...
        ButtonHandler bh = new ButtonHandler();

        // pane = scores, dicePane = all manner of rolling.
        GridPane detailPane = new GridPane();
        GridPane pane = new GridPane();
        GridPane dicePane = new GridPane();
        
        // And then our main attraction. The VBox holds everything!
        VBox mainView = new VBox(detailPane, dicePane, pane);
        Scene scene = new Scene(mainView, 500, 750);

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

    public void roll() {

        for ( int x = 0; x < 5; x++ ) {
            if ( rolls == 3 || !diceTB[x].isSelected())
                dice[x] = (int)(Math.random() * 6 + 1);
        }
        rolls--;
        showDice();
        updateRolls();
    }

    public void showDice() {
        for (int x = 0; x < 5; x++)
            diceTB[x].setText((dice[x] == 0) ? "" : "" + dice[x]);
        updateDiceTotal();
    }

    public void updateRolls() {
        rollsLeftTF.setText("" + rolls);
    }

    public void updateDiceTotal() {
        int sum = 0;
        for ( int x = 0; x < 5; x++ )
            sum = sum + dice[x];
        diceTotalTF.setText("" + sum);
    }

    public static void main(String[] args) {
        launch(args);
    }
}