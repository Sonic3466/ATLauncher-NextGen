package com.atlauncher.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface UpdatePacks{
    /**
     * Used to update the packs UI after a method call is finished.
     * Example:
     *  @UpdatePacks
     *  public void helloWorld(){
     *      System.out.println("Hello World");
     *  }
     *
     * AspectJ injects this bytecode to the tail end of the method:
     *  ATLauncher.EVENT_BUS.post(new UpdatePacksEvent());
     */
}