package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.calebleavell.textinterface.IDGenerator;

/**
 * <p>
 * A simple, abstract implementation of the Scene interface
 * </p>
 * 
 * <p>
 * This class is easily extendable, but subclasses will be more difficult
 * (due to the way the Builder pattern is implented)
 * </p>
 */
public abstract class GenericScene implements Scene {
    private final long id = IDGenerator.generateID();

    private String name;
    private List<Scene> children;
    private List<Function> functions;
    private boolean terminated = false;

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
     * <p>
     * Executes a depth-first seach on the children
     * </p>
     * <p>
     * Returns the first child with name matching param name
     * </p>
     * 
     * @param name the name to search for
     * @return the first child that matches name
     */
    @Override
    public Scene getChildByName(String name) {
        for (Scene child : children) {
            if (child.getName().equals(name)) {
                return child;
            } else {
                Scene childSearch = child.getChildByName(name);
                if (childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    /**
     * <p>
     * Executes a depth-first seach on the children
     * </p>
     * <p>
     * Returns the first child with name matching param ID
     * </p>
     * <p>
     * Unlike searching by name, there will only ever be up to one matching child
     * </p>
     * 
     * @param ID the ID to search for
     * @return the first child that matches ID
     */
    @Override
    public Scene getChildByID(long ID) {
        for (Scene child : children) {
            if (child.getID() == ID) {
                return child;
            } else {
                Scene childSearch = child.getChildByID(ID);
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
     * <p>
     * Return whether or not the scene is in a termninated state
     * </p>
     * <p>
     * note that the scene comes out of a termniated state when it is run again
     * </p>
     * 
     * @return whether or not the scene is in a terminate state
     */
    @Override
    public boolean isTerminated() {
        return this.terminated;
    }

    /**
     * Recursively generates toString with info for this scene and all children
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
        private String name = String.format("[Unnamed]");
        private List<Scene> children = new ArrayList<>();
        private List<Function> functions = new ArrayList<>();

        @SuppressWarnings("unchecked")
        public B self() {
            return (B) this;
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B children(List<Scene> children) {
            this.children = children;
            return self();
        }

        public B children(Scene... children) {
            this.children = Arrays.asList(children);
            return self();
        }

        // varargs not allowed to avoid heap pollution
        public B functions(Function... functions) {
            this.functions = Arrays.asList(functions);
            return self();
        }

        public abstract Scene build();
    }
}
