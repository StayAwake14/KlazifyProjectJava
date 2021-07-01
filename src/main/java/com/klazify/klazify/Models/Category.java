package com.klazify.klazify.Models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "confidence",
        "name"
})
public class Category {
    @JsonProperty("confidence")
    private Double confidence;
    @JsonProperty("name")
    private String name;
    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    @Override
    public String toString() {
        return "Category{" +
                "confidence=" + confidence +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Category withConfidence(Double confidence) {
        this.confidence = confidence;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Category withName(String name) {
        this.name = name;
        return this;
    }

}
