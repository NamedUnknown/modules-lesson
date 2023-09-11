package shopping.dependencies;

import sun.misc.Unsafe;

import java.time.LocalDate;
import java.util.List;

public class Water {
    private List<String> provider;
    private LocalDate availableUntil;

    public Water(List<String> provider, LocalDate availableUntil) {
        this.provider = provider;
        this.availableUntil = availableUntil;
    }

    public void unsafeMethod() {
        Unsafe unsafe = Unsafe.getUnsafe();
    }

    // COMPILE: javac -d jdeps/compiled jdeps/src/shopping/dependencies/*.java

}
