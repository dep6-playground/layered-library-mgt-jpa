package lk.ijse.dep.web.lms.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class JPAUtil {
    private static Logger logger = LoggerFactory.getLogger(JPAUtil.class);
    private static EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        Properties properties = new Properties();
        try {
            properties.load(JPAUtil.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            logger.error("Failed to load database properties", e);
            throw new RuntimeException(e);
        }
        properties.put(Environment.DATASOURCE, getDataSource());
        return Persistence.createEntityManagerFactory("dep-6", properties);
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static DataSource getDataSource() {
        Properties properties = new Properties();
        BasicDataSource bds = new BasicDataSource();

        try {
            properties.load(JPAUtil.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            logger.error("Failed to load database properties", e);
            throw new RuntimeException("Failed to load database properties", e);
        }
        bds.setInitialSize(Integer.parseInt(properties.getProperty("dbcp.initial_size")));
        bds.setMaxTotal(Integer.parseInt(properties.getProperty("dbcp.max_total")));
        bds.setUsername(properties.getProperty("dbcp.jdbc.user"));
        bds.setPassword(properties.getProperty("dbcp.jdbc.password"));
        bds.setDriverClassName(properties.getProperty("dbcp.jdbc.driver"));
        bds.setUrl(properties.getProperty("dbcp.jdbc.url"));
        return bds;
    }
}
