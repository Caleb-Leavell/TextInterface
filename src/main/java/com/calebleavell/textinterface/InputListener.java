package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.Inputtable;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p> InputListener Class </p>
 * 
 * <p> Attatches itself to an inputtable, and gets updated to the inputtable's specifications, 
 *     according to the functionality passed into this InputListener  </p>
 * 
 * <p> You can also attatch InputListeners to another InputListener </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class InputListener<T> implements Inputtable<T>{
    /**
     * The received input
     */
    T input;

    /**
     * Specifies what happens, both to the input and globally, upon updating
     */
    Function<T, T> updater; 

    /**
     * Any other listeners chained to this one
     */
    List<InputListener<T>> listeners = new ArrayList<>();

    /**
     * Default Constructor - the basic functionality simply updates the input with the given input
     */
    public InputListener() {
        this(i -> {return i;});
    }

    /**
     * Overloaded Constructor
     * @param updater Specifies how the input is received
     */
    public InputListener(Function<T, T> updater) {
        this.updater = updater;
    }

    /**
     * Overloaded Constructor
     * @param updater Specifies how the input is received
     * @param listeners Other listeners chained to this one
     */
    public InputListener(Function<T, T> updater, List<InputListener<T>> listeners) {
        this.updater = updater;
        this.listeners = listeners;
    }

    /**
     * Getter for input
     */
    public T getInput() {
        return input;
    }

    /**
     * Getter for listeners
     * @return The list of chained listeners
     */
    @Override
    public List<InputListener<T>> getInputListeners() {
        return listeners;
    }

    /**
     * Updates the input
     * @param provider The inputtable that is providing the input
     */
    public void update(Inputtable<T> provider) {
        input = updater.apply(provider.getInput());
        updateListeners();
    }

    /**
     * @return the input toString
     */
    @Override
    public String toString() {
        return this.input.toString();
    }
}  
