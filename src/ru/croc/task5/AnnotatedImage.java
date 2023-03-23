package ru.croc.task5;

import ru.croc.task4.Annotation;
public class AnnotatedImage {

    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations){
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    //Annotation - find Figure by points
    Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.toString().contains("(" + x + ", " + y + ")")) {
                return annotation;
            }
        }
        return null;
    }

    //Annotation - find Figure by label
    Annotation findByLabel(String label){
        for (Annotation annotation: annotations){
           if (annotation.toString().contains(label)){
               return annotation;
           }
        }
        return null;
    }

    //Getter for Image path
    public String getImagePath(){
        return this.imagePath;
    }

    public Annotation[] getAnnotations(){
        return this.annotations;
    }
}
