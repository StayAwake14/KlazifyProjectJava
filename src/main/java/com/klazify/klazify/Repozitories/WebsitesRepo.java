package com.klazify.klazify.Repozitories;

import com.klazify.klazify.Entities.Websites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsitesRepo extends CrudRepository<Websites, Long> {
    Websites findWebsiteByUrl(String name);
    List<Websites> findByUrl(String website_name);
}
