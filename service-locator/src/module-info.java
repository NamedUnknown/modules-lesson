module shopping.visitors.audit {

    exports shopping.visitors.audit;

    // Both "requires" and "uses" are NEEDED fot this to compile
    requires shopping.visitors.api;
    // for the lookup
    uses shopping.visitors.api.VisitorService;

}