package com.ecanteen.web.rest;

 import com.ecanteen.repository.ProductCategoryRepository;

import com.ecanteen.service.ProductCategoryService;

import com.ecanteen.service.dto.ProductCategoryDTO;

import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class ProductCategory {
    private static final String ENTITY_NAME = "productCategory";
    private final Logger log = LoggerFactory.getLogger(ProductCategory.class);

    private final ProductCategoryService productCategoryService;

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategory(ProductCategoryService productCategoryService, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryService = productCategoryService;
        this.productCategoryRepository = productCategoryRepository;
    }


    @PostMapping("/productCategory")
    public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save  productCategory : {}", productCategoryDTO);
        if (productCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new productCategory cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ProductCategoryDTO result = productCategoryService.save(productCategoryDTO);
        return ResponseEntity
                .created(new URI("/api/productCategories/" + result.getId()))
                .body(result);
    }


    @GetMapping("/ProductCategories")
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories(Pageable pageable) {
        Page<ProductCategoryDTO> page = productCategoryService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }



    @PutMapping("/productCategories/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProductCategoryDTO productCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update productCategory : {}, {}", id, productCategoryDTO);
        if (productCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductCategoryDTO result = productCategoryService.update(productCategoryDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @DeleteMapping("/productCategories/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        log.debug("REST request to delete productCategory : {}", id);
        productCategoryService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    /**
     *added new api
     */

    @GetMapping("/productCategories/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoriesById(@PathVariable("id") long id) {
        ProductCategoryDTO productCategoryDTO = productCategoryService.findOne(id)
                .orElseThrow(() -> new  BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/productCategories")
    public ResponseEntity<Void> deleteAllProductCategories() {
        log.debug("REST request to delete productCategories");
        productCategoryService.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }
}

