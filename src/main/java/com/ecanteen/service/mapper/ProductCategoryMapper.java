package com.ecanteen.service.mapper;


import com.ecanteen.domain.ProductCategory;
import com.ecanteen.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring" )
public interface ProductCategoryMapper extends EntityMapper<ProductCategoryDTO, ProductCategory> {


}
