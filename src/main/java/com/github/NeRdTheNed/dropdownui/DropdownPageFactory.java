package com.github.NeRdTheNed.dropdownui;

//data storage imports
import java.util.ArrayList;
import java.util.HashMap;
//javafx component imports
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//custom pagefactory import
import javafx.util.Callback;

/**
 *
 * @author nedloynd
 */
public class DropdownPageFactory implements Callback<Integer, Node> {

    /*
    
    The class DropdownPageFactory handles:
    the registration of activity groups (and by extention the activities inside an activity group),
    and returning a "page" (node) to the pagination when a page is requested at an index.
    
     */
    
    CommonGUIElements commonText = new CommonGUIElements();

    //a hashmap of activity groups
    HashMap<String, HashMap<String, ArrayList<Node>>> activityGroupList = new HashMap<>();
    //a hashmap of activities inside an activity group
    HashMap<String, ArrayList<Node>> activityList = new HashMap<>();
    //a list of "pages" (nodes) for an activity inside an activity group
    ArrayList<Node> currentActivityPages = new ArrayList<>();
    //the name of the currently selected activity inside the currently selected activity group
    String currentActivityName;

    public void registerActivityGroup(String activityType, HashMap<String, ArrayList<Node>> activity) {

        //registers a group of activityies inside the hashmap of activity groups
        activityGroupList.put(activityType, activity);

    }

    public int getAmountOfActivitiesInGroup(String activityType) {

        //gets the hashmap of activities inside an activity group
        //todo prevent null pointers if there are any and add better error handling
        return activityGroupList.get(activityType).size();

    }

    public int getAmountOfPagesInActivity(String activityType, String activityName) {

        //gets the amount of activity inside 
        //todo prevent null pointers if there are any add better error handling
        return activityGroupList.get(activityType).get(activityName).size();

    }

    public HashMap<String, HashMap<String, ArrayList<Node>>> getActivityGroupList() {

        return activityGroupList;

    }

    void setCurrentActivity(String activityType, String activityName) {

        //todo prevent null pointers if there are any and add better error handling
        currentActivityPages = activityGroupList.get(activityType).get(activityName);
        currentActivityName = activityName;

    }

    @Override
    public Node call(Integer pageIndex) {

        //catch null errors
        if (activityGroupList.size() > 0) {

            if (currentActivityPages != null && currentActivityPages.size() > pageIndex) {

                return currentActivityPages.get(pageIndex);

            } else {

                //todo: add better error handling
                return commonText.errorBox("No pages for activity (" + currentActivityName + ") at page " + (pageIndex + 1) + "!");

            }

        } else {

            //todo: add better error handling
            return commonText.errorBox("No activities in activityList!");

        }

    }
}

class CommonGUIElements {

    /*
    
    Contains any common GUI elements that might be used across the program.
    Only has an error message right now.
    
     */
    
    VBox errorBox(String error) {

        //todo: add better error handling
        return new VBox(10, new Label("The selected element"),
                new Label("returned an error!"),
                new Label("There was probably a bug..."),
                new Label("Bug text:"),
                new Label(error));

    }

}