package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * <p> NumberedSceneSelectorScene Class </p>
 * 
 * <p>  Utilizes a NumberedListInputScene to create a scene-selection mechanism </p>
 * <p> Much easier to construct than building a scene selector manually </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class NumberedSceneSelectorScene extends GenericScene {

    /**
     * Collector of input from a list
     */
    private NumberedListInputScene selector;

    /**
     * The list of scenes to choose from
     */
    private List<Scene> sceneList;

    /**
     * The name of scenes to add at runtime
     */
    private List<String> runtimeScenes;

    private Scene parent;

    /**
     * Adds a new scene to choose from
     * @param newScene The new scene
     * @param text The display text of the option
     */
    public void addScene(Scene newScene, String text) {
        //add the scene to the list
        sceneList.add(newScene);

        //generate a new TextScene with the display text and add it to the input collector
        selector.getNumberedList().getList().add(new TextScene.Builder()
                .text(text)
                .build());
    }

    /**
     * handles runtime Scenes, displays the selector, gets the input, then display the chosen scene
     * 
     * NOTE: all child scenes and functions will be ran BEFORE the selected scene is displayed
     */
    @Override
    public void run() throws Exception {
        findScenes();
        populateSelector();
        selector.run();
        super.run();
        sceneList.get(selector.getInput() - selector.getNumberedList().getStartIndex()).run();
    }

    private void findScenes() {
        for(String i : runtimeScenes) {
            this.sceneList.add(this.parent.getChild(i));
        }
        runtimeScenes.clear();
    }
    /**
     * Populate an incomplete scene list with list names
     * Prune extra list items
     * 
     * Only include the same number of display text items in the selector 
     * as there are scenes in the scene list.
     */
    private void populateSelector() {
        //pouplate
        for(int i = selector.getNumberedList().getList().size() ; i < sceneList.size(); i ++) {
            selector.getNumberedList().addItem(sceneList.get(i).getName());
        }

        //prune
        if(sceneList.size() < selector.getNumberedList().getList().size()) {
            selector.getNumberedList().setList(
                new ArrayList<>(selector.getNumberedList().getList().subList(0, sceneList.size())));
        }
    }

    /**
     * Overrides GenericScene's toString to include it's scene options
     * @param indent the amount of spacing (increases deeper into the layers of children)
     * @param displayChildren Whether or not to include children in the toString
     */
    @Override
    public String toString(int indent, boolean displayChildren) {
        if(indent > MAX_ITERATIONS_ON_TOSTRING) {
            return "";
        }

        String output = super.toString(indent, displayChildren);

        for (Scene newScene : sceneList) {
            output = output + "\n" + newScene.toString(indent + 1, false) + " >> scene choice";
        }

        return output;
    }

    /**
     * Return a new instance based on the builder fields
     * @param builder The new NumberedSceneSelectorScene
     */
    public NumberedSceneSelectorScene(Builder builder) {
        super(builder);
        this.selector = builder.selector;
        this.sceneList = builder.sceneList;
        this.runtimeScenes = builder.runtimeScenes;
        this.parent = builder.parent;
    }

    /**
     * Builder Class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * Input defaults to class-default
         */
        private NumberedListInputScene selector = new NumberedListInputScene.Builder().build();
        
        Scene parent = null;
        List<String> runtimeScenes = new ArrayList<>();

        /**
         * Defaults to an ArrayList
         */
        private List<Scene> sceneList = new ArrayList<>();

        /**
         * @param sceneList The List of Scenes to choose from
         * @return self
         */
        public Builder sceneList(List<Scene> sceneList) {
            this.sceneList = sceneList;
            return self();
        }

        /**
         * @param sceneList The List of Scenes to choose from (varargs)
         * @return self
         */
        public Builder sceneList(Scene... sceneList) {
            this.sceneList = Arrays.asList(sceneList);
            return self();
        }

        /**
         * Generates the sceneList (at runtime) based on the names of the scenes. 
         * It finds the scenes based on searching for children in the parent param.
         * 
         * @param parent The scene containing the child scenes
         * @param sceneNames The names of the child scenes
         * @return self
         */
        public Builder sceneList(Scene parent, String... sceneNames) {
            this.parent = parent;
            this.runtimeScenes = new ArrayList<>(Arrays.asList(sceneNames));

            return self();
        }

        /**
         * Generates the sceneList based on the IDs of the scenes. 
         * It finds the scenes based on searching for children in the parent param.
         * 
         * @param parent The scene containing the child scenes
         * @param sceneIDs The IDs of the child scenes
         * @return self
         */
        public Builder sceneList(Scene parent, long... sceneIDs) {
            for(int i = 0; i < sceneIDs.length; i ++) {
                sceneList.add(parent.getChild(sceneIDs[i]));
            }

            return self();
        }

        /**
         * @param text The displayText of each option (varargs)
         * @return self
         */
        public Builder listText(String... text) {
            selector.setList(new NumberedListScene.Builder().list(text).build());
            return self();
        }

        /**
         * @return the new NumberedSceneSelectorScene instance
         */
        public NumberedSceneSelectorScene build() {
            return new NumberedSceneSelectorScene(self());
        }

    }
}
