package com.github.NeRdTheNed.dropdownuiprojects;

//javafx application imports
import com.github.NeRdTheNed.dropdownui.DropdownUI;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
//DropdownPageUI related imports
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Node;
//all javafx geometry used in scene
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author nedloynd
 */
public class ChoreList extends Application {

    //Used to register activities to DropdownUI, and display the resulting gridPane.
    
    @Override
    public void start(Stage primaryStage) {

        DropdownUI chorePageUI = new DropdownUI();

        //label on top of accordian
        String accordianTitle = "Isolation activities";
        
        ////start construction of all activity groups
        
        ///start construction of Homework activity group

        /*create a hashmap containing all pages for the activity*/
        HashMap<String, ArrayList<Node>> groupHomework = new HashMap<>();

        //start construction of Maths activity
        ArrayList<Node> activitiesToRegisterMaths = new ArrayList<>();
        VBox mathTaskList = new VBox();

        /*add the node to the hashmap of pages etc*/
        activitiesToRegisterMaths.add(new Label("Here is all the Math homework that you need to do!"));
        
        mathTaskList.getChildren().add(new Label("1. Calculus"));
        mathTaskList.getChildren().add(new Label("2. Trigonometry"));
        mathTaskList.getChildren().add(new Label("3. Imaginary numbers"));
        mathTaskList.getChildren().add(new Label("4. Etc"));

        activitiesToRegisterMaths.add(mathTaskList);

        /*add the activity (containing the pages constructed) to the group of activities*/
        groupHomework.put("Maths", activitiesToRegisterMaths);

        //end maths
        //start english
        ArrayList<Node> activitiesToRegisterEnglish = new ArrayList<>();
        VBox englishTaskList = new VBox();

        activitiesToRegisterEnglish.add(new Label("Here is all the English homework that you need to do!"));

        englishTaskList.getChildren().add(new Label("1. Study Shakespaer"));
        englishTaskList.getChildren().add(new Label("2. Write poetry"));
        englishTaskList.getChildren().add(new Label("3. Etc"));

        activitiesToRegisterEnglish.add(englishTaskList);

        VBox shakespeareVBox = new VBox();
        shakespeareVBox.setAlignment(Pos.CENTER);

        /*hopefully Wikimedia doesn't change their url formats*/
        String shakespeareUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/375px-Shakespeare.jpg";

        Image shakespeareImg = new Image(shakespeareUrl, 400, 0, true, false);
        ImageView imageView = new ImageView(shakespeareImg);
        shakespeareVBox.getChildren().add(imageView);
        shakespeareVBox.getChildren().add(new Label("A picture of Shakespeare!"));

        activitiesToRegisterEnglish.add(shakespeareVBox);

        groupHomework.put("English", activitiesToRegisterEnglish);

        //end english
        
        /*register the group of activies in the processor factory*/
        chorePageUI.processorFactory.registerActivityGroup("Homework", groupHomework);

        ///end Homework
        
        ///start Music
        HashMap<String, ArrayList<Node>> groupMusic = new HashMap<>();

        //start practice
        ArrayList<Node> activitiesToRegisterPractice = new ArrayList<>();
        VBox musicPracticeTaskList = new VBox();

        activitiesToRegisterPractice.add(new Label("Here is all the music practice that you need to do!"));

        musicPracticeTaskList.getChildren().add(new Label("1. Practice scales"));
        musicPracticeTaskList.getChildren().add(new Label("2. Practice piano pieces"));
        musicPracticeTaskList.getChildren().add(new Label("3. Etc"));
        
        Label paddedPracticeEntry = new Label("Remember to have fun!");
        paddedPracticeEntry.setPadding(new Insets(10, 0, 0, 0));
        musicPracticeTaskList.getChildren().add(paddedPracticeEntry);

        activitiesToRegisterPractice.add(musicPracticeTaskList);

        groupMusic.put("music practice", activitiesToRegisterPractice);

        //end practice
        chorePageUI.processorFactory.registerActivityGroup("Music", groupMusic);

        ///end Music,
        ////end construction of all activity groups.
        
        //create the scene
        
        Scene scene = new Scene(chorePageUI.getActivityPane(accordianTitle), 800, 400);

        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(300);

        primaryStage.setTitle("Activity chooser");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

}
