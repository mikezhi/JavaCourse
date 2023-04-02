package ru.croc.task7;

public class Main {
    String source = "/*\n" +
                    " * My first ever program in JAVA!\n" +
                    " *\n" +
                    "class Hello {// class body starts here \n" +
                    "\n" +
                    "   /* main method */ \n" +
                    "   public static void main(String[] args/* we put command line arguments here*/) {\n" +
                    "      //this line prints my first greeting to the screen \n" +
                    "      System.out.println(\"Hi\"); // :)\n" +
                    "   }\n" +
                    "} // the end\n" +
                    "// to be continued...\n";
            ;
    public static void main(String[] args) {
        String source ="..."; //test data
        String noComents = removeJavaComments(source);
        System.out.println(noComents);
    }

}
