package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utilizes a NumberedListInputScene to create a scene-selection mechanism
 * Much easier to construct than building a scene selector manually
 */
public class NumberedSceneSelectorScene extends GenericScene {
    private NumberedListInputScene selector;
    private List<Scene> sceneList;

    public void addScene(Scene newScene, String text) {
        sceneList.add(newScene);
        selector.getNumberedList().getList().add(new TextScene.Builder()
                .text(text)
                .build());
    }

    /**
     * display displays the selector, gets the input, then display the chosen scene
     * NOTE: any child scenes will be displayed BEFORE the selected scene
     */
    @Override
    public void run() throws Exception {
        selector.run();
        super.run();
        sceneList.get(selector.getInput() - selector.getNumberedList().getStartIndex()).run();
    }

    @Override
    public String toString(int index, boolean displayChildren) {
        String output = super.toString(index, displayChildren);

        for (Scene newScene : sceneList) {
            output = output + "\n" + newScene.toString(index + 1, false) + " >> scene choice";
        }

        return output;
    }

    public NumberedSceneSelectorScene(Builder builder) {
        super(builder);
        this.selector = builder.selector;
        this.sceneList = builder.sceneList;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private NumberedListInputScene selector = new NumberedListInputScene.Builder().build();
        private List<Scene> sceneList = new ArrayList<>();

        public Builder sceneList(List<Scene> sceneList) {
            this.sceneList = sceneList;
            generateSelector();
            return self();
        }

        public Builder sceneList(Scene... sceneList) {
            this.sceneList = Arrays.asList(sceneList);
            generateSelector();
            return self();
        }

        public Builder listText(String... text) {
            List<TextScene> textList = selector.getNumberedList().getList();
            for (int i = 0; i < text.length && i < textList.size(); i++) {
                textList.get(i).setText(text[i]);
            }
            return self();
        }

        public NumberedSceneSelectorScene build() {
            return new NumberedSceneSelectorScene(self());
        }

        private void generateSelector() {
            String[] nameList = new String[sceneList.size()];
            for (int i = 0; i < sceneList.size(); i++) {
                nameList[i] = sceneList.get(i).getName();
            }
            selector = new NumberedListInputScene.Builder().list(nameList).build();
        }
    }
}
