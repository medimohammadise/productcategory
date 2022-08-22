package com.ecanteen.service;

import com.ecanteen.service.dto.ProductAttributeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductAttributeService {

    ProductAttributeDTO save(ProductAttributeDTO productAttributeDTO);

    ProductAttributeDTO update(ProductAttributeDTO productAttributeDTO);


    Optional<ProductAttributeDTO> partialUpdate(ProductAttributeDTO productAttributeDTO);


    Optional<ProductAttributeDTO> findOne(Long id);


    Page<ProductAttributeDTO> findAll(Pageable pageable);


    void delete(Long id);


    void deleteAll();
}
