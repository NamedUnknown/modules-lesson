package shopping.visitors.agency;

import shopping.visitors.api.*;

public class VisitorServiceImpl implements VisitorService {

    @Override
    public String count() {
        return "The visitor count is 100";
    }

    @Override
    public int averageAge() {
        return 45;
    }

    @Override
    public Visitor getByName(String name) {
        return new Visitor(name, 40);
    }

}
