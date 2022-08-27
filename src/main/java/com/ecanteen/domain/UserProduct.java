package com.ecanteen.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter

public class UserProduct extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "userProduct_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "profileName", unique = true, nullable = false)
    private String profileName;

    @Column(name = "weekDay", nullable = false)
    private String weekDay;

    @Column(name = "start_time", nullable = false)
    private Date start_time;

    @Column(name = "end_time", nullable = false)
    private Date end_time;


    public UserProduct id(Long id) {
        this.setId(id);
        return this;
    }

    public UserProduct profileName(String profileName) {
        this.setProfileName(profileName);
        return this;
    }

    public UserProduct start_time(Date start_time) {
        this.setStart_time(start_time);
        return this;
    }

    public UserProduct end_time(Date end_time) {
        this.setEnd_time(end_time);
        return this;
    }

    public UserProduct weekDay(String weekDay) {
        this.setWeekDay(weekDay);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProduct)) {
            return false;
        }
        return id != null && id.equals(((UserProduct) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
