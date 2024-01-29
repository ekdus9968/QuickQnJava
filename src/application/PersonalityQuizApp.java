package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JavaFX application class for the Personality Quiz
public class PersonalityQuizApp extends Application {
    private Answerable quiz = new Main();  // Creating an instance of the Main class that implements Answerable

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    // Override method to set up the UI and handle events
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSCI430:SeyoungKan:50301621-Quiz");  // Setting the title of the application window

        // Creating a grid layout for the UI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Creating a list to store ComboBox instances for user choices
        List<ComboBox<String>> choiceBoxes = new ArrayList<>();
        
        // Creating a list of questions (Color, Food, and Activity)
        List<Question> questions = Arrays.asList(new ColorQuestion(), new FoodQuestion(), new ActivityQuestion());

        // Setting up UI elements for each question
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            Label questionLabel = new Label(q.getQuestion());
            ComboBox<String> choiceBox = new ComboBox<>();
            choiceBox.getItems().addAll(q.getChoices());

            // Adding question label and choice box to the grid layout
            grid.add(questionLabel, 0, i);
            grid.add(choiceBox, 1, i);

            // Adding the choice box to the list for later reference
            choiceBoxes.add(choiceBox);
        }
        
        // Creating a submit button
        Button submitButton = new Button("Submit");  

        // Set up event handler for the submit button
        submitButton.setOnAction(e -> {
            Map<String, String> userAnswers = new HashMap<>();
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                String selectedChoice = choiceBoxes.get(i).getValue();
                userAnswers.put(q.getQuestion(), selectedChoice);
                quiz.answerQuestion(q.getQuestion(), selectedChoice);
            }
            // Showing the result based on user's choices
            showResult(quiz.getResult(), questions, userAnswers);  
        });

        // Set up UI layout for the submit button
        int row = questions.size();
        grid.add(submitButton, 1, row);

        // Creating a scene with the grid layout
        Scene scene = new Scene(grid, 400, 300);  
        primaryStage.setScene(scene);
        primaryStage.show();  // Displaying the application window
    }

    // Method to display the quiz result along with the user's choices
    private void showResult(String result, List<Question> questions, Map<String, String> userAnswers) {
        StringBuilder resultText = new StringBuilder(result);

        // Add user's choices to the result
        resultText.append("\n\nYour Choices:\n");
        for (Question q : questions) {
            String question = q.getQuestion();
            String userAnswer = userAnswers.get(question);
            resultText.append(question).append(": ").append(userAnswer).append("\n");
        }

        // Creating a TextArea to display the result and user's choices
        TextArea textArea = new TextArea(resultText.toString());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        // Creating a dialog to show the result
        Dialog<String> resultDialog = new Dialog<>();
        resultDialog.setTitle("CSCI428:SeyoungKan:50301621-Result");
        resultDialog.getDialogPane().setContent(scrollPane);

        // Adding a close button to the dialog
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
        resultDialog.getDialogPane().getButtonTypes().add(closeButton);

        // Displaying the result dialog
        resultDialog.showAndWait();  
    }
}
