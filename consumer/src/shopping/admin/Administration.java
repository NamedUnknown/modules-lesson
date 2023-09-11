package shopping.admin;

import shopping.visitors.api.VisitorService;
import shopping.visitors.audit.VisitorServiceFinder;

import java.util.List;

public class Administration {

    public static void main(String[] args) {
        VisitorService service = VisitorServiceFinder.findSingleVisitorService();
        System.out.println("Single service: " + service);

        List<VisitorService> services = VisitorServiceFinder.findAllVisitorServices();
        System.out.println("All services: " + services.size());

        List<VisitorService> servicesStream = VisitorServiceFinder.findAllVisitorServicesStream();
        System.out.println("All services stream: " + servicesStream.size());
    }

}
