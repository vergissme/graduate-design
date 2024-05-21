package org.example.nfchats.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoForm {
    //3.content+this.form3.xaxis+this.form3.yaxis+this.form3.path
    @JsonProperty("content")
    private String content;
    @JsonProperty("xaxis")
    private Integer xaxis;
    @JsonProperty("yaxis")
    private Integer yaxis;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getXaxis() {
        return xaxis;
    }

    public void setXaxis(Integer xaxis) {
        this.xaxis = xaxis;
    }

    public Integer getYaxis() {
        return yaxis;
    }

    public void setYaxis(Integer yaxis) {
        this.yaxis = yaxis;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("path")
    private String path;
}
