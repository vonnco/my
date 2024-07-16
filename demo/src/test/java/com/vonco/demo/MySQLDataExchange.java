package com.vonco.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Timestamp;

public class MySQLDataExchange {

  private JdbcTemplate sourceJdbcTemplate;
  private JdbcTemplate targetJdbcTemplate;

  public MySQLDataExchange(DataSource sourceDataSource, DataSource targetDataSource) {
    this.sourceJdbcTemplate = new JdbcTemplate(sourceDataSource);
    this.targetJdbcTemplate = new JdbcTemplate(targetDataSource);
  }

  public void exchange(String sourceTable, String targetTable) {
    String selectSql = "SELECT * FROM " + sourceTable;
    String insertSql = "INSERT INTO " + targetTable + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    sourceJdbcTemplate.query(selectSql, rs -> {
      String id = rs.getString("id");
      String coding = rs.getString("coding");
      String name = rs.getString("name");
      String idCard = rs.getString("id_card");
      String phone = rs.getString("phone");
      int sex = rs.getInt("sex");
      int age = rs.getInt("age");
      String company = rs.getString("company");
      String fullAddress = rs.getString("full_address");
      String createdBy = rs.getString("created_by");
      String updatedBy = rs.getString("updated_by");
      Timestamp createdTime = rs.getTimestamp("created_time");
      Timestamp updatedTime = rs.getTimestamp("updated_time");

      targetJdbcTemplate.update(insertSql, id, coding, name, idCard, phone, sex, age, company, fullAddress, createdBy, updatedBy, createdTime, updatedTime);
    });
  }

  public static void main(String[] args) {
    String sourceUrl = "jdbc:mysql://192.168.0.251:3306/enforce_law";
    String sourceUsername = "root";
    String sourcePassword = "mysql#2020";
    String targetUrl = "jdbc:mysql://localhost:3306/test";
    String targetUsername = "root";
    String targetPassword = "123456";
    DataSource sourceDataSource = new DriverManagerDataSource(sourceUrl, sourceUsername, sourcePassword);
    DataSource targetDataSource = new DriverManagerDataSource(targetUrl, targetUsername, targetPassword);
    MySQLDataExchange exchange = new MySQLDataExchange(sourceDataSource,targetDataSource);
    exchange.exchange("eo_resident", "eo_resident");
  }
}