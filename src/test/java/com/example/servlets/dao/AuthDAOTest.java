package com.example.servlets.dao;

import com.example.servlets.db.DBConnector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class AuthDAOTest {


    static DBConnector dbConnector;
    static AuthDAO authDAO;
    static Connection connection;
    static PreparedStatement statement;
    static ResultSet resultSet;
    @BeforeAll
    static void init() throws SQLException {

        dbConnector = mock(DBConnector.class);
        authDAO = AuthDAO.getInstance(dbConnector);
        connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        when(dbConnector.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
    }
    @Test
    void isAuth() throws SQLException {
        when(dbConnector.getConnection().prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertTrue(authDAO.isLoginUnique("auth"));


    }

    @Test
    void addUser() throws SQLException {
        when(statement.executeUpdate()).thenReturn(1);
        String login="log";
        String pass="pass";
        authDAO.addUser(login,pass);

        assertEquals(1, statement.executeUpdate());



    }

    @Test
    void isLoginNotUnique() throws SQLException {

        when(dbConnector.getConnection().prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        assertFalse(authDAO.isLoginUnique("exiting"));

    }
    @Test
    void isLoginUnique() throws SQLException {

        when(dbConnector.getConnection().prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertTrue(authDAO.isLoginUnique("not_exiting"));

    }

}