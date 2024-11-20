package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * <p> NumberedListScene </p>
 * 
 * <p> Displays a Numbered list of items to the user </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class NumberedListScene extends GenericScene {

    /**
     * The list of items
     */
    private List<TextScene> list;

    /**
     * The starting number
     * 
     * Ex. if startIndex was 4, the list would be:
     * 
     * 4. Element 1
     * 5. Element 2
     * etc.
     */
    private int startIndex;

    /**
     * Getter for list
     * @return The list of iteme
     */
    public List<TextScene> getList() {
        return list;
    }

    /**
     * Setter for list
     * @param list the new list of items
     */
    public void setList(List<TextScene> list) {
        this.list = list;
    }

    /**
     * Getter for startIndex
     * @return The start index
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Setter for startIndex
     * @param startIndex The new start index
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void addItem(String item) {
        list.add(new TextScene.Builder().text(item).build());
    }

    /**
     * Converts an array of strings to a List of TextScenes
     * @param strings The array of strings (varargs)
     * @return The list of TextScenes
     */
    protected static List<TextScene> convertStringListToTextSceneList(String... strings) {
        List<TextScene> list = new ArrayList<TextScene>();
        for (String s : strings) {
            list.add(new TextScene.Builder().text(s).build());
        }
        return list;
    }

    /**
     * Executes the program as follows:
     * 1. Display list of items (assuming start index is n) :
     * n: item 1
     * n + 1: item 2
     * n + 2: item 3
     * etc.
     * 2. Runs super - runs children and functions
     */
    @Override
    public void run() throws Exception {
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + startIndex) + ": ");
            list.get(i).run();
        }

        super.run();
    }
    /**
     * Return a new instance based on the builder fields
     * @param builder The new NumberedListScene
     */
    protected NumberedListScene(Builder builder) {
        super(builder);
        this.list = builder.list;
        this.startIndex = builder.startIndex;
    }

    /**
     * Builder class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * Default is an ArrayList
         */
        private List<TextScene> list = new ArrayList<>();

        /**
         * Default to starting the list at 1
         */
        private int startIndex = 1;

        /**
         * @param list The list of TextScenes
         * @return self
         */
        public Builder list(List<TextScene> list) {
            this.list = list;
            return self();
        }

        /**
         * Converts an array of Strings into the Scene's List of TextScenes
         * @param list The array of strings (varargs)
         * @return self
         */
        public Builder list(String... list) {
            this.list = NumberedListScene.convertStringListToTextSceneList(list);
            return self();
        }

        /**
         * @param startIndex The starting index of the list
         * @return self
         */
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return self();
        }

        /**
         * Return the new NumberedListScene instance
         */
        public NumberedListScene build() {
            return new NumberedListScene(self());
        }

    }
}
