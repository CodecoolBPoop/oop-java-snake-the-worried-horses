package com.codecool.snake.entities.playerscore;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Score {

    private int finalScore;

    public void createGameEndPopUp(Stage primaryStage) {
        final Stage dialog = new Stage();
        String winMessage = "Final Score: " + finalScore;

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogueVBox = new VBox(20);

        dialogueVBox.getChildren().addAll(new Text(winMessage));
        Scene dialogScene = new Scene(dialogueVBox, 200, 50);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void checkSneakLength(int lengthOfSnake) {
        finalScore = lengthOfSnake;
    }
}
