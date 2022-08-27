package com.ecanteen.service.dto;

import com.ecanteen.domain.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class SubProductCategoryDTO  implements Serializable {


    private Long id;

    private String Name;

    private String productCategoryName;

    @JsonIgnore
    private ProductCategory productCategory;



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubProductCategoryDTO)) {
            return false;
        }

        SubProductCategoryDTO subProductCategoryDTO = (SubProductCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, subProductCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}


