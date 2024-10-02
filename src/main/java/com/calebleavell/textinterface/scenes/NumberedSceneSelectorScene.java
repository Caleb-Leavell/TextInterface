package com.calebleavell.textinterface.scenes;

import java.util.List;

public class NumberedSceneSelectorScene extends GenericScene{
    private NumberedListInputScene selector;
    private List<Scene> sceneList;

    /**
     * display displays the selector, gets the input, then display the chosen scene
     * NOTE: any child scenes will be displayed BEFORE the selected scene
     */
    @Override
    public void display() {
        selector.display();
        super.display();
        sceneList.get(selector.getInput()).display();
    }

    public NumberedSceneSelectorScene(Builder builder) {
        super(builder);
        this.selector = builder.selector;
        this.sceneList = builder.sceneList;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private NumberedListInputScene selector;
        private List<Scene> sceneList;


        public Builder(List<Scene> sceneList) {
            this.sceneList = sceneList;

            String[] nameList = new String[sceneList.size()];
            for(int i = 0; i < sceneList.size(); i ++) {
                nameList[i] = sceneList.get(i).getName();
            }
            selector = new NumberedListInputScene.Builder().list(nameList).build();
        }

        public NumberedSceneSelectorScene build() {
            return new NumberedSceneSelectorScene(self());
        }
    }
}
