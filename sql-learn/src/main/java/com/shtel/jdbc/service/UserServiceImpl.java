package com.shtel.jdbc.service;

import com.shtel.jdbc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private String username = "root";
    private String password = "root";

    private static String sql = "select * from user";
    private static String sql1 = "insert into user(name, age) values (?, ?)";

    private static String sql_1 = "create table student ( sno int(20) NOT NULL AUTO_INCREMENT, name varchar(255), age int(20))";
    private static String sql_2 = "select * from student where sno=110";
    private static String sql_3 = "update student set age=16 where sno=110";

    static {
        try {
            // 1 加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try {
            // 2 获得数据库链接
            conn = DriverManager.getConnection(url, username, password);
            // 3
            pstmt = conn.prepareStatement(sql);
            // 4
            rst = pstmt.executeQuery();
            while (rst.next()) {
                User user = new User();
                user.setId(rst.getLong("id"));
                user.setName(rst.getString("name"));
                user.setAge(rst.getInt("age"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rst);
        }

        return userList;
    }

    @Override
    public void insertBatch() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 2 获得数据库链接
            conn = DriverManager.getConnection(url, username, password);
            // 3
            pstmt = conn.prepareStatement(sql1);
            // 4
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 1000; j++) {
                    pstmt.setString(1, "wk" + i + "_" + j);
                    pstmt.setString(2, "189000000" + i + "_" + j);
                    pstmt.addBatch();
                }
            }

            pstmt.executeBatch();
            //pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    pstmt = null;
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    conn = null;
                    e.printStackTrace();
                }
            }
        }
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rst) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                rst = null;
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                pstmt = null;
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                conn = null;
                e.printStackTrace();
            }
        }
    }
}
