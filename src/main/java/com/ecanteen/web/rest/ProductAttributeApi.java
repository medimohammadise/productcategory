package com.ecanteen.web.rest;


 import com.ecanteen.repository.ProductAttributeRepository;
 import com.ecanteen.service.dto.ProductAttributeDTO;
 import com.ecanteen.service.mapper.impl.ProductAttributeServiceImpl;
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
public class ProductAttributeApi {

    private static final String ENTITY_NAME = "productAttribute";
    private final Logger log = LoggerFactory.getLogger(ProductAttributeApi.class);

    private final ProductAttributeServiceImpl productAttributeServiceImpl;
    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public ProductAttributeApi( ProductAttributeServiceImpl productAttributeServiceImpl, ProductAttributeRepository productAttributeRepository) {
        this.productAttributeServiceImpl = productAttributeServiceImpl;
        this.productAttributeRepository = productAttributeRepository;
    }


    @PostMapping("/product-attributes")
    public ResponseEntity<ProductAttributeDTO> createProductAttributes(@RequestBody ProductAttributeDTO productAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save  attribute : {}", productAttributeDTO);
        ProductAttributeDTO result = productAttributeServiceImpl.save(productAttributeDTO);
        return ResponseEntity
                .created(new URI("/api/product-attributes/" + result.getId()))
                .body(result);
    }

    @GetMapping("/product-attributes")
    public ResponseEntity<List<ProductAttributeDTO>> getAllAttributes(Pageable pageable) {
        Page<ProductAttributeDTO> page = productAttributeServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @PutMapping("/product-attributes")
    public ResponseEntity<Optional<ProductAttributeDTO>> updateAttribute(
            @RequestBody ProductAttributeDTO productAttributeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update attribute : {}, {}", productAttributeDTO);
        if (!productAttributeRepository.existsById(productAttributeDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

       Optional <ProductAttributeDTO> result = productAttributeServiceImpl.update(productAttributeDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }


    @DeleteMapping("/product-attributes/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        log.debug("REST request to delete attribute : {}", id);
        productAttributeServiceImpl.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/product-attributes/{id}")
    public ResponseEntity<ProductAttributeDTO> getAttributeById(@PathVariable("id") long id) {
        ProductAttributeDTO productAttributeDTO = productAttributeServiceImpl.findOne(id)
                .orElseThrow(() -> new  BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productAttributeDTO, HttpStatus.OK);
    }


    @DeleteMapping("/product-attributes")
    public ResponseEntity<Void> deleteAllAttributes() {
        log.debug("REST request to delete attributes");
        productAttributeServiceImpl.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping(value = "/product-attributes", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProductAttributeDTO> partialUpdateProductAttributes(
            @RequestBody ProductAttributeDTO productAttributeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductAttribute partially : {}, {}", productAttributeDTO);
        if (productAttributeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }


        if (!productAttributeRepository.existsById(productAttributeDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductAttributeDTO> result = productAttributeServiceImpl.partialUpdate(productAttributeDTO);

        return ResponseEntity
                .noContent()
                .build();


    }


}
