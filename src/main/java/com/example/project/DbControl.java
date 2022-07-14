package com.example.project;
import java.sql.*;

public class DbControl {

    public void createTable() {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE WIFI (" +
                    "                        managementNumber               CHAR(20) PRIMARY KEY NOT NULL, " +
                    "                        boroughs                       CHAR(20) NULL, " +
                    "                        wifiName                       CHAR(20) NULL, " +
                    "                        roadNameAddress                CHAR(20) NULL, " +
                    "                        detailedAddress                CHAR(20) NULL, " +
                    "                        installationLocationFloor      CHAR(20) NULL, " +
                    "                        installationType               CHAR(20) NULL, " +
                    "                        installationOrgan              CHAR(20) NULL, " +
                    "                        serviceClassification          CHAR(20) NULL, " +
                    "                        communicationsNetwork          CHAR(20) NULL, " +
                    "                        installationYear               CHAR(20) NULL, " +
                    "                        indoorAndOutdoorClassification CHAR(20) NULL, " +
                    "                        wifiConnectionEnvironment      CHAR(20) NULL, " +
                    "                        xCoordinates                   CHAR(20) NULL, " +
                    "                        yCoordinates                   CHAR(20) NULL, " +
                    "                        operationDate                  CHAR(20) NULL " +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
