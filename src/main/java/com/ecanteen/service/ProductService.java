package com.ecanteen.service;


import com.ecanteen.service.dto.ProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductService {


    ProductDTO save(ProductDTO productDTO);


    ProductDTO update(ProductDTO productDTO);


    Optional<ProductDTO> partialUpdate(ProductDTO productDTO);


    Optional<ProductDTO> findOne(Long id);


    Page<ProductDTO> findAll(Pageable pageable);


    void delete(Long id);

    void deleteAll();
}

