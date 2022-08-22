package com.ecanteen.service.mapper;


import com.ecanteen.domain.ProductAttribute;
import com.ecanteen.service.dto.ProductAttributeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper extends EntityMapper<ProductAttributeDTO, ProductAttribute> {
}
