package com.kyw.lolgames.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname ConnectionPool
 * @Description 数据库连接池
 * @Date 2019/11/26 20:33
 * @Created by kyw
 */
public class ConnectionPool {

    private static String URL;
    private static String username;
    private static String password;
    private static boolean autoCommit;

    /**配置**/
    private static Properties properties;
    /**保存连接的map**/
    private final static Map<Integer, Connection> CONNECTION_MAP_POOL = new ConcurrentHashMap();
    /**连接池的连接索引**/
    private final static Queue<Integer> CONNECTION_KEY_POOL = new ConcurrentLinkedQueue();
    /**连接池初始连接数量**/
    private static int POOL_INIT_NUM;
    /**连接池最大连接数量**/
    private static int POOL_MAX_NUM;
    /**已创建连接数量**/
    private static AtomicInteger POOL_CREATE_NUM = new AtomicInteger(0);
    /**查询超时时间-秒**/
    private static int QUERY_TIMEOUT_SECONDS;

    static {
        try{
            // 1.通过当前类获取类加载器
            ClassLoader classLoader = ConnectionPool.class.getClassLoader();
            // 2.通过类加载器的方法获得一个输入流
            InputStream in = classLoader.getResourceAsStream("jdbc.properties");
            // 3.创建一个properties对象(集合)
            properties = new Properties();
            // 4.加载输入流
            properties.load(in);
            // 5.获取相关参数的值

            POOL_INIT_NUM = Integer.parseInt(properties.getProperty("initConnectionNum"));
            POOL_MAX_NUM = Integer.parseInt(properties.getProperty("maxConnectionNum"));
            QUERY_TIMEOUT_SECONDS = Integer.parseInt(properties.getProperty("maxQueryTime"));
            for (int i = 0; i < POOL_MAX_NUM ; i++) {
                POOL_CREATE_NUM.incrementAndGet();
                Connection conn = createConnection();
                CONNECTION_MAP_POOL.put(conn.hashCode(), conn);
                CONNECTION_KEY_POOL.add(conn.hashCode());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("driver"));
            URL = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            autoCommit = Boolean.valueOf(properties.getProperty("autoCommit"));
            connection = DriverManager.getConnection(URL, username, password);
            connection.setAutoCommit(autoCommit);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("建立数据库连接失败 , " + e.getMessage());
        }
        return connection;
    }


    //获取连接
    public static Connection getConnection() {
        Connection connection = null;
        Integer connkey = CONNECTION_KEY_POOL.poll();
        if (connkey == null) {
            if (POOL_CREATE_NUM.intValue() < POOL_MAX_NUM) {
                int poolNum = POOL_CREATE_NUM.incrementAndGet();
                if (poolNum <= POOL_MAX_NUM) {
                    connection = createConnection();
                } else {
                    POOL_CREATE_NUM.decrementAndGet();
                }
            }
        } else {
            connection = CONNECTION_MAP_POOL.get(connkey);
        }

        // 如果没有获取连接
        if (connection == null) {
            throw new RuntimeException("连接池已用完");
        }
        return connection;
    }

    public static void returnConnection(Connection conn) {
        if (conn != null) {
            try {
                if (conn.isClosed()) {
                    CONNECTION_MAP_POOL.remove(conn.hashCode());
                    POOL_CREATE_NUM.decrementAndGet();
                } else {
                    CONNECTION_KEY_POOL.add(conn.hashCode());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getQueryTimeoutSecond() {
        return QUERY_TIMEOUT_SECONDS;
    }
}
