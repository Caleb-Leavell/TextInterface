package com.calebleavell.textinterface.scenes;

import java.util.List;

/**
 * 
 * <p> Scene Interface </p>
 * 
 * <p>   A Scene is something that can be displayed to the terminal. </p>
 * <p> It has: </p>
 * 
 * <ul>
 *  <li> a name </li>
 *  <li> a unique, immutable id </li>
 *  <li> a list of functions it can execute </li>
 *  <li> a list of child scenes </li>
 * </ul>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public interface Scene extends Function {
    /**
     * Getter for name
     * 
     * @return the current name of the scene
     */
    public String getName();

    /**
     * Setter for name
     * 
     * @param name - the new name fore the scene
     */
    public void setName(String name);

    /**
     * Getter for id
     * 
     * @return the scene id - the id is a unique, immutable identifier
     */
    public long getID();

    /**
     * Getter for children
     * 
     * @return the list of child scenes attatched to the scene
     */
    public List<Scene> getChildren();

    /**
     * Setter for children
     * 
     * @param children the new list of child scenes
     */
    public void setChildren(List<Scene> children);

    /**
     * Add a new child to the list of children
     * 
     * @param child the new child to be added
     */
    public void addChild(Scene child);

    /**
     * Run child scenes in a way designed for the implementation
     * This could just be a linear display, a unique selection of children to
     * display, or anything else
     * 
     * @throws Exception
     */
    public void runChildren() throws Exception;

    /**
     * getter for functions
     * These functions are Consumers that can take anything that extends Scene
     * In general, implementation will pass in "this" as the Scene
     * 
     * @return the list of functions it can execute
     */
    public List<Function> getFunctions();

    /**
     * Executes each function of the scene without displaying
     * To execute a specific function call getFunctions and get an element
     * 
     * @throws Exception
     */
    public void executeFunctions() throws Exception;

    /**
     * Finds a child scene that matches name and returns it
     * The method of search is up to implementation
     * Depending on development, there may be more than one child with the same name
     * 
     * @param name The name of the scene to be found
     * @return the first scene that matches name
     */
    public Scene getChild(String name);

    /**
     * Finds the child scene tha matches ID and returns it
     * The method of search is up to implementation
     * Regardless of development, there will only be one child with the same ID (if
     * any)
     * 
     * @param ID the ID of the child
     * @return the child with the given ID
     */
    public Scene getChild(long ID);

    /**
     * Attempts to find a child scene that matches the name and type of Scene
     * 
     * @param <T> the desired type to find
     * @param name the name of the class to find
     * @param intendedClass the indended class of the child to find
     * @return the found child, or null if no child is found
     */
    public <T extends Scene> T getChild(String name, Class<T> intendedClass);

    /**
     * Attempts to find a child scene that matches the ID and type of Scene
     * 
     * @param <T> the desired type to find
     * @param ID the id of the class to find
     * @param intendedClass the indended class of the child to find
     * @return the found child, or null if no child is found
     */
    public <T extends Scene> T getChild(long ID, Class<T> intendedClass);

    /**
     * Allows for recursive tabbing of children for toString
     * In general, the overrided toString() method should return toString(0)
     * 
     * @param indent the amount of tab (base case call is 0)
     * @return the formatted string of the object and its children
     */
    public String toString(int indent, boolean displayChildren);

    /**
     * Quits a scene early
     */
    public void terminate();

    /**
     * returns whether or not a scene is currently terminated
     */
    public boolean isTerminated();

    

}
