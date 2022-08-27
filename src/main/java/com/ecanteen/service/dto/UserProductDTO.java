package com.ecanteen.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class UserProductDTO implements Serializable {



    private Long id;

    private String profileName;

    private String weekDay;

    private Date start_time;

    private Date end_time;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProductDTO)) {
            return false;
        }

        UserProductDTO userProductDTO = (UserProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userProductDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
