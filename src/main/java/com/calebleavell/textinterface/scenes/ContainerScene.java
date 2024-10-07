package com.calebleavell.textinterface.scenes;

/**
 * <p>
 * This is an empty scene that serves no purpose but to contain other scenes
 * </p>
 * <p>
 * It is a concrete version of GenericScene
 * </p>
 */
public class ContainerScene extends GenericScene {

    public ContainerScene(Builder builder) {
        super(builder);
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        public ContainerScene build() {
            return new ContainerScene(self());
        }
    }
}
