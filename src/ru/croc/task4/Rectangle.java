package ru.croc.task4;

import ru.croc.task4.Figure;

public class Rectangle extends Figure {

    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public Rectangle(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    /* Overriding method toString() */
    public String toString(){
        return "R " + "(" + this.x1 + ", " + this.y1 + "), (" + this.x2 + ", " + this.y2 + ")";
    }

    /*Creating getters for coordinates */

    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }
    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }

    /* Creating setters for coordinates */

    public void setX1(int x1) {
        this.x1 = x1;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public void setY1(int y1) {
        this.y1 = y1;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }
}
