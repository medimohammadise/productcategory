package com.ecanteen.service.mapper.impl;

import com.ecanteen.domain.ProductCategory;
import com.ecanteen.repository.ProductCategoryRepository;
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
public class ProductCategoryServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);


    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;


    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryMapper productCategoryMapper, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryMapper = productCategoryMapper;
        this.productCategoryRepository = productCategoryRepository;
      }



//    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
//        log.debug("Request to save ProductCategory : {}", productCategoryMapper);
//        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
//        productCategory = productCategoryRepository.save(productCategory);
//        return productCategoryMapper.toDto(productCategory);
//    }


    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
        ProductCategory newProduct = null;
        log.debug("Request to save ProductCategory : {}", productCategoryMapper);
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
         var productCategoryEntity = productCategoryRepository.findByName(productCategoryDTO.getName());

        if (!productCategoryEntity.isPresent()) {
            productCategory.setId(productCategory.getId());

            newProduct = productCategoryRepository.save(productCategory);
        } else {

            productCategoryRepository.save(productCategory);

        }

        return productCategoryMapper.toDto(newProduct);
    }


    public Optional <ProductCategoryDTO> update(ProductCategoryDTO productCategoryDTO) {
        log.debug("Request to save ProductCategory : {}", productCategoryDTO);
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
        productCategory = productCategoryRepository.save(productCategory);
        return Optional.ofNullable(productCategoryMapper.toDto(productCategory));
    }



    @Transactional(readOnly = true)
    public Page<ProductCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductCategory");
        return productCategoryRepository.findAll(pageable).map(productCategoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ProductCategoryDTO> findOne(Long id) {
        log.debug("Request to get productCategory : {}", id);
        return productCategoryRepository.findById(id).map(productCategoryMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete productCategory : {}", id);
        productCategoryRepository.deleteById(id);
    }

    public void deleteAll() {
        log.debug("Request to delete productCategory");
        productCategoryRepository.deleteAll();

    }


    public Optional<ProductCategoryDTO> partialUpdate(ProductCategoryDTO productCategoryDTO) {
        log.debug("Request to partially update ProductCategory : {}", productCategoryDTO);

        return productCategoryRepository
                .findById(productCategoryDTO.getId())
                .map(existingProductCategory -> {
                    productCategoryMapper.partialUpdate(existingProductCategory, productCategoryDTO);

                    return existingProductCategory;
                })
                .map(productCategoryRepository::save)
                .map(productCategoryMapper::toDto);
    }
}


