package com.ecanteen.service.mapper;


import com.ecanteen.domain.ProductCategory;
import com.ecanteen.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;

import java.util.Optional;


@Mapper(componentModel = "spring" )
public interface ProductCategoryMapper extends EntityMapper<ProductCategoryDTO, ProductCategory> {


 ProductCategoryDTO toDto(Optional<ProductCategory> productCategoryDTOList);
}
