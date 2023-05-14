package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
      //  String USERNAME = "postgres";
     //   String URL = "jdbc:postgresql://localhost:5432/postgres";
        UserService table = new UserServiceImpl();
        table.createUsersTable();
        table.saveUser("Roman","Popov", (byte) 24);
        table.saveUser("Katya","Mirova",(byte) 18);
        table.saveUser("Misha","Anronov",(byte) 28);
        table.saveUser("Den","Smernov",(byte) 46);
      table.removeUserById(1);
      for(User user : table.getAllUsers()){
          System.out.println(user);
      }
      table.cleanUsersTable();
      table.dropUsersTable();

    }
}
