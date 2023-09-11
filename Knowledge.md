# Modules
- Packages are grouped into Java archive (JAR) files.
- A JAR is a ZIP file with some extra information, and the extension is .jar.
- To use packages - **make sure you have compatible versions of all the relevant JARs available at runtime.**
- **JAR hell** - complex chain of dependencies and minimum versions
- **Java Platform Module System (JPMS)**
    - A format for module JAR files
    - Partitioning of the JDK into modules
    - Additional command-line options for Java tools
- Main purpose of a modules - provide groups of related packages that offer developers a particular set of functionality.

## Exploring a Module
- **Module** - a group of one or more packages + *module-info.java* (contents: *module declaration*)
- The *module-info.java* file is reuired to be in all **modules**

## Benefits of Modules
- **Better access control** - packages that are only accessible to other packages in  the module.
- **Clearer dependency management** - modules specify what they rely on, and Java will inform you on when something is missing
- **Custom Java builds** - create a Java runtime that has only the parts of the JDK that your program needs
- **Improved security** - don’t have to worry about vulnerabilities discovered in a part you don’t use (custom builds)
- **Improved performance** - *improved startup time* and a *lower memory requirement*.
- **Unique package enforcement** - Java can ensure that each package comes from only one module

## Creating and Running a Modular Program
### Creating the Files
**Rules for creating modules**
- Must be in the root directory of your module
- The module declaration MUST use the keyword - module
- often includes periods (.) in its name
- Are not allowed to include dashes (-) in its name (same as Regular class and package names)
```
module <package-name> {  
  
}
```

**Compile the module:**
```
javac --module-path | -p <other-modules-location> \
	-d | -dir <target-dir> \
	<root-of-src-files>/*.java <root-of-module-info>/module-info.java
```

## Updating Our Example for Multiple Modules
- The *exports* directive - used to indicate that **a module intends for those packages to be used by Java code outside the module.**

## Diving into the Module Declaration
- **exports**, **requires**, **opens**, **provides**, **uses**: directives can appear in any order in the module declaration

## Opening a Package
- Java allows callers to inspect and call code at runtime with a technique called *reflection*.
    - powerful approach that allows calling code that might not be available at compile time
- The **opens** directive is used to **enable reflection of a package within a module**.

## Creating a Service
- A *service* is composed of:
    - an interface
    - any classes the interface references
    - a way of looking up implementations of the interface.
- **The implementations are not part of the service**

## Creating a Service Locator
- A *service locator* can **find any classes that implement a service provider interface**.

## Invoking from a Consumer
- A *consumer* (or client) refers to **a module that obtains and uses a service**.

## Adding a Service Provider
- A *service provider* is the implementation of a service provider interface.
- In software development, the concept of **separating different components into stand-alone pieces** is referred to as *loose coupling*.

# Discovering Modules
## Identifying Built-in Modules
- The most important module to know is **java.base**.
    - Automatically added as a dependency to all modules.
    - contains most of the packages you have been learning
    - Don’t have to use the *requires* directive
    - is available to all modular applications
    - module-info.java file **will still compile if you explicitly require java.base**

**Remember:**
- Module names that begin with *java* are for APIs you are likely to use
- *jdk* are for APIs that are specific to the JDK

## Describing a Module
**Descibe the module:**
```java
java -p mods -d <module-name>
java -p mods --describe-module <module-name>
```

- The *qualified exports* is the full name of the package we are exporting to a specific module.
- The *contains* means that there is a package in the module that is **not exported at all**

## Listing Available Modules
```java
java --list-modules
```

## Showing Module Resolution
```java
java --show-module-resolution
```
- spits out a lot of output when the program starts up.
- runs the program.

## Describing with jar
```java
jar -f <path-to-jar>.jar -d  
jar --file <path-to-jar>.jar --describe-module
```

## Learning about Dependencies with jdeps
- Gives you information about dependencies within a module.
- **looks at the code in addition to the module declaration**.
```java
jdeps <path-to-jar>.jar

// Summary mode
jdeps -s | -summary <path-to-jar>.jar
```

## Using the --jdk-internals Flag
- Provides details about unsupported APIs.
- **Provides details on what you should do about unsafe and unsupported APIs**
```java
jdeps --jdk-internals | -jdkinternals <path-to-jar>.jar
```

## Using Module Files with jmod
- **JMOD** file is a Java module file
- Oracle recommends using JAR files for most modules
- JMOD files are **recommended only when you have native libraries or something that can’t go inside a JAR file**

## Creating Java Runtimes with jlink
**Create a smaller distribution:**
```java
jlink --module-path | -p <path-to-modules> --add-modules <module-name-list> --output <name-of-folder>
```

# Comparing Types of Modules
All of the previously used mduls are called **named modules**.
There are also:
- Automatic modules
- Unnamed modules

## Named modules
- A *named module* is one containing a **module-info.java file**.
- **Unless otherwise specified, a module is a named module.**
- Named modules appear **on the module path** rather than the classpath

## Automatic Modules
- An automatic module **appears on the module path** but **does NOT contain a module-info.java file**.
- Java *automatically* determines the module name.
- If **META-INF/MANIFEST.MF** specifies *Automatic-Module-Name*, use that for the module name
- If NOT - The name is based on the filename of the JAR file.
    - Without the **.jar** extension
    - Without the version
    - Convert any special char in the name  (like -) to .
    - **Special chars:** Any characters other than letters and numbers.
    - Any dots that don't belong are removed

**Remember:** A JAR file contains a special text file called META-INF/MANIFEST.MF that contains information about the JAR.

## Unnamed Modules
- An *unnamed module* appears on the classpath.
- A JAR
- Does not *usually* contain a module-info.java file.
- If it contains a module-info.java file -> ignore since it is on the class path
- They do not export any packages to named or automatic modules.
- Can read from any JARs on the classpath or module path.

**Remember:**
- Code on the classpath CAN access the module path.
- Code on the module path is UNABLE TO READ from the classpath.

# Migrating an Application
## Determining the Order
- How the packages and libraries in the existing application are structured?
- *Projects that don't have any dependencies* are the *bottom* (top-down and bottom-up)
- *Projects that do have dependencies* are at the *top*.

## Exploring a Bottom-Up Migration Strategy
*The easiest approach* to migration is a *bottom-up migration*.
1. Pick the lowest-level project that has not yet been migrated.
2. Add a module-info.java file to that project. (and everyhing that comes with ...)
3. Move **from classpath to the module path**.
4. Any projects that have not yet been migrated stay as unnamed modules on the classpath.
5. Repeat with the next-lowest-level project.

## Exploring a Top-Down Migration Strategy
Most useful when you don’t have control of every JAR file.
1. Place all projects on the module path.
2. Pick the highest-level project that has not yet been migrated.
3. Add a module-info.java file to that project.
4. Repeat with the next-highest-level project

## Splitting a Big Project into Modules
1. Break them into logical groupings
2. Draw the dependencies between them

Java Platform  Module System does not allow for *cyclic dependencies*.
A cyclic dependency, (*circular dependency*) is when two things directly or indirectly depend on each other.
- A common technique to fix *cyclic dependencies* is to introduce another module. (contains the code that the other two modules share)