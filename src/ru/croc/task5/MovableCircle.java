package ru.croc.task5;

import ru.croc.task4.Circle;

class MovableCircle extends Circle implements Movable {
    public MovableCircle(int x1, int y1, int r){
          super(x1, y1, r);
    }

    @Override
    public void move(int dx, int dy){
        this.x1 += dx;
        this.y1 +=dy;
    }

}
