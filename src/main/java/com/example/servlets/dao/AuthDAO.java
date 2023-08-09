package com.example.servlets.dao;

import com.example.servlets.db.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
    public static boolean isAuth(String login,String password){
        try(PreparedStatement statement = DBConnector.CONNECTION.prepareStatement("SELECT * FROM users where login=? and password=?");) {
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();

            if(resultSet.next())
            {
                return true;

            }
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void addUser(String login,String password){
        try(PreparedStatement statement = DBConnector.CONNECTION.prepareStatement("INSERT INTO users (login, password) Values (?, ?)");) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isLoginUnique(String login) {

        try(PreparedStatement statement = DBConnector.CONNECTION.prepareStatement("SELECT login FROM users WHERE login = ?");) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("aaaaaaaa");
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

}
