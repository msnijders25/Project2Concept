package com.example.project2concept;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private VBox chatItems;
    private VBox chatContainer;
    private TextArea chatTextArea;
    private TextArea inputTextArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        chatItems = new VBox();
        chatItems.setPrefWidth(200);
        chatItems.setSpacing(5);

        for (int i = 1; i <= 3; i++) {
            addChatItem();
        }

        chatContainer = new VBox();
        chatContainer.setSpacing(5);

        HBox inputContainer = new HBox();
        inputContainer.setSpacing(5);

        chatTextArea = new TextArea();
        chatTextArea.setPrefSize(600, 530);

        inputTextArea = new TextArea();
        inputTextArea.setPrefSize(600, 50);
        inputTextArea.setPromptText("Type your message...");

        // Event handler for handling the Enter key press
        inputTextArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
                    event.consume(); // Prevents a new line from being added to the text area
                    String message = inputTextArea.getText().trim(); // Get the entered message
                    if (!message.isEmpty()) {
                        // Process the entered message (e.g., send it, display it in the chat, etc.)
                        chatTextArea.appendText("You: " + message + "\n");
                        inputTextArea.clear(); // Clear the input area after sending the message
                    }
                }
            }
        });

        inputContainer.getChildren().addAll(inputTextArea);
        chatContainer.getChildren().addAll(chatTextArea, inputContainer);

        chatItems.getStyleClass().add("chat-items");
        chatContainer.getStyleClass().add("chat-container");

        root.setLeft(chatItems);
        root.setCenter(chatContainer);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("styles.css"); // Add custom CSS styles if needed

        primaryStage.setTitle("Chat42");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addChatItem() {
        TextArea item = new TextArea("New Chat");
        item.setPrefHeight(20);
        item.setStyle("-fx-control-inner-background: #333333;");
        item.setOnMouseClicked(event -> {
            chatTextArea.clear();
            chatTextArea.appendText("Clicked on: " + item.getText() + "\n");
        });
        chatItems.getChildren().add(item);
    }

    public static void main(String args[]) {
        launch(args);
    }
}
