package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import com.calebleavell.textinterface.IDGenerator;

/**
 * A simple, abstract implementation of the Scene interface
 */
public abstract class GenericScene<T extends GenericScene<T>> implements Scene<T>{
    private final long id = IDGenerator.generateID();

    private String name;
    private List<Scene<?>> children;
    private List<Consumer<Scene<T>>> functions;

    /**
     * Displays its contents to the terminal
     */
    @Override
    public void display() {
        displayChildren();
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
    public List<Scene<?>> getChildren() {
        return children;
    }

    /**
     * Linearly displays Children
     */
    @Override
    public void displayChildren() {
        for(Scene<?> child : children) {
            child.display();
        }
    }

    /**
     * Getter for functions
     * @return the list of functions it can execute
     */
    @Override
    public List<Consumer<Scene<T>>> getFunctions() {
        return functions;
    }

    @Override
    /**
     * Linearlly executes the functions, passing in this
     */
    public void executeFunctions() {
        for(Consumer<Scene<T>> function : functions) {
            function.accept(this);
        }
    }

    protected GenericScene(Builder<?> builder) {
        this.name = builder.name;
        this.children = builder.children;
    }

    abstract static class Builder<B extends Builder<B>> {
        private String name = String.format("[Unnamed]");
        private List<Scene> children = new ArrayList<>(); 

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

        abstract Scene build();
    }
}
