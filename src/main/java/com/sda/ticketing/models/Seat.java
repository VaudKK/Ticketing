package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "seats")
public class Seat extends BaseModel {

    @Id
    private String _id;

    private int seatNumber;

    private String churchId;

}
