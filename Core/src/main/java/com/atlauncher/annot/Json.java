package com.atlauncher.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE
})
public @interface Json{
    /**
     * Used to mark which objects are de/serialized by GSON
     */
}
