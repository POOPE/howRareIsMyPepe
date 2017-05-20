package aiss.api;

import aiss.api.resources.GalleryResource;
import aiss.api.resources.RarityResource;

import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class PepeApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    // Loads all resources that are implemented in the application
    // so that they can be found by RESTEasy.
    public PepeApplication() {
        singletons.add(RarityResource.getInstance());
        singletons.add(GalleryResource.getInstance());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
