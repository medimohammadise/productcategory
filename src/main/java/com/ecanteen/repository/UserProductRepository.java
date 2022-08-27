package com.ecanteen.repository;

import com.ecanteen.domain.ProductCategory;
import com.ecanteen.domain.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> , JpaSpecificationExecutor<UserProduct> {



    Optional<UserProduct> findByProfileName(String profileName);
}