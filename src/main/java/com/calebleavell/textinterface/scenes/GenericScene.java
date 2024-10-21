package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.calebleavell.textinterface.IDGenerator;

/**
 * 
 * <p> GenericScene Class </p>
 * 
 * <p> A simple, abstract implementation of the Scene interface </p>
 * 
 * <p> This class is easily extendable, but subclasses will be more difficult
 * (due to the way the Builder pattern is implemented) </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public abstract class GenericScene implements Scene {

    /**
     * The unique ID of the scene
     */
    private final long id = IDGenerator.generateID();

    /**
     * The name of the scene
     */
    private String name;

    /**
     * Every child scene contained within the scene
     * 
     */
    private List<Scene> children;

    /**
     * The list of functions to execute with the scene
     */
    private List<Function> functions;

    /**
     * If false, it will run as normal
     * 
     * If true, it will stop executing its children. 
     * 
     * This is set to false in run()
     */
    private boolean terminated = false;

    /**
     * The number of layers of children to add to the toString()
     */
    public static int MAX_ITERATIONS_ON_TOSTRING = 6;

    /**
     * Executes its functions, then displays its children
     */
    @Override
    public void run() throws Exception {
        this.terminated = false;
        executeFunctions();
        runChildren();
    }

    /**
     * Getter for name
     * 
     * @return the current name of the scene
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * 
     * @param name - the new name fore the scene
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for id
     * 
     * @return the scene id - the id is a unique, immutable identifier
     */
    @Override
    public long getID() {
        return id;
    }

    /**
     * Getter for children
     * 
     * @return the list of child scenes attatched to the scene
     */
    @Override
    public List<Scene> getChildren() {
        return children;
    }

    /**
     * Setter for children
     * 
     * @param children the new list of child scenes
     */
    @Override
    public void setChildren(List<Scene> children) {
        this.children = children;
    }

    /**
     * Add a child to the list of children
     */
    @Override
    public void addChild(Scene child) {
        this.children.add(child);
    }

    /**
     * Linearly displays Children
     */
    @Override
    public void runChildren() throws Exception {
        for (Scene child : children) {
            if (this.terminated) {
                return;
            }
            child.run();
        }
    }

    /**
     * Getter for functions
     * 
     * @return the list of functions it can execute
     */
    @Override
    public List<Function> getFunctions() {
        return functions;
    }

    /**
     * Linearlly executes each function
     */
    @Override
    public void executeFunctions() throws Exception {
        for (Function function : functions) {
            if (this.terminated) {
                return;
            }
            function.run();

        }
    }

    /**
     * Executes a depth-first seach on the children
     * 
     * Returns the first child with name matching param name
     * 
     * @param name the name to search for
     * @return the first child that matches name
     */
    @Override
    public Scene getChild(String name) {
        for (Scene child : children) {
            if (child.getName().equals(name)) {
                return child;
            } else {
                Scene childSearch = child.getChild(name);
                if (childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    /**
     * Executes a depth-first seach on the children
     * 
     * Returns the first child with name matching param ID
     * 
     * Unlike searching by name, there will only ever be up to one matching child
     * 
     * @param ID the ID to search for
     * @return the first child that matches ID
     */
    @Override
    public Scene getChild(long ID) {
        for (Scene child : children) {
            if (child.getID() == ID) {
                return child;
            } else {
                Scene childSearch = child.getChild(ID);
                if (childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    /**
     * Attempts to find a child scene that matches the name and type of Scene
     * Executes a depth-first search
     * 
     * @param <T> the desired type to find
     * @param name the name of the class to find
     * @param intendedClass the indended class of the child to find
     * @return the found child, or null if no child is found
     */
    @Override
    @SuppressWarnings("unchecked") //it's safe to suppress since it's checking that the class is the same before returning
    public <T extends Scene> T getChild(String name, Class<T> intendedClass) {
        for (Scene child : children) {
            if (child.getName().equals(name) && child.getClass().equals(intendedClass)) {
                return (T) child;
            } else {
                T childSearch = (T) child.getChild(name);
                if (childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    /**
     * Attempts to find a child scene that matches the ID and type of Scene
     * Executes a depth-first search
     * 
     * @param <T> the desired type to find
     * @param ID the id of the class to find
     * @param intendedClass the indended class of the child to find
     * @return the found child, or null if no child is found
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Scene> T getChild(long ID, Class<T> intendedClass) {
        for (Scene child : children) {
            if (child.getID() == ID && child.getClass().equals(intendedClass)) {
                return (T) child;
            } else {
                T childSearch = (T) child.getChild(name);
                if (childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }


    /**
     * Terminates scene execution
     */
    @Override
    public void terminate() {
        this.terminated = true;
    }

    /**
     * Return whether or not the scene is in a termninated state
     * 
     * note that the scene comes out of a termniated state when it is run again
     * 
     * @return whether or not the scene is in a terminate state
     */
    @Override
    public boolean isTerminated() {
        return this.terminated;
    }

    /**
     * Recursively generates toString with info for this scene and all children
     * Will only go as deep as MAX_ITERATIONS_ON_TOSTRING
     * 
     * @return formatted string
     */
    @Override
    public String toString() {
        return toString(0, true);
    }

    /**
     * recursive helper method for toString()
     */
    @Override
    public String toString(int indent, boolean displayChildren) {
        if(indent > MAX_ITERATIONS_ON_TOSTRING) {
            return "";
        }
        
        String output = "";
        for (int i = 0; i < indent; i++) {
            output += "\t";
        }

        output += this.name + " --- " + this.id;

        if (displayChildren) {
            for (Scene child : children) {
                output = output + "\n" + child.toString(indent + 1, true);
            }
        }

        return output;
    }

    /**
     * Takes a builder and extracts necessary fields out of it
     * 
     * @param builder a GenericScene.Builder (or something that extends it)
     */
    protected GenericScene(Builder<?> builder) {
        this.name = builder.name;
        this.children = builder.children;
        this.functions = builder.functions;
    }

    /**
     * Uses a Builder design pattern
     * Extending this class makes extending this Builder easy,
     * but extending any subclasses of this class will be difficult
     */
    public abstract static class Builder<B extends Builder<B>> {
        /**
         * Default name is "unnamed"
         */
        private String name = String.format("[Unnamed]");

        /**
         * Default is an empty ArrayList
         */
        private List<Scene> children = new ArrayList<>();
        private List<Function> functions = new ArrayList<>();

        /**
         * Return a version of this of type B
         * 
         * It is safe to suppress the warning on the user side,
         * assuming subclasses are implemented correctly
         * 
         * @return this, casted to B
         */
        @SuppressWarnings("unchecked")
        public B self() {
            return (B) this;
        }

        /**
         * Set the name field
         * @param name The name of the Scene
         * @return this, casted to B
         */
        public B name(String name) {
            this.name = name;
            return self();
        }

        /**
         * Set the children field
         * @param children The list of children
         * @return this, casted to B
         */
        public B children(List<Scene> children) {
            this.children = children;
            return self();
        }

        /**
         * Set the children field dynamically
         * @param children The list of children
         * @return this, casted to B
         */
        public B children(Scene... children) {
            this.children = Arrays.asList(children);
            return self();
        }

        /**
         * Set the list of functions dynamically
         * @param functions The list of functions
         * @return this, casted to B
         */
        public B functions(Function... functions) {
            this.functions = Arrays.asList(functions);
            return self();
        }

        /**
         * Initializes a new object of the class that the implementation
         * is contained in 
         * 
         * @return A new Scene (or subclass of Scene) with the fields of the Builder
         */
        public abstract Scene build();
    }
}
