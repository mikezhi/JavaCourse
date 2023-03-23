package ru.croc.task5;

import ru.croc.task4.Rectangle;

import java.security.PublicKey;

class MovableRectangle extends Rectangle implements Movable {

    public MovableRectangle(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }

    @Override
    public void move(int dx, int dy){
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
    }
}
