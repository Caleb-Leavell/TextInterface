package com.calebleavell.textinterface;

import java.util.List;
import java.util.Scanner;

public class TextInput extends Scene implements Inputable<String>{
    private Text text;
    private Text inputText;
    private String input;

    private static Scanner scnr = new Scanner(System.in);

    public static final String DEFAULT_TEXT = "[empty]";
    public static final String DEFAULT_INPUT_TEXT = "Input: ";

    public TextInput(String text, Text inputText, String name, List<Scene> children, final String ID) {
        super(name, children, ID);
        
        if(text == null) this.text = new Text(DEFAULT_TEXT, null, null, null);
            else this.text = new Text(text, null, null, null);

        if(inputText == null) this.inputText = new Text(DEFAULT_INPUT_TEXT, null, null, null);
            else this.inputText = new Text(text, null, null, null);
    }    
    public TextInput(Text text, Text inputText, String name, List<Scene> children, final String ID) {
        super(name, children, ID);
        
        if(text == null) this.text = new Text(DEFAULT_TEXT, null, null, null);
            else this.text = text;

        if(inputText == null) this.inputText = new Text(DEFAULT_INPUT_TEXT, null, null, null);
            else this.text = text;
    }    

    public String getInput() {
        return input;
    }

    public void print() {
        text.println();
        inputText.print();
        input = scnr.nextLine();
    }

    public void println() {
        this.print();
        System.out.println();
    }
}
