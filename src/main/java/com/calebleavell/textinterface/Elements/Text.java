package com.calebleavell.textinterface.Elements;

import java.util.List;

/*
 *  This is functionally a String, but it also can contain child Scenes for element traversal 
 */

public class Text extends Scene {
    private String text;

    public Text() {
        this(null, null, null, null);
    }
    public Text(String text) {
        this(text, null, null, null);
    }
    public Text(String text, String name, List<Scene> children, final String ID) {
        super(name, children, ID);

        if(text == null) this.text = "[empty]";
            else this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println(text);
        super.display();
    }
}
