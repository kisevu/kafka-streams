package com.ameda.kev.kafkastreamsmethods.events;


/**
 * Author: kev.Ameda
 */
public record RestReq(String name,
                      String url) {

    public RestReq {
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String url() {
        return url;
    }
}
