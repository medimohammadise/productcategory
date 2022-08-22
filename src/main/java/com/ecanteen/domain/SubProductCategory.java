package com.ecanteen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class SubProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "subProductCategory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "Name")
    private String  Name;


    /**
     * Many_To_One  relation with productCategory
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;



//    /**
//     * One_To_Many relation with product
//     */
//    @OneToMany(mappedBy = "subProductCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Product> product;



    public SubProductCategory id(Long id) {
        this.setId(id);
        return this;
    }


    public SubProductCategory name(String name) {
        this.setName(name);
        return this;
    }

    public SubProductCategory productCategory(ProductCategory productCategory) {
        this.setProductCategory(productCategory);
        return this;
    }
//    public SubProductCategory product(Product product) {
//        this.setProduct(product);
//        return this;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubProductCategory)) {
            return false;
        }
        return id != null && id.equals(((SubProductCategory) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
