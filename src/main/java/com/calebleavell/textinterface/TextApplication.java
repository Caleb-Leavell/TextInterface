package com.calebleavell.textinterface;

public class TextApplication {
    Scene root;
    
    public TextApplication(Scene root) {
        if(root == null) root = new Text("Hello, World!", null, null, null);
            else this.root = root;
    }

    public Scene getRoot() {
        return root;
    }

    public void setRoot(Scene root) {
        this.root = root;
    }

    public void run() {
        root.println();
    }
}
