module shopping.staff {

//    requires shopping.employee.system; // -> because store specifies system
//    requires shopping.employee.cleaning; // -> because store specifies system -> because system specifies cleaning
//    Java DOES NOT ALLOW you to repeat the same module in a requires clause
    //requires shopping.employee.store; // DOES NOT COMPILE
    requires transitive shopping.employee.store;

}