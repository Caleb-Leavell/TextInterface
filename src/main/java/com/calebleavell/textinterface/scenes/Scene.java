package com.calebleavell.textinterface.scenes;

import java.util.List;

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
public interface Scene extends Runnable{
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
    public List<Scene> getChildren();

    /**
     * Run child scenes in a way designed for the implementation
     * This could just be a linear display, a unique selection of children to display, or anything else
     */
    public void runChildren();    

    /**
     * getter for functions
     * These functions are Consumers that can take anything that extends Scene
     * In general, implementation will pass in "this" as the Scene
     * @return the list of functions it can execute
     */
    public List<Runnable> getFunctions();

    /**
     * Executes each function of the scene without displaying
     * To execute a specific function call getFunctions and get an element
     */
    public void executeFunctions();

    /**
     * Finds a child scene that matches name and returns it
     * Does a depth first search
     * Depending on development, there may be more than one child with the same name
     * @param name The name of the scene to be found
     * @return the first scene that matches name
     */
    public Scene getChildByName(String name);

    /**
     * Finds the child scene tha matches ID and returns it
     * Does a depth first search
     * Regardless of development, there will only be one child with the same ID (if any)
     * @param ID the ID of the child
     * @return the child with the given ID
     */
    public Scene getChildByID(long ID);

}
