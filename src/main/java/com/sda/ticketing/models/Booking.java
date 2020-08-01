package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "bookings")
public class Booking extends BaseModel {

    @Id
    private String _id;

    private Long bookingDate = 0L;

    private Long cancelledDate = 0L;

    private String church;

    private String seatNumber;

    private Boolean active = true;

}
