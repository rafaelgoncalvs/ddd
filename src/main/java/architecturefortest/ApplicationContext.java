package architecturefortest;

import java.util.Properties;

import javax.inject.Named;
import javax.sql.DataSource;

import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import architecturefortest.application.ShopperApplicationContext;
import architecturefortest.application.ShopperDto;
import architecturefortest.resource.CartResource;

@SpringBootApplication
@Configuration
public class ApplicationContext {

    @Named
    public static class ConfigRestResources extends ResourceConfig {
        public ConfigRestResources() {
            this.register(CartResource.class);
        }
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "architecturefortest.*" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:architecturefortest");
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                setProperty("hibernate.show_sql", "false");
                setProperty("hibernate.use_sql_comments", "false");
                setProperty("hibernate.format_sql", "false");
                setProperty("hibernate.hbm2ddl.auto", "update");
            }
        };
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ApplicationContext.class, args);
        ShopperApplicationContext shopperApplicationContext = app.getBean(ShopperApplicationContext.class);
        addFirstShopper(shopperApplicationContext);
    }

    private static void addFirstShopper(ShopperApplicationContext shopperApplicationContext) {
        ShopperDto shopperDto = new ShopperDto();
        shopperDto.setName("Jon Malcon");
        shopperApplicationContext.add(shopperDto);
    }

}
