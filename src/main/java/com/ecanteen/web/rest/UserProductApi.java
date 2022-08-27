package com.ecanteen.web.rest;


import com.ecanteen.repository.UserProductRepository;
import com.ecanteen.service.dto.UserProductDTO;
import com.ecanteen.service.mapper.impl.UserProductServiceImpl;
import com.ecanteen.web.rest.errors.BadRequestAlertException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name = "userProduct")

public class UserProductApi {

    private Logger log = LoggerFactory.getLogger(UserProductApi.class);
    private static final String ENTITY_NAME = "UserProduct";
    private String applicationName;
    private UserProductServiceImpl userProductServiceImpl;

    private UserProductRepository userProductRepository;


    public UserProductApi(UserProductServiceImpl userProductServiceImpl, UserProductRepository userProductRepository) {

        this.userProductServiceImpl = userProductServiceImpl;
        this.userProductRepository = userProductRepository;
    }

    @PostMapping("/user-products")
    public ResponseEntity<UserProductDTO> createUserProduct(@RequestBody UserProductDTO userProductDTO) throws URISyntaxException {
        log.debug("REST request to save  userProduct : {}", userProductDTO);
        UserProductDTO result = userProductServiceImpl.save(userProductDTO);
        return ResponseEntity
                .created(new URI("/api/user-products/" + result.getId()))
                .body(result);
    }

    @GetMapping("/user-products")
    public ResponseEntity<List<UserProductDTO>> getAllUserProduct(Pageable pageable) {
        Page<UserProductDTO> page = userProductServiceImpl.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @DeleteMapping("/user-products/{id}")
    public ResponseEntity<Void> deleteUserProduct(@PathVariable Long id) {
        log.debug("REST request to delete userProduct : {}", id);
        userProductServiceImpl.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/user-products/{id}")
    public ResponseEntity<UserProductDTO> getUserProductById(@PathVariable("id") long id) {
        UserProductDTO userProductDTO = userProductServiceImpl.findOne(id)
                .orElseThrow(() -> new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(userProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user-products")
    public ResponseEntity<Void> deleteUserProducts() {
        log.debug("REST request to delete userProduct");
        userProductServiceImpl.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping(value = "/user-products", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<UserProductDTO> partialUpdateUserProduct(
            @RequestBody UserProductDTO userProductDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update userProduct partially : {}, {}", userProductDTO);
        if (userProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }


        if (!userProductRepository.existsById(userProductDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserProductDTO> result = userProductServiceImpl.partialUpdate(userProductDTO);

        return ResponseEntity
                .noContent()
                .build();


    }


    @PutMapping("/user-products")
    public ResponseEntity<Optional<UserProductDTO>> updateUserProductDTO(

            @RequestBody UserProductDTO userProductDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Categories : {}, {}",   userProductDTO);

        if (!userProductRepository.existsById(userProductDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserProductDTO> result = userProductServiceImpl.update(userProductDTO);

        return ResponseEntity
                .ok()
                .body(result);
    }

}



