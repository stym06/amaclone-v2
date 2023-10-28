package com.personal.amacloneserver.repository;

import com.personal.amacloneserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
