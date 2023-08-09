package com.example.servlets.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL="jdbc:mysql://localhost:3306/servlets?useUnicode=true&characterEncoding=utf-8";
    private static final String USER="root";
    private static final String PASS="Raznie71593)";
    public static final Connection CONNECTION=getConnection();
   private static Connection getConnection(){
       try {

           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection= DriverManager.getConnection(URL,USER,PASS);
           return connection;
       } catch (ClassNotFoundException | SQLException e) {
           throw new RuntimeException(e);
       }

   }
}
