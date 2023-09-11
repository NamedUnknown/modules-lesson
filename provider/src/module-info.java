module shopping.visitors.agency {

    // Import the service provider interface
    requires shopping.visitors.api;

    // This specifies that this provides the implementation of the service
    provides shopping.visitors.api.VisitorService with shopping.visitors.agency.VisitorServiceImpl;

}

/**
 * 1. Compile => javac -p mods -d provider/compiled provider/src/module-info.java provider/src/shopping/visitors/agency/*java
 * 2. Package => jar -cvf mods/shoppng.visiors.agency.jar -C provider/compiled .
 * 3. Run => java -p mods -m shopping.admin/shopping.admin.Administration
 * No need to recompile anything
 * The service locator was able to observe that there was now a service provider implementation available
 * LOOSELY COUPLED code
 */