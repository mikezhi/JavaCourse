package ru.croc.project12;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержащий информацию о переводе слова на русский и английский языки, группе в которой слово находится
 * */

public class WordTranslate {

    /**Идентификатор слова.
     * Объект типа Word получает id только после добавления в базу данных системы Лейтейнера.*/
    private final int id;

    /**Перевод на русский*/
    private final String russian;

    /**перевод на английский*/
    private final String english;

    /**номер группы, в которой находится заданное слово*/
    private final int group;


    public WordTranslate(int id, String russian, String english, int group){
        this.id = id;
        this.russian = russian;
        this.english = english;
        this.group = group;
    }

    public WordTranslate(String russian, String english){
        this.id = -1;
        this.russian = russian;
        this.english = english;
        this.group = 1;
    }

    public int getId() {
        return id;
    }

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "russian: " + russian + "\n" +
                "english: " + english;
    }

    public static WordTranslate stringToWord(String line){
        String[] arr = line.split(";");
        return new WordTranslate(-1, arr[1].trim(), arr[0].trim(), 1);
    }

    public static List<WordTranslate> getNewWordsFromFile(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)){

            List<WordTranslate> words = new ArrayList<>();
            String line = reader.readLine();

            while (line != null) {
                words.add(WordTranslate.stringToWord(line));
                line = reader.readLine();
            }

            return words;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}