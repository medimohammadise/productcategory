package com.ecanteen.web.rest;

import com.ecanteen.repository.ProductCategoryRepository;
import com.ecanteen.repository.SubProductCategoryRepository;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import com.ecanteen.service.mapper.impl.ProductServiceImpl;
import com.ecanteen.service.mapper.impl.SubProductCategoryImpl;
import com.ecanteen.web.rest.errors.BadRequestAlertException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Tag(name = "ProductCategory")
public class SubCategoryApi {

    private static final String ENTITY_NAME = "SubProductCategory";
    private final Logger log = LoggerFactory.getLogger(SubCategoryApi.class);

    private final SubProductCategoryImpl subProductCategoryImpl;
    private final SubProductCategoryRepository subProductCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductServiceImpl productServiceImpl;


    @Autowired
    public SubCategoryApi(SubProductCategoryImpl subProductCategoryImpl, SubProductCategoryRepository subProductCategoryRepository, ProductCategoryRepository productCategoryRepository, ProductServiceImpl productServiceImpl) {
        this.subProductCategoryImpl = subProductCategoryImpl;
        this.subProductCategoryRepository = subProductCategoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productServiceImpl = productServiceImpl;
    }


    @PostMapping("/sub-categories")
    public ResponseEntity<SubProductCategoryDTO> createSubProductCategory(@RequestBody SubProductCategoryDTO subProductCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save  SubProductCategory : {}", subProductCategoryDTO);
        SubProductCategoryDTO result = subProductCategoryImpl.save(subProductCategoryDTO);
        return ResponseEntity
                .created(new URI("/api/sub-categories/" + result.getId()))
                .body(result);
    }


    @GetMapping("/sub-categories")
    public ResponseEntity<List<SubProductCategoryDTO>> getAllSubCategories(Pageable pageable) {

        Page<SubProductCategoryDTO> page = subProductCategoryImpl.findAll(pageable);

        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/sub-categories")
    public ResponseEntity<Optional<SubProductCategoryDTO>> updateSubCategory(

            @RequestBody SubProductCategoryDTO subProductCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}, {}", subProductCategoryDTO);


        if (!subProductCategoryRepository.existsById(subProductCategoryDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

       Optional< SubProductCategoryDTO> result = subProductCategoryImpl.update(subProductCategoryDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }


    @DeleteMapping("/sub-categories/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete SubCategory : {}", id);
        subProductCategoryImpl.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    @GetMapping("/sub-categories/{id}")
    public ResponseEntity<SubProductCategoryDTO> getSubProductCategoryById(@PathVariable("id") long id) {
        SubProductCategoryDTO subProductCategoryDTO = subProductCategoryImpl.findOne(id)
                .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(subProductCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/sub-categories")
    public ResponseEntity<Void> deleteAllSubCategories() {
        log.debug("REST request to delete subCategories");
        subProductCategoryImpl.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }


    @PatchMapping(value = "/sub-categories", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<SubProductCategoryDTO> partialUpdateSubProductCategory(
            @RequestBody SubProductCategoryDTO subProductCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update subProductCategory partially : {}, {}", subProductCategoryDTO);
        if (subProductCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }


        if (!subProductCategoryRepository.existsById(subProductCategoryDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SubProductCategoryDTO> result = subProductCategoryImpl.partialUpdate(subProductCategoryDTO);

        return ResponseEntity
                .noContent()
                .build();


    }


}
