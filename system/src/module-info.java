module shopping.employee.system {
    // Export the package
    exports shopping.employee.system.support;

    // Specify that NEED the package that we previously created
    // transitive: any module that requires this module will also require the module that this module requires
    requires transitive shopping.employee.cleaning;
}