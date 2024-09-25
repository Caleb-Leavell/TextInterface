package com.calebleavell.textinterface;

import java.util.List;

/*
 *  This is functionally a String, but it also can contain child Scenes for element traversal 
 */

public class Text extends Scene {
    private String text;

    public Text() {
        this(null, null, null, null);
    }
    public Text(String text, String name, List<Scene> children, final String ID) {
        super(name, children, ID);

        if(text == null) this.text = "[empty]";
            else this.text = text;
    }

    public void print() {
        System.out.print(text);
        super.print();
    }

    public void println() {
        System.out.println(text);
        super.print();
    }
}
