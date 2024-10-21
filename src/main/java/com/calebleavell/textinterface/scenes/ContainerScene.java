package com.calebleavell.textinterface.scenes;

/**
 * 
 * <p> ContainerScene Class </p>
 * 
 * <p> This is an empty scene that serves no purpose but to contain other scenes </p>
 * <p> It is a concrete version of GenericScene </p>
 * 
 * @author Caleb Leavell

 * @version 1.00 Initial Construction
 * 
 */
public class ContainerScene extends GenericScene {

    /**
     * Constructor
     * @param builder The builder with all fields
     */
    public ContainerScene(Builder builder) {
        super(builder);
    }

    /**
     * This Builder class is simply a concrete version of the GenericScene classes builder
     */
    public static class Builder extends GenericScene.Builder<Builder> {

        /**
         * Build the ContainerScene
         * @return a new ContainerScene with the specified fields
         */
        public ContainerScene build() {
            return new ContainerScene(self());
        }
    }
}
