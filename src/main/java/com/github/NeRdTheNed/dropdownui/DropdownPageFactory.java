package com.github.NeRdTheNed.dropdownui;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.util.Callback;

public class DropdownPageFactory implements Callback<Integer, Node> {

    static HashMap<String, HashMap<String, ArrayList<Node>>> activityGroupList = new HashMap<>();
    static HashMap<String, ArrayList<Node>> activityList = new HashMap<>();
    static ArrayList<Node> currentActivityPages = new ArrayList<>();
    static String currentActivityName;

    public void registerActivityGroup(String activityType, HashMap<String, ArrayList<Node>> activity) {
        activityGroupList.put(activityType, activity);
    }

    public int getAmountOfActivitiesInGroup(String activityType) {
        return activityGroupList.get(activityType).size();
    }

    public int getAmountOfPagesInActivity(String activityType, String activityName) {
        return activityGroupList.get(activityType).get(activityName).size();
    }

    public HashMap<String, HashMap<String, ArrayList<Node>>> getActivityGroupList() {
        return activityGroupList;
    }

    void setCurrentActivity(String activityType, String activityName) {
        currentActivityPages = activityGroupList.get(activityType).get(activityName);
        currentActivityName = activityName;
    }

    @Override
    public Node call(Integer pageIndex) {
        if (activityGroupList.size() > 0) {
            if ((currentActivityPages != null) && (currentActivityPages.size() > pageIndex)) {
                return currentActivityPages.get(pageIndex);
            } else {
                return Util.createErrorBox("No pages for activity (" + currentActivityName + ") at page " + (pageIndex + 1) + "!");
            }
        } else {
            return Util.createErrorBox("No activities in activityList!");
        }
    }
}
