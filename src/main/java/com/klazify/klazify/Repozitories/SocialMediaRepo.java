package com.klazify.klazify.Repozitories;

import com.klazify.klazify.Entities.Categories;
import com.klazify.klazify.Entities.SocialMedias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepo extends CrudRepository<SocialMedias, Long> {
}
