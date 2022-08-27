package com.ecanteen.domain;

 import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
 import java.util.List;


@Entity
@Getter
@Setter
public class SubProductCategory extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "subProductCategory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "productCategoryName", unique = true, nullable = false)
    private String productCategoryName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategory_id", referencedColumnName = "id" ,nullable = false, updatable = false)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "subProductCategory")
    private List<Product> product;

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
