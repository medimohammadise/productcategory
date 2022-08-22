package com.ecanteen.service.impl;

import com.ecanteen.domain.Product;

import com.ecanteen.repository.ProductRepository;


import com.ecanteen.service.ProductService;
import com.ecanteen.service.dto.ProductDTO;

import com.ecanteen.service.mapper.ProductMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * Service Interface for managing {@link com.ecanteen.domain.Product}.
 */


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    private final ProductMapper productMapper;
    private final ProductRepository productRepository;



    @Autowired
    public ProductServiceImpl(  ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save product : {}", productMapper);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        log.debug("Request to save product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
        log.debug("Request to partially update product : {}", productDTO);

        return productRepository
            .findById(productDTO.getId())
            .map(existingSchool -> {
                productMapper.partialUpdate(existingSchool, productDTO);

                return existingSchool;
            })
            .map(productRepository::save)
            .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all product");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete product : {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete productCategory");
        productRepository.deleteAll();


    }
}
