package com.ecanteen.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "Name")
    private String Name;


    /**
     * Many_to_One  relation with subProductCategory
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subProductCategory_id")
    private SubProductCategory subProductCategory;


//    /**
//     * One_To_Many relation with productAttribute
//     */
//    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<ProductAttribute> productAttribute;


    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }


    public Product subProductCategory(SubProductCategory subProductCategory) {
        this.setSubProductCategory(subProductCategory);
        return this;
    }

//    public Product productAttribute(List<ProductAttribute> productAttribute) {
//        this.setProductAttribute(productAttribute);
//        return this;
//    }


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
