package com.calebleavell.textinterface.Elements;

import java.util.List;
import java.util.ArrayList;

public class NumberedList extends Scene {
    List<Text> listElements;

    public NumberedList() {
        this(null, null, null, null);
    }
    public NumberedList(List<Text> listElements, String name, List<Scene> children, String ID) {
        super(name, children, ID);

        if(listElements == null) this.listElements = new ArrayList<>();
            else this.listElements = listElements;
    }

    public List<Text> getElements() {
        return listElements;
    }

    public void setElements(List<Text> listElements) {
        this.listElements = listElements;
    }
    
    @Override
    public void display() {
        for(int i = 0; i < listElements.size(); i ++) {
            System.out.print(i + ": ");
            listElements.get(i).display();
        }        
    }
}
