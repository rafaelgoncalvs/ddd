package architecturefortest;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

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
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
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
