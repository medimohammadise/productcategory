package com.ecanteen.service.mapper.impl;

import com.ecanteen.domain.Product;
import com.ecanteen.domain.SubProductCategory;
import com.ecanteen.repository.ProductRepository;
import com.ecanteen.repository.SubProductCategoryRepository;
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
public class ProductServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final SubProductCategoryRepository subProductCategoryRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, SubProductCategoryRepository subProductCategoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.subProductCategoryRepository = subProductCategoryRepository;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product newProduct = null;
        log.debug("Request to save product : {}", productMapper);
        Product product = productMapper.toEntity(productDTO);
        Optional<SubProductCategory> subProductCategory = subProductCategoryRepository.findByName(productDTO.getSubProductCategoryName());
        var subProductCategoryEntity = subProductCategory.get();
        var productEntity = productRepository.findByName(productDTO.getName());

        if (!productEntity.isPresent()) {
            product.setId(product.getId());
            product.setSubProductCategory(subProductCategoryEntity);
            newProduct = productRepository.save(product);
        } else {

            product.setSubProductCategory(subProductCategoryEntity);
            productRepository.save(product);

        }

        return productMapper.toDto(newProduct);
    }


    public Optional<ProductDTO> update(ProductDTO productDTO) {
        log.debug("Request to save product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return Optional.ofNullable(productMapper.toDto(product));
    }


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


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all product");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }


    public void delete(Long id) {
        log.debug("Request to delete product : {}", id);
        productRepository.deleteById(id);
    }


    public void deleteAll() {
        log.debug("Request to delete productCategory");
        productRepository.deleteAll();


    }


}
