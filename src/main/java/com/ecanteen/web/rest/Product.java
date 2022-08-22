package com.ecanteen.web.rest;

 import com.ecanteen.repository.ProductRepository;

import com.ecanteen.service.ProductService;

import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.dto.ProductDTO;

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
public class Product {
    private static final String ENTITY_NAME = "product";
    private final Logger log = LoggerFactory.getLogger(Product.class);

    private final ProductService productService;

    private final ProductRepository productRepository;
    @Autowired
    public Product( ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    /**
     * {@code POST  /products} : Create a new product.
     * @param productDTO the productsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDTO, or with status {@code 400 (Bad Request)} if the product has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save  product : {}", productDTO);
        if (productDTO.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity
            .created(new URI("/api/products/" + result.getId()))
            .body(result);
    }


    /**
     * {@code GET  /products} : get all the products.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        Page<ProductDTO> page = productService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code PUT  /products/:id} : Updates an existing product.
     * @param id the id of the productDTO to save.
     * @param productDTO the  productDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productDTO,
     * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductDTO productDTO
    ) throws URISyntaxException {
        log.debug("REST request to update product : {}, {}", id, productDTO);
        if (productDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductDTO result = productService.update(productDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     * @param id the id of the productDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete product : {}", id);
        productService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }


    /**
     *added new api
     */

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id) {
        ProductDTO productDTO = productService.findOne(id)
                .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> deleteAllProduct() {
        log.debug("REST request to delete products");
        productService.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }
}

