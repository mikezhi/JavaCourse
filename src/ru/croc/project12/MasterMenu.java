package ru.croc.project12;

import java.util.Scanner;

/**
 * Класс, соответствующий главному меню приложения.*/
public class MasterMenu {

    /**Метод выводит интерфейс главного меню в консоль.*/
    public void printMenu(){
        String menuStr = """
                1) Введите логин
                0) Выйти из приложения
                """;
        System.out.println(menuStr);
    }

    /**Метод реализующий интерфейс главного меню*/
    public void menu(){

        Scanner scan = new Scanner(System.in);
        int x = 0;
        boolean checker = true;

        while (checker){
            printMenu();
            try {
                x = scan.nextInt();
            } catch (NumberFormatException e){
                System.out.println("Неверный ввод");
            }
            switch (x) {
                case 1 -> {
                    System.out.println("Введите свой логин, для проверки прав досутпа: ");
                    Scanner in = new Scanner(System.in);
                    if (new RoleChecker().hasRole(in.nextLine()) == true){
                        new WordForAdmin().menu();
                    };
                }
                case 0 -> checker = false;
            }
        }
        System.out.println("До свидания!");
    }
}