package com.ecanteen.service.impl;

import com.ecanteen.domain.ProductCategory;

import com.ecanteen.repository.ProductCategoryRepository;

import com.ecanteen.service.ProductCategoryService;
import com.ecanteen.service.dto.ProductCategoryDTO;

import com.ecanteen.service.mapper.ProductCategoryMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Interface for managing {@link com.ecanteen.domain.ProductCategory}.
 */

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final Logger log = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);


    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository ProductCategoryRepository;



    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryMapper productCategoryMapper, ProductCategoryRepository ProductCategoryRepository) {
        this.productCategoryMapper = productCategoryMapper;
        this.ProductCategoryRepository = ProductCategoryRepository;
    }

    @Override
    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
        log.debug("Request to save ProductCategory : {}", productCategoryMapper);
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
        productCategory = ProductCategoryRepository.save(productCategory);
        return productCategoryMapper.toDto(productCategory);
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO) {
        log.debug("Request to save ProductCategory : {}", productCategoryDTO);
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
        productCategory = ProductCategoryRepository.save(productCategory);
        return productCategoryMapper.toDto(productCategory);
    }

    @Override
    public Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO productCategoryDTO) {
        log.debug("Request to partially update ProductCategory : {}", productCategoryDTO);

        return ProductCategoryRepository
            .findById(productCategoryDTO.getId())
            .map(existingProductCategory -> {
                productCategoryMapper.partialUpdate(existingProductCategory, productCategoryDTO);

                return existingProductCategory;
            })
            .map(ProductCategoryRepository::save)
            .map(productCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductCategory");
        return ProductCategoryRepository.findAll(pageable).map(productCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductCategoryDTO> findOne(Long id) {
        log.debug("Request to get productCategory : {}", id);
        return ProductCategoryRepository.findById(id).map(productCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete productCategory : {}", id);
        ProductCategoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete productCategory");
        ProductCategoryRepository.deleteAll();

    }

}
