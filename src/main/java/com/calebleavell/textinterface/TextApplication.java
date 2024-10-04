package com.calebleavell.textinterface;

import com.calebleavell.textinterface.scenes.*;

public class TextApplication extends GenericScene{
    private Scene home;

    public Scene getHome() {
        return getChildren().get(0);
    }

    public void setHome(Scene home) {
        this.home = home;
        this.getChildren().set(0, home);
    }

    public TextApplication(Builder builder) {
        super(builder.children(builder.home));
        this.home = builder.home;
    }

    public static class Builder extends GenericScene.Builder<Builder> {
        private Scene home = new TextScene.Builder().text("Hello, World!").build();

        public Builder home(Scene home) {
            this.home = home;
            return self();
        }

        public TextApplication build() {
            return new TextApplication(self());
        }
    }

}
