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
     * display displays the selector, gets the input, then display the chosen scene
     * 
     * NOTE: all child scenes and functions will be ran BEFORE the selected scene is displayed
     */
    @Override
    public void run() throws Exception {
        selector.run();
        super.run();
        sceneList.get(selector.getInput() - selector.getNumberedList().getStartIndex()).run();
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
    }

    /**
     * Builder Class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * Input defaults to class-default
         */
        private NumberedListInputScene selector = new NumberedListInputScene.Builder().build();

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
            generateSelector();
            return self();
        }

        /**
         * @param sceneList The List of Scenes to choose from (varargs)
         * @return self
         */
        public Builder sceneList(Scene... sceneList) {
            this.sceneList = Arrays.asList(sceneList);
            generateSelector();
            return self();
        }

        /**
         * @param text The displayText of each option (varargs)
         * @return self
         */
        public Builder listText(String... text) {
            List<TextScene> textList = selector.getNumberedList().getList();
            //update the textList to match display text
            for (int i = 0; i < text.length && i < textList.size(); i++) {
                textList.get(i).setText(text[i]);
            }
            return self();
        }

        /**
         * @return the new NumberedSceneSelectorScene instance
         */
        public NumberedSceneSelectorScene build() {
            return new NumberedSceneSelectorScene(self());
        }

        /**
         * Convert the inputted list of scenes into the NumberedListSelector
         * 
         * By default, the displayText is the name of each scene
         */
        private void generateSelector() {
            //collect names
            String[] nameList = new String[sceneList.size()];
            for (int i = 0; i < sceneList.size(); i++) {
                nameList[i] = sceneList.get(i).getName();
            }

            //generate selector
            selector = new NumberedListInputScene.Builder().list(nameList).build();
        }
    }
}
