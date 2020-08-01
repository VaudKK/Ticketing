package com.sda.ticketing.models.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class BaseModel {

    @CreatedDate
    private Long createdAt;

    @LastModifiedDate
    private Long modifiedAt;

}
