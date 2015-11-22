package com.bozidar.labas.gcm_microdroid;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Bozidar on 16.11.2015..
 */
public class BusProvider {
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    private BusProvider(){}

    public static Bus getInstance(){
        return BUS;
    }
}
