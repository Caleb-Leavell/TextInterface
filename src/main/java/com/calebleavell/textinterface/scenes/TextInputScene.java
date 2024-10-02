package com.calebleavell.textinterface.scenes;

import java.util.Scanner;

public class TextInputScene extends GenericScene implements Inputtable<String> {
    private TextScene displayText;
    private String input;
    
    private Scanner scnr =  new Scanner(System.in);

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void display() {
        displayText.display();
        input = scnr.nextLine();
        super.display();
    }

    protected TextInputScene(Builder builder) {
        super(builder);
        this.displayText = builder.displayText;
    }

    public static class Builder extends GenericScene.Builder<Builder>{
        private TextScene displayText = 
            new TextScene.Builder()
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
