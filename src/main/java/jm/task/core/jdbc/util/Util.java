package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class Util {

    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "01020129Sv";
    private static SessionFactory sessionFactory;
    private static Properties properties = new Properties();


    static {
        configureProperties();
    }

    static {
        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    private static void configureProperties() {
        try {
            properties.load(Util.class.getClassLoader().getResourceAsStream("111.properties"));
        } catch (IOException ignore) {
            throw new RuntimeException(ignore);
        }

    }
}


// реализуйте настройку соеденения с БД
