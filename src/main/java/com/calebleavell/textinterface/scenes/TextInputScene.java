package com.calebleavell.textinterface.scenes;

import java.util.Scanner;

/**
 * Gets text input from the user
 */
public class TextInputScene extends GenericScene implements Inputtable<String> {
    private TextScene displayText;
    private String input;

    private Scanner scnr = new Scanner(System.in);

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void run() throws Exception {
        displayText.run();
        input = scnr.nextLine();
        super.run();
    }

    protected TextInputScene(Builder builder) {
        super(builder);
        this.displayText = builder.displayText;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private TextScene displayText = new TextScene.Builder()
                .text("Your Input: ")
                .endWithNewLine(false).build();

        public Builder displayText(TextScene displayText) {
            this.displayText = displayText;
            return self();
        }

        public Builder displayText(String displayText) {
            this.displayText.setText(displayText);
            return self();
        }

        public TextInputScene build() {
            return new TextInputScene(self());
        }
    }
}
