package ru.croc.project12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * файл конфигурации */
public class Config {

    /** логин пользователя для */
    private static String USER_LOGIN;

    /** строка соединения с БД */
    private static String DB_URL;

    /** логин для входа в БД*/
    private static String USER;

    /**Пароль для входа в БД*/
    private static String PASSWORD;


    /**
     * получаем конфиг для задяния из файла
     * @param path - путь к файлу где содержаться параметры системы
     * */
    public static void getConfig(String path){
        try (FileReader fr = new FileReader(path);
             BufferedReader reader = new BufferedReader(fr)) {

            USER_LOGIN = reader.readLine();
            DB_URL = reader.readLine();
            USER = reader.readLine();
            PASSWORD = reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * make a getters
     * */

    public static String getUserLogin() {
        return USER_LOGIN;
    }

    public static String getDBURL() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }


}