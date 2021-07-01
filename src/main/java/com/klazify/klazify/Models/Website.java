package com.klazify.klazify.Models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.criterion.Example;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "domain",
        "success"
})
public class Website {
    @JsonProperty("domain")
    private Domain domain;
    @JsonProperty("success")
    private Boolean success;

    @Override
    public String toString() {
        return "Website{" +
                "domain=" + domain +
                ", success=" + success +
                '}';
    }

    @JsonProperty("domain")
    public Domain getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Website withDomain(Domain domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Website withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

}
