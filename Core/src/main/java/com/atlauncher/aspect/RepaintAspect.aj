package com.atlauncher.aspect;

import com.atlauncher.ATLauncher;
import com.atlauncher.annot.Repaint;

public aspect RepaintAspect{
    pointcut publicMethod()
            : execution(public * * (..));

    after(Repaint ann)
            : publicMethod() && @annotation(ann){

        ATLauncher.getFrame().repaint();
    }
}