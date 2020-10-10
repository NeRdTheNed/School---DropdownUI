package com.github.NeRdTheNed.dropdownui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class Util {
	
    public final static VBox createErrorBox(String error) {

        //todo: add better error handling
        return new VBox(10, new Label("The selected element"),
                new Label("returned an error!"),
                new Label("There was probably a bug..."),
                new Label("Error message:"),
                new Label(error));

    }

}
