package com.example.lab6_javafx.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * RepositoryJDBC implements the ICrudRepository interface
 * the class creates the connection to the database
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
public abstract class RepositoryJDBC<T> implements ICrudRepository<T>{

    static  String DB_URL = "jdbc:mysql://localhost:3306/lab5_map";
    static final String USER = "Evelin";
    static final String PASS = "Dichtheitseigenschaft2021";

    protected Connection con;

    public RepositoryJDBC(String url) {

        setUrl(url);
        con = connectDB();
    }

    public Connection connectDB() {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER,
                    PASS);
        }

        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }
    public void setUrl(String url)
    {
        DB_URL=url;
    }



}
