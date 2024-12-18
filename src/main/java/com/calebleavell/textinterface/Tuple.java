package com.calebleavell.textinterface;

public class Tuple<T, U> {
    private T x;
    private U y;

    public Tuple(T x,  U y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }
    public U getY() {
        return y;
    }

    public void setX(T x) {
        this.x = x;
    }
    public void setY(U y) {
        this.y = y;
    }
}
