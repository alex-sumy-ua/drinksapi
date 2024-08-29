package com.northcoders.drinksapi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connect(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
