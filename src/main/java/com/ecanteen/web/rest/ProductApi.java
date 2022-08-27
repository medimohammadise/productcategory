package com.ecanteen.web.rest;

import com.ecanteen.repository.ProductRepository;

import com.ecanteen.service.dto.ProductDTO;

import com.ecanteen.service.mapper.impl.ProductAttributeServiceImpl;
import com.ecanteen.service.mapper.impl.ProductServiceImpl;
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
@Tag(name = "Products")
public class ProductApi {
    private static final String ENTITY_NAME = "product";
    private final Logger log = LoggerFactory.getLogger(ProductApi.class);

    private final ProductServiceImpl productServiceImpl;
    private final ProductRepository productRepository;
    private final ProductAttributeServiceImpl productAttributeServiceImpl;

    @Autowired
    public ProductApi(ProductServiceImpl productServiceImpl, ProductRepository productRepository, ProductAttributeServiceImpl productAttributeServiceImpl) {
        this.productServiceImpl = productServiceImpl;
        this.productRepository = productRepository;
        this.productAttributeServiceImpl = productAttributeServiceImpl;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save  product : {}", productDTO);
        ProductDTO result = productServiceImpl.save(productDTO);
        return ResponseEntity
                .created(new URI("/api/products/" + result.getId()))
                .body(result);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        Page<ProductDTO> page = productServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @PutMapping("/products")
    public ResponseEntity<Optional<ProductDTO>> updateProduct(
             @RequestBody ProductDTO productDTO
    ) throws URISyntaxException {
        log.debug("REST request to update product : {}, {}" , productDTO);

        if (!productRepository.existsById(productDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
     Optional <ProductDTO> result = productServiceImpl.update(productDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete product : {}", id);
        productServiceImpl.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id) {
        ProductDTO productDTO = productServiceImpl.findOne(id)
                .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> deleteAllProduct() {
        log.debug("REST request to delete products");
        productServiceImpl.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping(value = "/products", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProductDTO> partialUpdateProduct(
            @RequestBody ProductDTO productDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Product partially : {}, {}", productDTO);
        if (productDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }


        if (!productRepository.existsById(productDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductDTO> result = productServiceImpl.partialUpdate(productDTO);

        return ResponseEntity
                .noContent()
                .build();


    }

}

