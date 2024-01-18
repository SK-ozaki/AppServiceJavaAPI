package com.example.demo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSiteLists {

    @JsonProperty("sites")
    private List<JsonSiteList> sites;

    public void setsites(List<JsonSiteList> sites) {
        this.sites = sites;
    }

    public List<JsonSiteList> getsites() {
        return this.sites;
    }

}
