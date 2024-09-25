package com.calebleavell.textinterface.Elements;

import java.util.List;
import java.util.ArrayList;

public class NumberedSceneChooser extends Scene {
    NumberedActionChooser form;
    List<Scene> sceneList;

    public NumberedSceneChooser(List<Scene> sceneList, String name, List<Scene> children, String ID) {
        super(name, children, ID);

        if(sceneList == null) this.sceneList = new ArrayList<Scene>();
            else this.sceneList = sceneList;
        
        List<Text> sceneNames = new ArrayList<>();
        List<Runnable> switchScene = new ArrayList<>();
        for(Scene scene : sceneList) {
            sceneNames.add(new Text(scene.getName()));
            switchScene.add(() -> {scene.display();});
        }
        form = new NumberedActionChooser(
            new NumberedList(sceneNames, null, null, null), 
            new TextInput(), 
            switchScene,
            null,
            null,
            null,
            null);
    }

    public void display() {
        form.display();
        super.display();
    }
}
