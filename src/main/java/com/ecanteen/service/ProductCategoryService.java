package com.ecanteen.service;

import com.ecanteen.service.dto.ProductCategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductCategoryService {


    ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);

    ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO);


    Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO productCategoryDTO);


    Optional<ProductCategoryDTO> findOne(Long id);


    Page<ProductCategoryDTO> findAll(Pageable pageable);


    void delete(Long id);


    void deleteAll();



}

