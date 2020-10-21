package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

    @Id
    private String _id;

    private String ticketNo;

    @NonNull
    private String userId;

    private Long bookingDate = 0L;

    private Long cancelledDate = 0L;

    @NonNull
    private String churchId;

    @NonNull
    private String sessionId;

    @NonNull
    private String seatId;

    private Boolean active = true;

}
