package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.*;

/**
 * 
 * <p> TextApplication Class </p>
 * 
 * <p> The house of all scenes in an application </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class TextApplication extends GenericScene {

    /**
     * The first scene to display
     */
    private Scene home;

    /**
     * Getter for home
     * @return The first scene to run
     */
    public Scene getHome() {
        return home;
    }

    /**
     * Setter for home
     * @param home The first scene to run
     */
    public void setHome(Scene home) {
        this.home = home;
        this.getChildren().set(0, home);
    }

    /**
     * Return a new TextApplication based on builder fields
     * @param builder The builder
     */
    public TextApplication(Builder builder) {
        super(builder.children(builder.home));
        this.home = builder.home;
    }

    /**
     * Builder class
     */
    public static class Builder extends GenericScene.Builder<Builder> {
        /**
         * By default, it simply creates a new scene that displays "Hello, World!"
         */
        private Scene home = new TextScene.Builder().text("Hello, World!").build();

        /**
         * Constructor
         * 
         * It takes no arguments, but it sets the name in the Builder of the super class to "root"
         */
        public Builder() {
            this.name("root");
        }

        /**
         * @param home The first scene to run
         * @return self
         */
        public Builder home(Scene home) {
            this.home = home;
            return self();
        }

        /**
         * @return the new TextApplication
         */
        public TextApplication build() {
            return new TextApplication(self());
        }
    }

}
