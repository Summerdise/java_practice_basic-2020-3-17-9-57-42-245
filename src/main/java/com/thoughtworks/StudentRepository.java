package com.thoughtworks;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentRepository {

  public void save(List<Student> students) { students.forEach(this::save); }

  public void save(Student student){
    // TODO:
    Connection connection = null;
    Statement statement = null;
    try {
      connection = DbUtil.getConnection();
      statement = connection.createStatement();
      String sql = "INSERT INTO student(id,name,gender,admission_year,birthday,class_id) VALUES ("+
              "?,?,?,?,?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,student.getId());
      preparedStatement.setString(2,student.getName());
      preparedStatement.setString(3,student.getGender());
      preparedStatement.setInt(4,student.getAdmissionYear());
      preparedStatement.setString(5, String.valueOf(new Date(student.getBirthday().getTime())));
      preparedStatement.setString(6,student.getClassId());

      preparedStatement.execute();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }finally {
      DbUtil.releaseSource(connection,statement);
    }
  }

  public List<Student> query() {
    // TODO:
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DbUtil.getConnection();
      statement = connection.createStatement();
      String sql = "Select * FROM student";
      resultSet = statement.executeQuery(sql);
      List<Student> list = new ArrayList<>();
      while(resultSet.next()){
        Student stu = new Student();
        stu.setId(resultSet.getString("id"));
        stu.setName(resultSet.getString("name"));
        stu.setGender(resultSet.getString("gender"));
        stu.setAdmissionYear(resultSet.getInt("admission_year"));
        stu.setBirthday(resultSet.getDate("birthday"));
        stu.setClassId(resultSet.getString("class_id"));
        list.add(stu);
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally {
      DbUtil.releaseSource(connection,statement,resultSet);
    }
    return new ArrayList<>();
  }

  public List<Student> queryByClassId(String classId) {
    // TODO:
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DbUtil.getConnection();
      statement = connection.createStatement();
      String sql = "Select * FROM student WHERE class_id = '1-1' ORDER BY id DESC";
      resultSet = statement.executeQuery(sql);
      List<Student> list = new ArrayList<>();
      while(resultSet.next()){
        Student stu = new Student();
        stu.setId(resultSet.getString("id"));
        stu.setName(resultSet.getString("name"));
        stu.setGender(resultSet.getString("gender"));
        stu.setAdmissionYear(resultSet.getInt("admission_year"));
        stu.setBirthday(resultSet.getDate("birthday"));
        stu.setClassId(resultSet.getString("class_id"));
        list.add(stu);
      }
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally {
      DbUtil.releaseSource(connection,statement,resultSet);
    }
    return new ArrayList<>();
  }

  public void update(String id, Student student) {
    Connection connection = null;
    Statement statement = null;

    try {
      connection = DbUtil.getConnection();
      statement = connection.createStatement();
      String sql = "UPDATE student SET name='王伍' WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,id);
      preparedStatement.execute();
      student.setName("王伍");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally {
      DbUtil.releaseSource(connection,statement);
    }

  }

  public void delete(String id) {
    Connection connection = null;
    Statement statement = null;

    try {
      connection = DbUtil.getConnection();
      statement = connection.createStatement();
      String sql = "DELETE FROM student WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,id);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }finally {
      DbUtil.releaseSource(connection,statement);
    }
  }
}
