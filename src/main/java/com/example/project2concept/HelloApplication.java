package com.example.project2concept;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private VBox chats;
    private VBox chatContainer;
    private TextArea chatTextArea;
    private TextArea inputText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        chats = new VBox();
        chats.setPrefWidth(200);
        chats.setSpacing(10);

        chatContainer = new VBox();
        chatContainer.setSpacing(10);

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
                        // Process the entered message (e.g., send it, display it in the chat, etc.)
                        chatTextArea.appendText("You: " + message + "\n");
                        inputText.clear(); // Clear the input area after sending the message
                    }
                }
            }
        });

        inputContainer.getChildren().addAll(inputText);
        chatContainer.getChildren().addAll(chatTextArea, inputContainer);

        Button newChatButton = new Button("New Chat");
        newChatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createNewChat();
            }
        });

        HBox buttonContainer = new HBox(newChatButton);
        buttonContainer.setSpacing(5);

        root.setTop(buttonContainer);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(chats, chatContainer);
        splitPane.setDividerPositions(0.2); // Set the initial divider position

        root.setCenter(splitPane);

        // Create a custom MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("chat-menu-bar");

        // Create a File menu with Open and Save menu items
        Menu fileMenu = new Menu("File");
        fileMenu.getStyleClass().add("chat-menu");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.getStyleClass().add("chat-menu-item");
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.getStyleClass().add("chat-menu-item");
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem);

        // Create an Edit menu with Copy and Paste menu items
        Menu editMenu = new Menu("Edit");
        editMenu.getStyleClass().add("chat-menu");
        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.getStyleClass().add("chat-menu-item");
        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.getStyleClass().add("chat-menu-item");
        editMenu.getItems().addAll(copyMenuItem, pasteMenuItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu);

        // Position the MenuBar at the left bottom
        root.setLeft(menuBar);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("style.css"); // Load custom CSS
        primaryStage.setTitle("Chat42");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createNewChat() {
        String chatName = "New Chat"; // Default chat name
        addChatItem(chatName);
    }

    private void addChatItem(String chatName) {
        TextArea item = new TextArea(chatName);
        item.setPrefHeight(20);
        item.setStyle("-fx-control-inner-background: #333333;");
        item.setEditable(false); // Set the chat name TextArea uneditable

        // Mini button for editing chat name
        Button editButton = new Button("Edit");
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openEditWindow(item);
            }
        });

        HBox chatItemContainer = new HBox(item, editButton);
        chatItemContainer.setSpacing(5);

        item.setOnMouseClicked(event -> {
            chatTextArea.clear();
            chatTextArea.appendText("Clicked on: " + item.getText() + "\n");
        });
        chats.getChildren().add(chatItemContainer);
    }

    private void openEditWindow(TextArea chatNameTextArea) {
        Stage editStage = new Stage();
        BorderPane editRoot = new BorderPane();
        TextArea editTextArea = new TextArea(chatNameTextArea.getText());
        Button saveButton = new Button("Save");

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String updatedName = editTextArea.getText();
                chatNameTextArea.setText(updatedName);
                editStage.close();
            }
        });

        editRoot.setCenter(editTextArea);
        editRoot.setBottom(saveButton);
        Scene editScene = new Scene(editRoot, 300, 200);
        editStage.setScene(editScene);
        editStage.setTitle("Edit Chat Name");
        editStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}