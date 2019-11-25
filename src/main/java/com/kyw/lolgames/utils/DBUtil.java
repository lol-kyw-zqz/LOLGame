package com.kyw.lolgames.utils;

import java.sql.*;
import java.util.ResourceBundle;


/**
 * @author : kangyw
 * @date : 上午 11:00 2019/11/25
 */
public class DBUtil {
    /**数据库地址*/
    private static  String url = null;
    private static  String driver = null;
    private static  String username = null;
    private static  String password = null;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    static {
        try {
            url = ResourceBundle.getBundle("jdbc").getString("url").trim();
            driver = ResourceBundle.getBundle("jdbc").getString("driver").trim();
            username = ResourceBundle.getBundle("jdbc").getString("username").trim();
            password = ResourceBundle.getBundle("jdbc").getString("password").trim();
            //1.获取驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private DBUtil(String sql){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 释放资源 方法的重载
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        String sql = "select * from heros";
        DBUtil DBUtil = new DBUtil(sql);

        String name,skillDescription;
        float hp,mp,ad,skillsVal,skillCost,armor,magicResistance;
        double critRate;

        try {

            ResultSet result =  DBUtil.preparedStatement.executeQuery();
            while (result.next()){
                name = result.getString(2);
                hp = result.getFloat(3);
                mp = result.getFloat(4);
                ad = result.getFloat(5);
                critRate = result.getDouble(6);
                skillDescription = result.getString(7);
                skillsVal = result.getFloat(8);
                skillCost = result.getFloat(9);
                armor = result.getFloat(10);
                magicResistance = result.getFloat(11);

                System.out.println(name+" "+hp+" "+mp+" "+ad+" "+critRate+" "+skillDescription+" "+skillsVal+" "+
                        skillCost+" "+armor+" "+magicResistance);
            }
            result.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
