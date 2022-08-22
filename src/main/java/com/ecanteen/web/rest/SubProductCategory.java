package com.ecanteen.web.rest;

 import com.ecanteen.repository.SubProductCategoryRepository;
import com.ecanteen.service.SubProductCategoryService;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class SubProductCategory {

    private static final String ENTITY_NAME = "SubProductCategory";
    private final Logger log = LoggerFactory.getLogger(SubProductCategory.class);

    private final SubProductCategoryService subProductCategoryService;

    private final SubProductCategoryRepository subProductCategoryRepository;

    @Autowired
    public  SubProductCategory(SubProductCategoryService subProductCategoryService, SubProductCategoryRepository subProductCategoryRepository) {
        this.subProductCategoryService = subProductCategoryService;
        this.subProductCategoryRepository = subProductCategoryRepository;
    }


    @PostMapping("/SubProductCategories")
    public ResponseEntity<SubProductCategoryDTO> createSubProductCategory(@RequestBody SubProductCategoryDTO subProductCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save  SubProductCategory : {}", subProductCategoryDTO);
        if (subProductCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new SubProductCategory cannot already have an ID", ENTITY_NAME, "id-exists");
        }
        SubProductCategoryDTO result = subProductCategoryService.save(subProductCategoryDTO);
        return ResponseEntity
                .created(new URI("/api/SubProductCategories/" + result.getId()))
                .body(result);
    }



    @GetMapping("/SubProductCategories")
    public ResponseEntity<List<SubProductCategoryDTO>> getAllSubProductCategories(Pageable pageable) {
        Page<SubProductCategoryDTO> page = subProductCategoryService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }



    @PutMapping("/SubProductCategories/{id}")
    public ResponseEntity<SubProductCategoryDTO> updateSubProductCategory(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody SubProductCategoryDTO subProductCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SubProductCategory : {}, {}", id, subProductCategoryDTO);
        if (subProductCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subProductCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subProductCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SubProductCategoryDTO result = subProductCategoryService.update(subProductCategoryDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }


    @DeleteMapping("/SubProductCategories/{id}")
    public ResponseEntity<Void> deleteSubProductCategory(@PathVariable Long id) {
        log.debug("REST request to delete subProductCategory : {}", id);
        subProductCategoryService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    /**
     *added new api
     */

    @GetMapping("/subProductCategory/{id}")
    public ResponseEntity<SubProductCategoryDTO> getSubProductCategoryById(@PathVariable("id") long id) {
        SubProductCategoryDTO subProductCategoryDTO = subProductCategoryService.findOne(id)
                .orElseThrow(() -> new  BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
        return new ResponseEntity<>(subProductCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/subProductCategories")
    public ResponseEntity<Void> deleteAllSubProductCategories() {
        log.debug("REST request to delete subProductCategories");
        subProductCategoryService.deleteAll();
        return ResponseEntity
                .noContent()
                .build();
    }
}
