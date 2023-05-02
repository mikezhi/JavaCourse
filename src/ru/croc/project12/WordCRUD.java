package ru.croc.project12;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный DAO класс, он нужен для реализации CRUD-a, и работой с БД Words*/
public class WordCRUD {

    private final Connection connection;

    public WordCRUD(){
        try {
            connection = DriverManager.getConnection(Config.getDBURL(), Config.getUSER(), Config.getPASSWORD());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод удаляет таблицу "words", если она существует*/
    public void dropDB(){
        try (Statement stmt = connection.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXISTS words;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод добавляет объект типа Word в таблицу words.
     * В случае, если таблица отсутствует, то создаётся новая.*/
    public void addWordToDB(WordTranslate word){
        try (Statement stmt = connection.createStatement()){

            String createTableWords = "CREATE TABLE IF NOT EXISTS leitner_card ("+
            "id int NOT NULL, "+
            "role VARCHAR(25) NOT NULL, "+
            "user_login VARCHAR(255) NOT NULL,"+
            "ru_word VARCHAR(255) NOT NULL, "+
            "en_word VARCHAR(255) NOT NULL, "+
            "number_group int NOT NULL, "+
            "PRIMARY KEY (id));";

            stmt.executeUpdate(createTableWords);

            WordTranslate wordFromDB = findWordInDB(word.getEnglish());
            if (wordFromDB != null){
                return;
            }

            String insertWordInTable = String.format(
                    "INSERT INTO words (id, user_login, russian, english, group_number)" +
                            "VALUES (%d, '%s', '%s', '%s', %d);",
                    getMaxIndex() + 1, Config.getUserLogin(), word.getRussian(), word.getEnglish(), word.getGroup());
            stmt.executeUpdate(insertWordInTable);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод ищет слово в БД
     * @param word - перевод слова на русский или английский языки
     * @return возвращает объект типа Word или null в случае,
     * если слово с таким переводом отсутствует в таблице*/
    public WordTranslate findWordInDB(String word){
        try (Statement stmt = connection.createStatement()){

            String sql = String.format("SELECT * FROM words WHERE russian = '%s' OR english = '%s'", word, word);
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String russian = resultSet.getString("russian");;
                String english = resultSet.getString("english");;
                int group = resultSet.getInt("group_number");;
                return new WordTranslate(id, russian, english, group);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод удаляет слово с заданным переводом из БД
     * @param word - перевод слова на русский или английский языки
     */
    public void deleteWordFromDB(String word){
        try (Statement stmt = connection.createStatement()){

            String sql = String.format("DELETE FROM words WHERE russian = '%s' OR english = '%s'", word, word);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод возвращает слова, соответствующие определенной группе
     * @param groupNum - номер группы
     * @return возвращает Map<Integer, Word>, где ключ это id слова, а значение - объект типа Word*/
    public Map<Integer, WordTranslate> getGroupByNum(int groupNum){
        try (Statement stmt = connection.createStatement()){

            String sql  = String.format(
                    "SELECT * FROM words WHERE group_number = %d AND user_login = '%s'",
                    groupNum, Config.getUserLogin());
            ResultSet resultSet = stmt.executeQuery(sql);
            Map<Integer, WordTranslate> words = new HashMap<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String russian = resultSet.getString("russian");;
                String english = resultSet.getString("english");;
                int group = resultSet.getInt("group_number");;
                WordTranslate word = new WordTranslate(id, russian, english, group);
                words.put(word.getId(), word);
            }
            return words;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод возвращает все слова из БД
     * @return возвращает Map<Integer, Word>, где ключ это id слова, а значение - объект типа Word*/
    public Map<Integer, WordTranslate> getAllWords(){
        try (Statement stmt = connection.createStatement()){

            String sql  = String.format("SELECT * FROM words WHERE user_login = '%s'",
                    Config.getUserLogin());

            ResultSet resultSet = stmt.executeQuery(sql);

            Map<Integer, WordTranslate> words = new HashMap<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String russian = resultSet.getString("russian");;
                String english = resultSet.getString("english");;
                int group = resultSet.getInt("group_number");;
                WordTranslate word = new WordTranslate(id, russian, english, group);
                words.put(word.getId(), word);
            }

            return words;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод возвращает максимальный индекс в БД*/
    public int getMaxIndex(){
        try (Statement stmt = connection.createStatement()){

            String sql  = "SELECT MAX(id) AS \"MAX\" FROM words";

            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            return resultSet.getInt("MAX");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод увеличивает номер группы на один у слова,
     * которое задаётся идентфиикатором
     * @param id - идентификатор слова, группу которого следует увеличить*/
    /*public void incrementGroup(int id){
        try (Statement stmt = connection.createStatement()){
            String sql;
            sql  = "SELECT * FROM words WHERE id = " + id;
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();
            int group = resultSet.getInt("group_number");
            if (group != Config.getGroupNumber()) {
                group++;
                sql = String.format("UPDATE words SET group_number = %d WHERE id = %d", group, id);
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /**Метод устанавливает номер группы у слова,
     * которое задаётся идентфиикатором
     * @param id - идентификатор слова, группу которого следует увеличить
     * @param group - номер группы*/
    public void setGroup(int id, int group){
        try (Statement stmt = connection.createStatement()){
            String sql = String.format("UPDATE words SET group_number = %d WHERE id = %d", group, id);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
