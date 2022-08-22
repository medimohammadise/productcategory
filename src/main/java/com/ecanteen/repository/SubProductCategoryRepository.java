package com.ecanteen.repository;

import com.ecanteen.domain.SubProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Long>, JpaSpecificationExecutor<SubProductCategory> {
}