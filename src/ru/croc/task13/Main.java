package ru.croc.task13;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class BlackListFilterImpl implements BlackListFilter {
    public static void main(String[] args) {

        BlackListFilterImpl bli = new BlackListFilterImpl();

        List<String> commentsListData = Arrays.asList("nigger", "nigga", "negro");

        Predicate<String> blackListData = Predicate.isEqual("nigger");

        System.out.println(bli.filterComments(commentsListData, blackListData));
    }
}