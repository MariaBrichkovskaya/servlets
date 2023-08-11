package com.example.servlets.dao;

import com.example.servlets.db.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AuthDAO {

    private AuthDAO(){
    }
    private static volatile AuthDAO instance;
    public static synchronized AuthDAO getInstance() {
        if (instance == null) {
            instance = new AuthDAO();
        }
        return instance;
    }
        public boolean isAuth(String login,String password){
        try(PreparedStatement statement = Objects.requireNonNull(DBConnector.getConnection()).prepareStatement("SELECT * FROM users where login=? and password=?");) {
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
    public void addUser(String login,String password){
        try(PreparedStatement statement = Objects.requireNonNull(DBConnector.getConnection()).prepareStatement("INSERT INTO users (login, password) Values (?, ?)");) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isLoginUnique(String login) {

        try(PreparedStatement statement = Objects.requireNonNull(DBConnector.getConnection()).prepareStatement("SELECT login FROM users WHERE login = ?");) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    /*public static void deleteUser(String login,String password){
        try(PreparedStatement statement = DBConnector.getConnection().prepareStatement("delete from users where login=? and password =?");) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }*/

}
