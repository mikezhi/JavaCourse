package ru.croc.task4;

public class Annotation {
    public String info;
    public Figure figure;

    /* Create public constructor */
    public Annotation(Figure figure, String info){
        this.figure = figure;
        this.info = info;
    }

    @Override
    /* overriding toString() method */
    public String toString(){
        return this.figure.toString() + ": " + this.info;
    }

    /* Create getter for Figure */
    public Figure getFigure(){
        return figure;
    }
    /* Create setter for Figure */
    public void setFigure(Figure figure){
        this.figure = figure;
    }

    /* Create getter for Info about figures */
    public String info(){
        return info;
    }
    /* Create setter for Info about figures */
    public void setInfo(String info){
       this.info = info;
    }


}