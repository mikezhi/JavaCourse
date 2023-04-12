package ru.croc.task8;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan;
        try {
            //Создаем сканнер и считываем файл ввденный из команной строки (кс) пареметром
            scan = new Scanner(Paths.get(args[0]));
        } catch (IOException e){
            //Обработка ошибки если файл не совпадает с введеным из кс
            System.out.println("File <" +args[0]+ "> not found!");
            return;
        }
        //Инициализируем и обнуляем счетчик слов
        int cnt = 0;
        //Истина когда слово найдено
        boolean cond;
        //Для хранения слов из файла
        String word;
        while (scan.hasNext()){
            cond = false;
            //Считываем последовательно слова в цикле
            word = scan.next();
            /* Проверяем удовлетворяют ли слова нашим условиям и выставляем cond = true
             * для итогового подсчета колличество слов в нашем файле
            */
            for (int i = 0; i < word.length(); i++){
                // Получаем символы из строки по индексу
                char chr = word.charAt(i);
                //Проверяем на наличие символов из условия
                if ((chr >= '0' && chr <= '9') || (chr >= 'a' && chr <= 'z') ||
                    (chr >= 'A' && chr <= 'Z') || (chr >= 'а' && chr <= 'я')  ||
                    (chr >= 'А' && chr <= 'Я')
                ){
                    //Флаг истина когда слово найдено
                    cond = true;
                    break;
                }
            }
            //Если удовлетворяют условию, то инкрементируем счетчик слов
            if (cond) {
                cnt++;
            }
        }
        //Вывод результата работы программы
        System.out.println("Words counts in file <"+args[0]+ "> is: " + cnt);
    }
}
