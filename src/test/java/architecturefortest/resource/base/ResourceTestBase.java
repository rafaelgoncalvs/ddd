package architecturefortest.resource.base;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import java.util.List;

public abstract class ResourceTestBase extends JerseyTest {

    @Override
    public ResourceConfig configure() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourcesParaSeremRegistrados().forEach(objeto -> resourceConfig.register(objeto));
        return resourceConfig;
    }

    protected abstract List<Object> resourcesParaSeremRegistrados();

}
