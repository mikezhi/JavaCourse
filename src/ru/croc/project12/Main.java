package ru.croc.project12;
/**
В приложении должно быть реализовано разделение доступа по ролям пользователей.
 Пользователь с ролью «Администратор» добавляется через БД и имеет доступ к полному функционалу программы
 (CRUD-операции, запуск тестирования). Обычные пользователи имеют доступ только к запуску тестирования.
 Авторизация в приложении выполняется по логину пользователя.
 Если пользователь ввел логин, которого нет в БД, то создается новый пользователь.

        12. Обучение через карточки: по системе Лейтнера
        Реализовать приложение, выполняющее тестирование по системе Лейтнера.
        Приложение должно поддерживать CRUD-операции с тестами, запуск
        тестирования, сохранение прогресса по тестам.
        Тесты и результаты тестирования должны храниться в БД.
        Реализовать механизм экспорта прогресса по тестам в
        CSV-файл.
 */

import java.sql.*;


public class Main {


    public static void main(String[] args){

/**

        String pathToFileWithNewWords = args[0];
        String pathToConfig = args[1];
        Config.getConfig(pathToConfig);

        new WordsFromFile().addingNewWordsFromFile(pathToFileWithNewWords);
        new MasterMenu().menu();
*/

        String url = "jdbc:h2:tcp://localhost/~/test";

        try (Connection conn = DriverManager.getConnection(url, "sa", "tiger")) {
            String sql = "select * from leitner_card";
            try (Statement statement = conn.createStatement()) {
                boolean hasResult = statement.execute(sql);
                if (hasResult) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ID");
                            String role = resultSet.getString("ROLE");
                            String user_login = resultSet.getString("USER_LOGIN");
                            String ru_word = resultSet.getString("RU_WORD");
                            String en_word = resultSet.getString("EN_WORD");
                            String number_group = resultSet.getString("NUMBER_GROUP");

                            if (role == "Administrator") System.out.println("you is ADMIN");


                            String format = String.format("%d, %s %s %s %s %s", id, role, user_login, ru_word, en_word, number_group);
                            System.out.println(format);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
        }




    }
}