package com.atlauncher.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface UpdateI18N{
    /**
     * Used to update the localization after a method call is finished.
     * Example:
     *  @UpdateI18N
     *  public void helloWorld(){
     *      System.out.println("Hello World");
     *  }
     *
     * AspectJ injects this bytecode to the tail end of the method:
     *  ATLauncher.EVENT_BUS.post(new UpdateI18NEvent());
     */
}