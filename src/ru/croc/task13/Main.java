package ru.croc.task13;

import java.util.ArrayList;
import java.util.HashSet;

public class Main extends Filter{

    public static void main(String[] args) {
        ArrayList<String> comments = new ArrayList();
        comments.add("I hate JAVA");
        comments.add("I love sleep always");
        comments.add("Cola Zero is greatest!");
        comments.add("Quality Assurance the best!");
        System.out.println("Before filtration: " + comments);

        HashSet<String> blacklist = new HashSet<>();
        blacklist.add("HATE");
        blacklist.add("LOVE");

        Filter filter = new Filter();
        filter.filterComments(comments,blacklist);
        System.out.println("After filtration:  " + comments);
    }

}
