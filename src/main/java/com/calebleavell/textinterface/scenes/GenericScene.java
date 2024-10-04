package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.calebleavell.textinterface.IDGenerator;

/**
 * A simple, abstract implementation of the Scene interface
 */
public abstract class GenericScene implements Scene{
    private final long id = IDGenerator.generateID();

    private String name;
    private List<Scene> children;
    private List<Runnable> functions;

    /**
     * Executes its functions, then displays its children
     */
    @Override
    public void run() {
        executeFunctions();
        runChildren();
    }

    /**
     * Getter for name
     * @return the current name of the scene
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name - the new name fore the scene
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for id
     * @return the scene id - the id is a unique, immutable identifier
     */
    @Override
    public long getID() {
        return id;
    }

    /**
     * Getter for children
     * @return the list of child scenes attatched to the scene
     */
    @Override
    public List<Scene> getChildren() {
        return children;
    }

    /**
     * Linearly displays Children
     */
    @Override
    public void runChildren() {
        for(Scene child : children) {
            child.run();
        }
    }

    /**
     * Getter for functions
     * @return the list of functions it can execute
     */
    @Override
    public List<Runnable> getFunctions() {
        return functions;
    }

    /**
     * Linearlly executes the functions, passing in this
     */
    @Override
    public void executeFunctions() {
        for(Runnable function : functions) {
            function.run();
            
        }
    }

    @Override
    public Scene getChildByName(String name) {
        for(Scene child : children) {
            if(child.getName().equals(name)) {
                return child;
            } else {
                Scene childSearch = child.getChildByName(name);
                if(childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    @Override
    public Scene getChildByID(long ID) {
        for(Scene child : children) {
            if(child.getID() == ID) {
                return child;
            } else {
                Scene childSearch = child.getChildByID(ID);
                if(childSearch != null) {
                    return childSearch;
                }
            }
        }

        return null;
    }

    protected GenericScene(Builder<?> builder) {
        this.name = builder.name;
        this.children = builder.children;
        this.functions = builder.functions;
    }

    public abstract static class Builder<B extends Builder<B>> {
        private String name = String.format("[Unnamed]");
        private List<Scene> children = new ArrayList<>(); 
        private List<Runnable> functions = new ArrayList<>();

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
        public B children(Scene...children) {
            this.children = Arrays.asList(children);
            return self();
        }

        //varargs not allowed to avoid heap pollution
        public B functions(Runnable... functions) {
            this.functions = Arrays.asList(functions);
            return self();
        }

        public abstract Scene build();
    }
}
