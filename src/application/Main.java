package application;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


//The main class implementing the Answerable interface to manage the quiz
public class Main implements Answerable {
 // List to store different types of questions
 private List<Question> questions; 
 //Map to store user's answers for each question
 private Map<String, String> answers;  

 // Constructor to initialize questions and answers
 public Main() {
     // Creating instances of ColorQuestion, FoodQuestion, and ActivityQuestion
     this.questions = Arrays.asList(new ColorQuestion(), new FoodQuestion(), new ActivityQuestion());
     // Initializing the answers map
     this.answers = new HashMap<>();  
 }

 // Implementation of the Answerable interface method to record user's answer for a question
 @Override
 public void answerQuestion(String question, String answer) {
	 // Storing the user's answer in the answers map
     answers.put(question, answer);  
 }

 // Implementation of the Answerable interface method to generate a result based on user's answers
 @Override
 public String getResult() {
	 // Retrieving user's answer for color question
     String colorAnswer = answers.get("Choose a color:");  
     // Retrieving user's answer for food question
     String foodAnswer = answers.get("Choose a food:");   
     // Retrieving user's answer for activity question
     String activityAnswer = answers.get("Choose an activity:");  

     // Customizing the result based on the combined answers
     if ("Red".equals(colorAnswer) && "Pizza".equals(foodAnswer) && "Reading".equals(activityAnswer)) {
         return "You have a warm heart and you are a bookworm!";
     } else if ("Blue".equals(colorAnswer) && "Burger".equals(foodAnswer) && "Swimming".equals(activityAnswer)) {
         return "You are adventurous, love the sea, and enjoy reading!";
     } else if ("Green".equals(colorAnswer) && "Salad".equals(foodAnswer) && "Hiking".equals(activityAnswer)) {
         return "You enjoy a healthy lifestyle, love nature, and are an avid reader!";
     } else {
         return "Unknown"; 
     }
 }
}

//Abstract class representing a generic question
abstract class Question {
 // String to store the question text
 private String question;     
 //List to store possible choices for the question
 private List<String> choices;   

 // Constructor to initialize question and choices
 public Question(String question, List<String> choices) {
     this.question = question;
     this.choices = choices;
 }

 // Getter method to retrieve the question text
 public String getQuestion() {
     return question;
 }

 // Getter method to retrieve possible choices for the question
 public List<String> getChoices() {
     return choices;
 }

 // Abstract method to be implemented by subclasses to provide a result based on user's answer
 public abstract String getResult(String answer);
}

//Concrete subclass representing a color question
class ColorQuestion extends Question {
 // Constructor to initialize color question with possible choices
 public ColorQuestion() {
     super("Choose a color:", Arrays.asList("Red", "Blue", "Green"));
 }

 // Implementation of the abstract method to provide a result based on user's answer
 @Override
 public String getResult(String answer) {
     // Customizing the result based on the user's color choice
     if ("Red".equals(answer)) {
         return "Red Panda";
     } else if ("Blue".equals(answer)) {
         return "Blue Dolphin";
     } else if ("Green".equals(answer)) {
         return "Green Turtle";
     } else {
         return "Unknown";
     }
 }
}

//Concrete subclass representing a food question
class FoodQuestion extends Question {
 // Constructor to initialize food question with possible choices
 public FoodQuestion() {
     super("Choose a food:", Arrays.asList("Pizza", "Burger", "Salad"));
 }

 // Implementation of the abstract method to provide a result based on user's answer
 @Override
 public String getResult(String answer) {
     // Customizing the result based on the user's food choice
     if ("Pizza".equals(answer)) {
         return "Pizza Lover";
     } else if ("Burger".equals(answer)) {
         return "Burger Enthusiast";
     } else if ("Salad".equals(answer)) {
         return "Healthy Eater";
     } else {
         return "Unknown";
     }
 }
}

//Concrete subclass representing an activity question
class ActivityQuestion extends Question {
 // Constructor to initialize activity question with possible choices
 public ActivityQuestion() {
     super("Choose an activity:", Arrays.asList("Reading", "Swimming", "Hiking"));
 }

 // Implementation of the abstract method to provide a result based on user's answer
 @Override
 public String getResult(String answer) {
     // Customizing the result based on the user's activity choice
     if ("Reading".equals(answer)) {
         return "Bookworm";
     } else if ("Swimming".equals(answer)) {
         return "Swimmer";
     } else if ("Hiking".equals(answer)) {
         return "Hiker";
     } else {
         return "Unknown";
     }
 }
}

//Interface providing a common structure for answering questions and getting results
interface Answerable {
 // Method to record user's answer for a question
 void answerQuestion(String question, String answer);  
 //Method to generate a result based on user's answers
 String getResult();  
}
