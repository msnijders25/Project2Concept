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
import javafx.scene.control.Button;

public class HelloApplication extends Application {

    private VBox chats;
    private VBox chatContainer;
    private TextArea chatTextArea;
    private TextArea inputText;
    private Button addButton;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        chats = new VBox();
        chats.setPrefWidth(200);
        chats.setSpacing(5);

        for (int i = 1; i <= 13; i++) {
            addChatItem();
        }

        chatContainer = new VBox();
        chatContainer.setSpacing(5);

        HBox inputContainer = new HBox();
        inputContainer.setSpacing(5);

        chatTextArea = new TextArea();
        chatTextArea.setPrefSize(600, 530);

        inputText = new TextArea();
        inputText.setPrefSize(600, 50);
        inputText.setPromptText("Type een bericht...");

        inputText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
                    event.consume(); // Prevents a new line from being added to the text area
                    String message = inputText.getText().trim(); // Get the entered message
                    if (!message.isEmpty()) {

                        chatTextArea.appendText("You: " + message + "\n");
                        inputText.clear();
                    }
                }
            }
        });

        inputContainer.getChildren().addAll(inputText);
        chatContainer.getChildren().addAll(chatTextArea, inputContainer);

        chats.getStyleClass().add("chat-items");
        chatContainer.getStyleClass().add("chat-container");

        root.setLeft(chats);
        root.setCenter(chatContainer);

        createAddButton();
        createSendButton();

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("styles.css");

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
            chatTextArea.appendText("Geklikt op: " + item.getText() + "\n");
        });
        chats.getChildren().add(item);
    }

    private void createAddButton() {
        addButton = new Button("Add");
        addButton.setOnAction(event -> {
            addChatItem();
        });

        VBox buttonContainer = new VBox();
        buttonContainer.setSpacing(5);
        buttonContainer.getChildren().add(addButton);

        chats.getChildren().add(buttonContainer);
    }
//sendButton
    private void createSendButton() {
        sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String message = inputText.getText().trim();
            if (!message.isEmpty()) {
                chatTextArea.appendText("You: " + message + "\n");
                inputText.clear();
            }
        });
        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(5);
        buttonContainer.getChildren().add(sendButton);

        chatContainer.getChildren().add(buttonContainer);
    }

    public static void main(String args[]) {
        launch(args);
    }
}