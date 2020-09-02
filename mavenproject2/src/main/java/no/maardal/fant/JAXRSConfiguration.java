package no.maardal.fant;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features
        classes.add(MultiPartFeature.class);
        return classes;
    }
}
