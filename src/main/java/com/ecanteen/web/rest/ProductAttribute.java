package com.ecanteen.web.rest;


 import com.ecanteen.repository.ProductAttributeRepository;
import com.ecanteen.service.ProductAttributeService;
import com.ecanteen.service.dto.ProductAttributeDTO;
import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import com.ecanteen.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.SocketOption;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductAttribute {

    private static final String ENTITY_NAME = "productAttribute";
    private final Logger log = LoggerFactory.getLogger(ProductAttribute.class);

    private final ProductAttributeService productAttributeService;

    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public ProductAttribute( ProductAttributeService productAttributeService, ProductAttributeRepository productAttributeRepository) {
        this.productAttributeService = productAttributeService;
        this.productAttributeRepository = productAttributeRepository;
    }


    @PostMapping("/productAttributes")
    public ResponseEntity<ProductAttributeDTO> createProductAttributes(@RequestBody ProductAttributeDTO productAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save  productAttribute : {}", productAttributeDTO);
        if (productAttributeDTO.getId() != null) {
            throw new BadRequestAlertException("A new productCategory cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        ProductAttributeDTO result = productAttributeService.save(productAttributeDTO);
        return ResponseEntity
                .created(new URI("/api/productAttributes/" + result.getId()))
                .body(result);
    }

    @GetMapping("/productAttributes")
    public ResponseEntity<List<ProductAttributeDTO>> getAllProductAttributes(Pageable pageable) {
        Page<ProductAttributeDTO> page = productAttributeService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @PutMapping("/productAttributes/{id}")
    public ResponseEntity<ProductAttributeDTO> updateProductAttribute(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProductAttributeDTO productAttributeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update productAttribute : {}, {}", id, productAttributeDTO);
        if (productAttributeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productAttributeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductAttributeDTO result = productAttributeService.update(productAttributeDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }


    @DeleteMapping("/productAttributes/{id}")
    public ResponseEntity<Void> deleteProductAttribute(@PathVariable Long id) {
        log.debug("REST request to delete productAttribute : {}", id);
        productAttributeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
    /**
     *added new api
     */

    @GetMapping("/productAttribute/{id}")
    public ResponseEntity<ProductAttributeDTO> getProductAttributeById(@PathVariable("id") long id) {
        ProductAttributeDTO productAttributeDTO = productAttributeService.findOne(id)
                .orElseThrow(() -> new  BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(productAttributeDTO, HttpStatus.OK);
    }


    @DeleteMapping("/productAttributes")
    public ResponseEntity<Void> deleteAllProductAttributes() {
        log.debug("REST request to delete productAttributes");
        productAttributeService.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }
}
