package architecturefortest;

import java.util.Set;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;

import architecturefortest.application.ShopperApplicationContext;
import architecturefortest.application.ShopperDto;

@SpringBootApplication
@Configuration
public class ApplicationContext {

	@Component
    public static class ConfigRestResources extends ResourceConfig {
        public ConfigRestResources() {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
            scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
            Set<BeanDefinition> beans = scanner.findCandidateComponents("architecturefortest");
            for (BeanDefinition beanDefinition : beans) {
                try {
                    this.registerClasses(Class.forName(beanDefinition.getBeanClassName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
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
