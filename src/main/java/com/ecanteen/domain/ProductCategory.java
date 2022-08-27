package com.ecanteen.domain;


import com.ecanteen.domain.enumeration.Visibility;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"id", "name"})})
public class ProductCategory extends Auditable<String>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "productCategory_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility"  )
    private Visibility visibility;


    @OneToMany(mappedBy = "productCategory")
    private List<SubProductCategory> subProductCategories;

    public ProductCategory id(Long id) {
        this.setId(id);
        return this;
    }


    public ProductCategory name(String name) {
        this.setName(name);
        return this;
    }


    public ProductCategory visibility(Visibility visibility) {
        this.setVisibility(visibility);
        return this;
    }


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
