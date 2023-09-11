package shopping.visitors.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import shopping.visitors.api.*;

/**
 * Service locator
 *
 * can find any classes that implement a service provider interface.
 * - Java provides a ServiceLoader class to help with this
 * - It will return any implementation services it can find.
 */
public class VisitorServiceFinder {

    public static VisitorService findSingleVisitorService() {
        // The book suggests using the ServiceLoader.load() method, but it is EXPENSIVE.
        // Better to cache the results
        ServiceLoader<VisitorService> loader = ServiceLoader.load(VisitorService.class);
        for (VisitorService service : loader)
            return service;
        return null;
    }

    public static List<VisitorService> findAllVisitorServices() {
        List<VisitorService> services = new ArrayList<>();
        ServiceLoader<VisitorService> loader = ServiceLoader.load(VisitorService.class);
        for (VisitorService service : loader)
            services.add(service);
        return services;
    }

    public static List<VisitorService> findAllVisitorServicesStream() {
        return ServiceLoader.load(VisitorService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

}
