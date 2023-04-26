package ru.croc.task15;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Iterator;

public interface BlackListFilter<T> {
    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blackList) {

        ArrayList<T> filtrationResult = new ArrayList<>();
        Iterator<T> it = comments.iterator();

        while (it.hasNext()) {
            T comment = it.next();
            if (!blackList.test(comment))
                filtrationResult.add(comment);
        }
        return filtrationResult;
    }
}
