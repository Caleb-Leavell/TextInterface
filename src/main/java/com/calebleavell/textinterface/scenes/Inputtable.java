package com.calebleavell.textinterface.scenes;

/**
 * 
 * <p> Inputtable Interface </p>
 * 
 * <p> An Inputtable class should be able to collect and return some sort of input
 * from the user </p>
 * 
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
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
