package ru.croc.task13;

/**
 * Небольшой стартап разрабатывает социальный сервис, в котором пользова-
 * тели могут оставлять комментарии. Со временем в комментариях появился спам
 *
 * и разработчики решили бороться с ним с помощью "черных списков" запрещенных
 * слов. Они составили такие списки и поручили вам написать функцию, удаляющую
 * из всех накопленных комментариев нежелательные.
 * Они предоставили вам интерфейс, который внедрили в свой продукт, и
 * попросили написать его реализацию:
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public interface BlackListFilter<T> {
    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments list of comments; every comment
     * is a sequence of words, separated
     * by spaces, punctuation or line breaks
     * @param blackList list of words that should not
     * be present in a comment
     */

    default ArrayList<T> filterComments(Iterable<T> comments, Predicate<T> blacklist) {
        ArrayList<T> filteredResult = new ArrayList<>();
        Iterator<T> iterator  = comments.iterator();

        while (iterator.hasNext()){
         T comment  = iterator.next();
         if (!blacklist.test(comment))
             filteredResult.add(comment);
        }
        return filteredResult;
    }

}