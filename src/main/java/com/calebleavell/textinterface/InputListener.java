package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.Inputtable;
import java.util.function.Function;
import java.util.List;

public class InputListener<T> implements Inputtable<T>{
    T input;
    Function<T, T> updater;
    List<InputListener<T>> listeners;

    public InputListener() {
        this(i -> {return i;});
    }

    public InputListener(Function<T, T> updater) {
        this.updater = updater;
    }

    public InputListener(Function<T, T> updater, List<InputListener<T>> listeners) {
        this.listeners = listeners;
    }

    public T getInput() {
        return input;
    }

    @Override
    public List<InputListener<T>> getInputListeners() {
        return listeners;
    }

    public void update(Inputtable<T> provider) {
        input = updater.apply(provider.getInput());
    }

    @Override
    public String toString() {
        return this.input.toString();
    }
}  
