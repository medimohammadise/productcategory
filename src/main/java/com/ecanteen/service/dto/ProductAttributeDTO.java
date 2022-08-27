package com.ecanteen.service.dto;

import com.ecanteen.domain.Auditable;
import com.ecanteen.domain.Product;
import com.ecanteen.domain.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ProductAttributeDTO implements Serializable {

    private Long id;

    private String name;

    private String value;


    private String productName;

    //private Product product;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductAttributeDTO)) {
            return false;
        }

        ProductAttributeDTO productAttributeDTO = (ProductAttributeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productAttributeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
