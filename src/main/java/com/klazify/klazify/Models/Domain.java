package com.klazify.klazify.Models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "categories",
        "logo_url",
        "social_media"
})
public class Domain {
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("logo_url")
    private String logoUrl;
    @JsonProperty("social_media")
    private SocialMedia socialMedia;
    @JsonProperty("domain_url")
    private String domainUrl;
    @JsonIgnore

    @JsonProperty("domain_url")
    public String getDomainUrl() {
        return domainUrl;
    }

    @JsonProperty("domain_url")
    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Domain withCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "categories=" + categories +
                ", logoUrl='" + logoUrl + '\'' +
                ", socialMedia=" + socialMedia +
                ", domainUrl='" + domainUrl + '\'' +
                '}';
    }

    @JsonProperty("logo_url")
    public String getLogoUrl() {
        return logoUrl;
    }

    @JsonProperty("logo_url")
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Domain withLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    @JsonProperty("social_media")
    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    @JsonProperty("social_media")
    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Domain withSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
        return this;
    }

}
