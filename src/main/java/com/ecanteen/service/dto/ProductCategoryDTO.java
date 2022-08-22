package com.ecanteen.service.dto;


import com.ecanteen.domain.SubProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;



@Getter
@Setter
public class ProductCategoryDTO implements Serializable {
    private Long id;

    private String Name;

    //private List<SubProductCategory> subProductCategory;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCategoryDTO)) {
            return false;
        }

        ProductCategoryDTO productCategoryDTO = (ProductCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


}
