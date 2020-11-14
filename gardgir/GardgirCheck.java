package gardgir;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GardgirCheck {
    public int importance() default 1;
    public Class tester() default GardgirDefaultCallable.class;
}
