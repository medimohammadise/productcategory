package com.ecanteen.repository;

import com.ecanteen.domain.Product;
import com.ecanteen.domain.SubProductCategory;
import com.ecanteen.service.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    @Query("SELECT u FROM Product u WHERE u.id = :subProductCategoryId")
    List<ProductDTO> findBySubProductCategoryId(Long subProductCategoryId);


    @Query("SELECT u FROM Product u WHERE u.id = :subProductCategoryId")
    @Transactional
    void deleteBySubProductCategoryId(Long subProductCategoryId);

    Optional<Product> findByName(String name);

}
