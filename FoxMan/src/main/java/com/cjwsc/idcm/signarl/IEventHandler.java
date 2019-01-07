package com.cjwsc.idcm.signarl;

/**
 * Created by hpz on 17/9/20.
 */

public interface IEventHandler<TEventArgs extends EventArgs> {

    void eventReceived(Object sender, TEventArgs e);
}
