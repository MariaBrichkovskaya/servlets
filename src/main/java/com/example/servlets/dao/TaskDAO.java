package com.example.servlets.dao;

import com.example.servlets.db.DBConnector;
import com.example.servlets.dto.TaskDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private DBConnector dbConnector;
    private TaskDAO(DBConnector dbConnector){
        this.dbConnector=dbConnector;
    }
    private static volatile TaskDAO instance;
    public static synchronized TaskDAO getInstance(DBConnector dbConnector) {
        if (instance == null) {
            instance = new TaskDAO(dbConnector);
        }
        return instance;
    }
    public List<TaskDTO> getTasks() {
        List<TaskDTO> tasks = new ArrayList<>();
        try(ResultSet resultSet = dbConnector.getConnection().createStatement().executeQuery("SELECT * FROM tasks");) {

            while(resultSet.next()) {
                TaskDTO task = new TaskDTO(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getTimestamp(4).toLocalDateTime());
                tasks.add(task);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void addTask(TaskDTO task) {
        try(PreparedStatement statement = dbConnector.getConnection().prepareStatement("insert into tasks(name,description,time) values (?,?,?)");) {
            statement.setString(1,task.getName());
            statement.setString(2,task.getDescription());
            statement.setObject(3,task.getTime());
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTask(Long id) {
        try(PreparedStatement statement=  dbConnector.getConnection().prepareStatement("delete from tasks where id=?");) {
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void editTask(String name, Long id) {
        try(PreparedStatement statement=  dbConnector.getConnection().prepareStatement("UPDATE tasks SET name = ? WHERE id = ?;");) {
            statement.setString(1, name);
            statement.setLong(2,id);
            statement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

}
