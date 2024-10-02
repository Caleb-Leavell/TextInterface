package com.calebleavell.textinterface.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A Scene is something that can be displayed to the terminal.
 * It has:
 *      - a name 
 *      - a unique, immutable id
 *      - a list of functions it can execute
 *      - a list of child scenes
 * 
 * The generic type is the type of Scene to require for the functions
 */
public interface Scene<T extends Scene<T>> {
    /**
     * Displays its contents to the terminal
     * In general, display should also run the functions
     */
    public void display();

    /**
     * Getter for name
     * @return the current name of the scene
     */
    public String getName();

    /**
     * Setter for name
     * @param name - the new name fore the scene
     */
    public void setName(String name);

    /**
     * Getter for id
     * @return the scene id - the id is a unique, immutable identifier
     */
    public long getID();

    /**
     * Getter for children
     * @return the list of child scenes attatched to the scene
     */
    public List<Scene<?>> getChildren();

    /**
     * Display child scenes in a way designed for the implementation
     * This could just be a linear display, a unique selection of children to display, or anything else
     */
    public void displayChildren();    

    /**
     * getter for functions
     * These functions are Consumers that can take anything that extends Scene
     * In general, implementation will pass in "this" as the Scene
     * @return the list of functions it can execute
     */
    public List<Consumer<Scene<T>>> getFunctions();

    /**
     * Executes each function of the scene without displaying
     * To execute a specific function call getFunctions and get an element
     */
    public void executeFunctions();
}
