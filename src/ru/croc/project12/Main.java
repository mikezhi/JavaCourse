package ru.croc.project12;

/**
 *
 *
 * В приложении должно быть реализовано разделение доступа по ролям пользователей.
 * Пользователь с ролью «Администратор» добавляется через БД и имеет доступ к полному функционалу программы
 * (CRUD-операции, запуск тестирования).
 * Обычные пользователи имеют доступ только к запуску тестирования. Авторизация в приложении выполняется по логину пользователя.
 * Если пользователь ввел логин, которого нет в БД, то создается новый пользователь.
 *
 * 2. Используем библиотеки из состава JDK, javax.
 * 3. Описываем подробно свое решение в README-файле.
 *
 * Необходимо описать, как запускать вашу программу, какие команды поддерживает программа.
 * Желательно приводить скриншоты в описании функционала.
 *
 */

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class Main {
    public static void main(String[] args){
      //  String pathToFileWithNewWords = args[0];
       // String pathToConfig = args[1];
        // Config.getConfig(pathToConfig);



        String url = "jdbc:h2:tcp://localhost/~/test";

        try (Connection conn = DriverManager.getConnection(url, "sa", "tiger")) {
            String sql = "select * from users";
            try (Statement statement = conn.createStatement()) {
                boolean hasResult = statement.execute(sql);
                if (hasResult) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");
                            String status = resultSet.getString("STATUS");
                            String firstname = resultSet.getString("FIRSTNAME");
                            String lastname = resultSet.getString("LASTNAME");
                            String password = resultSet.getString("PASSWORD");
                            String cards = resultSet.getString("CARDS");
                            String scores = resultSet.getString("SCORES");

                            String format = String.format("%d, %s %s %s %s %s %s", id, status, firstname, lastname, password, cards,scores);
                            System.out.println(format);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
        }




        //new Starter().addingNewWordsFromFile(pathToFileWithNewWords);
      // new MasterMenu().menu();
    }
}