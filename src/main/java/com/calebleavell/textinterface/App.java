package com.calebleavell.textinterface;

import com.calebleavell.textinterface.Elements.NumberedList;
import com.calebleavell.textinterface.Elements.NumberedActionChooser;
import com.calebleavell.textinterface.Elements.TextInput;
import com.calebleavell.textinterface.Elements.Text;
import com.calebleavell.textinterface.Elements.Scene;
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
        NumberedActionChooser myChoices = new NumberedActionChooser(
            new NumberedList(new ArrayList<Text>(Arrays.asList(
                new Text("Print hello"),
                new Text("Print goodbye")
            )), null, null, null), null, 
            new ArrayList<Runnable>(Arrays.asList(
                () -> {System.out.println("hello"); },
                () -> {System.out.println("goodbye"); }
        )), null, null, null, null);

        Scene myScene1 = new Scene(null, null, "hello!");
        Scene myScene2 = new Scene(null, null, "hello?");

        System.out.println(myScene1.equals(myScene2));

    }

}
