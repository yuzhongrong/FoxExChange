package com.cjwsc.idcm.signarl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hpz on 17/9/20.
 */

public class Event<TEventArgs extends EventArgs> {

    private List<IEventHandler<TEventArgs>> observerList = Collections.synchronizedList(new ArrayList<IEventHandler<TEventArgs>>());

    public void raiseEvent(Object sender,TEventArgs e){

        for (IEventHandler<TEventArgs> handler:this.observerList){
            handler.eventReceived(sender,e);
        }
    }

    public void addEventHandler(IEventHandler<TEventArgs> handler){

        this.observerList.add(handler);
    }

    public void removeEventHandler(IEventHandler<TEventArgs> handler){

        this.observerList.remove(handler);
    }
}

