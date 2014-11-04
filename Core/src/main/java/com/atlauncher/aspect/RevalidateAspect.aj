package com.atlauncher.aspect;

import com.atlauncher.ATLauncher;
import com.atlauncher.annot.Revalidate;

public aspect RevalidateAspect{
    pointcut publicMethod()
            : execution(public * * (..));

    after(Revalidate ann)
            : publicMethod() && @annotation(ann){
        ATLauncher.getFrame().revalidate();
    }
}
