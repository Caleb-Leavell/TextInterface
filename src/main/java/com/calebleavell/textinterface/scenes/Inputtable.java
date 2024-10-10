package com.calebleavell.textinterface.scenes;

/**
 * An Inputtable class should be able to collect and return some sort of input
 * from the user
 */
@FunctionalInterface
public interface Inputtable<T> {
    /**
     * Getter for the input collected from the user
     * 
     * @return input of type T
     */
    public T getInput();

}
