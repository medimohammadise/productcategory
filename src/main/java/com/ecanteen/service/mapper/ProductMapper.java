package com.ecanteen.service.mapper;


import com.ecanteen.domain.Product;
import com.ecanteen.service.dto.ProductDTO;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring" )
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {


}
