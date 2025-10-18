package com.pavamana;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnector {
    public static Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/bootcampdb";
        String user = "postgres";
        String password = "yourpassword";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}