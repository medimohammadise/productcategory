package com.ecanteen.service.impl;


 import com.ecanteen.domain.SubProductCategory;
 import com.ecanteen.repository.SubProductCategoryRepository;
import com.ecanteen.service.SubProductCategoryService;
 import com.ecanteen.service.dto.SubProductCategoryDTO;
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
public class SubProductCategoryImpl implements SubProductCategoryService {

    private final Logger log = LoggerFactory.getLogger(SubProductCategoryImpl.class);


    private final SubProductCategoryMapper subProductCategoryMapper;
    private final SubProductCategoryRepository subProductCategoryRepository;



    @Autowired
    public SubProductCategoryImpl( SubProductCategoryMapper subProductCategoryMapper, SubProductCategoryRepository subProductCategoryRepository) {
        this.subProductCategoryMapper = subProductCategoryMapper;
        this.subProductCategoryRepository = subProductCategoryRepository;
    }

    @Override
    public SubProductCategoryDTO save(SubProductCategoryDTO subProductCategoryDTO) {
        log.debug("Request to save subProductCategory : {}", subProductCategoryMapper);
        SubProductCategory subProductCategory = subProductCategoryMapper.toEntity(subProductCategoryDTO);
        subProductCategory = subProductCategoryRepository.save(subProductCategory);
        return subProductCategoryMapper.toDto(subProductCategory);
    }

    @Override
    public SubProductCategoryDTO update(SubProductCategoryDTO subProductCategoryDTO) {
        log.debug("Request to save subProductCategory : {}", subProductCategoryDTO);
        SubProductCategory subProductCategory = subProductCategoryMapper.toEntity(subProductCategoryDTO);
        subProductCategory = subProductCategoryRepository.save(subProductCategory);
        return subProductCategoryMapper.toDto(subProductCategory);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Page<SubProductCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all subProductCategory");
        return subProductCategoryRepository.findAll(pageable).map(subProductCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubProductCategoryDTO> findOne(Long id) {
        log.debug("Request to get subProductCategory : {}", id);
        return subProductCategoryRepository.findById(id).map(subProductCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete subProductCategory : {}", id);
        subProductCategoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete subProductCategory");
        subProductCategoryRepository.deleteAll();


    }
}
