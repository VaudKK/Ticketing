package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "sessions")
public class Session extends BaseModel {

    @Id
    private String _id;

    @NonNull
    private String sessionName;

    @NonNull
    private Long startTime;

    @NonNull
    private Long endTime;

    @NonNull
    private Long sessionDate;

    @NonNull
    private String churchId;

    private Integer seatsAvailable = 100;

    private Boolean active = true;

}
