package net.yasfu.acopvp.mode;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ModeData {

    String name();
    String version();
    String author();

}
