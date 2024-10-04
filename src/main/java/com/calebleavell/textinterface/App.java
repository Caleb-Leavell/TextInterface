package com.calebleavell.textinterface;

import com.calebleavell.textinterface.*;
import com.calebleavell.textinterface.scenes.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 */
public final class App {

    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        TextApplication myApp = new TextApplication.Builder().build();
        myApp.setHome(new TextScene.Builder()
            .text(
            """
            ________________________

                Tri-Function App
            ________________________
            """)
            .build()); 

        myApp.run();
    }   

}

