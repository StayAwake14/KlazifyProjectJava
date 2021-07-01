package com.klazify.klazify.Models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "facebook_url",
        "twitter_url",
        "instagram_url",
        "medium_url",
        "youtube_url",
        "pinterest_url",
        "linkedin_url",
        "github_url"
})
public class SocialMedia {

    @JsonProperty("facebook_url")
    private String facebookUrl;
    @JsonProperty("twitter_url")
    private String twitterUrl;
    @JsonProperty("instagram_url")
    private String instagramUrl;
    @JsonProperty("medium_url")
    private String mediumUrl;
    @JsonProperty("youtube_url")
    private String youtubeUrl;
    @JsonProperty("pinterest_url")
    private String pinterestUrl;
    @JsonProperty("linkedin_url")
    private String linkedinUrl;
    @JsonProperty("github_url")
    private String githubUrl;

    @JsonProperty("facebook_url")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    @JsonProperty("facebook_url")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public SocialMedia withFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
        return this;
    }

    @JsonProperty("twitter_url")
    public String getTwitterUrl() {
        return twitterUrl;
    }

    @JsonProperty("twitter_url")
    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public SocialMedia withTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
        return this;
    }

    @JsonProperty("instagram_url")
    public String getInstagramUrl() {
        return instagramUrl;
    }

    @JsonProperty("instagram_url")
    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public SocialMedia withInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
        return this;
    }

    @JsonProperty("medium_url")
    public String getMediumUrl() {
        return mediumUrl;
    }

    @JsonProperty("medium_url")
    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public SocialMedia withMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
        return this;
    }

    @JsonProperty("youtube_url")
    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    @JsonProperty("youtube_url")
    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public SocialMedia withYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
        return this;
    }

    @JsonProperty("pinterest_url")
    public String getPinterestUrl() {
        return pinterestUrl;
    }

    @JsonProperty("pinterest_url")
    public void setPinterestUrl(String pinterestUrl) {
        this.pinterestUrl = pinterestUrl;
    }

    public SocialMedia withPinterestUrl(String pinterestUrl) {
        this.pinterestUrl = pinterestUrl;
        return this;
    }

    @JsonProperty("linkedin_url")
    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    @JsonProperty("linkedin_url")
    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public SocialMedia withLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
        return this;
    }

    @Override
    public String toString() {
        return "SocialMedias{" +
                "facebookUrl=" + facebookUrl +
                ", twitterUrl=" + twitterUrl +
                ", instagramUrl=" + instagramUrl +
                ", mediumUrl=" + mediumUrl +
                ", youtubeUrl='" + youtubeUrl + '\'' +
                ", pinterestUrl=" + pinterestUrl +
                ", linkedinUrl=" + linkedinUrl +
                ", githubUrl=" + githubUrl +
                '}';
    }

    @JsonProperty("github_url")
    public String getGithubUrl() {
        return githubUrl;
    }

    @JsonProperty("github_url")
    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public SocialMedia withGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
        return this;
    }
}
