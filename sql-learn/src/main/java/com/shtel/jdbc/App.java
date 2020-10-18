package com.shtel.jdbc;

import com.shtel.jdbc.entity.User;
import com.shtel.jdbc.service.UserService;
import com.shtel.jdbc.service.UserServiceImpl;

import java.util.List;


public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        List<User> all = userService.findAll();

        for (User user : all) {
            System.out.println(user);
        }
      /*  userService.insertBatch();*/
    }
}
