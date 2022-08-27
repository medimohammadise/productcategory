package com.ecanteen.web.rest;


import com.ecanteen.domain.ProductCategory;
import com.ecanteen.repository.ProductCategoryRepository;

import com.ecanteen.repository.SubProductCategoryRepository;
import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.mapper.ProductCategoryMapper;
import com.ecanteen.service.mapper.impl.ProductCategoryServiceImpl;
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
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Tag(name = "ProductCategory")

public class CategoryApi {
    private static final String ENTITY_NAME = "productCategory";
    private final Logger log = LoggerFactory.getLogger(CategoryApi.class);

    private final ProductCategoryServiceImpl productCategoryServiceImpl;
    private final ProductCategoryRepository productCategoryRepository;
    private final SubProductCategoryImpl subProductCategoryImpl;
    private final SubProductCategoryRepository subProductCategoryRepository;
    private final ProductServiceImpl productServiceImpl;
    private final ProductCategoryMapper productCategoryMapper;

    @Autowired
    public CategoryApi(ProductCategoryServiceImpl productCategoryServiceImpl, ProductCategoryRepository productCategoryRepository, SubProductCategoryImpl subProductCategoryImpl, SubProductCategoryRepository subProductCategoryRepository, ProductServiceImpl productServiceImpl, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryServiceImpl = productCategoryServiceImpl;
        this.productCategoryRepository = productCategoryRepository;
        this.subProductCategoryImpl = subProductCategoryImpl;
        this.subProductCategoryRepository = subProductCategoryRepository;
        this.productServiceImpl = productServiceImpl;
        this.productCategoryMapper = productCategoryMapper;
    }


    @PostMapping("/product-categories")
    public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save  Categories : {}", productCategoryDTO);
        ProductCategoryDTO result = productCategoryServiceImpl.save(productCategoryDTO);
        return ResponseEntity
                .created(new URI("/api/product-categories/" + result.getId()))
                .body(result);
    }


    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategoryDTO>> getAllCategories(Pageable pageable) {
        Page<ProductCategoryDTO> page = productCategoryServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    @PutMapping("/product-categories")
    public ResponseEntity<Optional<ProductCategoryDTO>> updateCategory(

            @RequestBody ProductCategoryDTO productCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Categories : {}, {}", productCategoryDTO);

        if (!productCategoryRepository.existsById(productCategoryDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }


        Optional<ProductCategoryDTO> result = productCategoryServiceImpl.update(productCategoryDTO);

        return ResponseEntity
                .ok()
                .body(result);
    }


    @DeleteMapping("/product-categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Categories : {}", id);
        productCategoryServiceImpl.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategoryDTO> getCategoriesById(@PathVariable("id") long id) {
        ProductCategoryDTO productCategoryDTO = productCategoryServiceImpl.findOne(id)
                .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/product-categories")
    public ResponseEntity<Void> deleteAllCategories() {
        log.debug("REST request to delete Categories");
        productCategoryServiceImpl.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping(value = "/product-categories", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProductCategoryDTO> partialUpdateProductCategory(
            @RequestBody ProductCategoryDTO productCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductCategory partially : {}, {}", productCategoryDTO);
        if (productCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }


        if (!productCategoryRepository.existsById(productCategoryDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductCategoryDTO> result = productCategoryServiceImpl.partialUpdate(productCategoryDTO);

        return ResponseEntity
                .noContent()
                .build();


    }


}



