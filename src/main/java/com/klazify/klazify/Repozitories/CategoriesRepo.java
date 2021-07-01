package com.klazify.klazify.Repozitories;

import com.klazify.klazify.Entities.Categories;
import com.klazify.klazify.Entities.Websites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends CrudRepository<Categories, Long> {
    Categories findCategoryById(Long id);
}
