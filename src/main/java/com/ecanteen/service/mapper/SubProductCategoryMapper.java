package com.ecanteen.service.mapper;

 import com.ecanteen.domain.SubProductCategory;
 import com.ecanteen.service.dto.ProductDTO;
 import com.ecanteen.service.dto.SubProductCategoryDTO;
 import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface SubProductCategoryMapper extends EntityMapper<SubProductCategoryDTO, SubProductCategory> {
}
