package com.sda.ticketing.models;

import com.sda.ticketing.models.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Document(collection = "t_users")
@Data
public class User extends BaseModel {

    @Id
    private String _id;

    private String phoneNumber;

    private String password;

    private String firstName;

    private String lastName;

    private String country;

    public User(){

    }

    public User(User user) {
        this(user.get_id(),user.getPhoneNumber(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getCountry());
    }

    public User(String _id, String phoneNumber, String password, String firstName, String lastName, String country) {
        this._id = _id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

}
