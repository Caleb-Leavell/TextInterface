package com.calebleavell.textinterface.deprecated.elements;

import java.util.List;
import java.util.Scanner;

import com.calebleavell.textinterface.deprecated.Inputable;

public class TextInput extends Scene implements Inputable<String>{
    private Text inputText;
    private String input;

    private static Scanner scnr = new Scanner(System.in);

    public static final String DEFAULT_INPUT_TEXT = "Input: ";

    public TextInput() {
        this(new Text(DEFAULT_INPUT_TEXT), null, null, null);
    }
    public TextInput(Text inputText, String name, List<Scene> children, final String ID) {
        super(name, children, ID);
        
        if(inputText == null) this.inputText = new Text(DEFAULT_INPUT_TEXT, null, null, null);
            else this.inputText = inputText;
    }    
    public TextInput(String inputText, String name, List<Scene> children, final String ID) {
        super(name, children, ID);

        if(inputText == null) this.inputText = new Text(DEFAULT_INPUT_TEXT, null, null, null);
            else this.inputText = new Text(inputText, null, null, null);
    }    

    public Text getInputText() {
        return inputText;
    }
    public void setInputText(Text inputText) {
        this.inputText = inputText;
    }
    public void setInputText(String inputText) {
        this.inputText.setText(inputText);
    }

    public String getInput() {
        return input;
    }

    @Override
    public void display() {
        inputText.display();
        input = scnr.nextLine();

        super.display();
    }
}
