package shopping.employee.cleaning;

public class Task {

    public static void main(String[] args) {
        System.out.println("Cleaning Task");
    }

}

/**
 * Compile the module using the following command:
 * javac --module-path mods -d cleaning cleaning/src/shopping/employee/cleaning/*.java cleaning/src/module-info.java
 * -d flag specifies the directory in which you put the files in
 * --module-path || -p flag: specifies the directory in which the modules are located
 */

/**
 * --module-path -> -p
 * --module -> -m
 */

/**
 * Running a module:
 * java --module-path <location-of-modules> -m <module-name>/<package-name>.<class-name>
 *
 * Location of modules -> cleaning || mods
 * Module name -> shopping.employee.cleaning
 * Package name -> shopping.employee.cleaning
 * Class name -> Task
 */

/**
 * Packaging a module:
 * jar -cvf <location>.jar -C <dir-to-package> .
 *
 * Location -> mods/cleaning
 * Dir to package -> cleaning/ . -> all files in the directory
 *
 * -c -> create
 * -v -> verbose
 * -f -> file
 * -C -> change to directory
 */
