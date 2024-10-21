package com.calebleavell.textinterface.scenes;

/**
 * Displays a piece of text
 */
public class TextScene extends GenericScene {
    /**
     * The text to display
     */
    private String text;

    /**
     * Optionally displays the toString of an Input
     */
    private Inputtable<?> input;

    /**
     * Whether or not it should end with a println
     */
    private boolean endWithNewLine;

    /**
     * Getter for text
     * @return Text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text
     * @param text The new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter for endWithNewLine
     * @param endWithNewLine Whether or not the program should end with a newline
     */
    public void setEndWithNewLine(boolean endWithNewLine) {
        this.endWithNewLine = endWithNewLine;
    }

    /**
     * Displays the input if that's set, 
     * or more commonly, displays its set text. 
     * 
     * Ends with a newline if that's set, then 
     * display children and run functions.
     */
    @Override
    public void run() throws Exception {
        if (input == null) {
            System.out.print(text);
        } else {
            System.out.print(input.getInput());
        }
        if (endWithNewLine) {
            System.out.println();
        }
        super.run();
    }

    /**
     * Return new TextScene based on builder fields
     * @param builder The builder
     */
    protected TextScene(Builder builder) {
        super(builder);
        this.text = builder.text;
        this.input = builder.input;
        this.endWithNewLine = builder.endWithNewLine;
    }

    /**
     * Builder class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * The default is "[Empty Text]"
         */
        private String text = "[Empty Text]";

        /**
         * Default is null, signalling to display the text field instead
         */
        private Inputtable<?> input = null;

        /**
         * By default, it does end with a new line
         */
        private boolean endWithNewLine = true;

        /**
         * @param text The text to display
         * @return self
         */
        public Builder text(String text) {
            this.text = text;
            return self();
        }

        /**
         * Allows easy translation from input to output
         * Displays the direct input from another scene
         * 
         * @param input Something that can return an input (with a good toString() method)
         * @return the modified Builder
         */
        public Builder text(Inputtable<?> input) {
            this.input = input;
            return self();
        }

        /**
         * @param endWithNewLine Whether or not to end with a new line
         * @return self
         */
        public Builder endWithNewLine(boolean endWithNewLine) {
            this.endWithNewLine = endWithNewLine;
            return self();
        }

        /**
         * @return The new TextScene
         */
        @Override
        public TextScene build() {
            return new TextScene(self());
        }
    }
}
