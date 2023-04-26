package ru.croc.task13;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Filter implements BlackListFilter{

    public void filterComments(List<String> comments, Set<String> blackList) {
        Iterator<String> iterator = comments.iterator();
        while (iterator.hasNext()){
            String comment = iterator.next();
            for (String badWord : blackList){
                if (comment.toUpperCase().contains(badWord))
                    iterator.remove();
            }
        }
    }
}
