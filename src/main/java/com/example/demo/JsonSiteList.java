package com.example.demo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonSiteList {

    @JsonProperty("siteurl")
    private String siteurl;

    @JsonProperty("tag")
    private List<String> tag;

    @JsonProperty("comment")
    private String comment;

    public JsonSiteList() {
    }

    public JsonSiteList(String url, List<String> tag, String comment) {
        this.setsiteurl(url);
        this.settag(tag);
        this.setcomment(comment);
    }

    public void setsiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getsiteurl() {
        return siteurl;
    }

    public void settag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> gettag() {
        return tag;
    }

    public void setcomment(String comment) {
        this.comment = comment;
    }

    public String getcomment() {
        return comment;
    }
}
