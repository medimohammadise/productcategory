package com.ecanteen.service.impl;

import com.ecanteen.domain.ProductAttribute;
import com.ecanteen.repository.ProductAttributeRepository;
import com.ecanteen.service.ProductAttributeService;
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
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final Logger log = LoggerFactory.getLogger(ProductAttributeServiceImpl.class);


    private final ProductAttributeMapper productAttributeMapper;
    private final ProductAttributeRepository productAttributeRepository;



    @Autowired
    public ProductAttributeServiceImpl(ProductAttributeMapper productAttributeMapper, ProductAttributeRepository productAttributeRepository) {
        this.productAttributeMapper = productAttributeMapper;
        this.productAttributeRepository = productAttributeRepository;
    }

    @Override
    public ProductAttributeDTO save(ProductAttributeDTO productAttributeDTO) {
        log.debug("Request to save productAttribute : {}", productAttributeMapper);
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDTO);
        productAttribute = productAttributeRepository.save(productAttribute);
        return productAttributeMapper.toDto(productAttribute);
    }

    @Override
    public ProductAttributeDTO update(ProductAttributeDTO productAttributeDTO) {
        log.debug("Request to save productAttribute : {}", productAttributeDTO);
        ProductAttribute productAttribute = productAttributeMapper.toEntity(productAttributeDTO);
        productAttribute = productAttributeRepository.save(productAttribute);
        return productAttributeMapper.toDto(productAttribute);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Page<ProductAttributeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all productAttribute");
        return productAttributeRepository.findAll(pageable).map(productAttributeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductAttributeDTO> findOne(Long id) {
        log.debug("Request to get productAttribute : {}", id);
        return productAttributeRepository.findById(id).map(productAttributeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete productAttribute : {}", id);
        productAttributeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete productCategory");
        productAttributeRepository.deleteAll();

    }
}
