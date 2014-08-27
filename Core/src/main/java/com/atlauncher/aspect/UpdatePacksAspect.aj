package com.atlauncher.aspect;

import com.atlauncher.ATLauncher;
import com.atlauncher.annot.UpdatePacks;
import com.atlauncher.event.UpdatePacksEvent;

public aspect UpdatePacksAspect{
    pointcut publicMethod()
            : execution(public * * (..));

    after(UpdatePacks ann)
            : publicMethod() && @annotation(ann){
        ATLauncher.EVENT_BUS.post(new UpdatePacksEvent());
    }
}