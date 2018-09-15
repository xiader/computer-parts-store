package com.gmail.sasha.config;


import com.gmail.sasha.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

import static com.gmail.sasha.config.ConfigurationManager.*;

public class HibernateUtil {
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> properties = new HashMap<>();
                properties.put(Environment.DRIVER,
                        ConfigurationManager.getInstance().getProperty(DATABASE_DRIVER_NAME));
                properties.put(Environment.URL,
                        ConfigurationManager.getInstance().getProperty(DATABASE_URL));
                properties.put(Environment.USER,
                        ConfigurationManager.getInstance().getProperty(DATABASE_USERNAME));
                properties.put(Environment.PASS,
                        ConfigurationManager.getInstance().getProperty(DATABASE_PWD));
                properties.put(Environment.HBM2DDL_AUTO,
                        ConfigurationManager.getInstance().getProperty(Environment.HBM2DDL_AUTO));
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
                        ConfigurationManager.getInstance().getProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS));
                properties.put(Environment.DEFAULT_SCHEMA,
                        ConfigurationManager.getInstance().getProperty(Environment.DEFAULT_SCHEMA));
                properties.put(Environment.SHOW_SQL,
                        ConfigurationManager.getInstance().getProperty(Environment.SHOW_SQL));

                standardServiceRegistryBuilder.applySettings(properties);
                standardServiceRegistry = standardServiceRegistryBuilder.build();

                logger.info("Hibernate registry builder created");
                MetadataSources metadataSource = new MetadataSources(standardServiceRegistry);
                metadataSource.addAnnotatedClass(Comment.class);
                metadataSource.addAnnotatedClass(Item.class);
                metadataSource.addAnnotatedClass(Order.class);
                metadataSource.addAnnotatedClass(Permission.class);
                metadataSource.addAnnotatedClass(Role.class);
                metadataSource.addAnnotatedClass(User.class);
                metadataSource.addAnnotatedClass(Profile.class);
                metadataSource.addAnnotatedClass(Audit.class);
                metadataSource.addAnnotatedClass(News.class);

                Metadata metadata = metadataSource.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();


            } catch (Exception e) {
                logger.error("SessionFactory creation failed");
                logger.error(e.getMessage(), e);
                if (sessionFactory != null) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
