package com.ecanteen.service.mapper.impl;


import com.ecanteen.domain.ProductCategory;
import com.ecanteen.domain.SubProductCategory;
import com.ecanteen.repository.ProductCategoryRepository;
import com.ecanteen.repository.ProductRepository;
import com.ecanteen.repository.SubProductCategoryRepository;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import com.ecanteen.service.mapper.ProductMapper;
import com.ecanteen.service.mapper.SubProductCategoryMapper;
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
public class SubProductCategoryImpl {

    private final Logger log = LoggerFactory.getLogger(SubProductCategoryImpl.class);


    private final SubProductCategoryMapper subProductCategoryMapper;
    private final SubProductCategoryRepository subProductCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;


    @Autowired
    public SubProductCategoryImpl(SubProductCategoryMapper subProductCategoryMapper, SubProductCategoryRepository subProductCategoryRepository, ProductCategoryRepository productCategoryRepository, ProductMapper productMapper, ProductRepository productRepository) {
        this.subProductCategoryMapper = subProductCategoryMapper;
        this.subProductCategoryRepository = subProductCategoryRepository;
        this.productCategoryRepository = productCategoryRepository;
    }


    public SubProductCategoryDTO save(SubProductCategoryDTO subProductCategoryDTO) {
        SubProductCategory newSubProductCategory = null;
        log.debug("Request to save subProductCategory : {}", subProductCategoryMapper);
        SubProductCategory subProductCategory = subProductCategoryMapper.toEntity(subProductCategoryDTO);
        Optional<ProductCategory> productCategory = productCategoryRepository.findByName(subProductCategoryDTO.getProductCategoryName());
        var ProductCategoryEntity = productCategory.get();
        var subProductCategoryEntity = subProductCategoryRepository.findByName(subProductCategoryDTO.getName());

        if (!subProductCategoryEntity.isPresent()) {
            subProductCategory.setId(subProductCategory.getId());
            subProductCategory.setProductCategory(ProductCategoryEntity);
            newSubProductCategory = subProductCategoryRepository.save(subProductCategory);
        } else {

            subProductCategory.setProductCategory(ProductCategoryEntity);
            subProductCategoryRepository.save(subProductCategory);

        }

        return subProductCategoryMapper.toDto(newSubProductCategory);
    }


    public Optional<SubProductCategoryDTO> update(SubProductCategoryDTO subProductCategoryDTO) {
        log.debug("Request to save subProductCategory : {}", subProductCategoryDTO);
        SubProductCategory subProductCategory = subProductCategoryMapper.toEntity(subProductCategoryDTO);
        subProductCategory = subProductCategoryRepository.save(subProductCategory);
        return Optional.ofNullable(subProductCategoryMapper.toDto(subProductCategory));
    }

    public Optional<SubProductCategoryDTO> partialUpdate(SubProductCategoryDTO subProductCategoryDTO) {
        log.debug("Request to partially update subProductCategory : {}", subProductCategoryDTO);

        return subProductCategoryRepository
                .findById(subProductCategoryDTO.getId())
                .map(existingSubProductCategory -> {
                    subProductCategoryMapper.partialUpdate(existingSubProductCategory, subProductCategoryDTO);

                    return existingSubProductCategory;
                })
                .map(subProductCategoryRepository::save)
                .map(subProductCategoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<SubProductCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all subProductCategory");
        return subProductCategoryRepository.findAll(pageable).map(subProductCategoryMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<SubProductCategoryDTO> findOne(Long id) {
        log.debug("Request to get subProductCategory : {}", id);
        return subProductCategoryRepository.findById(id).map(subProductCategoryMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete subProductCategory : {}", id);
        subProductCategoryRepository.deleteById(id);
    }

    public void deleteAll() {
        log.debug("Request to delete subProductCategory");
        subProductCategoryRepository.deleteAll();


    }


}
