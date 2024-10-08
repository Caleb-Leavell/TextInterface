package com.calebleavell.textinterface.scenes;

/**
 * Displays a piece of text
 */
public class TextScene extends GenericScene {
    private String text;
    private Inputtable<?> input;
    private boolean endWithNewLine;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEndWithNewLine(boolean endWithNewLine) {
        this.endWithNewLine = endWithNewLine;
    }

    @Override
    public void run() throws Exception {
        executeFunctions();
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

    protected TextScene(Builder builder) {
        super(builder);
        this.text = builder.text;
        this.input = builder.input;
        this.endWithNewLine = builder.endWithNewLine;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private String text = "[Empty Text]";
        private Inputtable<?> input = null;
        private boolean endWithNewLine = true;

        public Builder text(String text) {
            this.text = text;
            return self();
        }

        /**
         * Allows easy translation from input to output
         * Displays the direct input from another scene
         * 
         * @param input Something that can return an input (with a good toString()
         *              method)
         * @return the modified Builder
         */
        public Builder text(Inputtable<?> input) {
            this.input = input;
            return self();
        }

        public Builder endWithNewLine(boolean endWithNewLine) {
            this.endWithNewLine = endWithNewLine;
            return self();
        }

        @Override
        public TextScene build() {
            return new TextScene(self());
        }
    }
}
