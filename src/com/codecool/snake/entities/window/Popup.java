package com.codecool.snake.entities.window;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Popup {

    private String messageField;
    private String buttonName;
    private Stage dialog = new Stage();

    public void createGameEndPopUp(Stage primaryStage) {
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        VBox dialogueVBox = new VBox(20);

        dialogueVBox.getChildren().addAll(createTextField());
        dialogueVBox.getChildren().addAll(createCloseButton());

        Scene dialogScene = new Scene(dialogueVBox, 200, 70);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public Button createCloseButton() {
        Button button = new Button(buttonName);

        button.setOnAction((e) -> {
                dialog.close();
        });

        return button;
    }

    public Text createTextField() {
        Text text = new Text(messageField);

        return text;
    }


    public void setMessageField(String messageField) {
        this.messageField = messageField;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
