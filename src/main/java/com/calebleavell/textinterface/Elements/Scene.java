
package com.calebleavell.textinterface.Elements;

import java.util.List;

import com.calebleavell.textinterface.Displayable;

import java.util.ArrayList;

/*
 *  This class serves as both the foundation parent class for all other  Elements,
 *  as well as an empty container that can be used to house child elements
 */

public class Scene implements Displayable{

    // unique, immutable identifier
    // if a specific ID is not provided one is generated by hashing the object
    private final String ID;

    // mutable identifier
    private String name;

    // any children that are attatched to this scene
    private List<Scene> children;

    //constructors
    public Scene() {
        this(null, null, null);
    }
    public Scene(String name, List<Scene> children, final String ID) {
        if(ID == null) this.ID = generateID();
            else this.ID = ID;

        if(children == null) this.children = new ArrayList<>();
            else this.children = children;

        if (name == null) this.name = "[UNNAMED_TEXT]"; 
            else this.name = name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return this.ID;
    }
    public String getName() {
        return this.name;
    }
    public List<Scene> getChildren() {
        return children;
    }

    @Override
    public void display() {
        displayChildren();
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Scene) return (((Scene) other).getID() == ID);
            else return false;
    }

    public void displayChildren() {
        for(Displayable child : children) {
            child.display();
        }
    }

    // Converts the object hash code to a String
    private String generateID() {
        return String.valueOf(this.hashCode());
    }
}
