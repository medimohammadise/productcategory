package com.ecanteen.service.mapper;


import com.ecanteen.domain.UserProduct;
import com.ecanteen.service.dto.UserProductDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface UserProductMapper extends EntityMapper<UserProductDTO,UserProduct> {
}
