package com.example.project2concept;
mport javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    private VBox chats;
    private VBox chatContainer;
    private TextArea chatTextArea;
    private TextArea inputText;
    private Button addButton;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Username label and text field
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        GridPane.setConstraints(usernameField, 1, 0);

        // Password label and password field
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 1, 1);

        // Login button
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);

        // Add all the elements to the grid
        grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        // Login button event handler
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Perform login validation (dummy example)
            if (username.equals("admin") && password.equals("password")) {
                System.out.println("Login successful!");
                openChatWindow(primaryStage);
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        });

        Scene loginScene = new Scene(grid, 300, 150);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void openChatWindow(Stage primaryStage) {
        BorderPane root = new BorderPane();

        chats = new VBox();
        chats.setPrefWidth(200);
        chats.setSpacing(5);

        for (int i = 0; i <= 1; i++) {
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
        inputText.setPromptText("Type a message...");

        inputText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
                    event.consume(); // Prevents a new line from being added to the text area
                    String message = inputText.getText().trim(); // Get the entered message
                    if (!message.isEmpty()) {
                        // Process the entered message (e.g., send it, display it in the chat, etc.)
                        chatTextArea.appendText("You: " + message + "\n");
                        inputText.clear();                         // Clear the input area after sending the message
                    }
                }
            }
        });

        inputContainer.getChildren().addAll(inputText);
        chatContainer.getChildren().addAll(chatTextArea, inputContainer);

        chats.getStyleClass().add("chat-items");
        chatContainer.getStyleClass().add("chat-container");

        root.setCenter(chatContainer);

        createAddButton();
        createSendButton();

        // Create the menu
        VBox menuContainer = new VBox();
        menuContainer.setSpacing(5);

        MenuBar menuBar = new MenuBar();

        // Menu File
        Menu menuFile = new Menu("File");
        MenuItem fileItem = new MenuItem("Open");
        menuFile.getItems().add(fileItem);

        // Menu Option
        Menu menuOption = new Menu("Option");
        MenuItem optionItem = new MenuItem("Settings");
        menuOption.getItems().add(optionItem);

        // Menu Edit
        Menu menuEdit = new Menu("Edit");
        MenuItem editItem = new MenuItem("Cut");
        menuEdit.getItems().add(editItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(menuFile, menuOption, menuEdit);

        menuContainer.getChildren().addAll(chats, menuBar);

        root.setLeft(menuContainer);

        Scene chatScene = new Scene(root, 800, 600);
        chatScene.getStylesheets().add("styles.css");

        primaryStage.setTitle("Chat Application");
        primaryStage.setScene(chatScene);
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

    public static void main(String[] args) {
        launch(args);
    }
}
