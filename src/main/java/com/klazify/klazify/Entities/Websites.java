package com.klazify.klazify.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "domain")
public class Websites{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @OneToOne(mappedBy = "websites", cascade=CascadeType.ALL)
    private SocialMedias socialMedias;
    private String logoUrl;

    @OneToMany(mappedBy = "websites", cascade=CascadeType.ALL)
    private List<Categories> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public SocialMedias getSocialMedia() {
        return socialMedias;
    }

    public void setSocialMedia(SocialMedias socialMedias) {
        this.socialMedias = socialMedias;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}

