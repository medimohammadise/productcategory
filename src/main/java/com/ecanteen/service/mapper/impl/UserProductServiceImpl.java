package com.ecanteen.service.mapper.impl;

import com.ecanteen.domain.SubProductCategory;
import com.ecanteen.domain.UserProduct;
import com.ecanteen.repository.UserProductRepository;
import com.ecanteen.service.dto.ProductCategoryDTO;
import com.ecanteen.service.dto.SubProductCategoryDTO;
import com.ecanteen.service.dto.UserProductDTO;
import com.ecanteen.service.mapper.UserProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class UserProductServiceImpl {

    private final Logger log = LoggerFactory.getLogger(UserProductServiceImpl.class);

    private UserProductMapper userProductMapper;

    private UserProductRepository userProductRepository;


    @Autowired
    public UserProductServiceImpl(UserProductMapper userProductMapper, UserProductRepository userProductRepository) {
        this.userProductMapper = userProductMapper;
        this.userProductRepository = userProductRepository;
    }


    public UserProductDTO save(UserProductDTO userProductDTO) {
        UserProduct newUserProduct = null;
        log.debug("Request to save a new userProduct :{} ", userProductDTO);
        UserProduct userProduct = userProductMapper.toEntity(userProductDTO);
        var userProductEntity = userProductRepository.findByProfileName(userProductDTO.getProfileName());

        if (!userProductEntity.isPresent()) {
            userProduct.setId(userProduct.getId());

            newUserProduct = userProductRepository.save(userProduct);
        } else {

            userProductRepository.save(userProduct);

        }

        return userProductMapper.toDto(newUserProduct);
    }


    public Optional <UserProductDTO> update(UserProductDTO userProductDTO) {
        log.debug("Request to save subProductCategory : {}", userProductDTO);
        UserProduct userProduct = userProductMapper.toEntity(userProductDTO);
        userProduct = userProductRepository.save(userProduct);
        return Optional.ofNullable(userProductMapper.toDto(userProduct));
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<UserProductDTO> findOne(Long id) {
        log.debug("Request to get userProduct : {}", id);
        return userProductRepository.findById(id).map(userProductMapper::toDto);
    }


    public void delete(Long id) {
        log.debug("Request to delete userProduct : {}", id);
        userProductRepository.deleteById(id);
    }


    public void deleteAll() {
        log.debug("Request to delete userProduct");
        userProductRepository.deleteAll();

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UserProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all userProduct");
        return userProductRepository.findAll(pageable).map(userProductMapper::toDto);
    }

    public Optional<UserProductDTO> partialUpdate(UserProductDTO userProductDTO) {
        log.debug("Request to partially update userProduct : {}", userProductDTO);

        return userProductRepository
                .findById(userProductDTO.getId())
                .map(existingUserProduct -> {
                    userProductMapper.partialUpdate(existingUserProduct, userProductDTO);

                    return existingUserProduct;
                })
                .map(userProductRepository::save)
                .map(userProductMapper::toDto);
    }

}
