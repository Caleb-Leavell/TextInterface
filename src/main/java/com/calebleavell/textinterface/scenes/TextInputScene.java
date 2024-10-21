package com.calebleavell.textinterface.scenes;

import java.util.Scanner;

/**
 * 
 * <p> TextScene Class </p>
 * 
 * <p>  Collects User Input in the Form of Text from the User </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class TextInputScene extends GenericScene implements Inputtable<String> {

    /**
     * Display Prompt (eg. "Input: ")
     */
    private TextScene displayText;

    /**
     * The actual user input
     */
    private String input;

    /**
     * The scanner
     */
    private Scanner scnr = new Scanner(System.in);

    /**
     * @return The user input
     */
    @Override
    public String getInput() {
        return input;
    }

    /**
     * Executes the scene by displaying the prompt, 
     * collecting the input, then running children and functions
     */
    @Override
    public void run() throws Exception {
        displayText.run();
        input = scnr.nextLine();
        super.run();
    }

    /**
     * Returns 
     * @param builder
     */
    protected TextInputScene(Builder builder) {
        super(builder);
        this.displayText = builder.displayText;
    }

    /*
     * Builder Class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * Default displayText is "Your Input: "
         * By default, it doesn't end with a new line
         * 
         * So, the default is "Your Input: <Input>"
         */
        private TextScene displayText = new TextScene.Builder()
                .text("Your Input: ")
                .endWithNewLine(false).build();

        /**
         * @param displayText The prompt text
         * @return self
         */
        public Builder displayText(TextScene displayText) {
            this.displayText = displayText;
            return self();
        }

        /**
         * @param displayText The prompt text
         * @return self
         */
        public Builder displayText(String displayText) {
            this.displayText.setText(displayText);
            return self();
        }

        /**
         * @return The new TextInputScene
         */
        public TextInputScene build() {
            return new TextInputScene(self());
        }
    }
}
