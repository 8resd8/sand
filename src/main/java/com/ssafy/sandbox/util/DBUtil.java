package com.ssafy.sandbox.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ssafymovie?serverTimezone=UTC";
    private static final String DB_ID = "ssafy";
    private static final String DB_PWD = "ssafy";

    @Getter
    private static final DBUtil instance = new DBUtil();

    private DBUtil() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.info("DRIVER error: {}", e);
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_ID, DB_PWD);
    }
}