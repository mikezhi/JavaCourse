package ru.croc.project12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManager {

    private Connection connection;

    public ConnManager(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(Config.getDBURL(), Config.getUSER(), Config.getPASSWORD());
            return connection;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


