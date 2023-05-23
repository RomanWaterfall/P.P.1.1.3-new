package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Roman", "Popov", (byte) 24);
        userService.saveUser("Katya", "Mirova", (byte) 18);
        userService.saveUser("Misha", "Anronov", (byte) 28);
        userService.saveUser("Den", "Smernov", (byte) 46);
        userService.removeUserById(1);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
