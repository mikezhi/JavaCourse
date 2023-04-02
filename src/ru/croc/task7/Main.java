package ru.croc.task7;

public class Main {
    public static void main(String[] args) {
        //test data-string with two kind (/*...*/ and //) of comments
        String sourceString = "/*\n" +
                " * My first ever program in JAVA!\n" +
                " */\n" +
                "class Hello {// class body starts here \n" +
                "\n" +
                "   /* main method */" +
                "   public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "      //this line prints my first greeting to the screen \n" +
                "      System.out.println(\"Hi\"); // :)\n" +
                "   }\n" +
                "} // the end\n" +
                "// to be continued...\n";

        //make a remove comments
        String noComments = removeJavaComments(sourceString);
        //show result
        System.out.println(noComments);
    }

    static String removeJavaComments(String sourceString) {
        //remove comments using regular expression
        return sourceString.replaceAll(
                "//.*|/\\*((.|\\n)(?!=*/))+\\*/", ""
        );
    }
}




