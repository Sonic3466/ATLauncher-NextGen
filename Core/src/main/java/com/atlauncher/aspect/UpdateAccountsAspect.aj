package com.atlauncher.aspect;

import com.atlauncher.ATLauncher;
import com.atlauncher.annot.UpdateAccounts;
import com.atlauncher.event.UpdateAccountsEvent;

/**
 * Advice on how to update accounts
 */
public aspect UpdateAccountsAspect{
    pointcut publicMethod()
            : execution(public * * (..));

    after(UpdateAccounts ann)
            : publicMethod() && @annotation(ann){
        ATLauncher.EVENT_BUS.post(new UpdateAccountsEvent());
    }
}