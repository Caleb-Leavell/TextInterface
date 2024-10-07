package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;

/**
 * Displays a Numbered list of TextScenes
 */
public class NumberedListScene extends GenericScene{
    private List<TextScene> list;
    private int startIndex;

    public List<TextScene> getList() {
        return list;
    }
    public void setList(List<TextScene> list) {
        this.list = list;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    static List<TextScene> convertStringListToTextSceneList(String...strings) {
        List<TextScene> list = new ArrayList<TextScene>();
        for(String s : strings) {
            list.add(new TextScene.Builder().text(s).build());
        }
        return list;
    }


    @Override
    public void run() throws Exception {
        //TODO: input validation
        
        for(int i = 0; i < list.size(); i ++) {
            System.out.print((i + startIndex) + ": ");
            list.get(i).run();
        }

        super.run();
    }

    protected NumberedListScene(Builder builder) {
        super(builder);
        this.list = builder.list;
        this.startIndex = builder.startIndex;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private List<TextScene> list = new ArrayList<>();
        private int startIndex = 1;

        public Builder list(List<TextScene> list) {
            this.list = list;
            return self();
        }

        //this generates an arraylist of TextScenes based on the inputted parameters
        public Builder list(String...list) {
            this.list = NumberedListScene.convertStringListToTextSceneList(list);
            return self();
        }
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return self();
        }

        public NumberedListScene build() {
            return new NumberedListScene(self());
        }

    }
}
