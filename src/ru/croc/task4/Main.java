package ru.croc.task4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create scanner for user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the numbers of annotations: ");
        Annotation[] annotations = new Annotation[scan.nextInt()];

        for(int i=0; i<annotations.length; i++){
            System.out.println("Please select type of figure:");
            System.out.println("1 - Rectangle or 2 - Circle");
            int typeOfFigure = scan.nextInt();

            switch (typeOfFigure){
                //selected figure Rectangle
                case 1:
                    System.out.println("Please enter the coord. of corners: (x1, y1) and (x2, y2):");
                    Figure rectangle = new Rectangle(scan.nextInt(), // x1
                                                     scan.nextInt(), // y1
                                                     scan.nextInt(), // x2
                                                     scan.nextInt()  // y2
                                                    );
                    System.out.println("Input description of rectangle:");
                    Annotation annotationRectangle = new Annotation(rectangle, scan.next());
                    annotations[i] = annotationRectangle;
                    break;

                    //select figure Circle
                case 2:
                    System.out.println("Please enter center coordinates (x1, y1) and R: ");
                    Figure circle = new Circle(scan.nextInt(), // x1
                                               scan.nextInt(), // y2
                                               scan.nextInt()  // Radius
                                              );
                    System.out.println("Input the description of circle: ");
                    Annotation annotationCircle = new Annotation(circle, scan.next());
                    annotations[i] = annotationCircle;
                    break;
                default:
                   System.out.println("Invalid type of figure!");
                   break;
            }

        }

        //Show the list of all annotations
        System.out.println("List of annotations:");
        for (Annotation annotation : annotations){
             System.out.println(annotation.toString());
        }
            }
}





