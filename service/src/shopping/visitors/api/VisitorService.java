package shopping.visitors.api;

/**
 * Service provider interface
 *
 * it specifies what behavior our service will have.
 */
public interface VisitorService {

    String count();
    int averageAge();
    Visitor getByName(String name);

}
