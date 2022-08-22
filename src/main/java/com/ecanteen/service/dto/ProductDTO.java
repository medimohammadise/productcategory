package com.ecanteen.service.dto;

import com.ecanteen.domain.ProductAttribute;
import com.ecanteen.domain.SubProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;



@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;

     private String Name;

     private SubProductCategory subProductCategory;

     //private List<ProductAttribute> productAttribute;




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
