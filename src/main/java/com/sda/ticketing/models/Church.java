package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "churches")
public class Church extends BaseModel {

    @Id
    private String _id;

    @Indexed
    private String churchName;

    private String location;

    private Integer seatCount = 0;

}
