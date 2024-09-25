package com.calebleavell.textinterface;

@FunctionalInterface
public interface Inputable<T> {

    /*
     * This is a getter method that returns the collected input of the object
     * Note: while it is possible to collect input in this method, it is NOT forced 
     *  nor is it done in the I implementation
     */
    public T getInput();
}
