package com.sda.ticketing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "users")
public class User extends BaseModel {

    @Id
    private String _id;

    @Indexed
    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String gender;

    private String church;

    private Boolean tested;

    @JsonIgnore
    private String password;

    List<Booking> bookings = new ArrayList<>();

}
