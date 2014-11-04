package com.atlauncher.aspect;

import com.atlauncher.ATLauncher;
import com.atlauncher.annot.UpdateI18N;
import com.atlauncher.event.UpdateI18NEvent;

/**
 * Advice on how to update localization
 */
public aspect UpdateI18NAspect{
    pointcut publicMethod()
            : execution(public * * (..));

    after(UpdateI18N ann)
            : publicMethod() && @annotation(ann){
        ATLauncher.EVENT_BUS.post(new UpdateI18NEvent());
    }
}