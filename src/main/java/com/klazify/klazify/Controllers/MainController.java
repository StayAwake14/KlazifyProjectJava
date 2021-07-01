package com.klazify.klazify.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.klazify.klazify.Entities.Categories;
import com.klazify.klazify.Entities.Websites;
import com.klazify.klazify.Models.Domain;
import com.klazify.klazify.Services.KlazifyService;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class MainController {

    @Autowired
    private final KlazifyService klazifyService;

    public MainController(KlazifyService klazifyService) {
        this.klazifyService = klazifyService;
    }

    @GetMapping(path="/website/get/external_api/{url}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Get website details from external API - Klazify", notes="Provide a website {url} (fe: google.com) to receive website details from external API - Klazify", response=ResponseEntity.class)
    public ResponseEntity getExternalWebsite(@PathVariable("url") String url){
        ResponseEntity<Domain> response = klazifyService.makeRequest("https://"+url);
        return response;
    }

    /*@GetMapping(path="/website/get/{url}")
    @ApiOperation(value="Get website details from local DB", notes="Provide a website {url} (fe: google.com) to receive website details from local DB", response=ResponseEntity.class)
    public ResponseEntity<Websites> getWebsite(@PathVariable("url") String url){
        ResponseEntity<Websites> website = klazifyService.findWebsite("https://"+url);
        return website;
    }*/

   /* @GetMapping(path="/website/get/categories/{website_name}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Get list of website categories", notes="Provide a {website_name} (fe: google.com) to receive website categories", response=List.class)
    public List<Categories> getWebsiteCategories(@PathVariable("website_name") String website_name){
        List<Categories> categoriesList = klazifyService.getWebsiteCategories(website_name);
        return categoriesList;
    }*/

    @GetMapping(path="/website/get/category/{category_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Find category by {category_id}", notes="Provide an {category_id} to find specific category for selected website", response=Categories.class)
    public Categories getWebsiteCategory(@PathVariable("category_id") Long category_id){
        Categories category = klazifyService.getSingleWebsiteCategory(category_id);
        return category;
    }

    @PatchMapping("/website/patch/category/{category_id}/{new_category_name}")
    @ApiOperation(value="Patch category name by {category_id} in local DB", notes="Provide a category {category_id} to find specific category and provide {new_category_name} to change category name on new one", response=ResponseEntity.class)
    public ResponseEntity<Categories> updateWebsiteCategory(@PathVariable("category_id") Long id, @PathVariable("new_category_name") String category_name) {
        ResponseEntity<Categories> category = klazifyService.updateWebsiteCategory(category_name, id);
        return category;
    }

    @DeleteMapping(value = "/website/delete/{website_name}")
    @ApiOperation(value="Delete website from local DB", notes="Provide {website_name} in order to delete website from local DB", response=Boolean.class)
    public Boolean deleteWebsite(@PathVariable("website_name") String website_name) {
        Boolean website = klazifyService.deleteWebsite(website_name);
        return website;
    }

    @PostMapping(path="/website/add/{website_name}")
    @ApiOperation(value="Add website details to local DB", notes="Provide {website_name} in order to get website details from external API and save them to local DB if they are not added already", response=Websites.class)
    public Websites addWebsite(@PathVariable("website_name") String website_name){
        Websites website = klazifyService.addWebsite(website_name);
        return website;
    }

}
