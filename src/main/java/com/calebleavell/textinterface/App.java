package com.calebleavell.textinterface;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        TextInput myInput = new TextInput("Tell me your name!", null, null, null, null);

        myInput.print();

        System.out.println("You entered: " + myInput.getInput());
    }
}
