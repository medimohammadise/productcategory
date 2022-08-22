package com.ecanteen.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "productCategory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "Name")
    private String Name;


//    /**
//     * One_To_Many relation with subProductCategory
//     */
//    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<SubProductCategory> subProductCategory;


    public ProductCategory id(Long id) {
        this.setId(id);
        return this;
    }


    public ProductCategory name(String name) {
        this.setName(name);
        return this;
    }
//
//    public ProductCategory subProductCategory(List<SubProductCategory> subProductCategory) {
//        this.setSubProductCategory(subProductCategory);
//        return this;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCategory)) {
            return false;
        }
        return id != null && id.equals(((ProductCategory) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
