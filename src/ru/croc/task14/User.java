package ru.croc.task14;

import java.util.Objects;

public class User {
    private String moviesIsWatched;

    public User(String moviesIsWatched){
        this.moviesIsWatched = moviesIsWatched;
    }

    @Override
    public String toString(){
        return moviesIsWatched;
    }

    @Override
    public int hashCode(){
        return Objects.hash(moviesIsWatched);
    }
}
