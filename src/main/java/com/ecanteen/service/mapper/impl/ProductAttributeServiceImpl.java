package com.ecanteen.service.mapper.impl;

import com.ecanteen.domain.Product;
import com.ecanteen.domain.ProductAttribute;
import com.ecanteen.repository.ProductAttributeRepository;
import com.ecanteen.repository.ProductRepository;
import com.ecanteen.service.dto.ProductAttributeDTO;
import com.ecanteen.service.mapper.ProductAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ProductAttributeServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ProductAttributeServiceImpl.class);


    private final ProductAttributeMapper productAttributeMapper;
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductRepository productRepository;


    @Autowired
    public ProductAttributeServiceImpl(ProductAttributeMapper productAttributeMapper, ProductAttributeRepository productAttributeRepository, ProductRepository productRepository) {
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeRepository = productAttributeRepository;
        this.productRepository = productRepository;
    }


    public ProductAttributeDTO save(ProductAttributeDTO productAttributeDTO) {
        ProductAttribute newProductAttribute = null;
        log.debug("Request to save ProductAttribute : {}", productAttributeMapper);
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDTO);
        Optional<Product> product = productRepository.findByName(productAttributeDTO.getProductName());
        var productEntity = product.get();
        var productAttributeEntity = productAttributeRepository.findByName(productAttributeDTO.getProductName());

        if (!productAttributeEntity.isPresent()) {
            productAttribute.setId(productAttribute.getId());
            productAttribute.setProduct(productEntity);
            newProductAttribute = productAttributeRepository.save(productAttribute);
        } else {

            productAttribute.setProduct(productEntity);
            productAttributeRepository.save(productAttribute);

        }

        return productAttributeMapper.toDto(newProductAttribute);
    }


    public Optional<ProductAttributeDTO> update(ProductAttributeDTO productAttributeDTO) {
        log.debug("Request to save productAttribute : {}", productAttributeDTO);
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDTO);
        productAttribute = productAttributeRepository.save(productAttribute);
        return Optional.ofNullable(productAttributeMapper.toDto(productAttribute));
    }


    public Optional<ProductAttributeDTO> partialUpdate(ProductAttributeDTO productAttributeDTO) {
        log.debug("Request to partially update productAttribute : {}", productAttributeDTO);

        return productAttributeRepository
                .findById(productAttributeDTO.getId())
                .map(existingProductAttributeRepository -> {
                    productAttributeMapper.partialUpdate(existingProductAttributeRepository, productAttributeDTO);

                    return existingProductAttributeRepository;
                })
                .map(productAttributeRepository::save)
                .map(productAttributeMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Page<ProductAttributeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all productAttribute");
        return productAttributeRepository.findAll(pageable).map(productAttributeMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<ProductAttributeDTO> findOne(Long id) {
        log.debug("Request to get productAttribute : {}", id);
        return productAttributeRepository.findById(id).map(productAttributeMapper::toDto);
    }


    public void delete(Long id) {
        log.debug("Request to delete productAttribute : {}", id);
        productAttributeRepository.deleteById(id);
    }


    public void deleteAll() {
        log.debug("Request to delete productCategory");
        productAttributeRepository.deleteAll();

    }

}
