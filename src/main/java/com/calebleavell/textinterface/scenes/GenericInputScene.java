package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import com.calebleavell.textinterface.InputListener;

/**
 * 
 * <p> GenericInputScene Class </p>
 * 
 * <p> Provides basic abstract functionality for input scenes, specifically listener management </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public abstract class GenericInputScene<T> extends GenericScene implements Inputtable<T> {
    private List<InputListener<T>> listeners;

    /**
     * Updates the listeners, then calls super run
     */
    @Override
    public void run() throws Exception {
        updateListeners();
        super.run();
    }

    /**
     * gettter for listeners
     * @return The list of InputListeners
     */
    public List<InputListener<T>> getInputListeners() {
        return this.listeners;
    }
    
    /**
     * Constructor (builder design pattern)
     * @param b
     */
    protected GenericInputScene(Builder<T, ?> b) {
        super(b);
        this.listeners = b.listeners;
    }

    /**
     * Builder Class for GenericInputScene
     */
    public abstract static class Builder<T, B extends Builder<T, B>> extends GenericScene.Builder<Builder<T, B>> {

        /**
         * The list of inputListeners
         */
        private List<InputListener<T>> listeners = new ArrayList<>();

        /**
         * Set the list of inputListeners.
         * @param listeners The list of InputListeners
         * @return self
         */
        public B listeners(List<InputListener<T>> listeners) {
            this.listeners = listeners;
            return self();
        }

        /**
         * Add a single inputListener. 
         * This can be called multiple times on construction.
         * 
         * @param listener
         * @return
         */
        public B addListener(InputListener<T> listener) {
            this.listeners.add(listener);
            return self();
        }

        /**
         * Redefinition here since the type params are different. 
         * 
         * For method details, see GenericScene.Builder.
         */
        @Override
        @SuppressWarnings("unchecked")
        public B self() {
            return (B) this;
        }
    }
}
