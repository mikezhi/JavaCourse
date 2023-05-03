package ru.croc.project12;


import java.sql.*;
import java.util.Map;

/**
 * Данный класс нужен для реализации CRUD-a, и работой с БД */
public class WordCRUD {

    private final Connection connection;

    public WordCRUD(){
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "tiger");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Метод проверяет существование пользователя с ролью Administrator в БД */
    public boolean roleCheckDB(String role){

        try (Statement stmt = connection.createStatement()){

            String sql = String.format("SELECT * FROM leitner_card WHERE role = '%s'", role);
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()){
               //TODO thinking,...in progress
                return true;

            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**Метод удаляет таблицу "words", если она существует*/
    public void dropDB(){
        try (Statement stmt = connection.createStatement()){
            stmt.executeUpdate("DROP TABLE IF EXISTS leitner_card;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод добавляет объект типа слово в таблицу leitner_card.
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
                    "INSERT INTO leitner_card (id, role, user_login, ru_word, en_word, number_group)" +
                            "VALUES (%d, '%s', '%s', '%s', '%s', %d);",
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

            String sql = String.format("SELECT * FROM leitner_card WHERE russian = '%s' OR english = '%s'", word, word);
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String ru_word = resultSet.getString("ru_word");;
                String en_word = resultSet.getString("en_word");
                int number_group = resultSet.getInt("number_group");
                return new WordTranslate(id, ru_word, en_word, number_group);
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

            String sql = String.format("DELETE FROM leitner_card WHERE ru_word = '%s' OR en_word = '%s'", word, word);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO need be to realize in future
    /**Метод возвращает слова, соответствующие определенной группе
     * @param groupNum - номер группы
     * @return возвращает Map<Integer, Word>, где ключ это id слова, а значение - объект типа Word*/

    public Map<Integer, WordTranslate> getGroupByNum(int groupNum){
      return null;
    }


//TODO need be to realize in future
    /**Метод возвращает все слова из БД
     * @return возвращает Map<Integer, Word>, где ключ это id слова, а значение - объект типа Word*/

    public Map<Integer, WordTranslate> getAllWords(){
        return null;
    }

    /**Метод возвращает максимальный индекс в БД*/
    public int getMaxIndex(){
        try (Statement stmt = connection.createStatement()){

            String sql  = "SELECT MAX(id) AS \"MAX\" FROM leitner_cards";

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
    public void incrementGroup(int id){

    }

    /**Метод устанавливает номер группы у слова,
     * которое задаётся идентфиикатором
     * @param id - идентификатор слова, группу которого следует увеличить
     * @param number_group - номер группы*/
    public void setGroup(int id, int number_group){

    }
}
