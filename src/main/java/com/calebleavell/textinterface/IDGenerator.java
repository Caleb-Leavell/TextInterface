package com.calebleavell.textinterface;

/**
 * The ID class has a static counter for id's, and can generate a unique id.
 * Style note: whenever the i in id is capitalized, the d should be capitalized as well (ID)
 */
public class IDGenerator {
    private static long idCounter = 0;

    /**
     * @return a new, unique id
     */
    public static long generateID() {
        return ++idCounter;
    }
}
