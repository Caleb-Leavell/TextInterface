package com.calebleavell.textinterface.scenes;

import java.util.List;
import java.util.ArrayList;
import com.calebleavell.textinterface.InputListener;

public abstract class GenericInputScene<T> extends GenericScene implements Inputtable<T> {
    private List<InputListener<T>> listeners;

    public void run() throws Exception {
        updateListeners();
        super.run();
    }

    public List<InputListener<T>> getInputListeners() {
        return this.listeners;
    }


    protected void updateListeners() {
        for(InputListener<T> listener : listeners) {
            listener.update(this);
        }
    }
    
    public GenericInputScene(Builder<T, ?> b) {
        super(b);
        this.listeners = b.listeners;
    }

    public abstract static class Builder<T, B extends Builder<T, B>> extends GenericScene.Builder<Builder<T, B>> {
        private List<InputListener<T>> listeners = new ArrayList<>();

        public B listeners(List<InputListener<T>> listeners) {
            this.listeners = listeners;
            return self();
        }

        /**
         * Redefinition here since the type params are different
         * 
         * For method details, see GenericScene.Builder
         */
        @Override
        @SuppressWarnings("unchecked")
        public B self() {
            return (B) this;
        }
    }
}
