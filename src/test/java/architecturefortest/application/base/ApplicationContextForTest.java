package architecturefortest.application.base;

import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "architecturefortest")
@PropertySource("application.test.properties")
@AutoConfigureDataJpa
public class ApplicationContextForTest {
}
