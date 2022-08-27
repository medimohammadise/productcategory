package com.ecanteen.repository;

import com.ecanteen.domain.ProductCategory;
import com.ecanteen.service.dto.ProductDTO;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<ProductCategory> {



    Optional<ProductCategory> findByName(String name);


}
