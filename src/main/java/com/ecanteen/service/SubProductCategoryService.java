package com.ecanteen.service;

 import com.ecanteen.service.dto.SubProductCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubProductCategoryService {
    SubProductCategoryDTO save(SubProductCategoryDTO subProductCategoryDTO);


    SubProductCategoryDTO update(SubProductCategoryDTO subProductCategoryDTO);


    Optional<SubProductCategoryDTO> partialUpdate(SubProductCategoryDTO subProductCategoryDTO);


    Optional<SubProductCategoryDTO> findOne(Long id);


    Page<SubProductCategoryDTO> findAll(Pageable pageable);


    void delete(Long id);

    void deleteAll();
}
