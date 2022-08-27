package com.ecanteen.domain;

import com.ecanteen.service.dto.ProductAttributeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter

public class Product extends Auditable<String>  implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "subProductCategoryName",nullable = false)
    private String subProductCategoryName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subProductCategory_id", referencedColumnName = "id" ,nullable = false, updatable = false)
    private SubProductCategory subProductCategory;




    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> ProductAttribute;
    public Product id(Long id) {
        this.setId(id);
        return this;
    }


    public Product name(String name) {
        this.setName(name);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
