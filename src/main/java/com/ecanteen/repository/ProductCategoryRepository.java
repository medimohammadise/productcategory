package com.ecanteen.repository;

import com.ecanteen.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> , JpaSpecificationExecutor<ProductCategory> {
}
