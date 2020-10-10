package com.github.NeRdTheNed.dropdownui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class DropdownUI {
    
    GridPane gpane;
    Pagination pagenat;
    public DropdownPageFactory processorFactory;

    private Accordion getAccordionOfActivities() {
        
    	Accordion toAllLawsOfPhysics/*, a bee should not be able to fly.*/ = new Accordion();

        toAllLawsOfPhysics.setMinSize(100, 100);

        HashMap<String, HashMap<String, ArrayList<Node>>> registeredAcivities = processorFactory.getActivityGroupList();

        if (registeredAcivities.size() > 0) {

            for (String key : registeredAcivities.keySet()) {

                TitledPane planeToAdd = new TitledPane();
                VBox listOfButtons = new VBox(10);
                
                planeToAdd.setText(key);

                for (String activityName : registeredAcivities.get(key).keySet()) {

                    Button toAdd = new Button("Open " + activityName + "...");

                    toAdd.setOnAction(e -> updatePagesOnButtonPress(key, activityName));

                    listOfButtons.getChildren().add(toAdd);
                }

                toAllLawsOfPhysics.getPanes().add(new TitledPane(key, listOfButtons));

            }

        } else {

            toAllLawsOfPhysics.getPanes().add(new TitledPane("No activities found!", Util.createErrorBox("activityList was empety!")));

        }

        return toAllLawsOfPhysics;

    }

    private void updatePagesOnButtonPress(String activityType, String activityName) {
    	
        processorFactory.setCurrentActivity(activityType, activityName);
        int foo = processorFactory.getAmountOfPagesInActivity(activityType, activityName);

        /*
        
        Hacky code to ensure that the page always updates, as it doesn't update until a new page is retrived.
        
        Normally, you can update a pageination by changing the maximum page count.
        However, if the page count doesn't change, then it doesn't update.
        
        For example, if you click a button, and it has 2 pages,
        the pagination will set the maximum page count to 2.
        Clicking another button that sets the maximum page count to 2 will also attempt to set the maximum page count,
        but because it's the same (2), it doesn't update the maximum page count, so the pageination doesn't update.
        As a result, no new page is retrivied. This means it will show the same page as before.
        
        This code ensures that the new maximum page count will always be different from the previous maximum page count,
        by setting the maximum page count twice to two different values which will always differ by one.
        This ensures that the maximum page count will always be set to two unique values,
        and therefore allways differ from the maximum previous page count, as it can only have one value.
        
        This will, as said above, ensure that the page always updates, by always having a different maximum page count
        to the previous maximum page count.
        
         */
        
        pagenat.setPageCount(foo + 1);
        pagenat.setPageCount(foo);
        pagenat.setCurrentPageIndex(0);

        pagenat.setVisible(true);

    }

    public GridPane getActivityPane(String listTitle) {
        
        gpane = new GridPane();

        ColumnConstraints leftCol = new ColumnConstraints(200);
        ColumnConstraints rightCol = new ColumnConstraints(400);

        leftCol.setPercentWidth(30);
        rightCol.setPercentWidth(70);

        RowConstraints topRow = new RowConstraints();
        RowConstraints mainRowCons = new RowConstraints();

        topRow.setPercentHeight(5);
        mainRowCons.setPercentHeight(95);

        gpane.getColumnConstraints().addAll(leftCol, rightCol);
        gpane.getRowConstraints().addAll(topRow, mainRowCons);

        gpane.setHgap(20);
        gpane.setVgap(20);
        gpane.setPadding(new Insets(20, 20, 20, 20));
        gpane.setAlignment(Pos.CENTER);

        gpane.addRow(0, new Label(listTitle));
        gpane.addRow(1, getAccordionOfActivities(), pagenat);

        return gpane;

    }

    public DropdownUI() {
        
        pagenat = new Pagination(0, 0);
        processorFactory = new DropdownPageFactory();

        pagenat.setPageFactory(processorFactory);
        pagenat.setMinSize(100, 100);

        pagenat.setVisible(false);

    }

}