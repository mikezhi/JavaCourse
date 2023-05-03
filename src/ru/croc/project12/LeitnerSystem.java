package ru.croc.project12;


import java.util.Map;
import java.util.Scanner;

/**Класс реализует систему Лейтнера,
 * которая позволяет пользователю повторять перевод английских слов
 * с заранее заданной переодичнсоть.*/
public class LeitnerSystem {
    /**Метод позволяет повторить слова из конкретной группы*/
    public static void repeatGroupByNum(int groupNum){

        System.out.println("Введите '0', если хотите прекратить повторение группы №" + groupNum);

        WordCRUD wordDAO = new WordCRUD();
        Map<Integer, WordTranslate> words = wordDAO.getGroupByNum(groupNum);
        for (Map.Entry<Integer, WordTranslate> entry : words.entrySet()){
            WordTranslate word = entry.getValue();
            System.out.println("Введите перевод для " + word.getEnglish());
            String translatedWord = new Scanner(System.in).nextLine().toLowerCase();

            if (translatedWord.equals("0")){
                return;
            }

            if (translatedWord.equals(word.getRussian().toLowerCase())){
                System.out.println("Правильно!");
                wordDAO.incrementGroup(word.getId());
            } else{
                System.out.println("Неправильно!");
                wordDAO.setGroup(word.getId(), 1);
            }
        }
    }
}