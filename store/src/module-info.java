// Open all packages for this module
// open module shopping.employee.store {
module shopping.employee.store {
    // the "to" keyword restricts access so that only the specified package has access
    // No other modules would be allowed to access that package
    // The same access rules apply to modules as they do to classes
    opens shopping.employee.store.items to shopping.staff;
    exports shopping.employee.store.media;

    /**
     * Reflection
     * Reflection is the ability to inspect and possibly modify a module at runtime
     *
     * - enable reflection for this module
     * - Any module using this module (shopping.employee.store) will be able to use reflection
     * - opens -> open the package for reflection
     * - open -> open the whole module for reflection
     * - if the "open module <module-name>" is used, then this will not compile
     */
    opens shopping.employee.store.schedule;

    requires transitive shopping.employee.system;
    //  requires shopping.employee.cleaning; // -> because system specifies cleaning
}