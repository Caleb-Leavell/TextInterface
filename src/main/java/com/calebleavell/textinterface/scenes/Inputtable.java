package com.calebleavell.textinterface.scenes;

import java.util.List;
import com.calebleavell.textinterface.InputListener;

/**
 * 
 * <p> Inputtable Interface </p>
 * 
 * <p> An Inputtable class should be able to collect and return some sort of input
 * from the user </p>
 * 
 * 
 * @author Caleb Leavell
 * 
 * @version 1.01 Add InputListener Functionality
 * @version 1.00 Initial Construction
 * 
 */
public interface Inputtable<T> {
    /**
     * Getter for the input collected from the user
     * 
     * @return input of type T
     */
    public T getInput();


    public List<InputListener<T>> getInputListeners();

    /**
     * Adds a listener that gets updated to match the users's input
     */
    public default void addInputListener(InputListener<T> listener) {
        getInputListeners().add(listener);
    }

    /**
     * Runs through each listener and provides them with this object
     */
    public default void updateListeners() {
        for(InputListener<T> listener : getInputListeners()) {
            listener.update(this);
        }
    }


}
