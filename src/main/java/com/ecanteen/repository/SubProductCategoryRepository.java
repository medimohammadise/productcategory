package com.ecanteen.repository;

import com.ecanteen.domain.SubProductCategory;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Long>, JpaSpecificationExecutor<SubProductCategory> {


    @Query("SELECT u FROM SubProductCategory u WHERE u.id = :productCategoryId")
    List<SubProductCategoryDTO> findByProductCategoryId(Long productCategoryId);

    @Query("SELECT u FROM SubProductCategory u WHERE u.id = :productCategoryId")
    @Transactional
    void deleteByProductCategoryId(Long productCategoryId);


    Optional<SubProductCategory> findByName(String name);
}

