package com.calebleavell.textinterface;

import com.calebleavell.textinterface.*;
import com.calebleavell.textinterface.scenes.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 */
public final class App{

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        NumberedListInputScene myInput = new NumberedListInputScene.Builder()
            .list("choice 1", "choice 2", "choice 3")
            .build();

        myInput.getChildren().add(
            new TextScene.Builder()
                .text(() -> {return "You entered: " + myInput.getInput();})
                .build()
        );

        myInput.display();
    }   

}

