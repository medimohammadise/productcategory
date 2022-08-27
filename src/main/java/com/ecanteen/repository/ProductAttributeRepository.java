package com.ecanteen.repository;

import com.ecanteen.domain.Product;
import com.ecanteen.domain.ProductAttribute;
import com.ecanteen.service.dto.ProductAttributeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long>, JpaSpecificationExecutor<ProductAttribute> {


    @Query("SELECT u FROM ProductAttribute u WHERE u.id = :productId")
    List<ProductAttributeDTO> findByProductId(Long productId);

    @Query("SELECT u FROM ProductAttribute u WHERE u.id = :productId")
    @Transactional
    void deleteByProductId(Long productId);


    Optional<ProductAttribute> findByName(String name);
}