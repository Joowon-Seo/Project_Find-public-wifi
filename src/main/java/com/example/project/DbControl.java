package com.example.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbControl {
    public static Connection c = null;
    public static Statement stat = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static String result;

    public void connect() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            c = DriverManager.getConnection("jdbc:sqlite:C:\\zerobase\\Project Mission_1\\project\\test.db");
//            if (c!=null)
//                System.out.println("Connected to db.");
        } catch (SQLException ex) {
            System.err.println("Couldn't connect." + ex.getMessage());
        }
    }

    /*
        테이플 생성(test용)
     */
    public void createTable() {


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\zerobase\\Project Mission_1\\project\\test.db");
            System.out.println("Opened database successfully");

            stat = c.createStatement();
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
                    "                        xCoordinates                   double  NULL, " +
                    "                        yCoordinates                   double  NULL, " +
                    "                        operationDate                  CHAR(20) NULL " +
                    ");";
            stat.executeUpdate(sql);
            stat.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void createHistoryTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\zerobase\\Project Mission_1\\project\\test.db");
            System.out.println("Opened database successfully");

            stat = c.createStatement();
            String sql = "CREATE TABLE HISTORY ( " +
                    "    HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    xCoordinates    var(20) NULL, " +
                    "    yCoordinates    var(20) NULL, " +
                    "    CHECK_DATE      INTEGER null " +
                    ");";
            stat.executeUpdate(sql);
            stat.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void insert(ArrayList<Wifi> wifiArrayList) {


        connect();


        try {
            c.setAutoCommit(false);
            String sql = "";
            for (int i = 0; i < wifiArrayList.size(); i++) {
                Wifi wifi = wifiArrayList.get(i);

                sql += "INSERT INTO WIFI (managementNumber, boroughs, wifiName, roadNameAddress, " +
                        "                  detailedAddress, installationLocationFloor, installationType, installationOrgan, " +
                        "                  serviceClassification, communicationsNetwork, installationYear, indoorAndOutdoorClassification, " +
                        "                  wifiConnectionEnvironment, xCoordinates, yCoordinates, operationDate) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = c.prepareStatement(sql);

                preparedStatement.setString(1, wifi.getManagementNumber());
                preparedStatement.setString(2, wifi.getBoroughs());
                preparedStatement.setString(3, wifi.getWifiName());
                preparedStatement.setString(4, wifi.getRoadNameAddress());
                preparedStatement.setString(5, wifi.getDetailedAddress());
                preparedStatement.setString(6, wifi.getInstallationLocationFloor());
                preparedStatement.setString(7, wifi.getInstallationType());
                preparedStatement.setString(8, wifi.getInstallationOrgan());
                preparedStatement.setString(9, wifi.getServiceClassification());
                preparedStatement.setString(10, wifi.getCommunicationsNetwork());
                preparedStatement.setString(11, wifi.getInstallationYear());
                preparedStatement.setString(12, wifi.getIndoorAndOutdoorClassification());
                preparedStatement.setString(13, wifi.getWifiConnectionEnvironment());
                preparedStatement.setDouble(14, wifi.getyCoordinates());
                preparedStatement.setDouble(15, wifi.getxCoordinates());
                preparedStatement.setString(16, wifi.getOperationDate());

            }

            int affected = preparedStatement.executeUpdate();


            if (affected > 0) {
//                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 삽입 실패 ");
            }

            c.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

            // 위에서 오류가 나서 완벽한 close를 못 할 수도 있기 떄문에 finally 에서 처리해야 한다.

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (c != null && !c.isClosed()) {
                    c.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertHistory(String nowX, String nowY) {
        connect();
//
//        nowX = Math.round(7);
//        nowY = Math.round(7);

        try {

            String sql = "INSERT INTO HISTORY( " +
                    "                    xCoordinates, yCoordinates, CHECK_DATE " +
                    ")\n" +
                    "values (?, ?, DATETIME());";
            preparedStatement = c.prepareStatement(sql);

            preparedStatement.setString(1, nowX);
            preparedStatement.setString(2, nowY);
            System.out.println(nowX + " " + nowY);


            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
//                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 위에서 오류가 나서 완벽한 close를 못 할 수도 있기 떄문에 finally 에서 처리해야 한다.

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (c != null && !c.isClosed()) {
                    c.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public String selectCount() {
        connect();

        try {

            String sql = "SELECT COUNT(*) FROM WIFI;";

            preparedStatement = c.prepareStatement(sql);


            rs = preparedStatement.executeQuery();


            result = rs.getString("COUNT(*)");

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<Wifi> getDistance(double nowX, double nowY) {


        connect();

        List<Wifi> wifiList = new ArrayList<>();

        rs = null;

        try {

            String sql = "SELECT  round(6371 * acos( cos( radians(xCoordinates) ) " +
                    "                               * cos( radians( ? ) ) " +
                    "                               * cos( radians( ? ) - radians(yCoordinates) ) " +
                    "                               + sin( radians(xCoordinates) ) * sin( radians( ? ) ) ), 4) as distance,* " +
                    "from WIFI " +
                    "order by distance " +
                    "limit 20";
            preparedStatement = c.prepareStatement(sql);

            preparedStatement.setDouble(1, nowX);
            preparedStatement.setDouble(2, nowY);
            preparedStatement.setDouble(3, nowX);


            rs = preparedStatement.executeQuery();


            while (rs.next()) {

                Wifi wifi = new Wifi();

                wifi.setDistance(rs.getFloat("distance"));
                wifi.setManagementNumber(rs.getString("managementNumber"));
                wifi.setBoroughs(rs.getString("boroughs"));
                wifi.setWifiName(rs.getString("wifiName"));
                wifi.setRoadNameAddress(rs.getString("roadNameAddress"));
                wifi.setDetailedAddress(rs.getString("detailedAddress"));
                wifi.setInstallationLocationFloor(rs.getString("installationLocationFloor"));
                wifi.setInstallationType(rs.getString("installationType"));
                wifi.setInstallationOrgan(rs.getString("installationOrgan"));
                wifi.setServiceClassification(rs.getString("serviceClassification"));
                wifi.setCommunicationsNetwork(rs.getString("communicationsNetwork"));
                wifi.setInstallationYear(rs.getString("installationYear"));
                wifi.setIndoorAndOutdoorClassification(rs.getString("indoorAndOutdoorClassification"));
                wifi.setWifiConnectionEnvironment(rs.getString("wifiConnectionEnvironment"));
                wifi.setxCoordinates(rs.getDouble("xCoordinates"));
                wifi.setyCoordinates(rs.getDouble("yCoordinates"));
                wifi.setOperationDate(rs.getString("operationDate"));

                wifiList.add(wifi);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 위에서 오류가 나서 완벽한 close를 못 할 수도 있기 떄문에 finally 에서 처리해야 한다.

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (c != null && !c.isClosed()) {
                    c.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return wifiList;

    }

    public List<History> getHistoryList() {
        connect();

        List<History> historyList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM HISTORY;";

            preparedStatement = c.prepareStatement(sql);


            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                History history = new History();

                history.setHistory_id(rs.getInt("HISTORY_ID"));
                history.setxCoordinates(rs.getString("xCoordinates"));
                history.setyCoordinates(rs.getString("yCoordinates"));
                history.setCheck_date(rs.getString("CHECK_DATE"));


                historyList.add(history);

            }


        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return historyList;


    }

    public void deleteHistory(int delete_id) {
        connect();

        try {

            String sql = "delete " +
                    "from HISTORY " +
                    "where HISTORY_ID = ?";

            preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, delete_id);

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
//                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 위에서 오류가 나서 완벽한 close를 못 할 수도 있기 떄문에 finally 에서 처리해야 한다.

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (c != null && !c.isClosed()) {
                    c.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

