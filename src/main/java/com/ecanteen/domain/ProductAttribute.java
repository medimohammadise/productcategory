package com.ecanteen.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter

public class ProductAttribute extends Auditable<String> implements Serializable {

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "productAttribute_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name",unique = true, nullable = false)
    private String name;

    @Column(name = "value" , nullable = false)
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id" ,nullable = false, updatable = false)
    private Product product;


    public ProductAttribute id(Long id) {
        this.setId(id);
        return this;
    }


    public ProductAttribute name(String name) {
        this.setName(name);
        return this;
    }


    public ProductAttribute value(String value) {
        this.setValue(value);
        return this;
    }


    public ProductAttribute product(Product product) {
        this.setProduct(product);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductAttribute)) {
            return false;
        }
        return id != null && id.equals(((ProductAttribute) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
