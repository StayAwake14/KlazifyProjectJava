package com.klazify.klazify.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.klazify.klazify.Entities.Categories;
import com.klazify.klazify.Entities.SocialMedias;
import com.klazify.klazify.Entities.Websites;
import com.klazify.klazify.Models.Category;
import com.klazify.klazify.Models.Domain;
import com.klazify.klazify.Models.Website;
import com.klazify.klazify.Repozitories.CategoriesRepo;
import com.klazify.klazify.Repozitories.SocialMediaRepo;
import com.klazify.klazify.Repozitories.WebsitesRepo;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class KlazifyService {
        private String url = "https://www.klazify.com/api/categorize?url=";
        private String external_url = "https://facebook.com/";
        private final CategoriesRepo categoriesRepo;
        private final WebsitesRepo websitesRepo;
        private final SocialMediaRepo socialMediaRepo;

        public KlazifyService(CategoriesRepo categoriesRepo, WebsitesRepo websitesRepo, SocialMediaRepo socialMediaRepo) {
                this.categoriesRepo = categoriesRepo;
                this.websitesRepo = websitesRepo;
                this.socialMediaRepo = socialMediaRepo;
        }

        public ResponseEntity findWebsite(String website_name){
                Websites website = websitesRepo.findWebsiteByUrl(website_name);
                if(website != null)
                {
                        return new ResponseEntity<Websites>(website, HttpStatus.OK);
                }
                else
                {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        public Websites findSingleWebsite(String website_name){
                Websites website = websitesRepo.findWebsiteByUrl(website_name);
                if(website != null)
                {
                        return website;
                }
                else
                {
                        return null;
                }

        }

        public List<Categories> getWebsiteCategories(String website_name){
                Websites website = findSingleWebsite("https://"+website_name);
                if(website != null) {
                        List<Categories> categoriesList = new ArrayList<>();
                        for (Categories category : website.getCategories()) {
                                categoriesList.add(category);
                        }
                        return categoriesList;
                }
                else
                {
                        return null;
                }
        }

        public Categories getSingleWebsiteCategory(Long id){
                Categories category = categoriesRepo.findCategoryById(id);
                return category;
        }

        public ResponseEntity updateWebsiteCategory(String category_name, Long category_id){
                try {
                        Categories category = getSingleWebsiteCategory(category_id);
                        category.setName(category_name);
                        return new ResponseEntity<Categories>(categoriesRepo.save(category), HttpStatus.OK);
                }
                catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        public Boolean deleteWebsite(String website_name) {
                Websites websites = websitesRepo.findWebsiteByUrl("https://"+website_name);
                if(websites != null){
                        for(Categories category: websites.getCategories()){
                            categoriesRepo.delete(category);
                        }

                        if(websites.getSocialMedia() != null)
                        socialMediaRepo.delete(websites.getSocialMedia());


                        websitesRepo.delete(websites);

                        return true;
                }
                else
                {
                        return false;
                }
        }

        public Websites addWebsite(String website_name){
                List<Websites> websiteList = websitesRepo.findByUrl("https://"+website_name);

                if(websiteList.isEmpty()){
                        ResponseEntity<Website> response = makeRequest("https://"+website_name);
                        Website new_website = response.getBody();

                        Websites website_model = new Websites();
                        if(new_website.getDomain().getDomainUrl() == "null")
                                website_model.setUrl(new_website.getDomain().getDomainUrl());
                        else
                                website_model.setUrl("https://"+website_name);

                        website_model.setLogoUrl(new_website.getDomain().getLogoUrl());

                        List<Categories> categoriesList = new ArrayList<Categories>();
                        for(Category category:new_website.getDomain().getCategories()){
                                Categories new_categories = new Categories();
                                new_categories.setName(category.getName());
                                new_categories.setConfidence(category.getConfidence());
                                new_categories.setWebsites(website_model);
                                categoriesList.add(new_categories);
                        }

                        SocialMedias new_sm = new SocialMedias();
                        if(new_website.getDomain().getSocialMedia() != null) {
                                new_sm.setFacebookUrl(new_website.getDomain().getSocialMedia().getFacebookUrl());
                                new_sm.setGithubUrl(new_website.getDomain().getSocialMedia().getGithubUrl());
                                new_sm.setInstagramUrl(new_website.getDomain().getSocialMedia().getInstagramUrl());
                                new_sm.setMediumUrl(new_website.getDomain().getSocialMedia().getMediumUrl());
                                new_sm.setLinkedinUrl(new_website.getDomain().getSocialMedia().getLinkedinUrl());
                                new_sm.setTwitterUrl(new_website.getDomain().getSocialMedia().getTwitterUrl());
                                new_sm.setYoutubeUrl(new_website.getDomain().getSocialMedia().getYoutubeUrl());
                                new_sm.setPinterestUrl(new_website.getDomain().getSocialMedia().getPinterestUrl());
                                new_sm.setWebsites(website_model);
                                website_model.setSocialMedia(new_sm);
                        }

                        website_model.setCategories(categoriesList);

                        websitesRepo.save(website_model);
                        socialMediaRepo.save(new_sm);
                        for(Categories cat:website_model.getCategories()) {
                                categoriesRepo.save(cat);
                        }

                        return website_model;
                }
                else
                {
                        Websites website = findSingleWebsite("https://"+website_name);

                        return website;
                }
        }

        public ResponseEntity makeRequest(String external_url) {
                this.external_url = new String(this.url + external_url);
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNjI1N2M0OWY4M2Q1MDI3MDM3YjNiNmQ5ZGUwM2EzYmZlZGQ2MjgyNTNiMDc4YWNlNzMxYzkwMjg4MWQ2YmVmZDk5NWU4OTZmOThjNzEwZDEiLCJpYXQiOjE2MjUxNzE2NzUsIm5iZiI6MTYyNTE3MTY3NSwiZXhwIjoxNjU2NzA3Njc1LCJzdWIiOiIyNzk0Iiwic2NvcGVzIjpbXX0.wcJrf-Y9ecN2xyB19z76e6uzZ5NDPsJfNsUpuXdbwqbJ71raf45zny_U70MT36jO-byFPzQKaOY1VurbsJtC9Q");
                HttpEntity<String> entity = new HttpEntity<>("body", headers);
                ResponseEntity<Website> response = restTemplate.exchange(this.external_url, HttpMethod.POST, entity, Website.class);
                return response;
        }

        public String getExtUrl(){
                return this.external_url;
        }

        public String setUrl(String external_url){
                this.external_url = external_url;
                return external_url;
        }

}
