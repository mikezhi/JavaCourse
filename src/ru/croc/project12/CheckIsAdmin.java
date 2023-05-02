package ru.croc.project12;

import java.sql.*;

public class CheckIsAdmin {

    private final Connection connection;

    public CheckIsAdmin(){
        try{
            System.out.println("is ADMIN");
            connection = DriverManager.getConnection(Config.getDBURL(), Config.getUSER(), Config.getPASSWORD());
            System.out.println(connection);
        } catch (SQLException e){
throw new RuntimeException(e);
        }

    }

/*
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

                            if (status == "Администратор") System.out.println("you is ADMIN");


                            // String format = String.format("%d, %s %s %s %s %s %s", id, status, firstname, lastname, password, cards,scores);
                            // System.out.println(format);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
        }

        System.out.println("is ADMIN?");
}
 */

}

