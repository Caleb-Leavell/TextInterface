package com.calebleavell.textinterface;

import com.calebleavell.textinterface.Elements.NumberedList;
import com.calebleavell.textinterface.Elements.NumberedSceneChooser;
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
        NumberedSceneChooser myChooser = new NumberedSceneChooser(new ArrayList<Scene>(Arrays.asList(
            new Text("scene 1", "Scene 1", null, null),
            new Text("scene 2", "Scene 2", null, null),
            new Text("scene 3", "Scene 3", null, null)
        )), null, null, null);

        myChooser.display();

    }

}
